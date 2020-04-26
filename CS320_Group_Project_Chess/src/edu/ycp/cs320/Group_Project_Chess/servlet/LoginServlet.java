package edu.ycp.cs320.Group_Project_Chess.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.Group_Project_Chess.controller.LoginController;
import edu.ycp.cs320.Group_Project_Chess.model.Credentials;
import edu.ycp.cs320.Group_Project_Chess.model.User;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	LoginController controller = null;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Login Servlet: doGet");
		
		req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("Login Servlet: doPost");
		
		boolean needsEmail = true;
		boolean testLogin = false;
		
		if (req.getParameter("login") != null) {
			needsEmail = false;			
		}
		
		if (req.getParameter("newUser") != null) {
			needsEmail = true;
		}
		
		User user = null;
		Credentials credentials;
		
		String errorMessage = null;
		String destination = null;
		
		controller = new LoginController();
			
		String username = req.getParameter("username");
		String password = req.getParameter("password");
		String email = req.getParameter("email");
			
		if (needsEmail) {
			if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
				errorMessage = "Please enter a Username, Password, and Email Address";
				destination = "/_view/login.jsp";
			} else if (controller.existingUsername(username)){
				errorMessage = "Username already taken";
				destination = "/_view/login.jsp";
			} else if (controller.existingEmail(email)) {
				errorMessage = "Email already registered to an account";
				destination = "/_view/login.jsp";
			} else {
				try {
				controller.registerNewUser(new Credentials(email, username, password));
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
				testLogin = true;
				}
			}
		} else {
			if (username.isEmpty() || password.isEmpty()) {
				errorMessage = "Please enter a Username and Password";
				destination = "/_view/login.jsp";
			} else {
				testLogin = true;
			}
		}
		
		if (testLogin) {
			credentials = new Credentials(email, username, password);
			if (controller.validLogin(credentials)) {
				destination = "/_view/home.jsp";
			} else {
				errorMessage = "Invalid Login";
				destination = "/_view/login.jsp";
			}
		}
		
		req.setAttribute("errorMessage", errorMessage);
		req.setAttribute("username", username);
		req.setAttribute("email", email);
		
		req.getSession().setAttribute("name", username);
					
		System.out.println("Login Servlet: reposting login.jsp");
		req.getRequestDispatcher(destination).forward(req, resp);
		
	}
}
