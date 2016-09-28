<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Spring 4 MVC File Upload Example</title>
    <link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet" type="text/css"></link>
    <link href="<c:url value='/static/css/app.css' />" rel="stylesheet" type="text/css"></link>
</head>
<body>

<div>
    <form:form method="POST" modelAttribute="fileBucket" enctype="multipart/form-data">
        <sec:authorize access="isAuthenticated()">
            <%@include file="pages/authheader.jsp" %>
        </sec:authorize>
        <div>
            <div>
                <label>Photo preview:</label>
            </div>
            <div>
                <c:if test="${photoPath != null}">
                    <img src="data:image/jpeg;base64,${photoPath}" alt="df" width="234" height="320">
                </c:if>
                <c:if test="${photoPath == null}">
                    <img src="/static/images/default.JPG" width="234" height="320" alt="df">
                </c:if>
            </div>
        </div>
        <div>
            <div>
                <form:input type="file" path="file" id="file" title="dscasd"/>
                <div class="has-error">
                    <form:errors path="file" class="help-inline"/>
                </div>
            </div>
            <div>
                <input type="submit" name="preview" value="Preview">
            </div>
            <div>
                <input type="submit" name="upload" value="Upload"> or <input type="submit" name="cancel"  value="Cancel">
            </div>
            <div>
                <label>Photo path: ${photoPath}</label>
            </div>
        </div>
    </form:form>
</div>
</body>
</html>
