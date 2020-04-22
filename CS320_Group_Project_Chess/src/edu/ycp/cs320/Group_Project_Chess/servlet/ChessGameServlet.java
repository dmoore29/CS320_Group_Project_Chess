package edu.ycp.cs320.Group_Project_Chess.servlet;

import java.awt.Point;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.Group_Project_Chess.controller.GameController;
import edu.ycp.cs320.Group_Project_Chess.model.*;


public class ChessGameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//TEMPORARY PERSISTANT MEMORY
  	Profile pr1 = new Profile();
  	FriendsList f1 = new FriendsList();
  	Stats s1 = new Stats();
  	Credentials c1 = new Credentials("a", "b", "c");
  	Credentials c2 = new Credentials("d", "e", "f");
	User u1 = new User(c1, s1, f1, pr1);
	User u2 = new User(c2, s1, f1, pr1);
	Player p1 = new Player(u1, 0);
	Player p2 = new Player(u2, 1);
	Game game = null;
	 
	GameController controller = null;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		if (req.getSession().getAttribute("loadGameFlag") != null) {
			System.out.println("flag detected");
			if ((int) req.getSession().getAttribute("loadGameFlag") == 1) {
				System.out.println("load game detected");
				controller = new GameController();
				game = controller.loadGame((int) req.getSession().getAttribute("gameId"));
				req.getSession().setAttribute("loadGameFlag", 0);
				req.getSession().setAttribute("gameId", null);
			} else {
				game = new Game(p1, p2);
			}
		} else {
			game = new Game(p1, p2);
		}
		
		req.setAttribute("model", game);
		System.out.println("ChessGame Servlet: doGet");
		
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
		
		req.getRequestDispatcher("/_view/chessGame.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		System.out.println("ChessGame Servlet: doPost");
		
		controller = new GameController(game);
		
		if (req.getParameter("home") != null) {
			System.out.println("ChessGame Servlet: forwarding to hHome");
			resp.sendRedirect(req.getContextPath() + "/home");
		}
		
		if (req.getParameter("chessHome") != null) {
			System.out.println("ChessGame Servlet: forwarding to chessHome");
			resp.sendRedirect(req.getContextPath() + "/chessHome");
		}
		
		if (req.getParameter("profile") != null) {
			System.out.println("ChessGame Servlet: forwarding to profile");
			resp.sendRedirect(req.getContextPath() + "/profile");
		}
		
		if (req.getParameter("friends") != null) {
			System.out.println("ChessGame Servlet: forwarding to friends");
			resp.sendRedirect(req.getContextPath() + "/friends");
		}
		if(req.getParameter("rank") != null) {
			System.out.println("Recieved Promotion Piece");
			String r = (String)req.getParameter("rank");
			int color;
			if(game.getDestY() == 7) { //black pawn
				color = 1;
			} else { //white pawn
				color = 0;
			}
			switch(r) { //replaces pawn with relative piece
				case "Queen":
				game.getBoard().setPiece(new Queen(Rank.QUEEN, color, new Point(game.getDestX(), game.getDestY())));
				System.out.println("Setting Piece");
				break;
				
				case "Rook":
				game.getBoard().setPiece(new Rook(Rank.ROOK, color, new Point(game.getDestX(), game.getDestY())));
				System.out.println("Setting Piece");
				break;
				
				case "Knight":
				game.getBoard().setPiece(new Knight(Rank.KNIGHT, color, new Point(game.getDestX(), game.getDestY())));
				System.out.println("Setting Piece");
				break;
				
				case "Bishop":
				game.getBoard().setPiece(new Bishop(Rank.BISHOP, color, new Point(game.getDestX(), game.getDestY())));
				System.out.println("Setting Piece");
				break;
			}
			
			game.setPromo(0);
			
			req.setAttribute("promotionFlag", game.getPromo());
			req.setAttribute("model", game);
			System.out.println("ChessGame Servlet: doGet");
			req.getRequestDispatcher("/_view/chessGame.jsp").forward(req, resp);
			
		}
		if(req.getParameter("x1") != null && game.getPos1Recieved() == false && game.getPromo() == 0) { //if position 1 is received
			game.setPos1Recieved(true); //sets position 1 received flag to true
			System.out.println("Recieved Source");
			
			game.setSourceX(Integer.parseInt(req.getParameter("x1")));
			game.setSourceY(Integer.parseInt(req.getParameter("y1")));
			
			System.err.println("TURN: " + game.getTurn()%2);

			
			if(game.getBoard().getSpace(game.getSourceX(), game.getSourceY()).getPiece() != null) { //if space has a piece
				if(game.getTurn()%2 != game.getBoard().getSpace(game.getSourceX(), game.getSourceY()).getPiece().getColor()) { //if not player's turn
					game.setSourceX(8);
					game.setSourceY(8);
					game.setPos1Recieved(false);
				}
			} else {
				game.setSourceX(8);
				game.setSourceY(8);
				game.setPos1Recieved(false);
			}
			
			req.setAttribute("promotionFlag", game.getPromo());
			req.setAttribute("pos1x", game.getSourceX());
			req.setAttribute("pos1y", game.getSourceY());
			req.setAttribute("model", game);
			req.getRequestDispatcher("/_view/chessGame.jsp").forward(req, resp);
		} 
		
		else if(req.getParameter("x1") != null && game.getPos1Recieved() == true) { //if position 2 is received
			game.setPos1Recieved(false); //sets position 1 received flag to false
			System.out.println("Recieved Destination");
			
			game.setDestX(Integer.parseInt(req.getParameter("x1")));
			game.setDestY(Integer.parseInt(req.getParameter("y1")));
			
			if(game.getBoard().getSpace(game.getSourceX(), game.getSourceY()).getPiece() != null) { //if space has a piece
				if(game.getSourceX() == game.getDestX() && game.getSourceY() == game.getDestY()) { //if source is destination
					System.out.println("NOT VALID");
				}
				if(game.getBoard().getSpace(game.getSourceX(), game.getSourceY()).getPiece().validMove(new Point(game.getDestX(), game.getDestY()), game.getBoard()) == true ) {	//if move is valid			
					controller.movePiece(game.getBoard().getSpace(game.getSourceX(), game.getSourceY()), game.getBoard().getSpace(game.getDestX(), game.getDestY())); //moves piece
					game.setEnPx(8);
					game.setEnPy(8);
					if(game.getBoard().getPiece(game.getDestX(), game.getDestY()).getRank() == Rank.PAWN) { //if piece is a pawn
						Pawn p = (Pawn) game.getBoard().getPiece(game.getDestX(), game.getDestY()); //creates temporary pawn to call game.getPromo()tion
						if(p.promotion(game.getBoard())) { //if pawn is at y0 or y7
							game.setPromo(1);
						}
						if(Math.abs(game.getSourceY() - game.getDestY()) == 2){ //if its a pawn and its first move
							game.setEnPx(game.getSourceX());
							if(p.getColor() == 0) { //if piece is white
								game.setEnPy(5);
							} else {
								game.setEnPy(2);
							}
						}
					}
					System.out.println("VALID");
					game.setTurn(game.getTurn()+1); //increments turn counter
				//if selecting piece of same color after selecting source (makes moving smoother)
				} else if(game.getBoard().getPiece(game.getDestX(), game.getDestY()) != null && game.getBoard().getPiece(game.getDestX(), game.getDestY()).getColor() == game.getBoard().getPiece(game.getSourceX(), game.getSourceY()).getColor()){
					game.setSourceX(game.getDestX());
					game.setSourceY(game.getDestY());
					req.setAttribute("pos1x", game.getSourceX());
					req.setAttribute("pos1y", game.getSourceY());
					game.setPos1Recieved(true);
				} else if(game.getBoard().getSpace(game.getSourceX(), game.getSourceY()).getPiece().getRank() == Rank.PAWN && game.getDestX() == game.getEnPx() && game.getDestY() == game.getEnPy()) {
					controller.movePiece(game.getBoard().getSpace(game.getSourceX(), game.getSourceY()), game.getBoard().getSpace(game.getDestX(), game.getDestY())); //moves piece
					if(game.getEnPy() == 2) {
						game.getBoard().getSpace(game.getEnPx(), 3).setPiece(null);
					} else {
						game.getBoard().getSpace(game.getEnPx(), 4).setPiece(null);
					}
					game.setTurn(game.getTurn()+1); //increments turn on En Passant move
					game.setEnPx(8);
					game.setEnPy(8);
				}	else {
					System.out.println("NOT VALID ");
				}
			}
			req.setAttribute("promotionFlag", game.getPromo());
			req.setAttribute("model", game);
			System.out.println("ChessGame Servlet: doGet");
			req.getRequestDispatcher("/_view/chessGame.jsp").forward(req, resp);
		} else {
			req.setAttribute("promotionFlag", game.getPromo());
			req.setAttribute("model", game);
			System.out.println("ChessGame Servlet: doGet");
			req.getRequestDispatcher("/_view/chessGame.jsp").forward(req, resp);
		}
	}
}