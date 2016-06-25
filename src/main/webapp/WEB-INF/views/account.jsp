<%--
  Created by IntelliJ IDEA.
  User: Yuriy
  Date: 18.06.2016
  Time: 17:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>account</title>
</head>
<body>
<h3>User: ${login}</h3>
<form action="/save" method='POST' >
<table>
    <tr>
        <td>Name:</td>
        <td><input type='text' name='username' value=${username}></td>
    </tr>
    <tr>
        <td>Email:</td>
        <td><input type='text' name='email' value=${email}></td>
    </tr>
    <tr>
        <td>Description:</td>
        <td><input type='text' name='description' value=${description}></td>
    </tr>
    <tr>
        <td>Upload photo:</td>
        <td><input type='text' name='photoPath' value=${photoPath}></td>
    </tr>

    <tr>
        <td colspan='2'><input name="submit"  type="submit" value="Save" /></td>
    </tr>
</table>
</form>
</body>
</html>
