package edu.ycp.cs320.Group_Project_Chess.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.Group_Project_Chess.model.Credentials;
import edu.ycp.cs320.Group_Project_Chess.model.FriendsList;
import edu.ycp.cs320.Group_Project_Chess.model.Profile;
import edu.ycp.cs320.Group_Project_Chess.model.Stats;
import edu.ycp.cs320.Group_Project_Chess.model.User;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
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
		
		
		if (req.getParameter("login") != null) {
			System.out.println("Login Servlet: forwarding to home");
			req.getRequestDispatcher("/_view/home.jsp").forward(req, resp);
		}
		
		if (req.getParameter("newUser") != null) {
			
			User user = null;
			Credentials credentials;
			
			String errorMessage = null;
			String destination = null;
				
			String username = req.getParameter("username");
			String password = req.getParameter("password");
			String email = req.getParameter("email");
				
			if (username.isEmpty() || password.isEmpty() || email.isEmpty()) {
				errorMessage = "Please enter a Username, Password, and Email Address";
				destination = "/_view/login.jsp";
			} else {
				destination = "/_view/home.jsp";
				credentials = new Credentials(email, username, password);
				user = new User(credentials, new Stats(), new FriendsList(), new Profile());
			}
			
			req.setAttribute("errorMessage", errorMessage);
			req.setAttribute("user", user);
			req.setAttribute("username", username);
			req.setAttribute("email", email);
						
			System.out.println("Login Servlet: reposting login.jsp");
			req.getRequestDispatcher(destination).forward(req, resp);
		}
		
	}
}
