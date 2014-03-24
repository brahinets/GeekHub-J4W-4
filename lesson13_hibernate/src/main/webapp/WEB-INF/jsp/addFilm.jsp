<%--
  Created by IntelliJ IDEA.
  User: Yarik
  Date: 01.03.14
  Time: 10:31
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add movie</title>
</head>
<body>
    <div id="addForm">
        <form action="/film" method="post">
            <input type="number" name="id" value="${film.filmId}" hidden><br>

            <label for="name">Film name:</label>
            <input type="text" id="name" name="name" value="${film.name}" required><br>

            <label for="year">year:</label>
            <input type="number" id="year" name="year" value="${film.year}" required><br>

            <div id="lower">
                <input type="submit" value="Save">
            </div>
        </form>
    </div>
</body>
</html>
