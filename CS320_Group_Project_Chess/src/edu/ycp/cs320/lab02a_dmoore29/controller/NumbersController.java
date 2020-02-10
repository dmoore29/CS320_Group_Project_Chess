package edu.ycp.cs320.lab02a_dmoore29.controller;

import edu.ycp.cs320.lab02a_dmoore29.model.Numbers;

public class NumbersController {
	private Numbers model;
	
	/**
	 * Set the model.
	 * 
	 * @param model the model to set
	 */
	public void setModel(Numbers model) {
		this.model = model;
	}
	
	public void add(Double first, Double second, Double third) {
		model.setResult(first + second + third);
	}
	
	public void multiply(Double first, Double second) {
		model.setResult(first * second);
	}
}
