<%--
Created by IntelliJ IDEA.
User: Yarik
Date: 11.02.14
Time: 23:22
To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="/WEB-INF/taglib/gh.tld" prefix="gh" %>
<html>
<head>
    <title>NoteManager</title>
</head>

<body>
    <div>
        <form method="POST" action="/notemanager">
            <div>
                <span><input type="text" name="name"></span>
                <span><input type="text" name="value"></span>
                <span><input type="submit" name="action" value="add"></span>
            </div>
        </form>
    </div>

    <div>
        <c:forEach items="${notesList}" var="note">
            <div style="font-size: 200%; font-family: serif;">
                <span style="color: red; border:2px solid black;">
                    ${note.value.name}
                </span>
                <span style="color: blue; border:2px solid black;">
                    ${note.value.value}
                </span>
                <span style="color: lightseagreen; border:2px solid black;">
                    <gh:date-format date="${note.value.creationDate}"/>
                </span>
                <span style="color: orangered; border:2px solid black;">
                    <a href="notemanager?action=remove&id=${note.key}">delete</a>
                </span>
            </div>
        </c:forEach>
    </div>

</body>
</html>