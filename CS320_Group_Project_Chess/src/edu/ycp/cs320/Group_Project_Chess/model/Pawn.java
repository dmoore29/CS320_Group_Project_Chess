package edu.ycp.cs320.Group_Project_Chess.model;

import java.awt.Point;

public class Pawn extends Piece {

	public Pawn(Rank rank, int color, Point location) {
		super(rank, color, location);
	}
	//black = 1, white = 0

	@Override
	public  Boolean validMove(Point dest, Board board) {
		if(color == 1 //piece is black, moving 1 down
				&& board.getSpace((int)dest.getX(), (int)dest.getY()).getPiece() == null 
				&& dest.getY() == location.getY()+1
				&& dest.x == location.x) {
			return true;
		} else if (color == 0 //piece is white, moving 1 up
				&& board.getSpace((int)dest.getX(), (int)dest.getY()).getPiece() == null 
				&& dest.getY() == location.getY()-1
				&& dest.x == location.x) {
			return true;
		} else if (color == 1 //piece is black, moving 2 down from start
				&& board.getSpace((int)dest.getX(), (int)dest.getY()).getPiece() == null 
				&& dest.getY() == location.getY()+2
				&& location.getY() == 1
				&& board.getSpace((int)dest.getX(), (int)dest.getY()-1).getPiece() == null) {
			return true;
		} else if (color == 0 //piece is white, moving 2 up from start
				&& board.getSpace((int)dest.getX(), (int)dest.getY()).getPiece() == null 
				&& dest.getY() == location.getY()-2
				&& location.getY() == 6
				&& board.getSpace((int)dest.getX(), (int)dest.getY()+1).getPiece() == null) {
			return true;			
		} else if (color == 1 //piece is black, captures piece down 1 and 1 to either side
				&& (dest.x == location.x+1 || dest.x == location.x-1)
				&& dest.y == location.y+1
				&& board.getSpace((int)dest.getX(), (int)dest.getY()).getPiece() != null ){
			if(board.getSpace(dest.x, dest.y).getPiece().getColor() != color) {
				return true;
			}
		} else if (color == 0 //piece is white, captures piece up 1 and 1 to either side
				&& (dest.x == location.x+1 || dest.x == location.x-1)
				&& dest.y == location.y-1
				&& board.getSpace((int)dest.getX(), (int)dest.getY()).getPiece() != null ){
			if(board.getSpace(dest.x, dest.y).getPiece().getColor() != color) {
				return true;
			} else {
				return false;
			}
		}
		return false;
	}
}