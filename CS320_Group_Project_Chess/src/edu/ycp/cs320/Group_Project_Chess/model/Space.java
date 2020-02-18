package edu.ycp.cs320.Group_Project_Chess.model;

public class Space {
	private Piece piece;
	private int x, y;
	
	
	// Each space contains a single piece, and each piece will 
	// contain a rank. Spaces that are not filled will be null.

	public Space(Piece piece, int x, int y) {
		this.piece = piece;
		this.x = x;
		this.y =y;
	}
	
	public Piece getPiece() {
		return this.piece;
	}
	public void setPiece(Piece temp) {
		this.piece = temp;
	}
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}
}
