<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Download</title>
</head>
<body>
<div class="well lead">Download TranslateMe Dude Application</div>
<div class="generic-container col-xs-12" style="height:50px;">
    <div class="text-colour">
        <dl>
            <dt><label>Windows</label></dt>
            <dd>
                <c:if test="${fn:length(windowsFiles) != 0}">
                    <c:forEach items="${windowsFiles}" var="file" varStatus="status">
                        <div>
                            <a href="<c:url value='/download/windows-${file}' />">${file}</a>
                        </div>
                    </c:forEach>
                </c:if>
            </dd>
            <dd>
                <c:if test="${fn:length(windowsFiles) == 0}">
                    Sorry, dude... But, this OS not supported yet...
                </c:if>
            </dd>
        </dl>
    </div>

    <div class="text-colour">
        <dl>
            <dt><label>Android</label></dt>
            <dd>
                <c:if test="${fn:length(androidFiles) != 0}">
                    <c:forEach items="${androidFiles}" var="file" varStatus="status">
                        <div>
                            <a href="<c:url value='/download/android-${file}' />">${file}</a>
                        </div>
                    </c:forEach>
                </c:if>
            </dd>
            <dd>
                <c:if test="${fn:length(androidFiles) == 0}">
                    Sorry, dude... But, this OS not supported yet...
                </c:if>
            </dd>
        </dl>
    </div>

    <div class="text-colour">
        <dl>
            <dt><label>IOS</label></dt>
            <dd>
                <c:if test="${fn:length(iosFiles) != 0}">
                    <c:forEach items="${iosFiles}" var="file" varStatus="status">
                        <div>
                            <a href="<c:url value='/download/ios-${file}' />">${file}</a>
                        </div>
                    </c:forEach>
                </c:if>
            </dd>
            <dd>
                <c:if test="${fn:length(iosFiles) == 0}">
                    Sorry, dude... But, this OS not supported yet...
                </c:if>
            </dd>
        </dl>
    </div>

</div>
</body>
</html>
