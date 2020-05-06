package edu.ycp.cs320.Group_Project_Chess.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.Group_Project_Chess.controller.FriendsController;

public class FriendsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	FriendsController controller = null;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Friends Servlet: doGet");
		
//-- structure taken from the library example
		String name = (String) req.getSession().getAttribute("name");
		if (name == null) {
			System.out.println("   User: <" + name + "> not logged in or session timed out");
			
			// user is not logged in, or the session expired
			resp.sendRedirect(req.getContextPath() + "/login");
			return;
		}

		// now we have the user's User object,
		// proceed to handle request...
		System.out.println("   User: <" + name + "> logged in");
//-- structure taken from the library example
		
		controller = new FriendsController();
		
		req.setAttribute("friendsList", controller.getFriends(name));
		
		req.getRequestDispatcher("/_view/friends.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("Friends Servlet: doPost");
		
		if (req.getParameter("home") != null) {
			System.out.println("Friends Servlet: forwarding to home");
			resp.sendRedirect(req.getContextPath() + "/home");
		}
		
		if (req.getParameter("chessHome") != null) {
			System.out.println("Friends Servlet: forwarding to chessHome");
			resp.sendRedirect(req.getContextPath() + "/chessHome");
		}
		
		if (req.getParameter("profile") != null) {
			System.out.println("Friends Servlet: forwarding to profile");
			resp.sendRedirect(req.getContextPath() + "/profile");
		}
		if (req.getParameter("logout") != null) {
			req.getSession().setAttribute("name", null);
			System.out.println("Friends Servlet: forwarding to login");
			resp.sendRedirect(req.getContextPath() + "/login");
		}
		if (req.getParameter("remove") != null) {
			
			if (req.getParameter("userSelection") != null) { 
				String friendName = (String) req.getParameter("userSelection");
				System.out.println("user " + friendName + " selected");
				
				controller = new FriendsController();
				controller.setUser((String) req.getSession().getAttribute("name")); 
				
				try {
					controller.removeFriend(friendName);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				System.out.println("Friends Servlet: reloading friends");
				resp.sendRedirect(req.getContextPath() + "/friends");
			} else {
				System.out.println("Friends Servlet: no friend selected");
				resp.sendRedirect(req.getContextPath() + "/friends");
			}
			
		}
		if (req.getParameter("add") != null) {
			
			if (req.getParameter("username") != null) {
				String friendName = (String) req.getParameter("username");
				System.out.println("user " + friendName + " will be attempted to be added");
				
				controller = new FriendsController();
				controller.setUser((String) req.getSession().getAttribute("name")); 
				
				try {
					controller.addFriend(friendName);
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
				System.out.println("Friends Servlet: reloading friends");
				resp.sendRedirect(req.getContextPath() + "/friends");
			} else {
				System.out.println("Friends Servlet: no friend selected");
				resp.sendRedirect(req.getContextPath() + "/friends");
			}
			
		}
	}
}
