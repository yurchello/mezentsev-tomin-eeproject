<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%--<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>--%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Send an e-mail</title>
</head>
<body>

    <h1>Spring MVC - Send e-mail with attachments</h1>

    <form method="POST" action="/sendEmail">
        <table border="0" width="80%">
            <tr>
                <td>
                    <label for="mailFrom">Your Email:</label>
                </td>
            </tr>
            <tr>
                <td>
                    <input type="text" name="mailFrom" id="mailFrom"/>
                </td>
            </tr>
            <tr>
                <td>
                    <label for="subject">Subject:</label>
                </td>
            </tr>
            <tr>
                <td><input type="text" name="subject" id="subject"/></td>
            </tr>

            <tr>
                <td>
                    <label for="message">Message:</label>
                </td>
            </tr>
            <tr>
                <td><textarea style="resize:none" cols="60" rows="10" name="message" id="message" ></textarea></td>
            </tr>
            <tr>
                <td colspan="2" align="left">
                    <input type="submit" value="Send E-mail" />
                </td>
            </tr>
            <input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />
        </table>
    </form>

</body>
</html>