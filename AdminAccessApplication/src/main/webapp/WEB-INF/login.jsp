<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
${error}
	${SPRING_SECURITY_LAST_EXCEPTION.message}
	<form method="POST" action="login">
		<input type="text" name="user">
		<input type="password" name="pass">
		<input type="submit" name="submit">
	</form>

	<p>Not a member ? Click <a href="/register">Here</a> to register</p>
</body>
</html>