package edu.ycp.cs320.Group_Project_Chess.model;

import java.awt.Point;

public class Queen extends Piece {

	public Queen(Rank rank, int color, Point location) {
		super(rank, color, location);
	}

	@Override
	public  Boolean validMove(Point location) {
		return false; //FINISH IMPLEMENTING
	}
}