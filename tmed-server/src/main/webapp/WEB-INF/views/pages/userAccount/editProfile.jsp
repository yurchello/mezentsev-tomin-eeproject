<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Edit Personal Page</title>
</head>

<body>
<div class="well lead">Edit Personal Page</div>
<div class="generic-container col-xs-12" style="height:50px;">

    <form:form method="POST" modelAttribute="user" class="form-horizontal">
        <form:input type="hidden" path="id" id="id"/>
        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-2 control-lable text-colour" for="ssoId">SSO ID</label>
                <div class="col-md-4">
                    <form:input type="text" path="ssoId" id="ssoId" class="form-control input-sm" disabled="true"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-2 control-lable text-colour" for="firstName">First Name</label>
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
                <label class="col-md-2 control-lable text-colour" for="lastName">Last Name</label>
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
                <label class="col-md-2 control-lable text-colour" for="password">Password</label>
                <div class="col-md-4">
                    <div class="has-error">
                        <form:errors path="password" class="help-inline"/>
                    </div>
                    <form:input type="password" path="password" id="password1" onkeyup="checkPasswordStrength()"
                                class="form-control input-sm"/>
                    <label id="strengthValue"/>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-2 control-lable text-colour" for="email">Email</label>
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
                <label class="col-md-2 control-lable text-colour" for="description">Description</label>
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
                    <label class="col-md-2 control-lable text-colour" for="userProfiles">Roles</label>
                    <div class="col-md-4">
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
            <%--<div class="form-group col-md-12">--%>
                <%--<div class="col-md-4">--%>
                    <%--<input type="submit" disabled="true" value="Register" id="registerButton" class="btn btn-success btn-sm"/> or <a href="<c:url value='/' />">Cancel</a>--%>
                <%--</div>--%>
            <%--</div>--%>

                <div class="col-md-4 text-colour">
                    <input type="submit" value="Update" class="btn btn-success btn-sm"/> or <a
                        href="<c:url value='/user-${user.ssoId}'/>">Cancel</a>
                </div>

        </div>
        <%--<sec:authorize access="hasRole('ANONYMOUS')">--%>
        <%--&lt;%&ndash;<form:input type="hidden"  id="userProfiles" v path="userProfiles" />&ndash;%&gt;--%>
        <%--</sec:authorize>--%>

    </form:form>
</div>
</body>

</html>