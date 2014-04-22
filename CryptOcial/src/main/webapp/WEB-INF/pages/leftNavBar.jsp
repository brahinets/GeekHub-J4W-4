<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title></title>
</head>
<body>
    <div class="col-md-2 leftNavBar">
        <div class="padding">
            <ul class="list-unstyled in ">
                <li><a href="<c:url value="/home"/>"><span class="typicons-home nav-icon"></span>Home</a></li>
                <li><a href="<c:url value="/friends"/>"><span class="entypo-users nav-icon"></span>Friends</a></li>
                <li><a href="<c:url value="/photo"/>"><span class="fontawesome-camera nav-icon"></span>Photo</a></li>
                <li>
                    <a href="<c:url value="/mail"/>"><span class="typicons-mail nav-icon"></span>Mail</a>
                    <c:choose>
                        <c:when test="${loggedUser.unreadInputMail > 0}">
                            <span class="badge count pull-right">${loggedUser.unreadInputMail}</span>
                        </c:when>
                    </c:choose>
                </li>
                <li><a href="<c:url value="/groups"/>"><span class="fontawesome-group nav-icon"></span>Groups</a></li>
                <hr>
                <li><a href="<c:url value="/settings"/>"><span class="fontawesome-cogs nav-icon"></span>Settings</a></li>
            </ul>
        </div>
    </div>
</body>
</html>
