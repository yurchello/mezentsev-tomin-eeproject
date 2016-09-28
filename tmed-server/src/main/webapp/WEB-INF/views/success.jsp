<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>File Upload Success</title>
</head>
<body>
<div class="success">
    File  <strong>${fileName}</strong> uploaded successfully.
    <br/><br/>
    <a href="<c:url value='/welcome' />">Home</a>
</div>
</body>
</html>