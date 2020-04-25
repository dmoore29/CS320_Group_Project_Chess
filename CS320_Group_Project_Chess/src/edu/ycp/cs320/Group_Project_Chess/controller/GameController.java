package edu.ycp.cs320.Group_Project_Chess.controller;

import java.sql.SQLException;

import edu.ycp.cs320.Group_Project_Chess.database.DerbyDatabase;
import edu.ycp.cs320.Group_Project_Chess.model.Game;
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
	public boolean check() {
		throw new UnsupportedOperationException("TODO - implement");
	}
	
	/**
	 * Checks to see if the previous move set the other player in checkmate.
	 * 
	 * @param model   the model of the game
	 * @return false if the move did not set the player in checkmate,
	 *     true if the move did set the player in checkmate.
	 */
	public boolean checkmate() {
		throw new UnsupportedOperationException("TODO - implement");
	}
}
