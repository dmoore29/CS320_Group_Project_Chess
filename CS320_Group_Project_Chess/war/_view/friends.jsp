<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>

<head>
	<title>Friends</title>
</head>

<body>
	<form action="${pageContext.servletContext.contextPath}/friends" method="post">

		Friends page
		<div>
			<input name="home" type="submit" value="Home Page" />
			<input name="chessHome" type="submit" value="Chess Home" />
			<input name="profile" type="submit" value="Profile" />
		</div>
	</form>
</body>

</html>