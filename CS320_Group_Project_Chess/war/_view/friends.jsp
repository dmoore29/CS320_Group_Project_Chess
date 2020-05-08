<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page import="edu.ycp.cs320.Group_Project_Chess.model.*" %>
<%@page import="java.util.*" %>
<!-- java standard tag library -->

<html>
	<head>
		<title>Friends</title>
		<style type="text/css">
		<%@include file="/friends.css" %>
		</style>
	</head>
   <body>
        <header>
            <img src="images/chess_logo.png" alt="Logo">
            <section id="title-subtitle">
                <h2>Friends List</h2>
                <h3>View All of Your Friends Here</h3>
            </section>
        </header>
        <main>
        	<form action="${pageContext.servletContext.contextPath}/friends" method="post">
	            <nav id="menu">
	                <ul>
	                    <li><input name="home" type="submit" value="Home Page" /></li>
	                    <li><input name="chessHome" type="submit" value="Chess Home" /></li>
	                    <li><input name="profile" type="submit" value="Profile" /></a></li>
						<li><input name="logout" type="submit" value="Log Out" /></a></li>
	                </ul>
	            </nav>
            </form>
            <% FriendsList friends = new FriendsList(); %>
           	<% Object obj = request.getAttribute("friendsList"); %>
           	<% if (obj instanceof FriendsList) {
           		friends = (FriendsList) obj;
           	} %>
           	<form action="${pageContext.servletContext.contextPath}/friends" method="post">
           		<div class="friendsButtons">
	        	
	        		<ul>
	        			<li><input name="username" type="text" maxlength="40" size="40" value="Enter Friend's Username" /></li>
	                    <li><input name="add" type="submit" value="Add Friend" /></li>
	                    <li><input name="view" type="submit" value="View Profile" /></li>
	                    <li><input name="challenge" type="submit" value="Challenge Friend" /></li>
	                    <li><input name="remove" type="submit" value="Remove Friend" /></li>
	                </ul>
	        	</div>
	           	<table class="friendsListing">
	           		<thead>
	           			<tr>
	           				<th><h2 class="list_header">Friends List</h2></th>
	           			</tr>
	           		</thead>
	           		<tbody>
	           			<tr>
			           		<% ArrayList<User> userList = friends.getFriendsList();
		           		   	for (User users : userList) { %>
	           				<input name="userSelection" type="radio" value="<%=users.getCredentials().getUsername()%>" /><p><%=users.getCredentials().getUsername() %></p>	           				
	           				<% } %>
	           			</tr>
	           		</tbody>
	           	</table>
		           	<ul>
	        </form>
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