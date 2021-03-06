package edu.ycp.cs320.Group_Project_Chess.model;
import java.awt.Point;

public class Board {
	//Create an 8x8 array of Space objects.
	//test comment
	private int boardId;
	private Space[][] board;
	
	public Board() {}
	
	public Board(int boardId) {
		this.boardId = boardId;
	}
	
	// Sets the board to be completely empty. The board will
	// have to be updated to include the starting pieces.
	public void newGameBoard() {
		board = new Space[8][8];
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[i].length; j++) {
				board[i][j] = new Space(null, new Point(i,j));
			}
		}
	}
	
	// Updates the current state of the board. This function
	// may not be useful, but its here in case we need it to 
	// send the board between two players.
	public void setBoard(Space[][] board) {
		this.board = board;
	}
	
	public Space getSpace(int xIndex, int yIndex) {
		return this.board[xIndex][yIndex];
	}
	
	// Each space on the board will have a enum value between 0 and 5. 
	// the 6 numbers will each represent a certain piece (pawn, rook, knight,
	// bishop, queen, or king.
	public void setPiece(Piece piece) {
		this.board[piece.getLocation().x][piece.getLocation().y].setPiece(piece);
	}
	
	// Returns the value of the piece occupying a specific space.
	public Piece getPiece(int x, int y) {
		return this.board[x][y].getPiece();
	}
	
	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	
	public int getBoardId() {
		return boardId;
	}
	
}
