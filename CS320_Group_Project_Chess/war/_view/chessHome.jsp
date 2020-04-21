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
	                    <li><input name="chessGame" type="submit" value="Play Chess" /></li>
	                    <li><input name="profile" type="submit" value="Profile" /></li>
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
            	
            	<table class="gamesList">
	            	<% for (Game game: games){ %>
	            		<tr>
	            			<td><%=game.getPlayer2().getUser().getCredentials().getUsername() %></td>
	            			<td><%=game.getTurn() %></td>
	            		</tr>
	            	<% } %>
            
            	</table>
            </div>
        </main>
        <aside>
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
