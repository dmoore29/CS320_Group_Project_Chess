package edu.ycp.cs320.Group_Project_Chess.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.Group_Project_Chess.controller.GameController;
import edu.ycp.cs320.Group_Project_Chess.controller.GameHomeController;
import edu.ycp.cs320.Group_Project_Chess.model.Game;
import edu.ycp.cs320.Group_Project_Chess.model.Player;
import edu.ycp.cs320.Group_Project_Chess.model.User;

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
		
		if (req.getParameter("chessGame") != null) { //creating new game
			GameController controller = new GameController();
			String username = (String) req.getSession().getAttribute("name");
			User u1 = controller.loadUser(username);
			
			//TODO: SPECIFY USER 2
			User u2;
			if(username == "user1") {
				u2 = controller.loadUser("user2");
			} else {
				u2 = controller.loadUser("user1");
			}
			//TODO: SPECIFY USER 2

			Player p1 = new Player(u1, 0);
			Player p2 = new Player(u2, 1);
			Game game = new Game(p1, p2);
			
			//TODO: SPECIFY REAL BOARD ID
			game.getBoard().setBoardId(1);
			//TODO: SPECIFY REAL BOARD ID
			
			try {
				controller.StoreNewGame(game);
				game.setPromo(0);
				game.setEnPx(8);
				game.setEnPy(8);
				controller.updateGame(game);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			System.out.println("ChessHome Servlet: forwarding to chessGame");
			resp.sendRedirect(req.getContextPath() + "/chessGame");
		}
		
		if (req.getParameter("profile") != null) {
			System.out.println("ChessHome Servlet: forwarding to profile");
			resp.sendRedirect(req.getContextPath() + "/profile");
		}
		
		if (req.getParameter("oldChessGame") != null) {
			
			if (req.getParameter("oldChessGameRadio") != null) { //loading existing game
				int gameId = Integer.parseInt(req.getParameter("oldChessGameRadio"));
				System.out.println("game " + gameId + " selected");
				
				req.getSession().setAttribute("loadGameFlag", 1);
				req.getSession().setAttribute("gameId", gameId);
				
				System.out.println("ChessHome Servlet: forwarding to chessGame");
				resp.sendRedirect(req.getContextPath() + "/chessGame");
			} else {
				System.out.println("ChessHome Servlet: reloading chessHome");
				resp.sendRedirect(req.getContextPath() + "/chessHome");
			}
			
		}	
	}
}

