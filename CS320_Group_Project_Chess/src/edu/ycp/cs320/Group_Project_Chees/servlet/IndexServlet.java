package edu.ycp.cs320.Group_Project_Chees.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Index Servlet: doGet");
		
		req.getRequestDispatcher("/_view/index.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("Index Servlet: doPost");
		
		if (req.getParameter("addNumbers") != null) {
			System.out.println("Index Servlet: forwarding to add");
			req.getRequestDispatcher("/_view/addNumbers.jsp").forward(req, resp);
		}
		
		if (req.getParameter("multiplyNumbers") != null) {
			System.out.println("Index Servlet: forwarding to multiplyNumbers");
			req.getRequestDispatcher("/_view/multiplyNumbers.jsp").forward(req, resp);
		}
		
		if (req.getParameter("guessingGame") != null) {
			System.out.println("Index Servlet: forwarding to guessingGame");
			req.getRequestDispatcher("/_view/guessingGame.jsp").forward(req, resp);
		}
	}
}
