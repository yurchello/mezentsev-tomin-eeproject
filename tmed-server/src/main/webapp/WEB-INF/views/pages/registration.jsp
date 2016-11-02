<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>



<body>
<div class="well lead">User Registration Form</div>
<div class="generic-container col-xs-12" style="height:50px;">

    <form:form method="POST" modelAttribute="user" class="form-horizontal">
        <form:input type="hidden" path="id" id="id"/>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-2 control-lable" for="firstName">First Name</label>
                <div class="col-md-4">
                    <div class="has-error">
                        <form:errors path="firstName" class="help-inline"/>
                    </div>
                    <form:input type="text" path="firstName" id="firstName" class="form-control input-sm"/>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-2 control-lable" for="lastName">Last Name</label>
                <div class="col-md-4">
                    <div class="has-error">
                        <form:errors path="lastName" class="help-inline"/>
                    </div>
                    <form:input type="text" path="lastName" id="lastName" class="form-control input-sm"/>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-2 control-lable" for="ssoId">SSO ID</label>
                <div class="col-md-4">
                    <c:choose>
                        <c:when test="${edit}">
                            <form:input type="text" path="ssoId" id="ssoId" class="form-control input-sm"
                                        disabled="true"/>
                        </c:when>
                        <c:otherwise>
                            <div class="has-error">
                                <form:errors path="ssoId" class="help-inline"/>
                            </div>
                            <form:input type="text" path="ssoId" id="ssoId" class="form-control input-sm"/>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-2 control-lable" for="password">Password</label>
                <div class="col-md-4">
                    <div class="has-error">
                        <form:errors path="password" class="help-inline"/>
                    </div>
                    <form:input type="text" path="password" id="password1" onkeyup="checkPasswordStrength()"
                                class="form-control input-sm"/>
                    <label id="strengthValue"/>

                </div>
            </div>
        </div>
        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-2 control-lable" for="password2">Confirm password</label>
                <div class="col-md-4">
                    <input type="text" path="password2" id="password2" onkeyup="confirmPassword()"
                           class="form-control input-sm"/>
                    <label id="confirmLabel"/>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-2 control-lable" for="email">Email</label>
                <div class="col-md-4">
                    <div class="has-error">
                        <form:errors path="email" class="help-inline"/>
                    </div>
                    <form:input type="text" path="email" id="email" class="form-control input-sm"/>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-2 control-lable" for="description">Description</label>
                <div class="col-md-4">
                    <form:textarea style="resize:none" type="text" path="description" id="description" rows="5" cols="30"
                                   class="form-control input-sm"/>
                    <div class="has-error">
                        <form:errors path="description" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

        <form:input type="hidden" name="photo" path="photo"/>

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
        <div class="row">
            <div class="form-group col-md-12">
                <div class="col-md-4">
                    <input type="submit" disabled="true" value="Register" id="registerButton" class="btn btn-success btn-sm"/> or <a href="<c:url value='/' />">Cancel</a>
                </div>
            </div>
        </div>
        <%--<sec:authorize access="hasRole('ANONYMOUS')">--%>
            <%--&lt;%&ndash;<form:input type="hidden"  id="userProfiles" v path="userProfiles" />&ndash;%&gt;--%>
        <%--</sec:authorize>--%>

    </form:form>
</div>
</body>
<script>
    function checkPasswordStrength() {
        $.ajax({
            url: 'checkStrength',
            data: ({password: $('#password1').val()}),
            success: function (data) {
                var label = document.getElementById('strengthValue');
                switch (data) {
                    case "1":
                        label.innerHTML = "Low protection";
                        label.style.color = "red";
                        break;
                    case "2":
                        label.innerHTML = "Middle protection";
                        label.style.color = "blue";
                        break;
                    case "3":
                        label.innerHTML = "High protection";
                        label.style.color = "green";
                        break;
                    default:
                        label.innerHTML = "Low protection";
                        label.style.color = "";
                        break;
                }
            }
        });
    }

    function confirmPassword() {
        var password1 = document.getElementById('password1').value;
        var password2 = document.getElementById('password2').value;
        var label = document.getElementById('confirmLabel');
        if (password1 == password2) {
            label.innerHTML = 'Confirm password OK';
            label.style.color = "green";
            document.getElementById('registerButton').disabled = false;
        } else {
            label.innerHTML = 'Passwords are not identical';
            label.style.color = "red";
            document.getElementById('registerButton').disabled = true;
        }
    }

    function doUpdateAvatar() {
        $.ajax({
            url: 'doUpdateAvatar',
            data: ({aaa: $('#firstName').val()}),
            success: function (data) {
                $('#rrrrr').html(data)
            }
        });
    }

</script>
</html>