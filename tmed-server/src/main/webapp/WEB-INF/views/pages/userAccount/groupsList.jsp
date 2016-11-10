<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>

<head>
    <link href="<c:url value='/static/css/jquery.dataTables.min.css' />" rel="stylesheet"/>
    <script type="text/javascript" src="/static/js/jquery-1.12.3.js"></script>
    <script type="text/javascript" src="/static/js/jquery.dataTables.min.js"></script>
    <title>Words Groups</title>
</head>
<body>
<div class="well lead">Words Groups</div>
<div style=" height:80%; overflow:auto">
    <div class="well">
        <table id="groupListTable" class="display" cellspacing="0" width="100%">
                <thead>
                <tr>
                    <th>Group name</th>
                    <th>Edit/View</th>
                    <c:if test="${edit}">
                        <th>Delete</th>
                    </c:if>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${wordsGroups}" var="wordsGroup" varStatus="status">
                    <tr>
                        <td>${wordsGroup.name}</td>
                        <td><a href='/view-group?wordsGroupId=${wordsGroup.id}&ssoId=${user.ssoId}'
                               class="btn btn-success custom-width">Edit/View</a></td>
                        <c:if test="${edit}">
                            <td><a href='/delete-group?wordsGroupId=${wordsGroup.id}&ssoId=${user.ssoId}'
                                   class="btn btn-danger custom-width">Delete</a></td>
                        </c:if>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
    </div>
    <div class="well">
        <c:if test="${edit}">
            <a href="/newGroup?ssoId=${user.ssoId}">Add New Words Group</a>
        </c:if>
    </div>
</div>

</div>
</body>

<script>
    $(document).ready(function () {
        var table = $('#groupListTable').DataTable();
        $('#groupListTable tbody').on('click', 'tr', function () {
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