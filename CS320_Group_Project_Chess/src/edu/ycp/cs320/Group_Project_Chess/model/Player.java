package edu.ycp.cs320.Group_Project_Chess.model;

public class Player{
	private int Color;
	private User user;
	
	public Player(User user, int Color) {
		this.user = user;
		this.Color = Color;
	}
	
	public int getColor() {
		return Color;
	}
	
	public User getUser() {
		return user;
	}
}