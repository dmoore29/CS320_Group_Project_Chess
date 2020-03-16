package edu.ycp.cs320.Group_Project_Chess.model;
import java.awt.Point;

public class Space {
	private Piece piece;
	private Point location;
	
	
	// Each space contains a single piece, and each piece will 
	// contain a rank. Spaces that are not filled will be null.

	public Space(Piece piece, Point location) {
		this.piece = piece;
		this.location = location;
	}
	
	public Piece getPiece() {
		return this.piece;
	}
	
	public void setPiece(Piece temp) {
		this.piece = temp;
	}
	
	public Point getLocation() {
		return location;
	}
}