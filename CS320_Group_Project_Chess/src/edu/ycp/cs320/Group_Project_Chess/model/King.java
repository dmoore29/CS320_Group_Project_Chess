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
		//
		// These check the amount of spaces being moved in each direction.
		
		if(Math.abs(yDifference) == 1) {
			return true;
		}
		else if(Math.abs(xDifference) == 1) {
			return true;
		}
		
		// Check to see if the destination contains a piece of the same color.
		else if(board.getSpace(dest.x, dest.y).getPiece().getColor() == color) {
			return false;
		}
		else {
			return false;
		}
	}
}