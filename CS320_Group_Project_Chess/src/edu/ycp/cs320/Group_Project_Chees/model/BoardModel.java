package edu.ycp.cs320.Group_Project_Chees.model;

public class BoardModel {
	//Create an 8x8 array of Space objects.
	private Space[][] board;
	
	// Sets the board to be completely empty. The board will
	// have to be updated to include the starting pieces.
	public void populateBoard() {
		board = new Space[8][8];
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				board[i][j] = null;
			}
		}
	}
	
	// Updates the current state of the board. This function
	// may not be useful, but its here in case we need it to 
	// send the board between two players.
	public void setBoard(Space[][] board) {
		this.board = board;
	}
	
	// Each space on the board will have a enum value between 0 and 5. 
	// the 6 numbers will each represent a certain piece (pawn, rook, knight,
	// bishop, queen, or king.
	public void setPiece(int x, int y, Piece piece) {
		this.board[x][y].setPiece(piece); ;
	}
	
	
	// Returns the value of the piece occupying a specific space.
	public Piece getPiece(int x, int y) {
		return this.board[x][y].getPiece();
	}
}
