<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New Word Group</title>
</head>
<body>
<form:form method="POST" modelAttribute="wordsGroup">
    <%--<input type="hidden" id="wordsGroupId" name="wordsGroupId" value="${wordsGroupId}">--%>
    <%--<form:input type="hidden" path="id" id="id"/>--%>
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
            <input type="submit" value="Add" id="addNewGroup" class="btn btn-primary btn-sm"/> or <a href="<c:url value='/groupsList-${user.ssoId}'/>">Cancel</a>
        </div>
    </div>
</form:form>
</body>
</html>
