<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Send an e-mail</title>
</head>
<body >
<div class="well lead">Contact Us</div>
<div class="generic-container col-xs-12" style="height:50px;">
    <form:form method="POST" modelAttribute="emailSenderModel">
        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-1 control-lable text-colour" for="mailFrom">Your Email</label>
                <div class="col-md-4">
                    <div class="has-error">
                        <form:errors path="mailFrom" class="help-inline"/>
                    </div>
                    <form:input type="text" path="mailFrom" name="mailFrom" id="mailFrom" class="form-control input-sm"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-1 control-lable text-colour" for="subject">Subject</label>
                <div class="col-md-4">
                    <div class="has-error">
                        <form:errors path="subject" class="help-inline"/>
                    </div>
                    <form:input type="text" path="subject" name="subject" id="subject" class="form-control input-sm"/>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-1 control-lable text-colour" for="message">Message</label>
                <div class="col-md-4">
                    <div class="has-error">
                        <form:errors path="message" class="help-inline"/>
                    </div>
                    <form:textarea style="resize:none" cols="60" rows="10" path="message" name="message" id="message" class="form-control input-sm"></form:textarea>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="form-group col-md-12">
                <div class="col-md-4" >
                    <input type="submit" value="Send E-mail" class="btn btn-success btn-sm"/>
                </div>
            </div>
        </div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form:form>
</div>
<%--<div class="generic-container col-xs-12" style="height:50px;">--%>
    <%--<form:form method="POST" modelAttribute="emailSenderModel">--%>
    <%--<table border="0" width="80%">--%>
        <%--<tr>--%>
            <%--<td>--%>
                <%--<label for="mailFrom">Your Email:</label>--%>
                <%--<div class="has-error">--%>
                    <%--<form:errors path="mailFrom" class="help-inline"/>--%>
                <%--</div>--%>
            <%--</td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td>--%>
                <%--<form:input type="text" path="mailFrom" name="mailFrom" id="mailFrom"/>--%>
            <%--</td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td>--%>
                <%--<label for="subject">Subject:</label>--%>
                <%--<div class="has-error">--%>
                    <%--<form:errors path="subject" class="help-inline"/>--%>
                <%--</div>--%>
            <%--</td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td>--%>
                <%--<form:input type="text" path="subject" name="subject" id="subject"/>--%>
            <%--</td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td>--%>
                <%--<label for="message">Message:</label>--%>
                <%--<div class="has-error">--%>
                    <%--<form:errors path="message" class="help-inline"/>--%>
                <%--</div>--%>
            <%--</td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td>--%>
                <%--<form:textarea style="resize:none" cols="60" rows="10" path="message" name="message"--%>
                               <%--id="message"></form:textarea>--%>
            <%--</td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<td colspan="2" align="left">--%>
                <%--<input type="submit" value="Send E-mail"/>--%>
            <%--</td>--%>
        <%--</tr>--%>
        <%--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>
    <%--</table>--%>
<%--</form:form>--%>
<%--</div>--%>

</body>
</html>