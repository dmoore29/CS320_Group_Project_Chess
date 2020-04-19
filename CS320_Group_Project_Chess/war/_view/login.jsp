<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!-- java standard tag library -->

<html>
	<head>
		<title>Login</title>
		<style type="text/css">
		<%@include file="/login.css" %>
		</style>
	</head>
   <body>
        <header>
            <img src="images/chess_logo.png" alt="Logo">
            <section id="title-subtitle">
                <h2>Login</h2>
                <h3>Enter Your Username and Password</h3>
            </section>
        </header>
	<c:if test="${! empty errorMessage}">
    	<div class="error">${errorMessage}</div>
    </c:if>

	<form action="${pageContext.servletContext.contextPath}/login" method="post">
		<div>
			<nav id="menu">
				<ul>
					<li><label for="username">Username:	</label></li>
					<li><input name="username" type="text" value="${username}"   /></li>
					<li><input id="login_button" name="login" type="submit" value="Login" /><br></li>
					<li><label for="password">Password:	</label></li>
					<li><input name="password" type="password" value="" /><br></li>
					<p>Don't have an account?	Add an Email Address!<br></p>
					<li><label for="email">Email:	</label></li>
					<li><input name="email" type="email" value='${email}'/><br></li>
					<li><input name="newUser" type="submit" value="Register" /></li>
				</ul>
			</nav>
		</div>
	</form>
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
