<%--
  Created by IntelliJ IDEA.
  User: Yarik
  Date: 21.03.14
  Time: 21:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>...Message from some...</title>
</head>
<body>
    <div id="messageReadPart">
        <div id="messageReadPhoto">
            Photo
        </div>
        <div id="messageData">
            <div id="messageReadFrom">
                ${message.sender}
            </div>
            <div id="messageReadTheme">
            ${message.theme}
            </div>
            <div id="messageReadActions">
                Delete
            </div>
            <div id="messageReadDate">
            ${message.mDate}
            </div>
            <div id="messageReadContent">
            ${message.text}
            </div>
        </div>

    </div>
</body>
</html>
