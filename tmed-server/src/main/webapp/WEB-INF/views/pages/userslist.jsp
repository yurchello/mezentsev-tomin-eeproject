<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<html>

<body>
	<div class="generic-container">
		<div class="panel panel-default">
			  <!-- Default panel contents -->
		  	<div class="panel-heading"><span class="lead">List of Users </span></div>
			<table class="table table-hover">
	    		<thead>
		      		<tr>
						<th>SSO ID</th>
				        <th>Firstname</th>
				        <th>Lastname</th>
				        <th>Email</th>
						<th>Photo</th>
				        <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
				        	<th width="100"></th>
				        </sec:authorize>
				        <sec:authorize access="hasRole('ADMIN')">
				        	<th width="100"></th>
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
					    <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
							<td><a href="<c:url value='/editUser-${user.ssoId}' />" class="btn btn-success custom-width">Edit</a></td>
				        </sec:authorize>
				        <sec:authorize access="hasRole('ADMIN')">
							<td><a href="<c:url value='/delete-user-${user.ssoId}' />" class="btn btn-danger custom-width">Delete</a></td>
        				</sec:authorize>
					</tr>
				</c:forEach>
	    		</tbody>
	    	</table>
		</div>
		<sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
		 	<div class="well">
		 		<a href="<c:url value='/newuser' />">Add New User</a>
		 	</div>
	 	</sec:authorize>
   	</div>
</body>
</html>