package edu.ycp.cs320.Group_Project_Chess.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.Group_Project_Chess.controller.ProfileController;
import edu.ycp.cs320.Group_Project_Chess.model.User;

public class ProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ProfileController controller = null;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("Profile Servlet: doGet");
		
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
		
		controller = new ProfileController();
		
		User user = controller.getProfile(name);
		
		req.setAttribute("profile", user);
		
		
		
		req.getRequestDispatcher("/_view/profile.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("Profile Servlet: doPost");
		
		if (req.getParameter("home") != null) {
			System.out.println("Profile Servlet: forwarding to home");
			resp.sendRedirect(req.getContextPath() + "/home");
		}
		
		if (req.getParameter("chessHome") != null) {
			System.out.println("Profile Servlet: forwarding to chessHome");
			resp.sendRedirect(req.getContextPath() + "/chessHome");
		}
		
		if (req.getParameter("friends") != null) {
			System.out.println("Profile Servlet: forwarding to friends");
			resp.sendRedirect(req.getContextPath() + "/friends");
		}
		if (req.getParameter("logout") != null) {
			req.getSession().setAttribute("name", null);
			System.out.println("Home Servlet: forwarding to login");
			resp.sendRedirect(req.getContextPath() + "/login");
		}
		if (req.getParameter("editBio") != null) {
			System.out.println("Profile Servlet: editing bio");
			controller = new ProfileController();
			User user = controller.getProfile((String) req.getSession().getAttribute("name"));
			req.setAttribute("profile", user);
			req.setAttribute("editBioFlag", 1);
			req.getRequestDispatcher("/_view/profile.jsp").forward(req, resp);
		}
		if (req.getParameter("editPic") != null) {
			System.out.println("Profile Servlet: editing picture");
			controller = new ProfileController();
			User user = controller.getProfile((String) req.getSession().getAttribute("name"));
			req.setAttribute("profile", user);
			req.setAttribute("editPicFlag", 1);
			req.getRequestDispatcher("/_view/profile.jsp").forward(req, resp);
		}
	}
}
