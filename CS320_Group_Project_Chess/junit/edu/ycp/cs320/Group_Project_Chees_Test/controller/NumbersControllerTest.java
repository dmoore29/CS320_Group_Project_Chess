package edu.ycp.cs320.Group_Project_Chees_Test.controller;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.Group_Project_Chess.controller.NumbersController;
import edu.ycp.cs320.Group_Project_Chess.model.Numbers;

public class NumbersControllerTest {
	private Numbers model;
	private NumbersController controller;
	
	@Before
	public void setUp() {
		model = new Numbers();
		controller = new NumbersController();
		
		model.setFirst(4);
		model.setSecond(9);
		model.setThird(5);
		
		controller.setModel(model);
	}
	
	@Test
	public void testAdd() {
		controller.add(model.getFirst(), model.getSecond(), model.getThird());
		double result = 18;
		assertTrue(result == model.getResult());
	}
	
	@Test
	public void testMultiply() {
		controller.multiply(model.getFirst(), model.getSecond());
		double result = 36;
		assertTrue(result == model.getResult());
	}
}
