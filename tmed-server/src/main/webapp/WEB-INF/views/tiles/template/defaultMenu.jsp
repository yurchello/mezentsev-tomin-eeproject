<nav align="center">
	<a href="${pageContext.request.contextPath}/"><img width="220" class="logo" src="${pageContext.request.contextPath}/static/img/main_logo.png"></a>
	<%@include file="../../login.jsp" %>
	<div >
		<sec:authorize access="isAuthenticated()">
			<%--<div align="center">--%>
				<%--<div align="left" class="row">--%>
					<%--<div class="form-group col-md-12" >--%>
						<%--<div class="col-md-12">--%>
							<%--<a href="<c:url value='/user-${loggedinuser}' />"><i class="fa fa-user" aria-hidden="true"></i> My Account</a>--%>
						<%--</div>--%>
					<%--</div>--%>
					<%--<div class="form-group col-md-12">--%>
						<%--<div class="col-md-12">--%>
							<%--<a href="<c:url value='/usersList' />"><i class="fa fa-book" aria-hidden="true"></i> Vocabulary</a>--%>
						<%--</div>--%>
					<%--</div>--%>
					<%--<div class="form-group col-md-12">--%>
						<%--<div class="col-md-12">--%>
							<%--<a href="<c:url value='/usersList' />"><i class="fa fa-users" aria-hidden="true"></i> Find User</a>--%>
						<%--</div>--%>
					<%--</div>--%>
				<%--</div>--%>
			<%--</div>--%>

			<ul align="left" class="nav">
				<li> <a href="<c:url value='/user-${loggedinuser}' />"><i class="fa fa-user" aria-hidden="true"></i> My Account</a></li>
				<li> <a href="<c:url value='/groupsList-${loggedinuser}' />"><i class="fa fa-book" aria-hidden="true"></i> Vocabulary</a></li>
				<li> <a href="<c:url value='/usersList' />"><i class="fa fa-users" aria-hidden="true"></i> Find User</a></li>
			</ul>

		</sec:authorize>
	</div>
</nav>