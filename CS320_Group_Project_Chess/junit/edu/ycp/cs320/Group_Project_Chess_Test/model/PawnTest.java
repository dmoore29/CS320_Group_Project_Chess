package edu.ycp.cs320.Group_Project_Chess_Test.model;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.Group_Project_Chess.model.Pawn;
import edu.ycp.cs320.Group_Project_Chess.model.Rank;





public class PawnTest {
	private Pawn pawn;
	
	@Before
	public void SetUp() {
		pawn = new Pawn(Rank.PAWN, 0, new Point(4, 0));
	}
	
	@Test
	public void TestGetRank() {
		assertEquals(pawn.getRank(), Rank.PAWN);
	}
	
	@Test
	public void TestGetColor() {
		assertEquals(pawn.getColor(), 0);
	}
	
	@Test
	public void TestGetLocation() {
		assertEquals(pawn.getLocation(), new Point(4, 0));
	}
}