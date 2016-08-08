<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <sec:authorize access="isAuthenticated()">
        <%@include file="authheader.jsp" %>
    </sec:authorize>

    <div class="well lead">User Personal Page</div>
    <form:form method="POST" modelAttribute="user" class="form-horizontal">
        <form:input type="hidden" path="id" id="id"/>

        <table>
            <tr>
                <td>
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
                                <img src="/static/images/default.JPG" width="117" height="160" alt="df">
                            </div>
                            <sec:authorize access="isAuthenticated()">
                                <c:if test="${loggedinuser==user.ssoId}">
                                    <div>
                                            <%--<input type="submit" value="Edit profile" class="btn btn-primary btn-sm"/>--%>
                                        <a href="<c:url value='/editUser-${user.ssoId}'/>">Edit Profile</a>
                                    </div>
                                    <div>
                                        <input type="submit" value="View Vocabulary" class="btn btn-primary btn-sm"/>
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