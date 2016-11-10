<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Access Denied</title>
</head>
<body>
<div class="well lead">Error</div>
<div class="generic-container col-xs-12" style="height:50px;">
    <div style="text-align: center" class="text-colour">
        <div><h1>Access Denied</h1></div>
        <div>Sorry dude. Please, log in or  <a href="<c:url value='/newuser' />">Register</a></div>
    </div>
</div>
</body>

</html>
