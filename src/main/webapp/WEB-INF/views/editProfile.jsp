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
    function doAjax() {
        $.ajax({
            url: 'previewPhoto',
            data: ({password :$('#password').val()}),
            success: function (data) {
                $('#strengthValue').html(data)
            }
        });
    }

//    function doUpdateAvatar() {
//
//        $.ajax({
//            url: 'doUpdateAvatar',
//            data: ({aaa :$('#firstName').val()}),
//            success: function (data) {
//                $('#hhh').html(data)
//            }
//        });
//    }

    function doUpdateAvatar() {
        var output = document.getElementById('output').src;

        $.ajax({
            url: 'doUpdateAvatar',
            data: $('#output').serialize(),
            type: 'GET',
            dataType: 'json',
            contentType: 'application/json',
            mimeType: 'application/json',
            success: function (data) {
//                $('#hhh').html(data)
                alert();
            }
        });

    }

    function doPutImage() {
            var image = document.getElementById('output').files[0];
            var formData = new FormData();
            formData.append('image', image);
            $.ajax({
                url: 'image',
                type: 'put',
                data: formData,
                contentType: false,
                processData: false,
                async: true,
                success: function(data) {
                    alert("ssss")
                    console.log("success");
                    console.log(data);
                },
                error: function(data) {
                    alert("eeeee")
                    console.log("error");
                    console.log(data);
                }
            });
    }

    function doImage() {
        var x = $('div.col-md-12 output').prop('src');
        var imgData = JSON.stringify(x);
        $('div.Fil output').prop('src')
        $.ajax({
            type: "POST",
            datatype: 'json',
            data: imgData,
            url: "test",
            success: function(data) {
                alert("successfully uploaded");
            },
            error: function (request, status, error) {
                alert("failure");
            }
        });
    }

    var loadFile = function(event) {
        var output = document.getElementById('output');
        output.src = URL.createObjectURL(event.target.files[0]);
    };

    var alertCall = function() {
        var output = document.getElementById('output');

    };


    $(function () {
        var token = $("meta[name='_csrf']").attr("content");
        var header = $("meta[name='_csrf_header']").attr("content");
        $(document).ajaxSend(function(e, xhr, options) {
            xhr.setRequestHeader(header, token);
        });
    });

    function saveAdvertWithImage() {

        var formData = new FormData();
        formData.append('mainImage', $('#file')[0].files[0]);
        //formData.append('mainImage', $('#output').val());
        //alert();
        $.ajax({
            headers: {
                Accept : "application/json; charset=utf-8"
            },
            url: '/addAdvert',
            data: formData,
            processData: false,
            contentType: false,
            type: 'POST'
        })

    }
</script>

<body>
<div class="generic-container">
    <%@include file="authheader.jsp" %>

    <div class="well lead">User Registration Form</div>
    <form:form method="POST" modelAttribute="user" class="form-horizontal">
        <form:input type="hidden" path="id" id="id"/>
        <table>
            <tr>
                <td>
                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-3 control-lable" for="firstName">First Name</label>
                            <div class="col-md-7">
                                <form:input type="text" path="firstName" onkeyup="doUpdateAvatar()" id="firstName" class="form-control input-sm"/>
                                <label id="hhh" />
                                <div class="has-error">
                                    <form:errors path="firstName" class="help-inline"/>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-3 control-lable" for="lastName">Last Name</label>
                            <div class="col-md-7">
                                <form:input type="text" path="lastName" id="lastName" class="form-control input-sm"/>
                                <div class="has-error">
                                    <form:errors path="lastName" class="help-inline"/>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-3 control-lable" for="ssoId">SSO ID</label>
                            <div class="col-md-7">
                                <form:input type="text" path="ssoId" id="ssoId" class="form-control input-sm"
                                            disabled="true" readonly="true"/>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-3 control-lable" for="password">Password</label>
                            <div class="col-md-7">
                                <form:input type="password" path="password" id="password"
                                            class="form-control input-sm"/>

                                <div class="has-error">
                                    <form:errors path="password" class="help-inline"/>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-3 control-lable" for="email">Email</label>
                            <div class="col-md-7">
                                <form:input type="text" path="email" id="email" class="form-control input-sm"/>
                                <div class="has-error">
                                    <form:errors path="email" class="help-inline"/>
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-3 control-lable" for="description">Description</label>
                            <div class="col-md-7">
                                <form:textarea type="text" path="description" id="description" rows="5" cols="30"
                                               class="form-control input-sm"/>
                                <div class="has-error">
                                    <form:errors path="description" class="help-inline"/>
                                </div>
                            </div>
                        </div>
                    </div>
                    <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
                        <div class="row">
                            <div class="form-group col-md-12">
                                <label class="col-md-3 control-lable" for="userProfiles">Roles</label>
                                <div class="col-md-7">
                                    <form:select path="userProfiles" items="${roles}" multiple="true" itemValue="id"
                                                 itemLabel="type" class="form-control input-sm"/>
                                    <div class="has-error">
                                        <c:if test="${param.error != null}">

                                        </c:if>
                                            <%--<form:errors path="userProfiles" class="help-inline"/>--%>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </sec:authorize>
                </td>
                <td>
                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-3 control-lable">Photo:</label>
                            <div>
                                <img src="/static/images/default.JPG" width="117" height="160" alt="df" id="statImg">
                            </div>
                            <div>
                                <input id="file" type="file" path="file" accept="image/*" onchange="loadFile(event)"/>
                                <img  id="output" width="117" height="160"/>
                                <button name="refresh" id="refresh" type="button" onclick="saveAdvertWithImage()" >Refresh</button>
                            </div>
                            <div>

                            </div>
                            <a href="<c:url value='/singleUpload' />">Change Photo</a>

                            <%--<div class="row">--%>
                                <%--<div class="form-group col-md-12">--%>
                                    <%--<label class="col-md-3 control-lable" for="file">Photo test(todo add upload)</label>--%>
                                    <%--<div class="col-md-7">--%>
                                       <%----%>
                                        <%--&lt;%&ndash;<input type="file" name="file" id="file"/>&ndash;%&gt;--%>
                                        <%--&lt;%&ndash;<span>&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;<form:errors path="file" cssClass="error" />&ndash;%&gt;--%>
                                        <%--&lt;%&ndash;</span>&ndash;%&gt;--%>


                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="row">--%>
                                <%--<div class="form-group col-md-12">--%>
                                    <%--<label class="col-md-3 control-lable" for="file">Upload a file</label>--%>
                                    <%--<div class="col-md-7">--%>
                                        <%--<input type="file" path="file" id="file" class="form-control input-sm"/>--%>
                                            <%--&lt;%&ndash;<div class="has-error">&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;<form:errors path="file" class="help-inline"/>&ndash;%&gt;--%>
                                        <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</div>--%>


                        <%--<div class="row">--%>
                                <%--<div class="form-group col-md-12">--%>
                                <%--<label class="col-md-3 control-lable" for="photo">Photo test(todo add upload)</label>--%>
                                <%--<div class="col-md-7">--%>
                                <%--<form:input type="text" path="photo" id="description" class="form-control input-sm" />--%>
                                <%--<div class="has-error">--%>
                                <%--<form:errors path="photo" class="help-inline"/>--%>
                                <%--</div>--%>
                                <%--</div>--%>
                                <%--</div>--%>
                                <%--</div>--%>
                        </div>
                    </div>
                </td>
            </tr>
        </table>

        <%--or <a href="<c:url value='/updateUser-${user.ssoId}'/>">Cancel</a>--%>
        <div class="row">
            <div class="form-actions floatRight">
                <input type="submit" value="Update" class="btn btn-primary btn-sm"/> or <a
                    href="<c:url value='/user-${user.ssoId}'/>">Cancel</a>
            </div>
            <%--<div>--%>
                <%--<input type="file" accept="image/*" onchange="loadFile(event)">--%>
                <%--<img id="output" width="117" height="160"/>--%>
            <%--</div>--%>
        </div>
    </form:form>
</div>
</body>
</html>