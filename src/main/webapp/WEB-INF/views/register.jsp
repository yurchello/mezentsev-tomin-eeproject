<%--
  Created by IntelliJ IDEA.
  User: Yuriy
  Date: 18.06.2016
  Time: 0:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/register" method='POST' >
    <h1>Registration Form</h1>
    <table>
        <tr>
            <td>Username:</td>
            <td><input type='text' name='username'></td>
        </tr>
        <tr>
            <td>Login:</td>
            <td><input type='text' name='login'></td>
        </tr>
        <tr>
            <td>Email:</td>
            <td><input type='text' name='email'></td>
        </tr>
        <tr>
            <td>Description:</td>
            <td><input type='text' name='description'></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input type='text' name='password' /></td>
        </tr>
        <tr>
            <td>Confirm password:</td>
            <td><input type='password' name='confirmPassword' /></td>
        </tr>
        <tr>
            <td>Photo:</td>
            <td><input type='text' name='photoPath'></td>
        </tr>


        <tr>
            <td colspan='2'><input name="submit"  type="submit" value="Register" /></td>
        </tr>

    </table>
</form>
</body>
</html>
