package edu.ycp.cs320.Group_Project_Chess.model;

public class Player{
	private int color;
	private User user;
	
	public Player(User user, int color) {
		this.user = user;
		this.color = color;
	}
	
	public int getColor() {
		return color;
	}
	
	public User getUser() {
		return user;
		
	}
}