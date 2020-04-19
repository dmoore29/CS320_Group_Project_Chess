<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
	                    <li><input name="profile" type="submit" value="Profile" /></a></li>
	                </ul>
	            </nav>
            </form>
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
