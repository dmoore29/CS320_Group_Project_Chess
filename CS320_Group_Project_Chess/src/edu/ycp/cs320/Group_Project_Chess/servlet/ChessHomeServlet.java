package edu.ycp.cs320.Group_Project_Chess.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import edu.ycp.cs320.Group_Project_Chess.controller.GameHomeController;
import edu.ycp.cs320.Group_Project_Chess.model.Game;

public class ChessHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	GameHomeController controller = null;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("ChessHome Servlet: doGet");
		
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
		
		controller = new GameHomeController();
		
		ArrayList<Game> games = controller.getGameswithUsername(name);
		
		req.setAttribute("games", games);
		
		req.getRequestDispatcher("/_view/chessHome.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("chessHome Servlet: doPost");

		if (req.getParameter("home") != null) {
			System.out.println("ChessHome Servlet: forwarding to home");
			resp.sendRedirect(req.getContextPath() + "/home");
		}
		
		if (req.getParameter("chessGame") != null) {
			System.out.println("ChessHome Servlet: forwarding to chessGame");
			resp.sendRedirect(req.getContextPath() + "/chessGame");
		}
		
		if (req.getParameter("profile") != null) {
			System.out.println("ChessHome Servlet: forwarding to profile");
			resp.sendRedirect(req.getContextPath() + "/profile");
		}
		
		if (req.getParameter("oldChessGame") != null) {
			
			if (req.getParameter("oldChessGameRadio") != null) {
				int gameId = Integer.parseInt(req.getParameter("oldChessGameRadio"));
				System.out.println("game " + gameId + " selected");
				req.setAttribute("gameId", gameId);
				
				System.out.println("ChessHome Servlet: forwarding to chessGame");
				resp.sendRedirect(req.getContextPath() + "/chessGame");
			} else {
				System.out.println("ChessHome Servlet: reloading chessHome");
				resp.sendRedirect(req.getContextPath() + "/chessHome");
			}
			
		}	
	}
}

