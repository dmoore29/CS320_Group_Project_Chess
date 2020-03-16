package edu.ycp.cs320.Group_Project_Chess.model;

import java.awt.Point;

public class Rook extends Piece {

	public Rook(Rank rank, int color, Point location) {
		super(rank, color, location);
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub
		
	}
	
	public boolean isValid(Space origin, Space destination) {
		
		// If the origin and destination have the same x or y value. The move is valid.
		// This would indicate that the piece is moving horizontally or vertically.
		
		if((origin.getLocation().x == destination.getLocation().x) || (origin.getLocation().y == destination.getLocation().y)) {
			return true;
		}
		else {
			return false;
		}
	}
	
}