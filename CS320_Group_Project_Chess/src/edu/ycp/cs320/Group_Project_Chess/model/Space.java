package edu.ycp.cs320.Group_Project_Chess.model;
import java.awt.Point;

public class Space {
	private Piece piece;
	private int startX, endX, startY, endY;
	private Point location;
	
	
	// Each space contains a single piece, and each piece will 
	// contain a rank. Spaces that are not filled will be null.

	public Space(Piece piece, int startX, int endX, int startY, int endY, Point location) {
		this.piece = piece;
		this.startX = startX;
		this.endX = endX;
		this.startY = startY;
		this.endY = endY;
		this.location = location;
	}
	
	public Piece getPiece() {
		return this.piece;
	}
	
	public void setPiece(Piece temp) {
		this.piece = temp;
	}
	
	public int getStartX() {
		return this.startX;
	}
	
	public int getEndX() {
		return this.endX;
	}
	
	public int getStartY() {
		return this.startY;
	}
	
	public int getEndY() {
		return this.endY;
	}
	
	public Point getLocation() {
		return location;
	}
	
	
	// Check to see if the mouse cursor is inside of the space.
	// This will be used to determine the space that the player wants to select.
	public boolean isInside(int mouseX, int mouseY) {
		if((this.startX < mouseX && mouseX < this.endX) && (this.startY < mouseY && mouseY < this.endY)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	// Returns the space's place inside of the 'Board' array.
	
	public int getArrayLocationX() {
		return this.startX / 100;
	}
	
	public int getArrayLocationY() {
		return this.startY / 100;
	}
	
}
