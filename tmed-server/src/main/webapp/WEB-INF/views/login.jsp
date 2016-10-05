<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<html>
	<%--<head>--%>
		<%--<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">--%>
		<%--<title>Login page</title>--%>
		<%--<link href="<c:url value='/static/css/bootstrap.css' />"  rel="stylesheet"></link>--%>
		<%--<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>--%>
		<%--<link rel="stylesheet" type="text/css" href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css" />--%>
	<%--</head>--%>
	<head>
		<link rel="icon" href="http://example.com/image.png" />
		<%--<link rel="icon" href="../../../static/img/favicon.ico" type="image/x-icon" />--%>
		<%--<link rel="shortcut icon" href="http://www.example.com/favicon.ico" type="image/x-icon" />--%>
	</head>
	<body>
		<div id="mainWrapper">
			<div class="login-container">
				<div class="login-card">
					<div class="login-form">
							<sec:authorize access="!isAuthenticated()">
								<div>
									<c:url var="loginUrl" value="/login" />
									<form action="${loginUrl}" method="post" class="form-horizontal">
										<c:if test="${param.error != null}">
										<div class="alert alert-danger">
											<p>Invalid username and password.</p>
										</div>
										</c:if>
										<c:if test="${param.logout != null}">
										<div class="alert alert-success">
											<p>You have been logged out successfully.</p>
										</div>
										</c:if>
										<div class="input-group input-sm">
											<label class="input-group-addon" for="username"><i class="fa fa-user"></i></label>
											<input type="text" class="form-control" id="username" name="ssoId" placeholder="Enter Username" required>
										</div>
										<div class="input-group input-sm">
											<label class="input-group-addon" for="password"><i class="fa fa-lock"></i></label>
											<input type="password" class="form-control" id="password" name="password" placeholder="Enter Password" required>
										</div>
										<div class="input-group input-sm">
											<div class="checkbox">
												<label><input type="checkbox" id="rememberme" name="remember-me"> Remember Me</label>
											</div>
										</div>
										<input type="hidden" name="${_csrf.parameterName}"  value="${_csrf.token}" />

										<div class="form-actions">
											<input type="submit"
												   class="btn btn-block btn-primary btn-default" value="Log in">
										</div>
										<div>
											<a href="<c:url value='/newuser' />">Registration</a>
										</div>

								</div>
							</sec:authorize>
							<sec:authorize access="isAuthenticated()">
								<div>
									<%@include file="pages/authheader.jsp" %>
								</div>
								<div align="center">
									<div>
										<a href="<c:url value='/user-${loggedinuser}' />">My Account</a>
									</div>
									<div>
										<a href="<c:url value='/usersList' />">Find User</a>
									</div>
								</div>
							</sec:authorize>


						</form>
					</div>
				</div>
			</div>
		</div>

	</body>
</html>