package edu.ycp.cs320.Group_Project_Chees.controller;

import edu.ycp.cs320.Group_Project_Chees.model.Numbers;

public class GameController {
	private Numbers model;
	
	/**
	 * Set the model.
	 * 
	 * @param model the model to set
	 */
	public void setModel(Numbers model) {
		this.model = model;
	}
	
	/**
	 * Return true if the intended move is allowed.
	 * 
	 * @param model   the model of the game
	 * @return false if the intended move is not allowed,
	 *     true if the intended move is allowed.
	 */
	public boolean validMove(Numbers model) {
		throw new UnsupportedOperationException("TODO - implement");
	}
	
	/**
	 * Moves the piece to intended location.
	 * 
	 * @param model   the model of the game
	 */
	public void movePiece(Numbers model) {
		throw new UnsupportedOperationException("TODO - implement");
	}
	
	/**
	 * Checks to see if the previous move set the other player in check.
	 * 
	 * @param model   the model of the game
	 * @return false if the move did not set the player in check,
	 *     true if the move did set the player in check.
	 */
	public boolean check(Numbers model) {
		throw new UnsupportedOperationException("TODO - implement");
	}
	
	/**
	 * Checks to see if the previous move set the other player in checkmate.
	 * 
	 * @param model   the model of the game
	 * @return false if the move did not set the player in checkmate,
	 *     true if the move did set the player in checkmate.
	 */
	public boolean checkmate(Numbers model) {
		throw new UnsupportedOperationException("TODO - implement");
	}
	
	/**
	 * Removes the piece from the board and sets it in the dead pile.
	 * 
	 * @param model   the model of the game
	 */
	public void killPiece(Numbers model) {
		throw new UnsupportedOperationException("TODO - implement");
	}
}
