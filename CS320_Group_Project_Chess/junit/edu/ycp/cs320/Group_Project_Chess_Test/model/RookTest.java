package edu.ycp.cs320.Group_Project_Chess_Test.model;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.Group_Project_Chess.model.Rook;
import edu.ycp.cs320.Group_Project_Chess.model.Rank;





public class RookTest {
	private Rook rook;
	
	@Before
	public void SetUp() {
		rook = new Rook(Rank.ROOK, 0, new Point(4, 0));
	}
	
	@Test
	public void TestGetRank() {
		assertEquals(rook.getRank(), Rank.ROOK);
	}
	
	@Test
	public void TestGetColor() {
		assertEquals(rook.getColor(), 0);
	}
	
	@Test
	public void TestGetLocation() {
		assertEquals(rook.getLocation(), new Point(4, 0));
	}
	
	@Test
	public void TestValidMove() {
		
	}
}