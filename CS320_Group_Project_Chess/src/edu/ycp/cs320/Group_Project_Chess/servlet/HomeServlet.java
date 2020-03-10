package edu.ycp.cs320.Group_Project_Chess.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Index Servlet: doGet");
		
		req.getRequestDispatcher("/_view/home.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("Home Servlet: doPost");
		
		if (req.getParameter("profile") != null) {
			System.out.println("Home Servlet: forwarding to profile");
			req.getRequestDispatcher("/_view/profile.jsp").forward(req, resp);
		}
		
		if (req.getParameter("chessHome") != null) {
			System.out.println("Home Servlet: forwarding to chessHome");
			req.getRequestDispatcher("/_view/chessHome.jsp").forward(req, resp);
		}
		
		if (req.getParameter("friends") != null) {
			System.out.println("Home Servlet: forwarding to friends");
			req.getRequestDispatcher("/_view/friends.jsp").forward(req, resp);
		}
	}
}
