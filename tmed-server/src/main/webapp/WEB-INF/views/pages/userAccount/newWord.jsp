<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'/>
    <title>Add New Word</title>
</head>
<body>
<h1>Add New Word to ${wordsGroup.name} group</h1>
<form:form method="POST" modelAttribute="word">
    <input type="hidden" id="wordsGroupId" name="wordsGroupId" value="${wordsGroup.id}">
    <form:input type="hidden" path="id" id="id"/>
    <div>
        <div>
            <label for="word">Word</label>
            <div class="has-error">
                <form:errors path="word" class="help-inline"/>
            </div>
            <div class="col-md-7">
                <form:input type="text" path="word" id="word"/>
            </div>
        </div>
        <div>
            <label for="transcription">Transcription</label>
            <div class="has-error">
                <form:errors path="transcription" class="help-inline"/>
            </div>
            <div class="col-md-7">
                <form:input type="text" path="transcription" id="transcription"/>
            </div>
        </div>
        <div>
            <label for="translation">Translation</label>
            <div class="has-error">
                <form:errors path="translation" class="help-inline"/>
            </div>
            <div class="col-md-7">
                <form:input type="text" path="translation" id="translation"/>
            </div>
        </div>
        <div>
            <label for="description">Description</label>
            <div class="has-error">
                <form:errors path="description" class="help-inline"/>
            </div>
            <div class="col-md-7">
                <form:textarea type="text" path="description" id="description" rows="5" cols="30"/>
            </div>
        </div>
        <div>
            <input type="submit" value="Add" id="addWord" class="btn btn-primary btn-sm"/> or <a
                href="/view-group?wordsGroupId=${wordsGroup.id}&ssoId=${ssoId}">Cancel</a>
        </div>
    </div>
</form:form>
</body>
</html>
