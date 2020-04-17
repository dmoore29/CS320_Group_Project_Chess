package edu.ycp.cs320.Group_Project_Chess.model;

//this is the worst piece

import java.awt.Point;
import java.lang.Math;

public class Bishop extends Piece {

	public Bishop(Rank rank, int color, Point location) {
		super(rank, color, location);
	}

	@Override
	public  Boolean validMove(Point dest, Board board) {
		
		if(location.x == dest.x && location.y == dest.y) { //if same place
			return false;
		}
		
		if(Math.abs(location.x - dest.x) != Math.abs(location.y - dest.y)) { //move is diagonal
			return false;
		}
		
		if(board.getSpace(dest.x, dest.y).getPiece() != null) { //if trying to capture piece of same color
			if(board.getSpace(dest.x, dest.y).getPiece().getColor() == board.getSpace(location.x, location.y).getPiece().getColor()) {
				return false;
			}
		}
		
		for(int i=1; i< Math.abs(location.x - dest.x); i++) { //in between spaces
			if(location.x < dest.x
					&& location.y < dest.y) { //prevents crash
				if(board.getSpace(location.x+i, location.y+i).getPiece() != null) { //down, right
				return false;
				}
			}
			if(location.x < dest.x
					&& location.y > dest.y) {
				if(board.getSpace(location.x+i, location.y-i).getPiece() != null) { //down, left
				return false;
				}
			}
			if(location.x > dest.x
					&& location.y < dest.y) {
				if(board.getSpace(location.x-i, location.y+i).getPiece() != null) { //up, right
				return false;
				}
			}
			if(location.x > dest.x
					&& location.y > dest.y) {
				if(board.getSpace(location.x-i, location.y-i).getPiece() != null) { //up, left
				return false;
				}
			}
		}
		return true; //FINISH IMPLEMENTING
	}
}