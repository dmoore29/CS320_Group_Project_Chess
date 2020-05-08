package edu.ycp.cs320.Group_Project_Chess.controller;

import java.awt.Point;
import java.sql.SQLException;

import edu.ycp.cs320.Group_Project_Chess.database.DerbyDatabase;
import edu.ycp.cs320.Group_Project_Chess.model.Bishop;
import edu.ycp.cs320.Group_Project_Chess.model.Game;
import edu.ycp.cs320.Group_Project_Chess.model.King;
import edu.ycp.cs320.Group_Project_Chess.model.Knight;
import edu.ycp.cs320.Group_Project_Chess.model.Pawn;
import edu.ycp.cs320.Group_Project_Chess.model.Piece;
import edu.ycp.cs320.Group_Project_Chess.model.Queen;
import edu.ycp.cs320.Group_Project_Chess.model.Rank;
import edu.ycp.cs320.Group_Project_Chess.model.Rook;
import edu.ycp.cs320.Group_Project_Chess.model.Space;
import edu.ycp.cs320.Group_Project_Chess.model.Stats;
import edu.ycp.cs320.Group_Project_Chess.model.User; 

public class GameController {
	private Game model;
	private DerbyDatabase database;
	
	/**
	 * Set the model.
	 *
	 * 
	 * @param model the model to set
	 */
	public GameController(Game model) {
		this.model = model;
		database = new DerbyDatabase();
	}
	
	public GameController() {
		database = new DerbyDatabase();
	}
	
	/**
	 * Check whether castling is valid
	 * 
	 * @return false king or knight has moved
	 *     true if king and knight have not moved
	 */
	public boolean validCastle() {
		System.out.println("MOVED: " + model.getMoved701());
		System.out.println("MOVED: " + model.getMoved401());
		System.out.println("MOVED: " + model.getMoved001());
		System.out.println("MOVED: " + model.getMoved770());
		System.out.println("MOVED: " + model.getMoved070());
		System.out.println("MOVED: " + model.getMoved470());
		if(!model.getMoved001() 
				&& !model.getMoved701()
				&& !model.getMoved401()
				&& !model.getMoved070()
				&& !model.getMoved770()
				&& !model.getMoved470()) { //if all conditions are true
			return true;
		} else { //no pieces moved
			return false;
		}
	}
	
	
	/**
	 * Updates the castle conditions if a rook or king has moved
	 * 
	 * @param sourceX starting x
	 * @param sourceY starting y
	 * @param destX destination x
	 * @param destY destination y
	 */
	public void updateCastleConditions(int sourceX, int sourceY, int destX, int destY) {
		if(model.getBoard().getPiece(destX, destY) != null) {
			if(model.getBoard().getPiece(destX, destY).getRank() == Rank.KING) { //moving king
				if(model.getBoard().getPiece(destX, destY).getColor() == 0) { //white piece
					model.setMoved470(true); //white king
					System.out.println("WHITE KING MOVED");
				} else { //black piece
					model.setMoved401(true); //black king
					System.out.println("BLACK KING MOVED");
				}
			} else if(model.getBoard().getPiece(destX, destY).getRank() == Rank.ROOK) { //moving rook
				if(sourceX == 0) { //left rook
					if(model.getBoard().getPiece(destX, destY).getColor() == 0) { //white piece
						model.setMoved070(true); //white left rook
						System.out.println("LEFT WHITE ROOK MOVED");
					} else { //black piece
						model.setMoved001(true); //black left rook
						System.out.println("LEFT BLACK ROOK MOVED");
					}
				} else { //right rook
					if(model.getBoard().getPiece(destX, destY).getColor() == 0) { //white piece
						model.setMoved770(true); //white right rook
						System.out.println("RIGHT WHITE ROOK MOVED");
					} else { //black piece
						model.setMoved701(true); //black right rook
						System.out.println("RIGHT BLACK ROOK MOVED");
					}
				}
			}
		}
	}
	
	public Game loadGame(int gameId) {
		model = database.findGamewithGameId(gameId);
		return model;
	}
	
	public void setGame(Game model) {
		this.model = model;
	}
	
	public Game getGame() {
		return model;
	}
	
	/**
	 * Stores the game in the database.
	 * 
	 * @param game   the model of the game
	 * @throws SQLException 
	 */
	public void updateGame(Game game) throws SQLException {
		database.updateGame(game);
		database.updateBoard(game.getBoard());
	}
	
	/**
	 * Returns a user from database.
	 * 
	 * @param String   the username of the user
	 * @return the user from specified name.
	 */
	public User loadUser(String username) {
		return database.findUserwithUsername(username);
	}
	
	/**
	 * Stores a new game in the database.
	 * 
	 * @param Game   the game you want to store
	 * @throws SQLException 
	 */
	public int StoreNewGame(Game game) throws SQLException {
		int boardId = database.newBoard(game.getBoard());
		game.getBoard().setBoardId(boardId);
		return database.newGame(game);
	}
	
	/**
	 * Stores a new game in the database.
	 * 
	 * @param Game   the game you want to store
	 * @throws SQLException 
	 */
	public int updateUserStats(boolean win, User user) throws SQLException {
		Stats newStats = null;
		if (win) {
			newStats = new Stats(user.getStats().getWins() + 1, user.getStats().getLosses(), user.getStats().getElo() + 5);
		} else if (user.getStats().getElo() < 4){
			newStats = new Stats(user.getStats().getWins(), user.getStats().getLosses() + 1, 0);
		} else {
			newStats = new Stats(user.getStats().getWins(), user.getStats().getLosses() + 1, user.getStats().getElo() - 3);
		}
		return database.updateStats(newStats, user.getUserId());
	}
	
	
	/**
	 * Moves the piece to intended location. (Called after validMove is called)
	 * 
	 * @param model   the model of the game
	 * @param origin	the starting space
	 * @param destination	the intended move space 
	 */
	public void movePiece(Space origin, Space destination) {
		// Set the piece of the 'destination' space equal to the piece from the 'origin' space.
		 model.getBoard().getSpace(destination.getLocation().x, destination.getLocation().y).setPiece(origin.getPiece());
		 
		//Change the location in the piece that was just moved.
		model.getBoard().getSpace(destination.getLocation().x, destination.getLocation().y).getPiece().setLocation(destination.getLocation());
	
		// Change the piece of the 'origin' space to null. The space is now empty.
		model.getBoard().getSpace(origin.getLocation().x, origin.getLocation().y).setPiece(null);
	}
	
	/**
	 * Checks to see if the previous move set the other player in check.
	 * 
	 * @param model   the model of the game
	 * @return false if the move did not set the player in check,
	 *     true if the move did set the player in check.
	 */
	public boolean check(int player) {
		// Obtain the player's king piece.
		// King is set to null in order to avoid provoking Java.
		Piece king = null;
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(model.getBoard().getPiece(j, i) != null) {	
					if( (model.getBoard().getSpace(j, i).getPiece().getColor() == player) && (model.getBoard().getSpace(j, i).getPiece().getRank() == Rank.KING) ) {
						king = model.getBoard().getSpace(j, i).getPiece();
					}
				}
			}
		}
		
		// Iterate through all of the player's chess pieces.
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				// If the space contains a piece.
				if(model.getBoard().getPiece(j, i) != null) {
					// If the piece that the space contains belongs to the opposing player.
					if((model.getBoard().getSpace(j, i).getPiece().getColor() != player)) {
						// If the opposing player's piece is able to move to the provided player's king, the current player is in check.
						if(model.getBoard().getSpace(j, i).getPiece().validMove(king.getLocation(), model.getBoard())) {
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * Checks to see if the previous move set the other player in checkmate.
	 * 
	 * @param model   the model of the game
	 * @return false if the move did not set the player in checkmate,
	 *     true if the move did set the player in checkmate.
	 */
	public boolean checkmate(int player) {
		boolean enP = false;
		// Obtain the player's king piece.
		// King is set to null in order to avoid provoking Java.
		Piece king = null;
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(model.getBoard().getPiece(j, i) != null) {	
					if( (model.getBoard().getSpace(j, i).getPiece().getColor() == player) && (model.getBoard().getSpace(j, i).getPiece().getRank() == Rank.KING) ) {
						king = model.getBoard().getSpace(j, i).getPiece();
					}
				}
			}
		}
		
		/*for (int y = -1; y < 2; y++) {			//this is the test for if the king can move out of checkmate. This test is subsequently done in the crude test below.
			for (int x = -1; x < 2; x++) {
				if ((x + king.getLocation().x >= 0 && x + king.getLocation().x <= 7) && (y + king.getLocation().y >= 0 && y + king.getLocation().y <= 7) && !(x == 0 && y == 0)) {
					if (model.getBoard().getSpace(king.getLocation().x, king.getLocation().y).getPiece().validMove(new Point(x + king.getLocation().x, y + king.getLocation().y), model.getBoard())) {
						Piece temp = null;
						if (model.getBoard().getSpace(x + king.getLocation().x, y + king.getLocation().y).getPiece() != null) {
							switch(model.getBoard().getSpace(x + king.getLocation().x, y + king.getLocation().y).getPiece().getRank()) {
							case PAWN:
								temp = new Pawn(Rank.PAWN, model.getBoard().getSpace(x + king.getLocation().x, y + king.getLocation().y).getPiece().getColor(), model.getBoard().getSpace(x + king.getLocation().x, y + king.getLocation().y).getPiece().getLocation());
								break;
							case ROOK:
								temp = new Rook(Rank.ROOK, model.getBoard().getSpace(x + king.getLocation().x, y + king.getLocation().y).getPiece().getColor(), model.getBoard().getSpace(x + king.getLocation().x, y + king.getLocation().y).getPiece().getLocation());
								break;
							case KNIGHT:
								temp = new Knight(Rank.KNIGHT, model.getBoard().getSpace(x + king.getLocation().x, y + king.getLocation().y).getPiece().getColor(), model.getBoard().getSpace(x + king.getLocation().x, y + king.getLocation().y).getPiece().getLocation());
								break;
							case BISHOP:
								temp = new Bishop(Rank.BISHOP, model.getBoard().getSpace(x + king.getLocation().x, y + king.getLocation().y).getPiece().getColor(), model.getBoard().getSpace(x + king.getLocation().x, y + king.getLocation().y).getPiece().getLocation());
								break;
							case QUEEN:
								temp = new Queen(Rank.QUEEN, model.getBoard().getSpace(x + king.getLocation().x, y + king.getLocation().y).getPiece().getColor(), model.getBoard().getSpace(x + king.getLocation().x, y + king.getLocation().y).getPiece().getLocation());
								break;
							case KING:
								temp = new King(Rank.KING, model.getBoard().getSpace(x + king.getLocation().x, y + king.getLocation().y).getPiece().getColor(), model.getBoard().getSpace(x + king.getLocation().x, y + king.getLocation().y).getPiece().getLocation());
								break;
							default:
								temp = null;
							}
						}
						movePiece(model.getBoard().getSpace(king.getLocation().x, king.getLocation().y), model.getBoard().getSpace(x + king.getLocation().x, y + king.getLocation().y));
						if (!check(player)) {
							movePiece(model.getBoard().getSpace(king.getLocation().x, king.getLocation().y), model.getBoard().getSpace(king.getLocation().x - x, king.getLocation().y - y));
							if (temp != null) {
								model.getBoard().setPiece(temp);
							}
							return false;
						} else {
							movePiece(model.getBoard().getSpace(king.getLocation().x, king.getLocation().y), model.getBoard().getSpace(king.getLocation().x - x, king.getLocation().y - y));
							if (temp != null) {
								model.getBoard().setPiece(temp);
							}
						}
					}
				}
			}
		}*/
		
		// finds a piece of the player being tested for
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(model.getBoard().getPiece(j, i) != null) {	
					if(model.getBoard().getSpace(j, i).getPiece().getColor() == player) {
						
						// stores found piece in temp piece reference
						Piece piece = model.getBoard().getSpace(j, i).getPiece();
						
						// also stores the location in a new point
						Point origin = new Point(j, i);
						
						// for loops that run over every board space
						for (int y = 0; y < 8; y++) {
							for (int x = 0; x < 8; x++) {
								
								// makes sure that the tested board space is not where the piece is currently located
								if (!(x == piece.getLocation().x && y == piece.getLocation().y)) {
									
									//if found piece is a pawn and a pawn moving forward 2 spaces put the king in check
									if(piece.getRank() == Rank.PAWN) {
										if(model.getEnPx() != 8 && x == model.getEnPx() && y == model.getEnPy()) {
											enP = true;
										} else {
											enP = false;
										}
									} else {
										enP = false;
									}
									
									//if enP is possible
									if(enP) { 
 										Piece temp = null;
										if(model.getBoard().getSpace(piece.getLocation().x,piece.getLocation().y).getPiece().getRank() == Rank.PAWN 
												&& x == model.getEnPx() 
												&& y == model.getEnPy()
												&& (piece.getLocation().x == x + 1 || piece.getLocation().x == x -1)) {
											movePiece(model.getBoard().getSpace(piece.getLocation().x, piece.getLocation().y), model.getBoard().getSpace(x, y)); //moves piece
											if(model.getEnPy() == 2) {
												temp = new Pawn(Rank.PAWN, model.getBoard().getSpace(x, 3).getPiece().getColor(), model.getBoard().getSpace(x, 3).getPiece().getLocation());
												model.getBoard().getSpace(model.getEnPx(), 3).setPiece(null);
											} else {
												temp = new Pawn(Rank.PAWN, model.getBoard().getSpace(x, 4).getPiece().getColor(), model.getBoard().getSpace(x, 4).getPiece().getLocation());
												model.getBoard().getSpace(model.getEnPx(), 4).setPiece(null);
											}
										}
										// test if this movement results in the player still being in check
										if (!check(player)) {
											
											// if the player is not in check anymore, then there is no checkmate. we restore the model state by replacing the found piece and the temp piece
											movePiece(model.getBoard().getSpace(piece.getLocation().x, piece.getLocation().y), model.getBoard().getSpace(origin.x, origin.y));
											if (temp != null) {
												model.getBoard().setPiece(temp);
											}
											
											// no checkmate
											return false;
										} else {
											
											// if the player is still in check, then we need to continue testing. Restore the model state same way as above
											movePiece(model.getBoard().getSpace(piece.getLocation().x, piece.getLocation().y), model.getBoard().getSpace(origin.x, origin.y));
											if (temp != null) {
												model.getBoard().setPiece(temp);
											}
										}
									} else {
									
										// tests if the piece can make a valid move to the iterated space
										if (model.getBoard().getSpace(piece.getLocation().x, piece.getLocation().y).getPiece().validMove(new Point(x, y), model.getBoard()) && enP == false) {
											
											// if the valid move passes, we need to store the contents of the space being moved to by placing it into this temp Piece
											Piece temp = null;
											
											// switch statement to determine rank of the piece in the iterated space
											if (model.getBoard().getSpace(x, y).getPiece() != null) {
												switch(model.getBoard().getSpace(x, y).getPiece().getRank()) {
												case PAWN:
													temp = new Pawn(Rank.PAWN, model.getBoard().getSpace(x, y).getPiece().getColor(), model.getBoard().getSpace(x, y).getPiece().getLocation());
													break;
												case ROOK:
													temp = new Rook(Rank.ROOK, model.getBoard().getSpace(x, y).getPiece().getColor(), model.getBoard().getSpace(x, y).getPiece().getLocation());
													break;
												case KNIGHT:
													temp = new Knight(Rank.KNIGHT, model.getBoard().getSpace(x, y).getPiece().getColor(), model.getBoard().getSpace(x, y).getPiece().getLocation());
													break;
												case BISHOP:
													temp = new Bishop(Rank.BISHOP, model.getBoard().getSpace(x, y).getPiece().getColor(), model.getBoard().getSpace(x, y).getPiece().getLocation());
													break;
												case QUEEN:
													temp = new Queen(Rank.QUEEN, model.getBoard().getSpace(x, y).getPiece().getColor(), model.getBoard().getSpace(x, y).getPiece().getLocation());
													break;
												case KING:
													temp = new King(Rank.KING, model.getBoard().getSpace(x, y).getPiece().getColor(), model.getBoard().getSpace(x, y).getPiece().getLocation());
													break;
												default:
													temp = null;
												}
											}
											// move the found piece to the iterated space
											movePiece(model.getBoard().getSpace(piece.getLocation().x, piece.getLocation().y), model.getBoard().getSpace(x, y));
	
											// test if this movement results in the player still being in check
											if (!check(player)) {
												
												// if the player is not in check anymore, then there is no checkmate. we restore the model state by replacing the found piece and the temp piece
												movePiece(model.getBoard().getSpace(piece.getLocation().x, piece.getLocation().y), model.getBoard().getSpace(origin.x, origin.y));
												if (temp != null) {
													model.getBoard().setPiece(temp);
												}
												
												// no checkmate
												return false;
											} else {
												
												// if the player is still in check, then we need to continue testing. Restore the model state same way as above
												movePiece(model.getBoard().getSpace(piece.getLocation().x, piece.getLocation().y), model.getBoard().getSpace(origin.x, origin.y));
												if (temp != null) {
													model.getBoard().setPiece(temp);
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		
		// If everything else fails.
		return true;
	}
}
