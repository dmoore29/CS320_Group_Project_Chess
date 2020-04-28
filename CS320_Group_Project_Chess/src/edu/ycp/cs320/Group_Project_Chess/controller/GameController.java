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
	 * Return true if the intended move is allowed.
	 * 
	 * @param model   the model of the game
	 * @param origin	the starting space
	 * @param destination	the intended move space 
	 * @return false if the intended move is not allowed,
	 *     true if the intended move is allowed.
	 */
	public boolean validMove(Space origin, Space destination) {
		throw new UnsupportedOperationException("TODO - implement");
//		model.getBoard().get
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
		
		for (int y = -1; y < 2; y++) {
			for (int x = -1; x < 2; x++) {
				if ((x + king.getLocation().x >= 0 && x + king.getLocation().x <= 7) && (y + king.getLocation().y >= 0 && y + king.getLocation().y <= 7) && !(x == 0 && y == 0)) {
					System.out.println((x + king.getLocation().x) + " " + (y + king.getLocation().y));
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
						if (!check(king.getColor())) {
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
		}
		// If everything else fails.
		return true;
	}
}
