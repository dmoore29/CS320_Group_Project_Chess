<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>

<head>
	<title>Chess Game</title>
</head>

<body>
	<form action="${pageContext.servletContext.contextPath}/chessGame" method="post">

		This is a chess game
		<div>
			<input name="chessHome" type="submit" value="Chess Home" />
			<input name="profile" type="submit" value="Profile" />
			<input name="friends" type="submit" value="Friends" />
		</div>
	</form>
</body>

</html>