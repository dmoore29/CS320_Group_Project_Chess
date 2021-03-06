package edu.ycp.cs320.Group_Project_Chess_Test.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.Group_Project_Chess.model.Numbers;

public class NumbersTest {
	private Numbers model;
	
	@Before
	public void setUp() {
		model = new Numbers();
	}
	
	@Test
	public void testSetFirst() {
		model.setFirst(5);
		assertTrue(5 == model.getFirst());
	}
	
	@Test
	public void testSetSecond() {
		model.setSecond(50);
		assertTrue(50 == model.getSecond());
	}	
	
	@Test
	public void testSetThird() {
		model.setThird(25);
		assertTrue(25 == model.getThird());
	}
	
	@Test
	public void testSetResult() {
		model.setResult(62);
		assertTrue(62 == model.getResult());
	}
}
