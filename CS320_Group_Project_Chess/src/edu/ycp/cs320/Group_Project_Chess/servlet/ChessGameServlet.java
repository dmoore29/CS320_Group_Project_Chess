package edu.ycp.cs320.Group_Project_Chess.servlet;

import java.awt.Point;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.ycp.cs320.Group_Project_Chess.controller.GameController;
import edu.ycp.cs320.Group_Project_Chess.model.*;


public class ChessGameServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Boolean pos1Recieved = false;
	private int sourceX;
	private int sourceY;
	private int destX;
	private int destY;
	
	//TEMPORARY PERSISTANT MEMORY
//  	Profile pr1 = new Profile();
//  	FriendsList f1 = new FriendsList();
//  	Stats s1 = new Stats();
//  	Credentials c1 = new Credentials("a", "b", "c");
//  	Credentials c2 = new Credentials("d", "e", "f");
//	User u1 = new User(c1, s1, f1, pr1);
//	User u2 = new User(c2, s1, f1, pr1);
//	Player p1 = new Player(u1, 0);
//	Player p2 = new Player(u2, 1);
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
			} else {
				controller = new GameController();
				game = controller.loadGame((int) req.getSession().getAttribute("gameId"));
			}
		} else {
			controller = new GameController();
			game = controller.loadGame((int) req.getSession().getAttribute("gameId"));
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
			if(destY == 7) { //black pawn
				color = 1;
			} else { //white pawn
				color = 0;
			}
			switch(r) { //replaces pawn with relative piece
				case "Queen":
				game.getBoard().setPiece(new Queen(Rank.QUEEN, color, new Point(destX, destY)));
				System.out.println("Setting Piece");
				break;
				
				case "Rook":
				game.getBoard().setPiece(new Rook(Rank.ROOK, color, new Point(destX, destY)));
				System.out.println("Setting Piece");
				break;
				
				case "Knight":
				game.getBoard().setPiece(new Knight(Rank.KNIGHT, color, new Point(destX, destY)));
				System.out.println("Setting Piece");
				break;
				
				case "Bishop":
				game.getBoard().setPiece(new Bishop(Rank.BISHOP, color, new Point(destX, destY)));
				System.out.println("Setting Piece");
				break;
			}
			
			game.setPromo(0);
			
			req.setAttribute("promotionFlag", game.getPromo());
			req.setAttribute("model", game);
			System.out.println("ChessGame Servlet: doGet");
			req.getRequestDispatcher("/_view/chessGame.jsp").forward(req, resp);
			
		}
		if(req.getParameter("x1") != null && pos1Recieved == false && game.getPromo() == 0) { //if position 1 is received
			pos1Recieved = true; //sets position 1 received flag to true
			System.out.println("Recieved Source");
			
			sourceX = Integer.parseInt(req.getParameter("x1"));
			sourceY = Integer.parseInt(req.getParameter("y1"));
			
			if(game.getBoard().getSpace(sourceX, sourceY).getPiece() != null) { //if space has a piece
				if(game.getTurn()%2 != game.getBoard().getSpace(sourceX, sourceY).getPiece().getColor()) { //if not player's turn
					sourceX = 8;
					sourceY = 8;
					pos1Recieved = false;
				}
			} else {
				sourceX = 8;
				sourceY = 8;
				pos1Recieved = false;
			}
			
			req.setAttribute("promotionFlag", game.getPromo());
			req.setAttribute("pos1x", sourceX);
			req.setAttribute("pos1y", sourceY);
			req.setAttribute("model", game);
			req.getRequestDispatcher("/_view/chessGame.jsp").forward(req, resp);
		} 
		
		else if(req.getParameter("x1") != null && pos1Recieved == true) { //if position 2 is received
			pos1Recieved = false; //sets position 1 received flag to false
			System.out.println("Recieved Destination");
			
			destX = Integer.parseInt(req.getParameter("x1"));
			destY = Integer.parseInt(req.getParameter("y1"));
			
			if(game.getBoard().getSpace(sourceX, sourceY).getPiece() != null) { //if space has a piece
				if(sourceX == destX && sourceY == destY) { //if source is destination
					System.out.println("NOT VALID");
				}
				if(game.getBoard().getSpace(sourceX, sourceY).getPiece().validMove(new Point(destX, destY), game.getBoard()) == true ) {	//if move is valid			
					controller.movePiece(game.getBoard().getSpace(sourceX, sourceY), game.getBoard().getSpace(destX, destY)); //moves piece
					game.setEnPx(8);
					game.setEnPy(8);
					if(game.getBoard().getPiece(destX, destY).getRank() == Rank.PAWN) { //if piece is a pawn
						Pawn p = (Pawn) game.getBoard().getPiece(destX, destY); //creates temporary pawn to call game.getPromo()tion
						if(p.promotion(game.getBoard())) { //if pawn is at y0 or y7
							game.setPromo(1);
						}
						if(Math.abs(sourceY - destY) == 2){ //if its a pawn and its first move
							game.setEnPx(sourceX);
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
				} else if(game.getBoard().getPiece(destX, destY) != null && game.getBoard().getPiece(destX, destY).getColor() == game.getBoard().getPiece(sourceX, sourceY).getColor()){
					sourceX = destX;
					sourceY = destY;
					req.setAttribute("pos1x", sourceX);
					req.setAttribute("pos1y", sourceY);
					pos1Recieved = true;
				} else if(game.getBoard().getSpace(sourceX, sourceY).getPiece().getRank() == Rank.PAWN && destX == game.getEnPx() && destY == game.getEnPy()) {
					controller.movePiece(game.getBoard().getSpace(sourceX, sourceY), game.getBoard().getSpace(destX, destY)); //moves piece
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
		try {
			controller.updateGame(game);
			System.out.println("UPDATED GAME");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}