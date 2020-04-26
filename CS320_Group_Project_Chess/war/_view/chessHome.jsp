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
	                    <li><input name="chessGame" type="submit" value="New Chess Game" /></li>
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
	            	<input name="oldChessGame" type="submit" value="Load Chess Game" />
	            	<input name="deleteOldChessGame" type="submit" value="Delete Chess Game" />
	            	<ul class="opponent">
		            	<label for="fname">Opponent:</label>
						<li><input name="opponent" type="text"</li>
		           	</ul>
	            	<table>
	            		<thead>
	            		<tr>
	            			<th></th>
	            			<th><b>Opponent</b></th>
	            			<th><b># Of Moves</b></th>
	            		</tr>
	            		</thead>
	            		<tbody>
		            	<% for (Game game: games){ %>
		            	<% int gameId = game.getGameId(); %>
		            		<tr>
		            			<td><input name="oldChessGameRadio" type="radio" value=<%=gameId%> />
		            			<td><%=game.getPlayer2().getUser().getCredentials().getUsername() %></td>
		            			<td><%=game.getTurn() %></td>
		            		</tr>
		            	<% } %>
	            		</tbody>
	            	</table>
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
