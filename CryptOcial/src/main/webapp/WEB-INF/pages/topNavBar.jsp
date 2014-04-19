<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<nav class="navbar navbar-default col-md-11" role="navigation">
    <div class="navbar-header col-md-2">
        <a class="navbar-brand" href="#"><span class="entypo-key icon-left"></span>CryptOcial</a>
    </div>
    <div>
        <form role="search" class="navbar-form navbar-left">
            <div class="form-group left">
                <input type="text" placeholder="Search people" class="form-control"/>
                <button type="submit" class="btn btn-default"><span class="entypo-search icon-right"></span></button>
            </div>
        </form>

        <div id="navbarCollapse" class="collapse navbar-collapse">
            <ul class="nav navbar-nav navbar-right">
                <li class="active"><a href="<c:url value="/home"/>"><span class="fontawesome-home icon-left"></span>Home</a></li>
                <li><a href="<c:url value="/people"/>"><span class="fontawesome-group icon-left"></span>People</a></li>
                <li><a href="<c:url value="/settings"/>"><span class="entypo-cog icon-left"></span>Settings</a></li>
                <li><a href="<c:url value="/help"/>"><span class="entypo-help icon-left"></span>Help</a></li>
                <li><a href="<c:url value="/logout"/>"><span class="maki-pitch icon-left"></span>Logout</a></li>
            </ul>
        </div>
    </div>
</nav>
</body>
</html>
