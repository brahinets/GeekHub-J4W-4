<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Login Page</title>
    <link rel="stylesheet" href="<c:url value="/res/css/login.css"/>" type="text/css">
</head>

<body>
	<div id="loginForm">
		<form action="<c:url value='/login' />" method="post">
			<label for="login">Login:</label>
			<input type="text" id="login" name="login" required>
			<label for="password">Password:</label>
			<input type="password" id="password" name="password" required>
			<div id="lower">
				<input type="checkbox" id="rememberMe" name="rememberMe">
                <label class="rememberMe" for="rememberMe">Remember Me!</label>
				<input type="submit" value="Login">
			</div>
		</form>
	</div>
</body>
</html>