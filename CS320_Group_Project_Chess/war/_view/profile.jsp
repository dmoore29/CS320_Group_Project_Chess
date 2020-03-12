<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>

<head>
	<title>Profile</title>
</head>

<body>
	<form action="${pageContext.servletContext.contextPath}/profile" method="post">

		User Profile Page
		<div>
			<input name="home" type="submit" value="Home Page" />
			<input name="chessHome" type="submit" value="Chess Home" />
			<input name="friends" type="submit" value="Friends" />
		</div>
	</form>
</body>

</html>