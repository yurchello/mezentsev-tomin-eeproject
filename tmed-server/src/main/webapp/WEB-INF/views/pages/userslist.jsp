<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>

<head>
    <link href="<c:url value='/static/css/jquery.dataTables.min.css' />" rel="stylesheet"/>
    <script type="text/javascript" src="/static/js/jquery-1.12.3.js"></script>
    <script type="text/javascript" src="/static/js/jquery.dataTables.min.js"></script>
</head>

<body>
<div class="generic-container">
    <div class="panel panel-default" style=" height:80%; overflow:auto">
        <!-- Default panel contents -->
        <h1>Find User</h1>
        <table id="userListTable" class="display" cellspacing="0" width="100%">
            <thead>
            <tr>
                <th>SSO ID</th>
                <th>First name</th>
                <th>Last name</th>
                <th>Email</th>
                <th>Photo</th>
                <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
                    <th>Delete</th>
                </sec:authorize>

            </tr>
            </thead>
            <tbody>
            <c:forEach items="${users}" var="user" varStatus="i">
                <tr>
                    <td><a href="<c:url value='/user-${user.ssoId}'/>">${user.ssoId}</a></td>
                    <td>${user.firstName}</td>
                    <td>${user.lastName}</td>
                    <td>${user.email}</td>
                    <td>
                        <c:if test="${images[i.index] == null}">
                            <img id="output" alt="df" width="20" src="/static/images/default.JPG">
                        </c:if>
                        <c:if test="${images[i.index] != null}">
                            <img src="data:image/jpeg;base64,${images[i.index]}" id="output" width="20" alt="df">
                        </c:if>
                    </td>
                    <sec:authorize access="hasRole('ADMIN')">
                        <td><a href="<c:url value='/delete-user-${user.ssoId}' />" class="btn btn-danger custom-width">Delete</a>
                        </td>
                    </sec:authorize>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
            <div class="well">
                <a href="<c:url value='/newuser' />">Add New User</a>
            </div>
        </sec:authorize>
    </div>

</div>

</body>
<script>
    $(document).ready(function () {
        var table = $('#userListTable').DataTable();

        $('#userListTable tbody').on('click', 'tr', function () {
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