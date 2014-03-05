<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Films</title>
</head>
<body>

<div>
    <div>Films</div><br>
    <div><a href="/actor">Actors list</a></div><br>
    <div><a href="/film/add">Add Film</a></div><br>
</div>

<table>
    <tr>
        <td>
            Film Name
        </td>
        <td>
            Year
        </td>
        <td>
            # of actors
        </td>
        <td colspan="2">
            Actions
        </td>
    </tr>
    <c:forEach items="${filmsList}" var="film">
        <tr>
            <td>
                ${film.name}
            </td>
            <td>
                ${film.year}
            </td>
            <td>
                ${fn:length(film.actors)}
            </td>
            <td>
                <a href="film/edit/${film.id}"><img src="img/edit.png" width="20px" height="20px"/></a>
            </td>
            <td>
                <a href="film/delete/${film.id}"><img src="img/delete.png"  width="20px" height="20px"/></a>
            </td>
        <tr>
    </c:forEach>
</table>
</body>
</html>
