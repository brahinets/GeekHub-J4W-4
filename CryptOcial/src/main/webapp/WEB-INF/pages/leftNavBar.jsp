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
                <li><a href="<c:url value="/home"/>">Home</a></li>
                <li><a href="<c:url value="/friends"/>">Friends</a></li>
                <li><a href="<c:url value="/photo"/>">Photo</a></li>
                <li>
                    <a href="<c:url value="/mail"/>">Mail</a>
                    <c:choose>
                        <c:when test="${user.unreadInputMail > 0}">
                            <span class="badge count pull-right">4</span>
                        </c:when>
                    </c:choose>:
                </li>
                <li><a href="<c:url value="/groups"/>">Groups</a></li>
                <hr>
                <li><a href="<c:url value="/settings"/>">Settings</a></li>
            </ul>
        </div>
    </div>
</body>
</html>
