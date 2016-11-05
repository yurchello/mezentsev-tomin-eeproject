<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
    <title>Edit Personal Page</title>
</head>

<body>
<div class="well lead">Edit Personal Page</div>
<div class="generic-container col-xs-12" style="height:50px;">

    <form:form method="POST" modelAttribute="user" class="form-horizontal">
        <form:input type="hidden" path="id" id="id"/>
        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-2 control-lable" for="ssoId">SSO ID</label>
                <div class="col-md-4">
                    <form:input type="text" path="ssoId" id="ssoId" class="form-control input-sm" disabled="true"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-2 control-lable" for="firstName">First Name</label>
                <div class="col-md-4">
                    <div class="has-error">
                        <form:errors path="firstName" class="help-inline"/>
                    </div>
                    <form:input type="text" path="firstName" id="firstName" class="form-control input-sm"/>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-2 control-lable" for="lastName">Last Name</label>
                <div class="col-md-4">
                    <div class="has-error">
                        <form:errors path="lastName" class="help-inline"/>
                    </div>
                    <form:input type="text" path="lastName" id="lastName" class="form-control input-sm"/>
                </div>
            </div>
        </div>
        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-2 control-lable" for="password">Password</label>
                <div class="col-md-4">
                    <div class="has-error">
                        <form:errors path="password" class="help-inline"/>
                    </div>
                    <form:input type="password" path="password" id="password1" onkeyup="checkPasswordStrength()"
                                class="form-control input-sm"/>
                    <label id="strengthValue"/>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-2 control-lable" for="email">Email</label>
                <div class="col-md-4">
                    <div class="has-error">
                        <form:errors path="email" class="help-inline"/>
                    </div>
                    <form:input type="text" path="email" id="email" class="form-control input-sm"/>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-12">
                <label class="col-md-2 control-lable" for="description">Description</label>
                <div class="col-md-4">
                    <form:textarea style="resize:none" type="text" path="description" id="description" rows="5" cols="30"
                                   class="form-control input-sm"/>
                    <div class="has-error">
                        <form:errors path="description" class="help-inline"/>
                    </div>
                </div>
            </div>
        </div>

        <form:input type="hidden" name="photo" path="photo"/>

        <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
            <div class="row">
                <div class="form-group col-md-12">
                    <label class="col-md-2 control-lable" for="userProfiles">Roles</label>
                    <div class="col-md-4">
                        <form:select path="userProfiles" items="${roles}" multiple="true" itemValue="id"
                                     itemLabel="type" class="form-control input-sm"/>
                        <div class="has-error">
                            <c:if test="${param.error != null}">

                            </c:if>
                                <%--<form:errors path="userProfiles" class="help-inline"/>--%>
                        </div>
                    </div>
                </div>
            </div>
        </sec:authorize>
        <div class="row">
            <%--<div class="form-group col-md-12">--%>
                <%--<div class="col-md-4">--%>
                    <%--<input type="submit" disabled="true" value="Register" id="registerButton" class="btn btn-success btn-sm"/> or <a href="<c:url value='/' />">Cancel</a>--%>
                <%--</div>--%>
            <%--</div>--%>

                <div class="col-md-4">
                    <input type="submit" value="Update" class="btn btn-success btn-sm"/> or <a
                        href="<c:url value='/user-${user.ssoId}'/>">Cancel</a>
                </div>

        </div>
        <%--<sec:authorize access="hasRole('ANONYMOUS')">--%>
        <%--&lt;%&ndash;<form:input type="hidden"  id="userProfiles" v path="userProfiles" />&ndash;%&gt;--%>
        <%--</sec:authorize>--%>

    </form:form>
</div>
</body>





<%--<body>--%>
<%--<div class="well lead">Edit Personal Page</div>--%>
<%--<div class="generic-container col-xs-12" style="height:50px;">--%>
    <%--<form:form method="POST" modelAttribute="user" class="form-horizontal">--%>
        <%--<form:input type="hidden" path="id" id="id"/>--%>
        <%--<table cellspacing="0" cellpadding="5">--%>
            <%--<tr>--%>
                <%--<td width="400" valign="top">--%>
                    <%--<div class="row">--%>
                        <%--<div class="form-group col-md-12">--%>
                            <%--<label class="col-md-8 control-lable" for="ssoId">SSOID</label>--%>
                            <%--<div class="col-md-4">--%>
                                <%--&lt;%&ndash;<label id="ssoid" class="control-lable">${user.ssoId}</label>&ndash;%&gt;--%>
                                <%--<form:input type="text" path="ssoId" id="ssoId" class="form-control input-sm" />--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--&lt;%&ndash;<div class="row">&ndash;%&gt;--%>
                        <%--&lt;%&ndash;<div class="form-group col-md-12">&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<label class="col-md-8 control-lable" for="firstName">First Name</label>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<div class="col-md-4">&ndash;%&gt;--%>
                                <%--&lt;%&ndash;<label id="firstName"&ndash;%&gt;--%>
                                       <%--&lt;%&ndash;class="control-lable">${user.firstName}</label>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;<div class="row">&ndash;%&gt;--%>
                        <%--&lt;%&ndash;<div class="form-group col-md-12">&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<label class="col-md-8 control-lable" for="lastName">Last Name</label>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<div class="col-md-4">&ndash;%&gt;--%>
                                <%--&lt;%&ndash;<label id="lastName"&ndash;%&gt;--%>
                                       <%--&lt;%&ndash;class="control-lable">${user.lastName}</label>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;<div class="row">&ndash;%&gt;--%>
                        <%--&lt;%&ndash;<div class="form-group col-md-12">&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<label class="col-md-8 control-lable" for="email">Email</label>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<div class="col-md-4">&ndash;%&gt;--%>
                                <%--&lt;%&ndash;<label id="email"&ndash;%&gt;--%>
                                       <%--&lt;%&ndash;class="control-lable">${user.email}</label>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;</div>&ndash;%&gt;--%>

                    <%--&lt;%&ndash;<div class="row">&ndash;%&gt;--%>
                        <%--&lt;%&ndash;<div class="form-group col-md-12">&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<div class="form-group col-md-12">&ndash;%&gt;--%>
                                <%--&lt;%&ndash;<label class="col-md-8 control-lable" for="description">Description</label>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<div class="col-md-12">&ndash;%&gt;--%>
                                <%--&lt;%&ndash;<form:textarea style="resize:none" type="text" path="description" id="description"&ndash;%&gt;--%>
                                               <%--&lt;%&ndash;rows="5" cols="30"&ndash;%&gt;--%>
                                               <%--&lt;%&ndash;class="form-control input-sm" readonly="true"/>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;</div>&ndash;%&gt;--%>

                    <%--&lt;%&ndash;<div class="row">&ndash;%&gt;--%>
                        <%--&lt;%&ndash;<div class="form-group col-md-12">&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<div class="col-md-12">&ndash;%&gt;--%>
                                <%--&lt;%&ndash;<a class="btn btn-primary btn-block" href="<c:url value='/editUser-${user.ssoId}'/>">Edit&ndash;%&gt;--%>
                                    <%--&lt;%&ndash;Profile</a>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;<div class="row">&ndash;%&gt;--%>
                        <%--&lt;%&ndash;<div class="form-group col-md-12">&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<div class="col-md-12">&ndash;%&gt;--%>
                                <%--&lt;%&ndash;<a class="btn btn-primary btn-block" href="<c:url value='/groupsList-${user.ssoId}'/>">View&ndash;%&gt;--%>
                                    <%--&lt;%&ndash;vocabulary</a>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;</div>&ndash;%&gt;--%>

                <%--</td>--%>
                <%--<td width="400" valign="top" >--%>
                    <%--<div class="accountImageArea">--%>
                        <%--<div class="row">--%>
                            <%--<div class="form-group col-md-12">--%>
                                <%--<div class=" col-md-12">--%>
                                    <%--<c:if test="${user.photo != ''}">--%>
                                        <%--<img src="data:image/jpeg;base64,${photoPath}" id="output" width="200"--%>
                                             <%--alt="df">--%>
                                    <%--</c:if>--%>
                                    <%--<c:if test="${user.photo == ''}">--%>
                                        <%--<img id="output" alt="df" width="200" src="/static/img/default.JPG">--%>
                                    <%--</c:if>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                        <%--</div>--%>


                        <%--<sec:authorize access="isAuthenticated()">--%>
                            <%--<c:if test="${loggedinuser==user.ssoId}">--%>
                                <%--&lt;%&ndash;<div class="row">&ndash;%&gt;--%>
                                    <%--&lt;%&ndash;<div class="form-group col-md-12">&ndash;%&gt;--%>
                                        <%--&lt;%&ndash;<div class="col-md-12">&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;<label class="col-md-12 btn btn-primary btn-block btn-file">&ndash;%&gt;--%>
                                                <%--&lt;%&ndash;Browse... <input onchange="loadFile(event)" id="file" type="file"&ndash;%&gt;--%>
                                                                 <%--&lt;%&ndash;path="file" style="display: none;">&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;</label>&ndash;%&gt;--%>
                                        <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                                    <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                                <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                                <%--&lt;%&ndash;<div class="row">&ndash;%&gt;--%>
                                    <%--&lt;%&ndash;<div class="form-group col-md-12">&ndash;%&gt;--%>
                                        <%--&lt;%&ndash;<div class="col-md-12">&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;<a class="btn btn-primary btn-block btn-sm" name="refresh" id="refresh"&ndash;%&gt;--%>
                                               <%--&lt;%&ndash;type="button" onclick="avatarUpload()">Refresh</a>&ndash;%&gt;--%>
                                        <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                                    <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                                <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                                <%--&lt;%&ndash;<div class="row">&ndash;%&gt;--%>
                                    <%--&lt;%&ndash;<div class="form-group col-md-12">&ndash;%&gt;--%>
                                        <%--&lt;%&ndash;<div class="col-md-12">&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;<a class="btn btn-primary btn-block btn-sm"&ndash;%&gt;--%>
                                               <%--&lt;%&ndash;href="<c:url value='/user-${user.ssoId}'/>">Cancel</a>&ndash;%&gt;--%>
                                        <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                                    <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                                <%--&lt;%&ndash;</div>&ndash;%&gt;--%>

                            <%--</c:if>--%>
                        <%--</sec:authorize>--%>
                    <%--</div>--%>
                <%--</td>--%>
            <%--</tr>--%>
        <%--</table>--%>

    <%--</form:form>--%>
<%--</div>--%>
<%--</body>--%>
<%--<body>--%>
<%--<div class="generic-container">--%>
    <%--<div class="well lead">Personal Page</div>--%>
    <%--<div class="generic-container col-xs-12" style="height:50px;">--%>
        <%--<div class="generic-container col-xs-12" style="height:50px;">--%>
            <%--<form:form method="POST" modelAttribute="user" class="form-horizontal">--%>
                <%--<form:input type="hidden" path="id" id="id"/>--%>
                <%--<table cellspacing="0" cellpadding="5">--%>
                    <%--<tr>--%>
                        <%--<td width="400" valign="top">--%>
                            <%--<div class="row">--%>
                                <%--<div class="form-group col-md-12">--%>
                                    <%--<label class="col-md-8 control-lable" for="ssoid">SSOID</label>--%>
                                    <%--<div class="col-md-4">--%>
                                        <%--<label id="ssoid"--%>
                                               <%--class="control-lable">${user.ssoId}</label>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="row">--%>
                                <%--<div class="form-group col-md-12">--%>
                                    <%--<label class="col-md-8 control-lable" for="firstName">First Name</label>--%>
                                    <%--<div class="col-md-4">--%>
                                        <%--<label id="firstName"--%>
                                               <%--class="control-lable">${user.firstName}</label>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="row">--%>
                                <%--<div class="form-group col-md-12">--%>
                                    <%--<label class="col-md-8 control-lable" for="lastName">Last Name</label>--%>
                                    <%--<div class="col-md-4">--%>
                                        <%--<label id="lastName"--%>
                                               <%--class="control-lable">${user.lastName}</label>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="row">--%>
                                <%--<div class="form-group col-md-12">--%>
                                    <%--<label class="col-md-8 control-lable" for="email">Email</label>--%>
                                    <%--<div class="col-md-4">--%>
                                        <%--<label id="email"--%>
                                               <%--class="control-lable">${user.email}</label>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</div>--%>

                            <%--<div class="row">--%>
                                <%--<div class="form-group col-md-12">--%>
                                    <%--<div class="form-group col-md-12">--%>
                                        <%--<label class="col-md-8 control-lable" for="description">Description</label>--%>
                                    <%--</div>--%>
                                    <%--<div class="col-md-12">--%>
                                        <%--<form:textarea style="resize:none" type="text" path="description" id="description"--%>
                                                       <%--rows="5" cols="30"--%>
                                                       <%--class="form-control input-sm" readonly="true"/>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</div>--%>

                            <%--<div class="row">--%>
                                <%--<div class="form-group col-md-12">--%>
                                    <%--<div class="col-md-12">--%>
                                        <%--<a class="btn btn-primary btn-block" href="<c:url value='/editUser-${user.ssoId}'/>">Edit--%>
                                            <%--Profile</a>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</div>--%>
                            <%--<div class="row">--%>
                                <%--<div class="form-group col-md-12">--%>
                                    <%--<div class="col-md-12">--%>
                                        <%--<a class="btn btn-primary btn-block" href="<c:url value='/groupsList-${user.ssoId}'/>">View--%>
                                            <%--vocabulary</a>--%>
                                    <%--</div>--%>
                                <%--</div>--%>
                            <%--</div>--%>

                        <%--</td>--%>
                        <%--<td width="400" valign="top" >--%>
                            <%--<div class="accountImageArea">--%>
                                <%--<div class="row">--%>
                                    <%--<div class="form-group col-md-12">--%>
                                        <%--<div class=" col-md-12">--%>
                                            <%--<c:if test="${user.photo != ''}">--%>
                                                <%--<img src="data:image/jpeg;base64,${photoPath}" id="output" width="200"--%>
                                                     <%--alt="df">--%>
                                            <%--</c:if>--%>
                                            <%--<c:if test="${user.photo == ''}">--%>
                                                <%--<img id="output" alt="df" width="200" src="/static/img/default.JPG">--%>
                                            <%--</c:if>--%>
                                        <%--</div>--%>
                                    <%--</div>--%>
                                <%--</div>--%>


                                <%--<sec:authorize access="isAuthenticated()">--%>
                                    <%--<c:if test="${loggedinuser==user.ssoId}">--%>
                                        <%--&lt;%&ndash;<div class="row">&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;<div class="form-group col-md-12">&ndash;%&gt;--%>
                                                <%--&lt;%&ndash;<div class="col-md-12">&ndash;%&gt;--%>
                                                    <%--&lt;%&ndash;<label class="col-md-12 btn btn-primary btn-block btn-file">&ndash;%&gt;--%>
                                                        <%--&lt;%&ndash;Browse... <input onchange="loadFile(event)" id="file" type="file"&ndash;%&gt;--%>
                                                                         <%--&lt;%&ndash;path="file" style="display: none;">&ndash;%&gt;--%>
                                                    <%--&lt;%&ndash;</label>&ndash;%&gt;--%>
                                                <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                                        <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                                        <%--&lt;%&ndash;<div class="row">&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;<div class="form-group col-md-12">&ndash;%&gt;--%>
                                                <%--&lt;%&ndash;<div class="col-md-12">&ndash;%&gt;--%>
                                                    <%--&lt;%&ndash;<a class="btn btn-primary btn-block btn-sm" name="refresh" id="refresh"&ndash;%&gt;--%>
                                                       <%--&lt;%&ndash;type="button" onclick="avatarUpload()">Refresh</a>&ndash;%&gt;--%>
                                                <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                                        <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                                        <%--&lt;%&ndash;<div class="row">&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;<div class="form-group col-md-12">&ndash;%&gt;--%>
                                                <%--&lt;%&ndash;<div class="col-md-12">&ndash;%&gt;--%>
                                                    <%--&lt;%&ndash;<a class="btn btn-primary btn-block btn-sm"&ndash;%&gt;--%>
                                                       <%--&lt;%&ndash;href="<c:url value='/user-${user.ssoId}'/>">Cancel</a>&ndash;%&gt;--%>
                                                <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                                        <%--&lt;%&ndash;</div>&ndash;%&gt;--%>

                                    <%--</c:if>--%>
                                <%--</sec:authorize>--%>
                            <%--</div>--%>
                        <%--</td>--%>
                    <%--</tr>--%>
                <%--</table>--%>

            <%--</form:form>--%>
        <%--</div>--%>




















    <%--&lt;%&ndash;<form:form method="POST" modelAttribute="user" class="form-horizontal">&ndash;%&gt;--%>
        <%--&lt;%&ndash;<form:input type="hidden" path="id" id="id"/>&ndash;%&gt;--%>
        <%--&lt;%&ndash;<table>&ndash;%&gt;--%>
            <%--&lt;%&ndash;<tr>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<td>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;<div class="row">&ndash;%&gt;--%>
                        <%--&lt;%&ndash;<div class="form-group col-md-12">&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<label class="col-md-3 control-lable" for="firstName">First Name</label>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<div class="col-md-7">&ndash;%&gt;--%>
                                <%--&lt;%&ndash;<form:input type="text" path="firstName" onkeyup="doUpdateAvatar()" id="firstName"&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;class="form-control input-sm"/>&ndash;%&gt;--%>
                                <%--&lt;%&ndash;<label id="hhh"/>&ndash;%&gt;--%>
                                <%--&lt;%&ndash;<div class="has-error">&ndash;%&gt;--%>
                                    <%--&lt;%&ndash;<form:errors path="firstName" class="help-inline"/>&ndash;%&gt;--%>
                                <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;</div>&ndash;%&gt;--%>

                    <%--&lt;%&ndash;<div class="row">&ndash;%&gt;--%>
                        <%--&lt;%&ndash;<div class="form-group col-md-12">&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<label class="col-md-3 control-lable" for="lastName">Last Name</label>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<div class="col-md-7">&ndash;%&gt;--%>
                                <%--&lt;%&ndash;<form:input type="text" path="lastName" id="lastName" class="form-control input-sm"/>&ndash;%&gt;--%>
                                <%--&lt;%&ndash;<div class="has-error">&ndash;%&gt;--%>
                                    <%--&lt;%&ndash;<form:errors path="lastName" class="help-inline"/>&ndash;%&gt;--%>
                                <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;</div>&ndash;%&gt;--%>

                    <%--&lt;%&ndash;<div>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;<form:hidden path="photo" id="photo"/>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;</div>&ndash;%&gt;--%>

                    <%--&lt;%&ndash;<div class="row">&ndash;%&gt;--%>
                        <%--&lt;%&ndash;<div class="form-group col-md-12">&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<label class="col-md-3 control-lable" for="ssoId">SSO ID</label>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<div class="col-md-7">&ndash;%&gt;--%>
                                <%--&lt;%&ndash;<form:input type="text" path="ssoId" id="ssoId" class="form-control input-sm"&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;disabled="true" readonly="true"/>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;</div>&ndash;%&gt;--%>

                    <%--&lt;%&ndash;<div class="row">&ndash;%&gt;--%>
                        <%--&lt;%&ndash;<div class="form-group col-md-12">&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<label class="col-md-3 control-lable" for="password">Password</label>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<div class="col-md-7">&ndash;%&gt;--%>
                                <%--&lt;%&ndash;<form:input type="password" path="password" id="password"&ndash;%&gt;--%>
                                            <%--&lt;%&ndash;class="form-control input-sm"/>&ndash;%&gt;--%>

                                <%--&lt;%&ndash;<div class="has-error">&ndash;%&gt;--%>
                                    <%--&lt;%&ndash;<form:errors path="password" class="help-inline"/>&ndash;%&gt;--%>
                                <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;</div>&ndash;%&gt;--%>

                    <%--&lt;%&ndash;<div class="row">&ndash;%&gt;--%>
                        <%--&lt;%&ndash;<div class="form-group col-md-12">&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<label class="col-md-3 control-lable" for="email">Email</label>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<div class="col-md-7">&ndash;%&gt;--%>
                                <%--&lt;%&ndash;<form:input type="text" path="email" id="email" class="form-control input-sm"/>&ndash;%&gt;--%>
                                <%--&lt;%&ndash;<div class="has-error">&ndash;%&gt;--%>
                                    <%--&lt;%&ndash;<form:errors path="email" class="help-inline"/>&ndash;%&gt;--%>
                                <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;</div>&ndash;%&gt;--%>

                    <%--&lt;%&ndash;<div class="row">&ndash;%&gt;--%>
                        <%--&lt;%&ndash;<div class="form-group col-md-12">&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<label class="col-md-3 control-lable" for="description">Description</label>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<div class="col-md-7">&ndash;%&gt;--%>
                                <%--&lt;%&ndash;<form:textarea type="text" path="description" id="description" rows="5" cols="30"&ndash;%&gt;--%>
                                               <%--&lt;%&ndash;class="form-control input-sm"/>&ndash;%&gt;--%>
                                <%--&lt;%&ndash;<div class="has-error">&ndash;%&gt;--%>
                                    <%--&lt;%&ndash;<form:errors path="description" class="help-inline"/>&ndash;%&gt;--%>
                                <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;<sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">&ndash;%&gt;--%>
                        <%--&lt;%&ndash;<div class="row">&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<div class="form-group col-md-12">&ndash;%&gt;--%>
                                <%--&lt;%&ndash;<label class="col-md-3 control-lable" for="userProfiles">Roles</label>&ndash;%&gt;--%>
                                <%--&lt;%&ndash;<div class="col-md-7">&ndash;%&gt;--%>
                                    <%--&lt;%&ndash;<form:select path="userProfiles" items="${roles}" multiple="true" itemValue="id"&ndash;%&gt;--%>
                                                 <%--&lt;%&ndash;itemLabel="type" class="form-control input-sm"/>&ndash;%&gt;--%>
                                    <%--&lt;%&ndash;<div class="has-error">&ndash;%&gt;--%>
                                        <%--&lt;%&ndash;<c:if test="${param.error != null}">&ndash;%&gt;--%>

                                        <%--&lt;%&ndash;</c:if>&ndash;%&gt;--%>
                                    <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                                <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;</sec:authorize>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</td>&ndash;%&gt;--%>
                <%--&lt;%&ndash;<td>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;<div class="row">&ndash;%&gt;--%>
                        <%--&lt;%&ndash;<div class="form-group col-md-12">&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<label class="col-md-3 control-lable">Photo:</label>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;<div>&ndash;%&gt;--%>
                                <%--&lt;%&ndash;<c:if test="${user.photo != ''}">&ndash;%&gt;--%>
                                    <%--&lt;%&ndash;<img src="data:image/jpeg;base64,${photoPath}" id="output" width="117" height="160"&ndash;%&gt;--%>
                                         <%--&lt;%&ndash;alt="df">&ndash;%&gt;--%>

                                <%--&lt;%&ndash;</c:if>&ndash;%&gt;--%>
                                <%--&lt;%&ndash;<c:if test="${user.photo == ''}">&ndash;%&gt;--%>
                                    <%--&lt;%&ndash;<img id="output" alt="df" width="117" height="160" src="/static/img/default.JPG">&ndash;%&gt;--%>
                                <%--&lt;%&ndash;</c:if>&ndash;%&gt;--%>
                            <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                        <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                    <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
                <%--&lt;%&ndash;</td>&ndash;%&gt;--%>
            <%--&lt;%&ndash;</tr>&ndash;%&gt;--%>
        <%--&lt;%&ndash;</table>&ndash;%&gt;--%>

        <%--&lt;%&ndash;<div class="row">&ndash;%&gt;--%>
            <%--&lt;%&ndash;<div class="form-actions floatRight">&ndash;%&gt;--%>
                <%--&lt;%&ndash;<input type="submit" value="Update" class="btn btn-primary btn-sm"/> or <a&ndash;%&gt;--%>
                    <%--&lt;%&ndash;href="<c:url value='/user-${user.ssoId}'/>">Cancel</a>&ndash;%&gt;--%>
            <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
        <%--&lt;%&ndash;</div>&ndash;%&gt;--%>
    <%--&lt;%&ndash;</form:form>&ndash;%&gt;--%>
<%--</div>--%>
<%--</body>--%>
</html>