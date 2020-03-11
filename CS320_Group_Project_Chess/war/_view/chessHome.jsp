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
            <h1 id="Title1">Get Up and Go</h1>
            <p>The list of activities we offer are vast but here are our most popular:</p>
            <ul id="Activities">
                <li>Bike rentals</li>
                <li>Mini golf</li>
                <li>Trampoline park</li>
                <li>Basketball</li>
                <li>Ski and board rentals</li>
            </ul>
            <p>Each and every day we have schedule events with specialized trainers, see todays schedule below. (Schedule updated daily!)</p>
            <table>
                    <thead>
                        <tr>
                            <th id="hdr-Name" scope="col">Trainer</th>
                            <th id="hdr-Game" scope="col">Activity</th>
                            <th id="hdr-Time" scope="col">Time</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                        <td headers="hdr-Name">Seth Howard</td>
                        <td headers="hdr-Game">Biking</td>
                        <td headers="hdr-Time">3:00 PM</td>
                    </tr>
                    <tr>
                        <td headers="hdr-Name">Kelly Smith</td>
                        <td headers="hdr-Game">Fencing (In gym)</td>
                        <td headers="hdr-Time">5:30 PM</td>
                    </tr>
                    <tr>
                        <td headers="hdr-Name">Andrew Hopkins</td>
                        <td headers="hdr-Game">Basketball</td>
                         <td headers="hdr-Time">7:00 PM</td>
                    </tr>
                </tbody>
            </table>
            <h2 id="Title2">Meet Our Staff</h2>
            <section id="Names">        
            <h2>Seth Howard</h2> 
            <h2>Kelly Smith</h2> 
            <h2 id="Hopkins">Andrew Hopkins</h2>
            </section>
            <img id="Seth" src="images/Seth_Howard.jpg" alt="Seth">
            <img id="Kelly" src="images/Kelly_Smith.jpeg" alt="Kelly">
            <img id="Andrew" src="images/Andrew_Hopkins.jpg" alt="Andrew">
            <section id="Bios">
                <div>
                    <span>Seth is a very accomplished cyclist, he has completed 3 Iron-Man races and competed in the New England Cycling Grand Pris in 2012. For the past 3 years he has been our cycling specialist here at Velocity Hotel!</span>
                </div>
                <div>
                    <span>Kelly Has competed in fencing professionally for 4 years and competed in the junior olympics when she was 16, Kelly has been wit us for the past 2 years and has been knows to bring our peoples love for the sport of fencing.</span>
                </div>
                <div>
                    <span>Andrew played basketball at the division 1 level at the University of Pittsburgh, he played in the D-league in the NBA and has been wit us for the past 8 months. Andrew's class has been known to be one of the most physical here at the hotel so be ready!</span>
                </div>
            </section>
        </main>
        <aside>
            <img id="Velocity" src="images/Velocity.png" alt="Velocity">   
            <img id="Velocity2" src="images/Velocity.png" alt="Velocity">    
        </aside>
        <footer>
            <p>&copy; Velocity Gaming Hotel Inc.</p>
        </footer>
    </body>
</html>
