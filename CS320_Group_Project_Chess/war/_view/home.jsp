<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>

<head>
	<title>Home Page</title>
</head>

<body>
	<form action="${pageContext.servletContext.contextPath}/home" method="post">

		This is the home page
		<div>
			<input name="profile" type="submit" value="Profile" />
			<input name="chessHome" type="submit" value="Chess" />
			<input name="friends" type="submit" value="Friends" />
		</div>
	</form>
</body>

</html>