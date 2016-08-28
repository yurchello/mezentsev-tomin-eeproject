<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>

<script src="http://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>

<meta name="_csrf" content="${_csrf.token}"/>
<!-- default header name is X-CSRF-TOKEN -->
<meta name="_csrf_header" content="${_csrf.headerName}"/>

<%--<head>--%>
<%--<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">--%>
<%--<title>User Registration Form</title>--%>
<%--<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>--%>
<%--<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>--%>
<%--</head>--%>
<script>

    $(function () {
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function (e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
    });

    function saveAdvertWithImage() {

        var formData = new FormData();
        formData.append('mainImage', $('#file')[0].files[0]);
        //formData.append('mainImage', $('#output').val());
        //alert();
        $.ajax({
//            headers: {
//                Accept : "application/json; charset=utf-8"
//            },
            url: '/addAdvert',
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
<body>
<div class="generic-container">
    <sec:authorize access="isAuthenticated()">
        <%@include file="authheader.jsp" %>
    </sec:authorize>

    <div class="well lead">User Personal Page</div>
    <form:form method="POST" modelAttribute="user" class="form-horizontal">
        <form:input type="hidden" path="id" id="id"/>

        <table>
            <tr>
                <td>
                    <div id="opt-in" style="display:none;">
                        <form>
                            <input type="email" placeholder="Your email goes here...">
                            <input type="submit" value="Free Instant Access!">
                        </form>
                    </div>
                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-3 control-lable">First Name: ${user.firstName}</label>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-3 control-lable">Last Name: ${user.lastName}</label>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-3 control-lable">SSO ID: ${user.ssoId}</label>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-3 control-lable">Email: ${user.email}</label>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-3 control-lable" for="description">Description: </label>
                            <div class="col-md-7">
                                <form:textarea type="text" path="description" id="description" rows="5" cols="30"
                                               class="form-control input-sm" readonly="true"/>
                            </div>
                        </div>
                    </div>
                </td>
                <td>
                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-3 control-lable">Photo:</label>
                            <div>
                                <c:if test="${user.photo != null}">
                                    <img src="data:image/jpeg;base64,${photoPath}" id="output" width="117" height="160"
                                         alt="df">
                                </c:if>
                                <c:if test="${user.photo == null}">
                                    <img id="output" alt="df" width="117" height="160" src="/static/images/default.JPG">
                                </c:if>
                            </div>

                            <sec:authorize access="isAuthenticated()">
                                <c:if test="${loggedinuser==user.ssoId}">
                                    <div>
                                        <div>
                                            <input id="file" type="file" path="file" accept="image/*"
                                                   onchange="loadFile(event)"/>
                                        </div>
                                        <div>
                                            <button name="refresh" id="refresh" type="button"
                                                    onclick="saveAdvertWithImage()">Refresh
                                            </button>
                                            <a href="<c:url value='/user-${user.ssoId}'/>">Cancel</a>
                                        </div>
                                    </div>
                                    <div>
                                        <a href="<c:url value='/editUser-${user.ssoId}'/>">Edit Profile</a>
                                    </div>
                                </c:if>
                            </sec:authorize>
                        </div>
                    </div>
                </td>
            </tr>
        </table>


    </form:form>
</div>
</body>
</html>