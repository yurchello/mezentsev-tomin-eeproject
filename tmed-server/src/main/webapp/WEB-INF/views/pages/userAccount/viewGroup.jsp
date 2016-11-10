<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>

<html>
<head>
    <link href="<c:url value='/static/css/jquery.dataTables.min.css' />" rel="stylesheet"/>
    <script type="text/javascript" src="/static/js/jquery-1.12.3.js"></script>
    <script type="text/javascript" src="/static/js/jquery.dataTables.min.js"></script>
    <title>Words Group</title>
</head>

<body>
<div class="well lead">Group Name: ${wordsGroup.name}</div>
<div  style=" height:80%; overflow:auto">
    <div class="well">
        <!-- Default panel contents -->
        <table class="display" cellspacing="0" width="100%" id="wordsList">
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
                        <td><a href='/editWord?wordId=${word.id}&wordsGroupId=${wordsGroup.id}&ssoId=${user.ssoId}'
                               class="btn btn-success custom-width">Edit</a></td>
                        <td><a href='/deleteWord?wordId=${word.id}&wordsGroupId=${wordsGroup.id}&ssoId=${user.ssoId}'
                               class="btn btn-danger custom-width">Delete</a></td>
                    </c:if>
                </tr>
            </c:forEach>
            </tbody>

        </table>
    </div>
    <div class="well">
        <c:if test="${edit}">
            <a href="/newWord?wordsGroupId=${wordsGroup.id}&ssoId=${user.ssoId}">Add New Word</a>
        </c:if>
    </div>
</div>

</body>
<script>
    $(document).ready(function () {
        var table = $('#wordsList').DataTable();
        $('#wordsList tbody').on('click', 'tr', function () {
            if ($(this).hasClass('selected')) {
                $(this).removeClass('selected');
            }
            else {
                table.$('tr.selected').removeClass('selected');
                $(this).addClass('selected');
            }
        });
        $('#button').click(function () {
            table.row('.selected').remove().draw(false);
        });
    });
</script>
</html>
