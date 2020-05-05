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
           	
           	<ul>
           		<% ArrayList<User> userList = friends.getFriendsList();
           		   	for (User users : userList) { %>
           				<li><p><%=users.getCredentials().getUsername() %></p></li>	
           			<% } %>
            </ul> 
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