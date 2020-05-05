<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page import="java.util.*" %>
<%@page import="edu.ycp.cs320.Group_Project_Chess.model.*" %>
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
							<% for (Game game: games){ %>
							<% int gameId = game.getGameId(); %>
								<tr>
									<td class="col1"><input name="oldChessGameRadio" type="radio" value=<%=gameId%> />
									<td class="col2"><%=game.getPlayer2().getUser().getCredentials().getUsername() %></td>
									<td class="col3"><%=game.getTurn() %></td>
									<%if(game.getPlayer1().getColor() == game.getTurn()%2) { //if player1's turn
										%><td class="col4">Your Turn</td> <%
									}  else {
										%>
										<td class="col4">Waiting</td>
										<%
									}%>
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
