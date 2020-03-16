package edu.ycp.cs320.Group_Project_Chess_Test.model;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.Group_Project_Chess.model.Space;
import edu.ycp.cs320.Group_Project_Chess.model.King;
import edu.ycp.cs320.Group_Project_Chess.model.Queen;
import edu.ycp.cs320.Group_Project_Chess.model.Rank;





public class SpaceTest {
	private Space model;
	private King king;
	private Queen queen;
	
	@Before
	public void SetUp() {
		king = new King(Rank.KING, 0, new Point(4, 0));
		queen = new Queen(Rank.QUEEN, 1, new Point (4,1));
		model = new Space(queen, queen.getLocation());
	}
	
	@Test
	public void testSetPiece() {
		model.setPiece(king);
		assertEquals(king, model.getPiece());
	}
	
	@Test
	public void testSetLocation() {
		model.setLocation(king.getLocation());
		assertEquals(new Point(4,0), model.getLocation());
	}
}