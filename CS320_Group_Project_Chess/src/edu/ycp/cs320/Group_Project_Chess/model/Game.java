package edu.ycp.cs320.Group_Project_Chess.model;

public class Game {
	
	private BoardModel board;
	private Piece[] capturedP1;
	private Piece[] capturedP2;


	
	// Updates the current state of the board. This function
	// may not be useful, but its here in case we need it to 
	// send the board between two players.
	public void setBoard(BoardModel board) {
		this.board = board;
	}
	
	public BoardModel getBoard() {
		return board;
	}
	
	public void addCapturedP1() {
		throw new UnsupportedOperationException("TODO - implement");
	}
	
	public Piece[] getCapturedP1() {
		throw new UnsupportedOperationException("TODO - implement");
	}
	
	public Piece[] getCapturedP2() {
		throw new UnsupportedOperationException("TODO - implement");
	}
	
}
