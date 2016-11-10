<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
    <link href="<c:url value='/static/css/headerMenu.css' />" rel="stylesheet"/>
</head>
<body>
<%--<ul class="hrMenu nav nav-tabs nav-justified">--%>
<ul class="hrMenu nav  nav-tabs"  >
	<li > <a  href="${pageContext.request.contextPath}/">Home</a></li>
    <li> <a href="${pageContext.request.contextPath}/download">Download</a></li>
    <%--<li> <a href="${pageContext.request.contextPath}/about">About Us</a></li>--%>
    <li> <a href="${pageContext.request.contextPath}/help">Help</a></li>
    <li> <a href="${pageContext.request.contextPath}/contactus">Contact Us</a></li>
</ul>
</body>
</html>