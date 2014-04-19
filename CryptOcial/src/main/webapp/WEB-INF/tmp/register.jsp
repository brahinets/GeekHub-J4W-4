<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>Registration</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" href="css/login.css" type="text/css">
</head>

<body>
<div id="loginForm">
    <form action="<c:url value='/register' />" method="post">

        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required>

        <label for="name">Surname:</label>
        <input type="text" id="surname" name="surname" required>

        <label for="name">Birthdate:</label>
        <input type="mDate" id="birthdate" name="birthdate" required>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required>

        <label for="email">Email:</label>
        <input type="text" id="email" name="email" required>

        <div id="lower">
            <input type="checkbox" id="checkbox" name="keep">
            <label class="check" for="checkbox">Keep me logged in</label>
            <input type="submit" value="Register">
        </div>
    </form>
</div>
</body>
</html>