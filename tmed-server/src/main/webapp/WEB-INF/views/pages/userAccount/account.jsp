<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Personal Page</title>
</head>

<script src="http://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>

<meta name="_csrf" content="${_csrf.token}"/>
<!-- default header name is X-CSRF-TOKEN -->
<meta name="_csrf_header" content="${_csrf.headerName}"/>


<body>
<div class="well lead">Personal Page</div>
<div class="text-colour">
    <div class="generic-container col-xs-12 " style="height:50px;">
        <form:form method="POST" modelAttribute="user" class="form-horizontal">
            <form:input type="hidden" path="id" id="id"/>
            <table cellspacing="0" cellpadding="5">
                <tr>
                    <td width="400" valign="top">
                        <div class="row">
                            <div class="form-group col-md-12">
                                <label class="col-md-8 control-lable text-colour" for="ssoid">SSOID</label>
                                <div class="col-md-4">
                                    <label id="ssoid"
                                           class="control-lable text-colour">${user.ssoId}</label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-md-12">
                                <label class="col-md-8 control-lable text-colour" for="firstName">First Name</label>
                                <div class="col-md-4">
                                    <label id="firstName"
                                           class="control-lable text-colour">${user.firstName}</label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-md-12">
                                <label class="col-md-8 control-lable text-colour" for="lastName">Last Name</label>
                                <div class="col-md-4">
                                    <label id="lastName"
                                           class="control-lable text-colour">${user.lastName}</label>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-md-12">
                                <label class="col-md-8 control-lable text-colour" for="email">Email</label>
                                <div class="col-md-4">
                                    <label id="email"
                                           class="control-lable text-colour">${user.email}</label>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="form-group col-md-12">
                                <div class="form-group col-md-12">
                                    <label class="col-md-8 control-lable text-colour" for="description">Description</label>
                                </div>
                                <div class="col-md-12">
                                    <form:textarea style="resize:none" type="text" path="description" id="description"
                                                   rows="5" cols="30"
                                                   class="form-control input-sm" readonly="true"/>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="form-group col-md-12">
                                <div class="col-md-12">
                                    <a class="btn btn-primary btn-block" href="<c:url value='/editUser-${user.ssoId}'/>">Edit
                                        Profile</a>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="form-group col-md-12">
                                <div class="col-md-12">
                                    <a class="btn btn-primary btn-block" href="<c:url value='/groupsList-${user.ssoId}'/>">View
                                        vocabulary</a>
                                </div>
                            </div>
                        </div>

                    </td>
                    <td width="400" valign="top" >
                        <div class="accountImageArea">
                            <div class="row">
                                <div class="form-group col-md-12">
                                    <div class=" col-md-12">
                                        <c:if test="${user.photo != ''}">
                                            <img src="data:image/jpeg;base64,${photoPath}" id="output" width="200"
                                                 alt="df">
                                        </c:if>
                                        <c:if test="${user.photo == ''}">
                                            <img id="output" alt="df" width="200" src="/static/img/default.JPG">
                                        </c:if>
                                    </div>
                                </div>
                            </div>


                            <sec:authorize access="isAuthenticated()">
                                <c:if test="${loggedinuser==user.ssoId}">
                                    <div class="row">
                                        <div class="form-group col-md-12">
                                            <div class="col-md-12">
                                                <label class="col-md-12 btn btn-primary btn-block btn-file">
                                                    Browse... <input onchange="loadFile(event)" id="file" type="file"
                                                                     path="file" style="display: none;">
                                                </label>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="form-group col-md-12">
                                            <div class="col-md-12">
                                                <a class="btn btn-primary btn-block btn-sm" name="refresh" id="refresh"
                                                   type="button" onclick="avatarUpload()">Refresh</a>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="form-group col-md-12">
                                            <div class="col-md-12">
                                                <a class="btn btn-primary btn-block btn-sm"
                                                   href="<c:url value='/user-${user.ssoId}'/>">Cancel</a>
                                            </div>
                                        </div>
                                    </div>

                                </c:if>
                            </sec:authorize>
                        </div>
                    </td>
                </tr>
            </table>

        </form:form>
    </div>
</div>



</body>
<script>

    $(function () {
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function (e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
    });

    function avatarUpload() {
        var formData = new FormData();
        formData.append('mainImage', $('#file')[0].files[0]);
        $.ajax({
            url: '/avatarUpload',
            data: formData,
            processData: false,
            contentType: false,
            type: 'POST',
            success: function () {
                alert("Success!");
            },
            error: function () {
                alert("Error. Please, choose a photo.");
            }
        })

    }

    var loadFile = function (event) {
        var output = document.getElementById('output');
        output.src = URL.createObjectURL(event.target.files[0]);
    };


</script>
</html>