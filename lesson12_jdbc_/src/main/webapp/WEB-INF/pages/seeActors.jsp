<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Actors</title>
</head>
<body>

<div>
    <div>Actors</div><br>
    <div><a href="/actor/add">Add Actor</a></div><br>
    <div><a href="/film">Films list</a></div><br>
</div>

<table>
    <tr allign="center">
        <td>
            First Name
        </td>
        <td>
            Second name
        </td>
        <td>
            Birth Date
        </td>
        <td>
            # of films
        </td>
        <td colspan="2">
            Actions
        </td>
    </tr>

    <c:forEach items="${actorsList}" var="actor">
        <tr allign="center">
            <td>
                ${actor.firstName}
            </td>
            <td>
                ${actor.secondName}
            </td>
            <td>
                <fmt:formatDate type="date" value="${actor.birthDate}" pattern="dd/MM/yyyy" />
            </td>
            <td>
                ${actor.filmsCount}
            </td>
            <td>
                <a href="actor/edit/${actor.id}"><img src="img/edit.png" width="20px" height="20px"/></a>
            </td>
            <td>
                <a href="actor/delete/${actor.id}"><img src="img/delete.png"  width="20px" height="20px"/></a>
            </td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
