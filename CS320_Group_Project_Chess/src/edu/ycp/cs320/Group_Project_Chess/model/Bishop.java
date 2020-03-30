package edu.ycp.cs320.Group_Project_Chess.model;

import java.awt.Point;

public class Bishop extends Piece {

	public Bishop(Rank rank, int color, Point location) {
		super(rank, color, location);
	}

	@Override
	public  Boolean validMove(Point location, Board board) {
		return false; //FINISH IMPLEMENTING
	}
}