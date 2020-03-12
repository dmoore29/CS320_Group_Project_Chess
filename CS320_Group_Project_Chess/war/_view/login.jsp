<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>

<head>
	<title>Login</title>
</head>

<body>
	<form action="${pageContext.servletContext.contextPath}/login" method="post">

		Login page
		<div>
			<input name="login" type="submit" value="Login" />
			<input name="newUser" type="submit" value="Register" />
		</div>
	</form>
</body>

</html>