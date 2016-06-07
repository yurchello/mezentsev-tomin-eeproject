<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="true"%>
<html>
<head>
    <title>Login Page</title>
</head>
<body onload='document.loginForm.username.focus();'>

<h1>Login Form Name</h1>

<div id="login-box">

    <h2>Login with Username and Password</h2>

    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>
    <c:if test="${not empty msg}">
        <div class="msg">${msg}</div>
    </c:if>

    <form name='loginForm' action="/login" method='POST'>

        <table>
            <tr>
                <td>User:</td>
                <td><input type='text' name='username'></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input type='password' name='password' /></td>
            </tr>
            <tr>
                <td colspan='2'><input name="submit"  type="submit" value="submit" /></td>
            </tr>
        </table>

        <%--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />--%>

    </form>
</div>

</body>
</html>
<%--<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>--%>
<%--<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>--%>
<%--<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>--%>
<%--<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>--%>
<%--<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>--%>

<%--<div>--%>
    <%--<h2>Hello form!</h2>--%>
    <%--<form:form method="POST" action="${context }/login" commandName="loginForm" >--%>
        <%--<table>--%>
            <%--<tr>--%>
                <%--<td colspan="2" class="errors"><form:errors path="*"/></td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td><form:label path="login">Login</form:label></td>--%>
                <%--<td><form:input path="login" /></td>--%>
            <%--</tr>--%>
            <%--<tr>--%>
                <%--<td><form:label path="password">Password</form:label></td>--%>
                <%--<td><form:password path="password"/> </td>--%>
            <%--</tr>--%>
        <%--</table>--%>
    <%--</form:form>--%>
<%--</div>--%>