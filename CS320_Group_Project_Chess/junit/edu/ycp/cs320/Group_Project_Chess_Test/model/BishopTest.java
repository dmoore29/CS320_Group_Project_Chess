package edu.ycp.cs320.Group_Project_Chess_Test.model;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.Group_Project_Chess.model.Bishop;
import edu.ycp.cs320.Group_Project_Chess.model.Rank;





public class BishopTest {
	private Bishop bishop;
	
	@Before
	public void SetUp() {
		bishop = new Bishop(Rank.BISHOP, 0, new Point(4, 0));
	}
	
	@Test
	public void TestGetRank() {
		assertEquals(bishop.getRank(), Rank.BISHOP);
	}
	
	@Test
	public void TestGetColor() {
		assertEquals(bishop.getColor(), 0);
	}
	
	@Test
	public void TestGetLocation() {
		assertEquals(bishop.getLocation(), new Point(4, 0));
	}
	
	@Test
	public void TestValidMove() {
		
	}
}