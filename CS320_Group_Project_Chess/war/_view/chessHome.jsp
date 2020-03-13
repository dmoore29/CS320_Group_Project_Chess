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
                <h2>Velocity Games</h2>
                <h3>Welcome To the Future of Board Games</h3>
            </section>
        </header>
        <main>
        	<form action="${pageContext.servletContext.contextPath}/chessHome" method="post">
        		<div>
					<input name="home" type="submit" value="Home Page" />
					<input name="chessGame" type="submit" value="Play Chess" />
					<input name="profile" type="submit" value="Profile" />
				</div>
			</form>
            <nav id="menu">
                <ul>
                    <li><a href="Home.html"><strong>Home</strong></a></li>
                    <li><a href="Events&Schedule.html"><strong>Events & Schedule</strong></a></li>
                    <li><a href="Our_Systems.html"><strong>Our Games</strong></a></li>
                    <li><a href="Activities.html"><strong>Leaderboards</strong></a></li>
                    <li><a href="MOS.html">Meet Our Staff</a></li>
                    <li><a href="HOF.html">Hall of Fame</a></li>
                    <li><a href="Sponsors.html">Our Sponsors</a></li>
                    <li><a href="BAM.html"><strong>Become A Gold Member</strong></a></li>
                    <li><a href="CP.html">Corperate Policy</a></li>
                    <li><a href="FAQ.html">FAQ</a></li>
                </ul>
            </nav>
        </main>
        <aside>
            <img id="Velocity" src="images/Velocity.png" alt="Velocity">   
            <img id="Velocity2" src="images/Velocity.png" alt="Velocity">    
        </aside>
        <footer>
            <p>&copy; Velocity Games Inc.</p>
        </footer>
    </body>
</html>
