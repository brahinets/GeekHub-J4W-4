<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>...Number of messages...</title>
    <link rel="stylesheet" href="<c:url value="/res/css/bootstrap.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/res/css/bootstrap-theme.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/res/css/bootstrap-theme.min.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/res/css/bootstrap.min.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/res/css/global.css"/>" type="text/css">
    <link rel="stylesheet" href="<c:url value="/res/css/user.css"/>" type="text/css">
    <script src="<c:url value="/res/js/jquery-2.1.0.min.js"/>"></script>
    <script src="<c:url value="/res/js/dragAndDrop.js"/>"></script>
</head>

<body onload="test.init()">
<div class="container">

    <!-- top nav bar start -->
    <jsp:include page="topNavBar.jsp"/>
    <!-- top nav bar end -->

    <!-- body start -->
    <div class="row">
        <!-- left nav bar start -->
        <jsp:include page="leftNavBar.jsp"/>
        <!-- left nav bar end -->

        <div class="col-md-8">
            <form class="form-horizontal" action="<c:url value="/settings"/>" method="post" enctype="multipart/form-data">
                <div class="form-group ">
                    <textarea style="display: none;" name="avatar" id="avatar"></textarea>
                    <div class="col-sm-4 col-md-offset-1 control-label">
                        <c:choose>
                            <c:when test="${user.avatar != null}">
                                <img src="data:image/png;base64,${user.avatar}" style="width: 100%; height: 300px;"/>
                            </c:when>
                            <c:otherwise>
                                <img src="<c:url value="/res/img/noAvatar.png"/>" style="width: 100%; height: 300px;"/>
                            </c:otherwise>
                        </c:choose>
                    </div>

                    <div class="col-sm-3">
                        <div id="avatarBlock" contenteditable="true">
                            <div class="form-control" style="border: solid 2px #123341; margin-top:50px; height: 200px;">Drop here</div>
                        </div>
                    </div>

                    <div class="col-sm-4 control-label">
                        <img src="" style="width: 100%; height: 300px; display: none;" id="imagePreview" />
                    </div>
                </div>

                <div class="form-group">
                    <label for="name" class="col-sm-2 control-label">Name</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="name" name="name" placeholder="Name" value="${user.name}">
                    </div>
                </div>

                <div class="form-group">
                    <label for="surname" class="col-sm-2 control-label">Surname</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="surname" name="surname" placeholder="Surname" value="${user.surname}">
                    </div>
                </div>

                <div class="form-group">
                    <label for="surname" class="col-sm-2 control-label">Email</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="email" name="email" placeholder="Email" value="${user.email}">
                    </div>
                </div>

                <div class="form-group">
                    <label for="surname" class="col-sm-2 control-label">Password</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="password" name="password" placeholder="Password" value="${user.password}">
                    </div>
                </div>

                <div class="form-group">
                    <label for="birthdate" class="col-sm-2 control-label">Birthdate</label>
                    <div class="col-sm-10">
                        <input type="date" class="form-control" id="birthDate" name="birthDate" value="${user.birthdate}">
                    </div>
                </div>

                <%--<div class="form-group">--%>
                    <%--<label for="gender" class="col-sm-2 control-label">Gender</label>--%>
                    <%--<div class="col-sm-10">--%>
                        <%--<select name="gender" id="gender">--%>
                        <%--<c:forEach items="genderList" var="gender">--%>
                            <%--<c:choose>--%>
                                <%--<c:when test="${gender == user.gender}">--%>
                                    <%--<option value="${gender}" selected>${gender}</option>--%>
                                <%--</c:when>--%>
                                <%--<c:otherwise>--%>
                                    <%--<option value="${gender}">${gender}</option>--%>
                                <%--</c:otherwise>--%>
                            <%--</c:choose>--%>
                        <%--</c:forEach>--%>
                        <%--</select>--%>
                    <%--</div>--%>
                <%--</div>--%>



                <hr>

                <div class="col-sm-6 col-md-offset-3">
                    <p class="center">
                        <button type="submit" class="btn btn-primary btn-lg btn-block">Save</button>
                    </p>
                </div>
            </form>
        </div>
    </div>
    <!-- body end -->
</div>
</body>
</html>