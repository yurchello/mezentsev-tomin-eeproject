<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<html>

<head>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
	<%--<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">--%>
	<%--<title>Users List</title>--%>
	<%--<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>--%>
	<%--<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>--%>
</head>

<body>
<h1>Word Group</h1>
	<div class="generic-container">
		<div class="panel panel-default">
			  <!-- Default panel contents -->
		  	<div class="panel-heading"><span class="lead">List of Groups </span></div>
			<table class="table table-hover">
	    		<thead>
		      		<tr>
				        <th>Group name</th>
							<th>Edit/View</th>
							<c:if test="${edit}">
								<th>Delete</th>
							</c:if>

						<th width="100"></th>
					</tr>
		    	</thead>
	    		<tbody>
				<c:forEach items="${wordsGroups}" var="wordsGroup" varStatus="status">
					<tr>
						<td>${wordsGroup.name}</td>

							<td><a href='/view-group?wordsGroupId=${wordsGroup.id}&ssoId=${user.ssoId}' class="btn btn-success custom-width">Edit/View</a></td>
						<c:if test="${edit}">
							<td><a href='/delete-group?wordsGroupId=${wordsGroup.id}&ssoId=${user.ssoId}' class="btn btn-danger custom-width">Delete</a></td>
						</c:if>
						</tr>
				</c:forEach>
	    		</tbody>
	    	</table>
		</div>
		<div>
			<c:if test="${edit}">
				<a href="/newGroup?ssoId=${user.ssoId}">Add New Group</a>
			</c:if>

		</div>
   	</div>
</body>

<script type="text/javascript">


</script>
</html>