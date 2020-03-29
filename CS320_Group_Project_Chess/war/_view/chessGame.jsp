<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page import="edu.ycp.cs320.Group_Project_Chess.model.Game" %>

<html>

<head>
	<title>Chess Game</title>
	<style type="text/css">
	<%@include file="/chessGame.css" %>
	</style>
</head>

<body>
	<form action="${pageContext.servletContext.contextPath}/chessGame" method="post">
	<% Game model = (Game)request.getAttribute("model"); %>

		This is a chess game
		<div>
			<input name="home" type="submit" value="Home Page" />
			<input name="chessHome" type="submit" value="Chess Home" />
			<input name="profile" type="submit" value="Profile" />
			<input name="friends" type="submit" value="Friends" />
		</div>
		<table class="board">
			<% for (int y = 0; y < 8; y++ ) { %>
				<tr>
				<% for (int x = 0; x < 8; x++) { %>
					<td>
					<% if (model.getBoard().getSpace(x, y).getPiece() != null) {
						String color = null;
						if (model.getBoard().getPiece(x, y).getColor() == 1) {
							color = "Black";
						} else {
							color = "White";
						}
						
						String rank = null;
						switch(model.getBoard().getPiece(x, y).getRank()){
							case PAWN:
								rank = "Pawn";
								break;
							case ROOK:
								rank = "Rook";
								break;
							case KNIGHT:
								rank = "Horse";
								break;
							case BISHOP:
								rank = "Bishop";
								break;
							case KING:
								rank = "King";
								break;
							case QUEEN:
								rank = "Queen";
						}
						
						String source = "images/" + color + rank + ".png"; 
						System.out.print(source + "\n"); %>
						
						<img src=<%= source %> alt="images/WhitePawn.png">
						
					<% } %>					
					</td>
				<% } %>
				</tr>
			<% } %>
		
		
		
		</table>
	</form>
</body>

</html>