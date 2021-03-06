package edu.ycp.cs320.Group_Project_Chess_Test.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.Group_Project_Chess.model.Board;
import edu.ycp.cs320.Group_Project_Chess.model.Rank;
import edu.ycp.cs320.Group_Project_Chess.model.Rook;
import java.awt.Point;

public class BoardTest {
	private Board model;
	
	//creating the game board
	@Before
	public void setUp() {
		model = new Board();
		model.newGameBoard();
	}
	
	@Test
	public void TestSetPiece() { //also tests getPiece
		Rook rook = new Rook(Rank.ROOK, 0, new Point(4,4));
		model.setPiece(rook);
		assertTrue(rook == model.getPiece(4, 4));
	}
}
