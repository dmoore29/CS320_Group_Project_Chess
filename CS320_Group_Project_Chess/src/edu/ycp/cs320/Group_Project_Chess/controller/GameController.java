package edu.ycp.cs320.Group_Project_Chess.controller;

import java.sql.SQLException;

import edu.ycp.cs320.Group_Project_Chess.database.DerbyDatabase;
import edu.ycp.cs320.Group_Project_Chess.model.Game;
import edu.ycp.cs320.Group_Project_Chess.model.Piece;
import edu.ycp.cs320.Group_Project_Chess.model.Rank;
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
				if(model.getBoard().getPiece(i, j) != null) {	
					if( (model.getBoard().getSpace(i, j).getPiece().getColor() == player) && (model.getBoard().getSpace(i, j).getPiece().getRank() == Rank.KING) ) {
						king = model.getBoard().getSpace(i, j).getPiece();
					}
				}
			}
		}
		
		// Iterate through all of the player's chess pieces.
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				// If the space contains a piece.
				if(model.getBoard().getPiece(i, j) != null) {
					// If the piece that the space contains belongs to the opposing player.
					if((model.getBoard().getSpace(i, j).getPiece().getColor() != player)) {
						// If the opposing player's piece is able to move to the provided player's king, the current player is in check.
						if(model.getBoard().getSpace(i, j).getPiece().validMove(king.getLocation(), model.getBoard())) {
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
				if(model.getBoard().getPiece(i, j) != null) {	
					if( (model.getBoard().getSpace(i, j).getPiece().getColor() == player) && (model.getBoard().getSpace(i, j).getPiece().getRank() == Rank.KING) ) {
						king = model.getBoard().getSpace(i, j).getPiece();
					}
				}
			}
		}
		
		// Check possible moves using the X and Y location of the determined king.
		int kingX = king.getLocation().x;
		int kingY = king.getLocation().y;
		
		// Row above king AND if the piece is already in the top row.
		if(kingY != 0) {
			if(model.getBoard().getSpace(kingX, kingY - 1).getPiece() == null || model.getBoard().getSpace(kingX - 1, kingY - 1).getPiece() == null || model.getBoard().getSpace(kingX, kingY - 1).getPiece() == null) {
				return false;
			}
		}
		// Row below king AND if the piece is already in the bottom row.
		else if(kingX != 7){
			if(model.getBoard().getSpace(kingX, kingY + 1).getPiece() == null || model.getBoard().getSpace(kingX - 1, kingY + 1).getPiece() == null || model.getBoard().getSpace(kingX + 1, kingY + 1).getPiece() == null) {
				return false;
			}
		}
		// Left of king AND if the piece is to the left-most space.
		else if(model.getBoard().getPiece(kingX - 1, kingY) == null && kingX != 0) {
			return false;
		}
		// Right of king AND if the piece is to the right-most space.
		else if(model.getBoard().getPiece(kingX + 1, kingY) == null && kingX != 7) {
			return false;
		}
		// If everything else fails.
		return true;
	}
}
