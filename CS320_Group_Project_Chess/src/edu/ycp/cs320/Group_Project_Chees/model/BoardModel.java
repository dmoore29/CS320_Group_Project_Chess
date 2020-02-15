package edu.ycp.cs320.Group_Project_Chees.model;

// This is a quick model. After writing this I realized that we need 
// a Piece class and potentially a Space class.



public class BoardModel {
	//Create an 8x8 array.
	private int[][] board;
	
	// Sets the board to be completely empty. The board will
	// have to be updated to include the starting pieces.
	public void populateBoard() {
		board = new int[8][8];
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				board[i][j] = 0;
			}
		}
	}
	
	// Updates the current state of the board. This function
	// may not be useful, but its here in case we need it to 
	// send the board between two players.
	public void setBoard(int[][] board) {
		this.board = board;
	}
	
	// Each space on the board will have a value between 0 and 6. 
	// 0 is to represent an empty space, while the other 6 numbers
	// will each represent a certain piece (pawn, rook, knight,
	// bishop, queen, or king.
	public void setPiece(int x, int y, int pieceRank) {
		this.board[x][y] = pieceRank;
	}
	
	
	// Returns the value of the piece occupying a specific space.
	public int getPiece(int x, int y) {
		return this.board[x][y];
	}
}
