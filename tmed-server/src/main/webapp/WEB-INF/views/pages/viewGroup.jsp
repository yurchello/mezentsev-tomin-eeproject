<%@ page contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<html>
<head>

    <title>View Words Group</title>
</head>
<body>
<h1>Group Name: ${wordsGroup.name}</h1>
<div class="generic-container">
    <div class="panel panel-default">
        <!-- Default panel contents -->
        <table class="table table-hover">
            <thead>
            <tr>
                <th>Word</th>
                <th>Transcription</th>
                <th>Translation</th>
                <th>Description</th>
                <c:if test="${edit}">
                    <th>Edit</th>
                    <th>Delete</th>
                </c:if>

                <th width="100"></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${wordsGroup.words}" var="word" varStatus="status">
                <tr>
                    <td>${word.word}</td>
                    <td>${word.transcription}</td>
                    <td>${word.translation}</td>
                    <td>${word.description}</td>
                    <c:if test="${edit}">
                        <td><a href='/editWord?wordId=${word.id}&wordsGroupId=${wordsGroup.id}&ssoId=${user.ssoId}' class="btn btn-success custom-width">Edit</a></td>
                        <td><a href='/deleteWord?wordId=${word.id}&wordsGroupId=${wordsGroup.id}&ssoId=${user.ssoId}' class="btn btn-danger custom-width">Delete</a></td>
                    </c:if>
                   <%--<td>${word.id}</td>--%>
                </tr>
            </c:forEach>
            </tbody>
        </table>



    </div>
    <c:if test="${edit}">
        <div class="well">
            <a href="/newWord?wordsGroupId=${wordsGroup.id}&ssoId=${user.ssoId}">Add New Word</a>
        </div>
    </c:if>


</div>

</body>
</html>
