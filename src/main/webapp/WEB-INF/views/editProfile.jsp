<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>

<%--<head>--%>
<%--<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">--%>
<%--<title>User Registration Form</title>--%>
<%--<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>--%>
<%--<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>--%>
<%--</head>--%>

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
                                <form:input type="text" path="firstName" id="firstName" class="form-control input-sm"/>
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
                                <form:input type="text" path="lastName" id="lastName" class="form-control input-sm" />
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
                                <form:input type="text" path="ssoId" id="ssoId" class="form-control input-sm" disabled="true" readonly="true"/>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-md-12">
                            <label class="col-md-3 control-lable" for="password">Password</label>
                            <div class="col-md-7">
                                <form:input type="password" path="password" id="password" class="form-control input-sm" />
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
                                <form:input type="text" path="email" id="email" class="form-control input-sm" />
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
                                <form:textarea  type="text" path="description" id="description"  rows="5" cols="30" class="form-control input-sm" />
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
                                    <form:select path="userProfiles" items="${roles}" multiple="true" itemValue="id" itemLabel="type" class="form-control input-sm" />
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
                            <label class="col-md-3 control-lable" >Photo:</label>
                            <div>
                                <img src="/static/images/default.JPG" width="117" height="160" alt="df" >
                            </div>

                            <div class="row">
                                <div class="form-group col-md-12">
                                    <label class="col-md-3 control-lable" for="photo">Photo test(todo add upload)</label>
                                    <div class="col-md-7">
                                        <form:input type="text" path="photo" id="description" class="form-control input-sm" />
                                        <div class="has-error">
                                            <form:errors path="photo" class="help-inline"/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </td>
            </tr>
        </table>

        <%--or <a href="<c:url value='/updateUser-${user.ssoId}'/>">Cancel</a>--%>
        <div class="row">
            <div class="form-actions floatRight">
                <input type="submit" value="Update" class="btn btn-primary btn-sm"/> or <a href="<c:url value='/user-${user.ssoId}'/>">Cancel</a>
            </div>
        </div>
    </form:form>
</div>
</body>
</html>