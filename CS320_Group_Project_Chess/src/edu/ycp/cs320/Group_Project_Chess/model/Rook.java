package edu.ycp.cs320.Group_Project_Chess.model;

import java.awt.Point;

public class Rook extends Piece {

	public Rook(Rank rank, int color, Point location) {
		super(rank, color, location);
	}
	//black = 1, white = 0

	@Override
	public  Boolean validMove(Point dest, Board board) {
		
		if(location.x == dest.x && location.y == dest.y) {
			return false;
		}
		
		if(location.x != dest.x && location.y != dest.y) { //if diagonal
			return false;
		}
		
		if(location.x < dest.x) { //if there is a piece in between source and destination on x axis
			for(int i = location.x+1; i< dest.x; i++) {
				if(board.getSpace((int)dest.getX(), (int)dest.getY()).getPiece() != null) {
					return false;
				}
			}
		}
		
		if(location.x > dest.x) { //if there is a piece in between source and destination on x axis
			for(int i = dest.x+1; i < location.x; i++) {
				if(board.getSpace((int)dest.getX(), (int)dest.getY()).getPiece() != null) {
					return false;
				}
			}
		}
		
		if(location.y < dest.y) { //if there is a piece in between source and destination on y axis
			for(int i = location.y+1; i< dest.y; i++) {
				if(board.getSpace((int)dest.getX(), (int)dest.getY()).getPiece() != null) {
					return false;
				}
			}
		}
		
		if(location.y > dest.y) { //if there is a piece in between source and destination on y axis
			for(int i = dest.y+1; i < location.y; i++) {
				if(board.getSpace((int)dest.getX(), (int)dest.getY()).getPiece() != null) {
					return false;
				}
			}
		}
		
		if(board.getSpace(dest.x, dest.y).getPiece() != null) {
			if(board.getSpace(dest.x, dest.y).getPiece().getColor() == color) {
				return false;
			}
		}
		
		return true;
	}
}