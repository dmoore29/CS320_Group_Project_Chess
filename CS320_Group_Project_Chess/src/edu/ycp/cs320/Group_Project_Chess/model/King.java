package edu.ycp.cs320.Group_Project_Chess.model;

import java.awt.Point;

public class King extends Piece {

	public King(Rank rank, int color, Point location) {
		super(rank, color, location);
	}

	@Override
	public  Boolean validMove(Point dest, Board board) {
		int xDifference = location.x - dest.x;
		int yDifference = location.y - dest.y;
		
		
		// These statements are written separately because it is checking for the Y difference 
		// and THEN checking for the X difference. This can't be done in an OR statement.
		
		if(Math.abs(yDifference) == 1) {
			return true;
		}
		else if(Math.abs(xDifference) == 1) {
			return true;
		}
		else {
			return false;
		}
	}
}