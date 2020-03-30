package edu.ycp.cs320.Group_Project_Chess_Test.model;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.Group_Project_Chess.model.King;
import edu.ycp.cs320.Group_Project_Chess.model.Rank;





public class KingTest {
	private King king;
	
	@Before
	public void SetUp() {
		king = new King(Rank.KING, 0, new Point(4, 0));
	}
	
	@Test
	public void TestGetRank() {
		assertEquals(king.getRank(), Rank.KING);
	}
	
	@Test
	public void TestGetColor() {
		assertEquals(king.getColor(), 0);
	}
	
	@Test
	public void TestGetLocation() {
		assertEquals(king.getLocation(), new Point(4, 0));
	}
	
	@Test
	public void TestValidMove() {
		
	}
}