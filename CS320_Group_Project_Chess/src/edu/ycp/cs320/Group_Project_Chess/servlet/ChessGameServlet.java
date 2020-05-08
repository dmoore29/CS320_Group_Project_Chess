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
	private int checkFlag;
	private int checkMateFlag;
	private int restrictTurn = 1;
	Game game = null;
	GameController controller = null;
	private boolean validCastle = false;
	
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
		
		controller.setGame(game);
		if(controller.check(0)) {
			checkFlag = 1;
			if(controller.checkmate(0)) {
				checkMateFlag = 1;
			} else {
				checkMateFlag = 0;
			}
		} else if(controller.check(1)){
			checkFlag = 1;
			if(controller.checkmate(1)) {
				checkMateFlag = 1;
			} else {
				checkMateFlag = 0;
			}
		} else {
			checkFlag = 0;
			checkMateFlag = 0;
		}
		req.setAttribute("restrictTurn", restrictTurn);
		req.setAttribute("checkMateFlag", checkMateFlag);
		req.setAttribute("checkFlag", checkFlag);
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
		
		if (req.getParameter("logout") != null) {
			req.getSession().setAttribute("name", null);
			System.out.println("Home Servlet: forwarding to login");
			resp.sendRedirect(req.getContextPath() + "/login");
		}
		
		if (req.getParameter("restrictTurn") != null) {
			System.out.println("Restricting Turns");
			restrictTurn = (restrictTurn == 1) ? 0 : 1;
		}
		
		if(req.getParameter("rank") != null) { //promotion
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
				controller.getGame().getBoard().setPiece(new Queen(Rank.QUEEN, color, new Point(destX, destY)));
				System.out.println("Setting Piece");
				break;
				
				case "Rook":
				controller.getGame().getBoard().setPiece(new Rook(Rank.ROOK, color, new Point(destX, destY)));
				System.out.println("Setting Piece");
				break;
				
				case "Knight":
				controller.getGame().getBoard().setPiece(new Knight(Rank.KNIGHT, color, new Point(destX, destY)));
				System.out.println("Setting Piece");
				break;
				
				case "Bishop":
				controller.getGame().getBoard().setPiece(new Bishop(Rank.BISHOP, color, new Point(destX, destY)));
				System.out.println("Setting Piece");
				break;
			}
			
			controller.getGame().setPromo(0);
			
			boolean check;
			boolean checkMate = false;
			
			//check
			if(controller.getGame().getBoard().getPiece(destX, destY).getColor() == 0) {
				check = controller.check(1);
			} else {
				check = controller.check(0);
			}
			if(check) {
				checkFlag = 1;
				if(controller.getGame().getBoard().getPiece(destX, destY).getColor() == 0) {
					checkMate = controller.checkmate(1);
				} else {
					checkMate = controller.checkmate(0);
				}						
			}
			
			//checkmate
			if(checkMate) {
				checkMateFlag = 1;
				
				boolean user1Wins = false;
				if(controller.getGame().getBoard().getPiece(destX, destY).getColor() == 0) {
					user1Wins = true;
				}
				
				try {
					controller.updateUserStats(user1Wins, controller.getGame().getPlayer1().getUser());
					controller.updateUserStats(!user1Wins, controller.getGame().getPlayer2().getUser());
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			
			
			req.setAttribute("restrictTurn", restrictTurn);
			req.setAttribute("checkMateFlag", checkMateFlag);
			req.setAttribute("checkFlag", checkFlag);
			req.setAttribute("promotionFlag", controller.getGame().getPromo());
			req.setAttribute("model", controller.getGame());
			System.out.println("ChessGame Servlet: doGet");
			req.getRequestDispatcher("/_view/chessGame.jsp").forward(req, resp);
			
		}
		if(req.getParameter("x1") != null && pos1Recieved == false && controller.getGame().getPromo() == 0) { //if position 1 is received
			pos1Recieved = true; //sets position 1 received flag to true
			System.out.println("Recieved Source");
			
			sourceX = Integer.parseInt(req.getParameter("x1"));
			sourceY = Integer.parseInt(req.getParameter("y1"));
			
			if(controller.getGame().getBoard().getSpace(sourceX, sourceY).getPiece() != null) { //if space has a piece
				String username = (String) req.getSession().getAttribute("name");
				User u = controller.loadUser(username); //current user
				Boolean turn = false;
				
				if(controller.getGame().getPlayer1().getColor() == controller.getGame().getTurn()%2) { //if player1's turn
					if(controller.getGame().getPlayer1().getPlayerId() == u.getUserId()) { //if player 1 is user and their turn
						turn = true;
					}
				}  else if(controller.getGame().getPlayer2().getPlayerId() == u.getUserId()) { //if player2's turn and player2 is user
					turn = true;
				}
				
				if(controller.getGame().getTurn()%2 != controller.getGame().getBoard().getSpace(sourceX, sourceY).getPiece().getColor() || !turn && restrictTurn == 1) { //if not player's turn
					sourceX = 8;
					sourceY = 8;
					pos1Recieved = false;
				}
			} else {
				sourceX = 8;
				sourceY = 8;
				pos1Recieved = false;
			}
			
			req.setAttribute("restrictTurn", restrictTurn);
			req.setAttribute("checkMateFlag", checkMateFlag);
			req.setAttribute("checkFlag", checkFlag);
			req.setAttribute("promotionFlag", controller.getGame().getPromo());
			req.setAttribute("pos1x", sourceX);
			req.setAttribute("pos1y", sourceY);
			req.setAttribute("model", controller.getGame());
			req.getRequestDispatcher("/_view/chessGame.jsp").forward(req, resp);
		} 
		
		else if(req.getParameter("x1") != null && pos1Recieved == true) { //if position 2 is received
			pos1Recieved = false; //sets position 1 received flag to false
			System.out.println("Recieved Destination");
			
			destX = Integer.parseInt(req.getParameter("x1"));
			destY = Integer.parseInt(req.getParameter("y1"));
			
			Piece revert = controller.getGame().getBoard().getSpace(destX, destY).getPiece();
			if(controller.getGame().getBoard().getSpace(destX, destY).getPiece() != null)
			System.err.println("SPACE COLOR: " + revert.getColor());
			Boolean check = false;
			Boolean checkMate = false;
			Boolean failedCastle = false;
			
			if(controller.getGame().getBoard().getSpace(sourceX, sourceY).getPiece() != null) { //if space has a piece
				if(sourceX == destX && sourceY == destY) { //if source is destination
					System.out.println("NOT VALID");
				}
				if(controller.getGame().getBoard().getSpace(sourceX, sourceY).getPiece().validMove(new Point(destX, destY), controller.getGame().getBoard()) == true) {	//if move is valid			
					controller.movePiece(controller.getGame().getBoard().getSpace(sourceX, sourceY), controller.getGame().getBoard().getSpace(destX, destY)); //moves piece
					
					//castling
					validCastle = controller.validCastle(controller.getGame().getBoard().getPiece(destX, destY).getColor());
					if(controller.getGame().getBoard().getPiece(destX, destY).getRank() == Rank.KING 
							&& Math.abs(destX - sourceX) == 2
							&& !controller.check(controller.getGame().getBoard().getPiece(destX, destY).getColor())) {
						if(validCastle) {
							if (destX > sourceX) { //moving right
								controller.movePiece(controller.getGame().getBoard().getSpace(7, destY), controller.getGame().getBoard().getSpace(5, destY));
							} else { //moving left
								controller.movePiece(controller.getGame().getBoard().getSpace(0, destY), controller.getGame().getBoard().getSpace(3, destY));
							}
						} else {
							controller.movePiece(controller.getGame().getBoard().getSpace(destX, destY), controller.getGame().getBoard().getSpace(sourceX, sourceY));
							destX = sourceX;
							destY = sourceY;
							failedCastle = true;
						}
					}
					
					if(!controller.check(controller.getGame().getBoard().getPiece(destX, destY).getColor())) { //if move did not put player in check
						//overwrites current En Passant capture location
						controller.getGame().setEnPx(8);
						controller.getGame().setEnPy(8);

						checkFlag = 0;
						
						if(controller.getGame().getBoard().getPiece(destX, destY).getRank() == Rank.PAWN) { //if piece is a pawn
							Pawn p = (Pawn) controller.getGame().getBoard().getPiece(destX, destY); //creates temporary pawn to call controller.getGame().getPromo()tion
							if(p.promotion(controller.getGame().getBoard())) { //if pawn is at y0 or y7
								controller.getGame().setPromo(1);
							}
							//stores capture location for En Passant
							if(Math.abs(sourceY - destY) == 2){ //if its a pawn and its first move
								controller.getGame().setEnPx(sourceX);
								if(p.getColor() == 0) { //if piece is white
									controller.getGame().setEnPy(5);
								} else {
									controller.getGame().setEnPy(2);
								}
							}
						}
						
						//check
						if(controller.getGame().getBoard().getPiece(destX, destY).getColor() == 0) {
							check = controller.check(1);
						} else {
							check = controller.check(0);
						}
						if(check) {
							checkFlag = 1;
							if(controller.getGame().getBoard().getPiece(destX, destY).getColor() == 0) {
								checkMate = controller.checkmate(1);
							} else {
								checkMate = controller.checkmate(0);
							}						
						}
						
						//checkmate
						if(checkMate) {
							checkMateFlag = 1;
							
							boolean user1Wins = false;
							if(controller.getGame().getBoard().getPiece(destX, destY).getColor() == 0) {
								user1Wins = true;
							}
							
							try {
								controller.updateUserStats(user1Wins, controller.getGame().getPlayer1().getUser());
								controller.updateUserStats(!user1Wins, controller.getGame().getPlayer2().getUser());
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}
						controller.updateCastleConditions(sourceX, sourceY, destX, destY); //updates castle conditions
						System.out.println("VALID");
						if(!failedCastle)
							controller.getGame().setTurn(controller.getGame().getTurn()+1); //increments turn counter
					} else {
						controller.movePiece(controller.getGame().getBoard().getSpace(destX, destY), controller.getGame().getBoard().getSpace(sourceX, sourceY));
						controller.getGame().getBoard().getSpace(destX, destY).setPiece(revert);
						System.out.println("NOT VALID");
					}
				//if selecting piece of same color after selecting source (makes moving smoother)
				} else if(controller.getGame().getBoard().getPiece(destX, destY) != null && controller.getGame().getBoard().getPiece(destX, destY).getColor() == controller.getGame().getBoard().getPiece(sourceX, sourceY).getColor()){
					sourceX = destX;
					sourceY = destY;
					req.setAttribute("pos1x", sourceX);
					req.setAttribute("pos1y", sourceY);
					pos1Recieved = true;
				} 
				//En Passant logic
				else if(controller.getGame().getBoard().getSpace(sourceX, sourceY).getPiece().getRank() == Rank.PAWN 
						&& destX == controller.getGame().getEnPx() 
						&& destY == controller.getGame().getEnPy()
						&& (sourceX == destX + 1 || sourceX == destX -1)) {
					controller.movePiece(controller.getGame().getBoard().getSpace(sourceX, sourceY), controller.getGame().getBoard().getSpace(destX, destY)); //moves piece
					if(controller.getGame().getEnPy() == 2) {
						controller.getGame().getBoard().getSpace(controller.getGame().getEnPx(), 3).setPiece(null);
					} else {
						controller.getGame().getBoard().getSpace(controller.getGame().getEnPx(), 4).setPiece(null);
					}
					controller.getGame().setTurn(controller.getGame().getTurn()+1); //increments turn on En Passant move
					controller.getGame().setEnPx(8);
					controller.getGame().setEnPy(8);
					
					checkFlag = 0;
					if(controller.getGame().getBoard().getPiece(destX, destY).getColor() == 0) {
						check = controller.check(1);
					} else {
						check = controller.check(0);
					}
					if(check) {
						checkFlag = 1;
						if(controller.getGame().getBoard().getPiece(destX, destY).getColor() == 0) {
							checkMate = controller.checkmate(1);
						} else {
							checkMate = controller.checkmate(0);
						}						
					}
					
					if(checkMate) {
						checkMateFlag = 1;
						
						boolean user1Wins = false;
						if(controller.getGame().getBoard().getPiece(destX, destY).getColor() == 0) {
							user1Wins = true;
						}
						
						try {
							controller.updateUserStats(user1Wins, controller.getGame().getPlayer1().getUser());
							controller.updateUserStats(!user1Wins, controller.getGame().getPlayer2().getUser());
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}				}	else {
					System.out.println("NOT VALID ");
				}
			}
			req.setAttribute("restrictTurn", restrictTurn);
			req.setAttribute("checkMateFlag", checkMateFlag);
			req.setAttribute("checkFlag", checkFlag);
			req.setAttribute("promotionFlag", controller.getGame().getPromo());
			req.setAttribute("model", controller.getGame());
			System.out.println("ChessGame Servlet: doGet");
			req.getRequestDispatcher("/_view/chessGame.jsp").forward(req, resp);
		} else {
			req.setAttribute("restrictTurn", restrictTurn);
			req.setAttribute("checkMateFlag", checkMateFlag);
			req.setAttribute("checkFlag", checkFlag);
			req.setAttribute("promotionFlag", controller.getGame().getPromo());
			req.setAttribute("model", controller.getGame());
			System.out.println("ChessGame Servlet: doGet");
			req.getRequestDispatcher("/_view/chessGame.jsp").forward(req, resp);
		}
		try {
			controller.updateGame(controller.getGame());
			System.out.println("UPDATED GAME");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}