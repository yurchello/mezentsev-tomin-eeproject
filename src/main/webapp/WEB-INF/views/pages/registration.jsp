<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<%--<head>--%>
	<%--<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">--%>
	<%--<title>User Registration Form</title>--%>
	<%--<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>--%>
	<%--<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>--%>
<%--</head>--%>

<script>
	function checkPasswordStrength() {
		$.ajax({
			url: 'checkStrength',
			data: ({password :$('#password1').val()}),
			success: function (data) {
				var label = document.getElementById('strengthValue');
				switch (data){
					case "1":
						label.innerHTML = "Low protection";
						label.style.color = "red";
						break;
					case "2":
						label.innerHTML = "Middle protection";
						label.style.color = "blue";
						break;
					case "3":
						label.innerHTML = "High protection";
						label.style.color = "green";
						break;
					default:
						label.innerHTML = "Low protection";
						label.style.color = "";
						break;

				}
			}
		});
	}


	function confirmPassword(){
		var password1 = document.getElementById('password1').value;
		var password2 = document.getElementById('password2').value;
		var label = document.getElementById('confirmLabel');
		if (password1 == password2){
			label.innerHTML = 'Confirm password OK';
			label.style.color = "green";
			document.getElementById('registerButton').disabled = false;
		} else {
			label.innerHTML = 'Passwords are not identical';
			label.style.color = "red";
			document.getElementById('registerButton').disabled = true;
		}
	}

	function doUpdateAvatar() {

		$.ajax({
			url: 'doUpdateAvatar',
			data: ({aaa :$('#firstName').val()}),
			success: function (data) {
				$('#rrrrr').html(data)
			}
		});
	}



</script>

<body>
 	<div class="generic-container">
		<sec:authorize access="isAuthenticated()">
			<%@include file="authheader.jsp" %>
		</sec:authorize>

		<div class="well lead">User Registration Form</div>
	 	<form:form method="POST" modelAttribute="user" class="form-horizontal">
			<form:input type="hidden" path="id" id="id"/>

			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="firstName">First Name</label>
					<div class="col-md-7">
						<form:input type="text" path="firstName" id="firstName"  class="form-control input-sm"/>
						<div class="has-error">
							<form:errors path="firstName" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
	
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="lastName">Last Name</label>
					<div class="col-md-7">
						<form:input type="text" path="lastName" id="lastName"  class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="lastName" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
	
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="ssoId">SSO ID</label>
					<div class="col-md-7">
						<c:choose>
							<c:when test="${edit}">
								<form:input type="text" path="ssoId" id="ssoId"  class="form-control input-sm" disabled="true"/>
							</c:when>
							<c:otherwise>
								<form:input type="text" path="ssoId" id="ssoId"  class="form-control input-sm" />
								<div class="has-error">
									<form:errors path="ssoId" class="help-inline"/>
								</div>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>




			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="password">Password</label>
					<div class="col-md-7">
						<form:input type="text" path="password" id="password1" onkeyup="checkPasswordStrength()" class="form-control input-sm" />
						<label id="strengthValue"/>
						<div class="has-error">
							<form:errors path="password" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="password2">Confirm password</label>
					<div class="col-md-7">
						<input type="text" path="password2" id="password2" onkeyup="confirmPassword()" class="form-control input-sm" />
						<label id="confirmLabel"/>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="email">Email</label>
					<div class="col-md-7">
						<form:input type="text" path="email" id="email" onkeyup="doUpdateAvatar()" class="form-control input-sm" />
						<label id="rrrrr"/>
						<div class="has-error">
							<form:errors path="email" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="description">Description</label>
					<div class="col-md-7">
						<form:textarea  type="text" path="description" id="description"  rows="5" cols="30" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="description" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="photo">Photo test(todo add upload)</label>
					<div class="col-md-7">
						<form:input type="text" path="photo" id="description" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="photo" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
			<%--<textarea name='test' id='test' w><c:out value="wdfwdlfkdvlvmkla;sklsaklssnknklnn" /> </textarea>--%>
			<%--<form:input type="hidden"  name="userProfiles" value="[USER]"  path="userProfiles"/>--%>
			<sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="userProfiles">Roles</label>
					<div class="col-md-7">
						<form:select path="userProfiles" items="${roles}" multiple="true" itemValue="id" itemLabel="type" class="form-control input-sm" />
						<div class="has-error">
							<c:if test="${param.error != null}">

							</c:if>
							<%--<form:errors path="userProfiles" class="help-inline"/>--%>
						</div>
					</div>
				</div>
			</div>

			</sec:authorize>

			<sec:authorize access="hasRole('ANONYMOUS')">
				<%--<form:input type="hidden"  id="userProfiles" v path="userProfiles" />--%>
			</sec:authorize>

			<div class="row">
				<div class="form-actions floatRight">
					<c:choose>
						<c:when test="${edit}">
							<input type="submit" value="Update" class="btn btn-primary btn-sm"/> or <a href="<c:url value='/list' />">Cancel</a>
						</c:when>
						<c:otherwise>
							<input type="submit" value="Register" id="registerButton" class="btn btn-primary btn-sm"/> or <a href="<c:url value='/' />">Cancel</a>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</form:form>
	</div>
</body>
</html>