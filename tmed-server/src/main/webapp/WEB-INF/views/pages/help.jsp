<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Help</title>
</head>

<body >
<div class="well lead">Help</div>
<div  style="height:80%; overflow:auto">
    <div class="text-colour">
        <div class="generic-container col-xs-12">
            <h2>Step 1</h2>
            <p>
                Register your personal account.
            </p>
            <h2>Step 2</h2>
            <p>
                Fill your vocabulary.
            </p>
            <h2>Step 3</h2>
            <p>
                Go to download page to download application, specified to your operation system.
            </p>
            <h2>Step 4</h2>
            <p>
                Install TranslateMe Dude application.
            </p>
            <h2>Step 5</h2>
            <p>
                Run TranslateMe Dude application.
            </p>
            </div>

            <div class="generic-container col-xs-12">
                <h2>Step 6</h2>
                <p>
                    Go to the settings by right click on tray icon -> Settings.
                    In URL field input host path and your personal account data below. Click Apply button. Click Test Connection button.
                    If connection has success status close settings dialog. Also you can set reminder timer for popup balloon.
                    There we have a two modes. First it’s random mode.  In this mode, popup balloon will be appeared by random time.
                    Second mode provide the balloon appearing by constant time. The value sets in minutes.
                </p>
            </div>
            <div class="generic-container col-xs-12">
                <img src="${pageContext.request.contextPath}/static/img/tray_exmpl.png" class="leftimg">
                <img src="${pageContext.request.contextPath}/static/img/popup_settings.png" class="leftimg">
            </div>

            <div class="generic-container col-xs-12">
                <h2>Step 7</h2>
                <p>
                    Synchronize your vocabulary with personal remote account. For this action click right click on tray icon and chose Update Vocabulary
                </p>
            </div>

            <div class="generic-container col-xs-12">
                <h2>Step 8</h2>
                <p>By double click, or by timer the popup lite balloon will be appeared.
                    Input the translation of the word that displayed on the balloon. Click Translate button.
                    After that the translation status and examples of using were on the popup balloon.
                </p>
            </div>
            <div class="generic-container col-xs-12">
                <img src="${pageContext.request.contextPath}/static/img/popup_lite.png" class="leftimg">
                <img src="${pageContext.request.contextPath}/static/img/popup_full.png" class="leftimg">
            </div>
    </div>
</div>
</body>
</html>
