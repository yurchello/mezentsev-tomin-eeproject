<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New Words Group</title>
</head>
<body>
<form:form method="POST" modelAttribute="wordsGroup">
<div class="well lead">New Words Group</div>
<div style=" height:80%; overflow:auto">
    <div>
        <div>
            <label for="name">Group Name</label>
            <div class="has-error">
                <form:errors path="name" class="help-inline"/>
            </div>
            <div class="col-md-7">
                <form:input type="text" path="name" id="name" />
            </div>
        </div>

        <div>
            <input type="submit" value="Add" id="addNewGroup" class="btn btn-primary btn-sm"/> or <a href="<c:url value='/groupsList-${ssoId}'/>">Cancel</a>
        </div>
    </div>
</form:form>
</body>
</html>
