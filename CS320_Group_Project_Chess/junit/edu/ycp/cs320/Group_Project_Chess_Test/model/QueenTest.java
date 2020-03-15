package edu.ycp.cs320.Group_Project_Chess_Test.model;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.Group_Project_Chess.model.Queen;
import edu.ycp.cs320.Group_Project_Chess.model.Rank;





public class QueenTest {
	private Queen queen;
	
	@Before
	public void SetUp() {
		queen = new Queen(Rank.QUEEN, 0, new Point(4, 0));
	}
	
	@Test
	public void TestGetRank() {
		assertEquals(queen.getRank(), Rank.QUEEN);
	}
	
	@Test
	public void TestGetColor() {
		assertEquals(queen.getColor(), 0);
	}
	
	@Test
	public void TestGetLocation() {
		assertEquals(queen.getLocation(), new Point(4, 0));
	}
}