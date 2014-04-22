<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <title>Settings</title>
    <script src="<c:url value="/res/js/avatarLoader.js"/>"></script>
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
                    <textarea style="display: none;" name="avatar" id="avatar">${user.avatar}</textarea>
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
                    <label for="birthDate" class="col-sm-2 control-label">Birthdate</label>
                    <div class="col-sm-10">
                        <input type="date" class="form-control" id="birthDate" name="birthDate" value="${user.birthDate}">
                    </div>
                </div>

                <div class="form-group">
                    <label for="whereFrom" class="col-sm-2 control-label">From</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="whereFrom" name="whereFrom" placeholder="From" value="${user.whereFrom}">
                    </div>
                </div>

               <%-- <div class="form-group">
                    <label for="gender" class="col-sm-2 control-label">Gender</label>
                    <div class="col-sm-10">
                        <select name="gender" id="gender" class="form-control">
                        <c:forEach items="${genderList}" var="gender">
                            <c:choose>
                                <c:when test="${gender.id == user.gender.id}">
                                    <option value="${gender.id}" selected>${gender.name}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${gender.id}">${gender.name}</option>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                        </select>
                    </div>
                </div>
--%>
                <div class="form-group">
                    <label for="gender" class="col-sm-2 control-label">Gender</label>
                    <div class="col-sm-10">
                        <select name="gender" id="gender" class="form-control">
                            <c:choose>
                                <c:when test="${user.gender}">
                                    <option value="1" selected>Male</option>
                                    <option value="0">Female</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="1">Male</option>
                                    <option value="0" selected>Female</option>
                                </c:otherwise>
                            </c:choose>
                        </select>
                    </div>
                </div>

                <hr>

                <div class="col-sm-6 col-md-offset-3">
                    <p class="center">
                        <button type="submit" class="btn btn-primary btn-lg btn-block">Save</button>
                    </p>
                </div>

            </form>

            <a class="btn full-width" href="/user/delete/${user.id}">
                <button class="full-width blueButton" type="submit">
                    <span class="buttonText">Unregister</span>
                </button>
            </a>

        </div>
    </div>
    <!-- body end -->
</div>
</body>
</html>