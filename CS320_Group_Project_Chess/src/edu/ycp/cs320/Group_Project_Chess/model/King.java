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
		
		

		
		// Check to see if the destination contains a piece of the same color.
		// Check to see if the space is empty or not.
		if(board.getSpace(dest.x, dest.y).getPiece() != null) {	
			if(board.getSpace(dest.x, dest.y).getPiece().getColor() == color) {
				return false;
			}
		}
		else if((Math.abs(yDifference) == 1 && Math.abs(xDifference) == 0) || (Math.abs(yDifference) == 0 && Math.abs(xDifference) == 1) || (Math.abs(yDifference) == 1 && Math.abs(xDifference) == 1) ) {
			return true;
		}
		else {
			return false;
		}
		return false;
	}
}