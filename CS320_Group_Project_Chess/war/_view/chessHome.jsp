<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page import="java.util.*" %>
<%@page import="edu.ycp.cs320.Group_Project_Chess.model.*" %>
<%@page import="edu.ycp.cs320.Group_Project_Chess.controller.*" %>
<!-- java standard tag library -->

<html>
	<head>
		<title>Chess Home</title>
		<style type="text/css">
		<%@include file="/chessHome.css" %>
		</style>
	</head>
   <body>
        <header>
            <img src="images/chess_logo.png" alt="Logo">
            <section id="title-subtitle">
                <h2>Play Chess</h2>
                <h3>Challenge Millions of Players All Over the World</h3>
            </section>
        </header>
        <main>
        	<form action="${pageContext.servletContext.contextPath}/chessHome" method="post">
	            <nav id="menu">
	                <ul>
	                    <li><input name="home" type="submit" value="Home Page" /></li>
						<li><input name="profile" type="submit" value="Profile" /></li>
						<li><input name="friends" type="submit" value="Friends" /></a></li>
						<li><input name="logout" type="submit" value="Log Out" /></a></li>
	                </ul>
	            </nav>
            </form>
            <div class="gamesList">
            	<% ArrayList<Game> games = new ArrayList<Game>(); %>
            	<% Object obj = request.getAttribute("games"); %>
            	<% if (obj instanceof ArrayList<?>) {
            		ArrayList<?> al = (ArrayList<?>) obj;
            		if (al.size() > 0) {
            			for (int i=0; i<al.size(); i++) {
            				Object ob = al.get(i);
            				if (ob instanceof Game) {
            					games.add((Game) ob);
            				}
            			}
            		}
            	} %>
            	<form class="gameSelect" action="${pageContext.servletContext.contextPath}/chessHome" method="post">
	            	<ul class="opponent">
		            	<label for="fname">Challenge New Opponent:</label>
						<li><input class="opponentContent" name="opponent" type="text"</li>
						<li><input class="opponentContent" name="chessGame" type="submit" value="New Chess Game" /></li>
						<li><input class="opponentContent" name="oldChessGame" type="submit" value="Load Chess Game" /></li>
	            		<li><input class="opponentContent" name="deleteOldChessGame" type="submit" value="Delete Chess Game" /></li>
					   </ul>
					   
					<div class="table2">
						<table>
							<thead>
							<tr class="table2-head">
								<th class="col1"></th>
								<th class="col2"><b>Opponent</b></th>
								<th class="col3"><b># Of Moves</b></th>
								<th class="col4"><b>Player Turn</b></th>
							</tr>
							</thead>
							<tbody>
							<%Integer userId = (Integer)request.getAttribute("userId");%>
							<% for (Game game: games){ %>
							<% int gameId = game.getGameId(); %>
								<tr>
									<td class="col1"><input name="oldChessGameRadio" type="radio" value=<%=gameId%> />
									<td class="col2"><%=game.getPlayer2().getUser().getCredentials().getUsername() %></td>
									<td class="col3"><%=game.getTurn() %></td>
 
									<%
									int checkFlag = 0;
									int checkMateFlag = 0;
									GameController controller = new GameController();
									Game g = controller.loadGame(game.getGameId());
									controller.setGame(g);
									if(controller.check(0) == true && controller.check(0)) {
										checkFlag = 1;
										if(controller.checkmate(0)) {
											checkMateFlag = 1;
										} else {
											checkMateFlag = 0;
										}
									} else if(controller.check(1)){
										checkFlag = 1;
										if(controller.checkmate(1)) {
											checkMateFlag = 1;
										} else {
											checkMateFlag = 0;
										}
									}
									%>

									<%
									if(checkMateFlag == 0){
										if(game.getPlayer1().getColor() == game.getTurn()%2 && game.getPlayer1().getPlayerId() == userId) { //if player1's turn %>
											<td class="col4">Your Turn</td> 
											<% }  else if(game.getPlayer2().getColor() == game.getTurn()%2 && game.getPlayer2().getPlayerId() == userId){ %>
											<td class="col4">Your Turn</td> <%
										} else {
											%>
											<td class="col4">Waiting</td>
											<%
										}
									} else {
										%>
										<td class="col4">Game Over</td>
										<%
										}
									%>

								</tr>
							<% } %>
							</tbody>
						</table>
					</div>
            	</form>
            </div>
        </main>
        <aside class="velocity">
	    	<h2> VELOCITY </h2>
	        <h2> VELOCITY </h2>          
	    </aside>
	    <aside id="right_aside">
	           
	    </aside>
        <footer>
            <p>&copy; Velocity Games Inc.</p>
        </footer>
    </body>
</html>
