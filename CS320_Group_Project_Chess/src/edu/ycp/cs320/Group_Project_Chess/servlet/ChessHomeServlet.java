package edu.ycp.cs320.Group_Project_Chess.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import edu.ycp.cs320.Group_Project_Chess.model.Board;
//import edu.ycp.cs320.Group_Project_Chess.model.Game;
//import edu.ycp.cs320.Group_Project_Chess.controller.GameController;

public class ChessHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("ChessHome Servlet: doGet");
		
		req.getRequestDispatcher("/_view/chessHome.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("chessHome Servlet: doPost");

		if (req.getParameter("home") != null) {
			System.out.println("ChessHome Servlet: forwarding to home");
			req.getRequestDispatcher("/_view/home.jsp").forward(req, resp);
		}
		
		if (req.getParameter("chessGame") != null) {
			System.out.println("ChessHome Servlet: forwarding to chessGame");
			req.getRequestDispatcher("/_view/chessGame.jsp").forward(req, resp);
		}
		
		if (req.getParameter("profile") != null) {
			System.out.println("ChessHome Servlet: forwarding to profile");
			req.getRequestDispatcher("/_view/profile.jsp").forward(req, resp);
		}
		
		//initiate a board, game, and controller
		/*BoardModel board = new BoardModel();
		Game game = new Game();
		GameController controller = new GameController();
		
		//fill the board, and pass the models as parameters to their higher-ups
		board.populateBoard();
		game.setBoard(board);
		controller.setModel(game);
		*/
	}
}

