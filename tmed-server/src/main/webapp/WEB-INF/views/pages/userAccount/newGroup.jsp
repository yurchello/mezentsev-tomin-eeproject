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
<div class="generic-container col-xs-12" style="height:50px;">
    <div class="row">
        <div class="form-group col-md-12">
            <label class="col-md-2 control-lable text-colour" for="name">Words Group Name</label>
            <div class="col-md-4">
                <div class="has-error">
                    <form:errors path="name" class="help-inline"/>
                </div>
                <form:input type="text" path="name" id="name" class="form-control input-sm"/>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="form-group col-md-12">
            <div class="col-md-4 text-colour">
                <input type="submit" value="Add" id="addNewGroup" class="btn btn-success btn-sm"/> or <a href="<c:url value='/groupsList-${ssoId}'/>">Cancel</a>
            </div>
        </div>
    </div>


    <%--<div>--%>
        <%--<div>--%>
            <%--<label for="name">Group Name</label>--%>
            <%--<div class="has-error">--%>
                <%--<form:errors path="name" class="help-inline"/>--%>
            <%--</div>--%>
            <%--<div class="col-md-7">--%>
                <%--<form:input type="text" path="name" id="name" />--%>
            <%--</div>--%>
        <%--</div>--%>

        <%--<div>--%>
            <%--<input type="submit" value="Add" id="addNewGroup" class="btn btn-primary btn-sm"/> or <a href="<c:url value='/groupsList-${ssoId}'/>">Cancel</a>--%>
        <%--</div>--%>
    <%--</div>--%>
</form:form>
</body>
</html>
