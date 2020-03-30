package edu.ycp.cs320.Group_Project_Chess.model;

import java.awt.Point;

public class Pawn extends Piece {

	public Pawn(Rank rank, int color, Point location) {
		super(rank, color, location);
	}

	@Override
	public  Boolean validMove(Point location, Board board) {
		return false; //FINISH IMPLEMENTING
	}
}