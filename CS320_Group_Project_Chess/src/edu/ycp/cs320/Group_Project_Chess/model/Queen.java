package edu.ycp.cs320.Group_Project_Chess.model;

import java.awt.Point;

public class Queen extends Piece {

	public Queen(Rank rank, int color, Point location) {
		super(rank, color, location);
	}

	@Override
	public  Boolean validMove(Point dest, Board board) {
		System.err.println("Ayyyy it's red");
		System.err.println("Ayyyy it's red");
		System.err.println("Ayyyy it's red");
		System.err.println("Ayyyy it's red");		
		System.err.println("Ayyyy it's red");
		System.err.println("Ayyyy it's red");
		
		if(board.getSpace(dest.x, dest.y).getPiece() != null) {
			if(board.getSpace(dest.x, dest.y).getPiece().getColor() == color) {
				return false;
			}
		}

		if(location.y == dest.y) { //if moving along x axis
			if(location.x < dest.x) { //if there is a piece in between source and destination on x axis
				for(int i = location.x+1; i< dest.x; i++) {
					if(board.getSpace(i, dest.y).getPiece() != null) {
						return false;
					}
				}
			}
			if(location.x > dest.x) { //if there is a piece in between source and destination on x axis
				for(int i = dest.x+1; i < location.x; i++) {
					if(board.getSpace(i, dest.y).getPiece() != null) {
						return false;
					}
				}
			}
			return true;
		} else if(location.x == dest.x) { //if moving along y axis
			if(location.y < dest.y) { //if there is a piece in between source and destination on y axis
				for(int i = location.y+1; i< dest.y; i++) {
					if(board.getSpace(dest.x, i).getPiece() != null) {
						return false;
					}
				}
			}
			
			if(location.y > dest.y) { //if there is a piece in between source and destination on y axis
				for(int i = dest.y+1; i < location.y; i++) {
					if(board.getSpace(dest.x, i).getPiece() != null) {
						return false;
					}
				}
			}
			return true;
		}
		
		
		if(Math.abs(location.x - dest.x) != Math.abs(location.y - dest.y)) { //move is diagonal
			return false;
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
			return true;
		}
		
		return false;
	}
}