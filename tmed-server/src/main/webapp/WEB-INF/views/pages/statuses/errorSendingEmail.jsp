<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Error Sending Email</title>
</head>
<body>
<div class="well lead">Error</div>
<div class="generic-container col-xs-12" style="height:50px;">
    <div style="text-align: center">
        <div><h3>%=request.getAttribute("message")%</h3></div>
    </div>
</div>
</body>

</html>