<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page import="java.util.*" %>
<%@page import="edu.ycp.cs320.Group_Project_Chess.model.*" %>
<!-- java standard tag library -->

<html>
	<head>
		<title>Home Page</title>
		<style type="text/css">
		<%@include file="/home.css" %>
		</style>
	</head>
   <body>
        <header>
            <img src="images/chess_logo.png" alt="Logo">
            <section id="title-subtitle">
                <h2>Velocity Games</h2>
                <h3>Welcome To the Future of Board Games</h3>
            </section>
        </header>
        <main>
        	<form action="${pageContext.servletContext.contextPath}/home" method="post">
	            <nav id="menu">
	                <ul>
	                    <li><input name="profile" type="submit" value="Profile" /></li>
	                    <li><input name="chessHome" type="submit" value="Chess" /></li>
	                    <li><input name="friends" type="submit" value="Friends" /></a></li>
						<li><input name="logout" type="submit" value="Log Out" /></a></li>
	                </ul>
	            </nav>
            </form>
            <div class="leaderboards">
            	<% 	ArrayList<User> topWins = new ArrayList<User>();
            		ArrayList<User> topElo = new ArrayList<User>();%>
            	<% Object obj = request.getAttribute("topWins"); %>
            	<% if (obj instanceof ArrayList<?>) {
            		ArrayList<?> al = (ArrayList<?>) obj;
            		if (al.size() > 0) {
            			for (int i=0; i<al.size(); i++) {
            				Object ob = al.get(i);
            				if (ob instanceof User) {
            					topWins.add((User) ob);
            				}
            			}
            		}
            	} %>
            	<% obj = request.getAttribute("topElo"); %>
            	<% if (obj instanceof ArrayList<?>) {
            		ArrayList<?> al = (ArrayList<?>) obj;
            		if (al.size() > 0) {
            			for (int i=0; i<al.size(); i++) {
            				Object ob = al.get(i);
            				if (ob instanceof User) {
            					topElo.add((User) ob);
            				}
            			}
            		}
            	} %>
            
            	<table class="wins">
            		<thead>
            			<tr>
            				<td class="col1"><h2>All-Time Most Wins</h2></td>
            				<td class="col2"><b>User</b></td>
            				<td class="col3"><b>Wins</b></td></tr></thead>
            		<tbody>
            			<% 	int index = 1;
            				for (User user : topWins){ %>
            			<tr>
            				<td class="col1"><%=index++ %>:</td>
            				<td class="col2"><%=user.getCredentials().getUsername() %></td>
            				<td class="col3"><%=user.getStats().getWins() %></td>
            			</tr>
            			<% } %>
            		</tbody>
            	</table>
            	<table class="elo">
            		<thead>
            			<tr>
            				<td class="col1"><h2>All-Time Highest Elo</h2></td>
            				<td class="col2"><b>User</b></td>
            				<td class="col3"><b>Elo</b></td></tr></thead>
            		<tbody>
            			<% 	index = 1;
            				for (User user : topElo){ %>
            			<tr>
            				<td class="col1"><%=index++ %>:</td>
            				<td class="col2"><%=user.getCredentials().getUsername() %></td>
            				<td class="col3"><%=user.getStats().getElo() %></td>
            			</tr>
            			<% } %>
            		</tbody>
            	</table>
            </div>
        </main>
        <aside>
	    	<h2> VELOCITY </h2>
	        <h2> VELOCITY </h2>          
	    </aside>
        <footer>
            <p>&copy; Velocity Games Inc.</p>
        </footer>
    </body>
</html>
