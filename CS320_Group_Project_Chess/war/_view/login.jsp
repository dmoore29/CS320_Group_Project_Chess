<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>

<head>
	<title>Login</title>
	<style type="text/css">
	<%@include file="/login.css" %>
	</style>
</head>

<body>
	<c:if test="${! empty errorMessage}">
    	<div class="error">${errorMessage}</div>
    </c:if>

	<form action="${pageContext.servletContext.contextPath}/login" method="post">

		Login page
		<div>
			<label for="username">Username:	</label>
			<input name="username" type="text" value="${username}"   />
			<label for="password">Password:	</label>
			<input name="password" type="password" value="" /><br>
			<input name="login" type="submit" value="Login" /><br>
			Don't have an account?	Add an Email Address!<br>
			<label for="email">Email:	</label>
			<input name="email" type="email" value='${email}'/><br>
			<input name="newUser" type="submit" value="Register" />
		</div>
	</form>
</body>

</html>