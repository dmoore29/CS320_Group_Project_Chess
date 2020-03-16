package edu.ycp.cs320.Group_Project_Chess.controller;

import edu.ycp.cs320.Group_Project_Chess.model.Game; 
import edu.ycp.cs320.Group_Project_Chess.model.Space; 

public class GameController {
	private Game model;
	
	/**
	 * Set the model.
	 * 
	 * @param model the model to set
	 */
	public GameController(Game model) {
		this.model = model;
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
	
	/**
	 * Removes the piece from the board and sets it in the dead pile.
	 * 
	 * @param model   the model of the game
	 */
	public void capturePiece() {
		throw new UnsupportedOperationException("TODO - implement");
	}
}
