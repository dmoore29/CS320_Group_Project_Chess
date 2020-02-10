package edu.ycp.cs320.lab02.controller;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.lab02a_dmoore29.controller.NumbersController;
import edu.ycp.cs320.lab02a_dmoore29.model.Numbers;

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
		double result = 180;
		assertTrue(result == model.getResult());
	}
}
