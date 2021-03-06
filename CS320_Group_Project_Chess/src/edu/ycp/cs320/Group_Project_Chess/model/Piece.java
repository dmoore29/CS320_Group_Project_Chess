package edu.ycp.cs320.Group_Project_Chess.model;

import java.awt.Point;

public abstract class Piece {
	protected Rank rank;
	protected int color; //1 for black, 0 for white
	protected Point location; //x , y; 0-7 , 0-7
	
	// Each piece will contain a rank and a player value. Higher 
	// ranking pieces will be able to over take lower ranking pieces
	// as long as the piece has a different "player" value.
	
	public Piece(Rank rank, int color, Point location) {
		this.rank = rank;
		this.color = color;
		this.location = location;
	}
	
	public void setRank(Rank rank) {
		this.rank = rank;
	}
	public Rank getRank() {
		return rank;
	}
	
	public void setColor(int color) {
		this.color = color;
	}
	
	public int getColor() {
		return color;
	}
	
	public void setLocation(Point location) {
		this.location = location;
	}
	
	public Point getLocation() {
		return location;
	}
	
	public abstract Boolean validMove(Point dest, Board board);
}
