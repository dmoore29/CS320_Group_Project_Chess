package edu.ycp.cs320.Group_Project_Chess_Test.model;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.Group_Project_Chess.model.Knight;
import edu.ycp.cs320.Group_Project_Chess.model.Rank;





public class KnightTest {
	private Knight knight;
	
	@Before
	public void SetUp() {
		knight = new Knight(Rank.KNIGHT, 0, new Point(4, 0));
	}
	
	@Test
	public void TestGetRank() {
		assertEquals(knight.getRank(), Rank.KNIGHT);
	}
	
	@Test
	public void TestGetColor() {
		assertEquals(knight.getColor(), 0);
	}
	
	@Test
	public void TestGetLocation() {
		assertEquals(knight.getLocation(), new Point(4, 0));
	}
}