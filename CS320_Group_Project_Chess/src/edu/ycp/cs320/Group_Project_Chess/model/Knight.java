package edu.ycp.cs320.Group_Project_Chess.model;

import java.awt.Point;

public class Knight extends Piece {

	public Knight(Rank rank, int color, Point location) {
		super(rank, color, location);
	}

	@Override
	public  Boolean validMove(Point dest, Board board) {
		if(board.getSpace(dest.x, dest.y).getPiece() != null) {
			if(board.getSpace(dest.x, dest.y).getPiece().getColor() == color) {
				return false;
			}
		}
		if(dest.x == location.x + 1 && dest.y == location.y+2) { //up2, r1
			return true;
		}
		if(dest.x == location.x + 2 && dest.y == location.y+1) { //up1, r2
			return true;
		}
		if(dest.x == location.x + 2 && dest.y == location.y-1) { //down1, r2
			return true;
		}
		if(dest.x == location.x + 1 && dest.y == location.y-2) { //down2, r1
			return true;
		}
		if(dest.x == location.x - 1 && dest.y == location.y-2) { //down2, l1
			return true;
		}
		if(dest.x == location.x - 2 && dest.y == location.y-1) { //down1, l2
			return true;
		}
		if(dest.x == location.x - 2 && dest.y == location.y+1) { //up1, l2
			return true;
		}
		if(dest.x == location.x - 1 && dest.y == location.y+2) { //up2, l1
			return true;
		}
		return false; //FINISH IMPLEMENTING
	}
}