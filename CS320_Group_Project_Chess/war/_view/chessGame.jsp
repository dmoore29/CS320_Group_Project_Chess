<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page import="edu.ycp.cs320.Group_Project_Chess.model.*" %>

<html>

<head>
	<title>Chess Game</title>
	<style type="text/css">
		<%@include file="/chessGame.css"%>
	</style>

	<script>
		function promotionCallToServlet(r){
			console.log("RECIEVED SCRIPT");
			document.promotionForm.action = "${pageContext.servletContext.contextPath}/chessGame";
			document.promotionForm.rank.value = r;
			document.promotionForm.submit();
		}

		function transferCallToServlet(x, y) {
			document.requestForm.action = "${pageContext.servletContext.contextPath}/chessGame";
			document.requestForm.x1.value = x;
			document.requestForm.y1.value = y;
			document.requestForm.submit();
		}

	</script>

</head>

<body>
	<form action="${pageContext.servletContext.contextPath}/chessGame" method="post">
		<% Game model = (Game)request.getAttribute("model"); %>
		<%Integer sourceX = (Integer)request.getAttribute("pos1x");%>
		<%Integer sourceY = (Integer)request.getAttribute("pos1y");%>

		<div class='turn'>
			Chess Game
		</div>
		<div>
			<input name="home" type="submit" value="Home Page" />
			<input name="chessHome" type="submit" value="Chess Home" />
			<input name="profile" type="submit" value="Profile" />
			<input name="friends" type="submit" value="Friends" />
		</div>
	</form>

	<form name="requestForm" method="post">
		<input type="hidden" name="x1">
		<input type="hidden" name="y1">
		<div class='space'>
			<div class='turn'>
				Turn: <%=model.getTurn() %>
			</div>
			<div class='player'>
				Player: <%=model.getTurn()%2 +1 %>
			</div>
			<table class="board">
				<% for (int y = 0; y < 8; y++ ) { %>
				<tr>
					<% for (int x = 0; x < 8; x++) { %>
						<%if(sourceX != null && sourceX == x && sourceY == y){ %>
							<td onclick="transferCallToServlet(<%=x%> , <%=y%>)" class="selected">
						<%} else { %>
							<td onclick="transferCallToServlet(<%=x%> , <%=y%>)" class="test<%=(x+y)%2 %>">
						<% } %>
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
						
						String source = "images/" + color + rank + ".png";  %>
						<img src=<%= source %> alt=" images/WhitePawn.png">
						<% } %>
					</td>
					<% } %>
				</tr>
				<% } %>
			</table>
		</div>
	</form>
	<form name="promotionForm" method="post">
		<input type="hidden" name="rank">
		<div class="promotion">
			<%Integer promotionFlag = (Integer)request.getAttribute("promotionFlag");%>
			<%if(promotionFlag != null){ %>
			<%if(promotionFlag == 1){ %>
			Select a piece:
			<ul>
				<li onclick="promotionCallToServlet('Queen')" class="promotionOption">Queen</li>
				<li onclick="promotionCallToServlet('Rook')" class="promotionOption">Rook</li>
				<li onclick="promotionCallToServlet('Knight')" class="promotionOption">Knight</li>
				<li onclick="promotionCallToServlet('Bishop')" class="promotionOption">Bishop</li>
			</ul>
			<%}%>
			<%}%>
		</div>
		</input>
</body>

</html>