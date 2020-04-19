<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
	            <nav id="menu">
	                <ul>
	                    <li><input name="home" type="submit" value="Home Page" /></li>
	                    <li><input name="chessHome" type="submit" value="Chess Home" /></li>
	                    <li><input name="friends" type="submit" value="Friends" /></a></li>
	                </ul>
	            </nav>
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