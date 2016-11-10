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
<div class="well lead">Add New Word to ${wordsGroup.name}</div>
<div class="generic-container col-xs-12" style="height:50px;">
<form:form method="POST" modelAttribute="word">
    <input type="hidden" id="wordsGroupId" name="wordsGroupId" value="${wordsGroup.id}">
    <form:input type="hidden" path="id" id="id"/>

    <div class="row">
        <div class="form-group col-md-12">
            <label class="col-md-2 control-lable text-colour" for="word">Word</label>
            <div class="col-md-4">
                <div class="has-error">
                    <form:errors path="word" class="help-inline"/>
                </div>
                <form:input type="text" path="word" id="word" class="form-control input-sm"/>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="form-group col-md-12">
            <label class="col-md-2 control-lable text-colour" for="transcription">Transcription</label>
            <div class="col-md-4">
                <div class="has-error">
                    <form:errors path="transcription" class="help-inline"/>
                </div>
                <form:input type="text" path="transcription" id="transcription" class="form-control input-sm"/>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="form-group col-md-12">
            <label class="col-md-2 control-lable text-colour" for="translation">Translation</label>
            <div class="col-md-4">
                <div class="has-error">
                    <form:errors path="translation" class="help-inline"/>
                </div>
                <form:input type="text" path="translation" id="translation" class="form-control input-sm"/>
            </div>
        </div>
    </div>

    <div class="row">
        <div class="form-group col-md-12">
            <label class="col-md-2 control-lable text-colour" for="description">Description</label>
            <div class="col-md-4">
                <form:textarea style="resize:none" type="text" path="description" id="description" rows="5" cols="30"
                               class="form-control input-sm"/>
                <div class="has-error">
                    <form:errors path="description" class="help-inline"/>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="form-group col-md-12 text-colour">
                <input type="submit" value="Add" id="registerButton" class="btn btn-success btn-sm"/> or <a href="/view-group?wordsGroupId=${wordsGroup.id}&ssoId=${ssoId}">Cancel</a>
            </div>
        </div>
    </div>


</form:form>
</body>
</html>
