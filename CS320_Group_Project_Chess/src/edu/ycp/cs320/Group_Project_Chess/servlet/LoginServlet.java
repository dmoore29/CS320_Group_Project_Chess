package edu.ycp.cs320.Group_Project_Chess.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
			System.out.println("Login Servlet: reposting login.jsp, no newUser functionality created");
			req.getRequestDispatcher("/_view/login.jsp").forward(req, resp);
		}
		
	}
}