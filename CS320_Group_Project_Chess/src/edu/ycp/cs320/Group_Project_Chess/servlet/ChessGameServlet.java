package edu.ycp.cs320.Group_Project_Chess.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.Group_Project_Chess.model.Game;
import edu.ycp.cs320.Group_Project_Chess.controller.GameController;
import edu.ycp.cs320.Group_Project_Chess.model.Player;
import edu.ycp.cs320.Group_Project_Chess.model.User;


public class ChessGameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	User u1;
	User u2;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("ChessGame Servlet: doGet");
		
		req.getRequestDispatcher("/_view/chessGame.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("ChessGame Servlet: doPost");
		
		
		Player p1 = new Player(u1, 0);
		Player p2 = new Player(u2, 1);
		Game model = new Game(p1, p2);
		GameController controller = new GameController();
		controller.setModel(model);
		
		if (req.getParameter("home") != null) {
			System.out.println("ChessGame Servlet: forwarding to hHome");
			req.getRequestDispatcher("/_view/home.jsp").forward(req, resp);
		}
		
		if (req.getParameter("chessHome") != null) {
			System.out.println("ChessGame Servlet: forwarding to chessHome");
			req.getRequestDispatcher("/_view/chessHome.jsp").forward(req, resp);
		}
		
		if (req.getParameter("profile") != null) {
			System.out.println("ChessGame Servlet: forwarding to profile");
			req.getRequestDispatcher("/_view/profile.jsp").forward(req, resp);
		}
		
		if (req.getParameter("friends") != null) {
			System.out.println("ChessGame Servlet: forwarding to friends");
			req.getRequestDispatcher("/_view/friends.jsp").forward(req, resp);
		}
	}
}
