<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page import="edu.ycp.cs320.Group_Project_Chess.model.*" %>
<!-- java standard tag library -->

<html>
	<head>
		<title>Profile</title>
		<style type="text/css">
		<%@include file="/profile.css" %>
		</style>
	</head>
   <body>
        <header>
            <img src="images/chess_logo.png" alt="Logo">
            <section id="title-subtitle">
                <h2>Your Profile</h2>
                <h3>Change How the Community Views Your Page</h3>
            </section>
        </header>
        <main>
        	<form action="${pageContext.servletContext.contextPath}/profile" method="post">
	            <nav class="menu">
	                <ul>
	                    <li><input name="home" type="submit" value="Home Page" /></li>
	                    <li><input name="chessHome" type="submit" value="Chess Home" /></li>
	                    <li><input name="friends" type="submit" value="Friends" /></a></li>
						<li><input name="logout" type="submit" value="Log Out" /></a></li>
	                </ul>
	            </nav>
            </form>
            <p class="username"><b>${profile.getCredentials().getUsername()}</b></p>
            	<% User profile = new User(); %>
            	<% Object obj = request.getAttribute("profile"); %>
            	<% if (obj instanceof User) {
            		profile = (User) obj;
            	} %>
            <div class="rightSide">
	            <aside class="profilePicture">
	            	<% String rank = null; %>
		    		<% switch(profile.getProfile().getPictureNumber()){
								case 1:
									rank = "Pawn";
									break;
								case 2:
									rank = "Rook";
									break;
								case 3:
									rank = "Horse";
									break;
								case 4:
									rank = "Bishop";
									break;
								case 5:
									rank = "King";
									break;
								case 6:
									rank = "Queen";
					} %>
					<% String color = "White"; %>
					<% String source = "images/" + color + rank + ".png"; %>
					<img src=<%= source %> alt=" images/WhitePawn.png">
		    	</aside>
		    	<aside class="playerStats">
			    	<h2> ELO </h2>
			    	<p class="elo">${profile.getStats().getElo()}</p>
			    	<h2> WINS </h2>
			    	<p class="wins">${profile.getStats().getWins()}</p>
			    	<h2> LOSSES </h2>
			    	<p class="losses">${profile.getStats().getLosses()}</p>
		    	</aside>
		    </div>
            <% Integer bioFlag = (Integer) request.getAttribute("editBioFlag");
           	   Integer picFlag = (Integer) request.getAttribute("editPicFlag"); %>
           	<% if (bioFlag != null || picFlag != null){ %>
           		<% if (bioFlag != null){ %>
           		<p> Enter Bio Here: </p>
            	<input name="bioField" type="text" maxlength="100" size="30" value="${profile.getProfile().getBio()}" />
           		<% } else if (picFlag != null){ %>
           		<p> Pick Profile Picture Here: </p>
           		<table>
           			<tr>
           				<td><img src="images/WhitePawn.png"></td>
           				<td><img src="images/WhiteHorse.png"></td>
           			</tr>
           			<tr>
           				<td><img src="images/WhiteBishop.png"></td>
           				<td><img src="images/WhiteRook.png"></td>
           			</tr>
           			<tr>
           				<td><img src="images/WhiteQueen.png"></td>
           				<td><img src="images/WhiteKing.png"></td>
           			</tr>
           			<tr>
           				<td><img src="images/Cheese.jpg"></td>
           				<td><img src="images/chess_logo.png"></td>
           			</tr>
           		</table>
           		<% } %>
           	<% } else { %>
           	<p> About Me: </p>
            <p class="bio">${profile.getProfile().getBio()}</p>
           	<form action="${pageContext.servletContext.contextPath}/profile" method="post">
	            <div class="menu">
	            	<ul>
	            		<li><input name="editBio" type="submit" value="Edit Your Bio" /></li>
	            		<li><input name="editPic" type="submit" value="Edit Your Picture" /></li>
	            	</ul>    
	            </div>
	        </form>
            <% } %>
        </main>
        <aside class="websiteName">
	    	<h2> VELOCITY </h2>
	        <h2> VELOCITY </h2>          
	    </aside>
        <footer>
            <p>&copy; Velocity Games Inc.</p>
        </footer>
    </body>
</html>