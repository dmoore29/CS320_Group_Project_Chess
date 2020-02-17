package edu.ycp.cs320.Group_Project_Chess.model;

// model class for Numbers
// only the controller should be allowed to call the set methods
// the JSP will call the "get" and "is" methods implicitly
// when the JSP specifies game.min, that gets converted to
//    a call to model.getMin()

public class Numbers {
	private double first, second, third, result;
	
	public Numbers() {
	}
	
	public void setFirst(double first) {
		this.first = first;
	}
	
	public double getFirst() {
		return first;
	}
	
	public void setSecond(double second) {
		this.second = second;
	}
	
	public double getSecond() {
		return second;
	}
	
	public void setThird(double third) {
		this.third = third;
	}
	
	public double getThird() {
		return third;
	}
	
	
	public void setResult(double result) {
		this.result = result;
	}
	
	public double getResult() {
		return result;
	}
}
