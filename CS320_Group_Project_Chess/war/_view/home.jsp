<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
