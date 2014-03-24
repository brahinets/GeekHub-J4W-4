<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Add actor</title>
    <script src="js/jquery-2.1.0.min.js"></script>
    <script type="text/javascript">
        $("#submit").click(function(){
            window.close();
            window.open("/actor");
        });
    </script>

</head>
<body>
<div>
    <form action="/actor" method="post">
        <input type="number" name="id" value="${actor.id}" hidden>

        <label for="firstName">First name:</label>
        <input type="text" id="firstName" name="firstName" value="${actor.firstName}" required><br>

        <label for="secondName">Second name:</label>
        <input type="text" id="secondName" name="secondName" value="${actor.secondName}" required><br>

        <label for="birthDate">Birth date:</label>
        <input id="birthDate" type="date" name="birthDate" value="<fmt:formatDate type="date" value="${actor.birthDate}" pattern="yyyy-MM-dd" />" required=""><br>

        <label for="films">Films:</label>
        <select id="films" name="films" size="5" MULTIPLE>
            <c:forEach items="${filmsList}" var="film">
                <c:set var="exists" value="false"/>
                <c:forEach items="${actor.films}" var="actorFilm">
                    <c:if test="${film.id eq actorFilm.id}">
                        <c:set var="exists" value="true"/>
                    </c:if>
                </c:forEach >

                <c:choose>
                    <c:when test="${exists eq true}">
                        <option value="${film.id}" selected>${film.name}</option>
                    </c:when>
                    <c:otherwise>
                        <option value="${film.id}" >${film.name}</option>
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </select><br>

        <input type="submit" id="submit" value="Save">
    </form>
</div>
</body>
</html>
