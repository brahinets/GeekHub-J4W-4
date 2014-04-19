<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>Search Friends</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/global.css" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/people.css" type="text/css">
</head>

<body>

    <jsp:include page="../pages/header.jsp" />
    <jsp:include page="topNavbar.jsp" />

    Count = ${fn:length(peopleList)}

    <div id="content" class="clearfix">
        <div><input type="text" style="width:100%;"/></div>
        <section id="left">
            <c:forEach items="${peopleList}" var="people">
                <div class="rowInList">
                    <div class="photo">
                        <a href="/user/${people.id}">${people.id}</a>
                    </div>
                    <div class="info" >
                            ${people.name} ${people.surname}
                    </div>
                    <div class="actions">

                    </div>
                </div>

            </c:forEach>
        </section>

        <section id="right">
            <div class="gcontent">
                <div class="head">
                    <span>criteria</span>
                </div>

                <div class="boxy">
                    <div class="friendslist clearfix">

                        criteria1

                    </div>
                </div>
            </div>

            <div class="gcontent">
                <div class="head">
                    <span>criteria2</span>
                </div>

                <div class="boxy">
                    <div class="friendslist clearfix">

                        criteria2

                    </div>
                </div>
            </div>
            <div class="gcontent">
                <div class="head">
                    Criteries
                </div>

                <div class="boxy">
                    <div class="friendslist clearfix">

                        criteria2

                    </div>
                </div>
            </div>

        </section>
    </div>
</body>
</html>