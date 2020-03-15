package edu.ycp.cs320.Group_Project_Chess_Test.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.Group_Project_Chess.model.Stats;

public class StatsTest {
	private Stats model;
	
	@Before
	public void setUp() {
		model = new Stats();
	}
	
	@Test
	public void TestSetWins() {
		model.setWins(5);
		assertTrue(5 == model.getWins());
	}
	
	@Test
	public void TestSetLosses() {
		model.setLosses(50);
		assertTrue(50 == model.getLosses());
	}	
	
	@Test
	public void TestSetElo() {
		model.setElo(25);
		assertTrue(25 == model.getElo());
	}
}
