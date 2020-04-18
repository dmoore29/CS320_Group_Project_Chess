package edu.ycp.cs320.Group_Project_Chess.model;

public class Player{
	private int playerId;
	private int color;
	private User user;
	
	public Player(User user, int color) {
		this.user = user;
		this.color = color;
	}
	
	public Player(int playerId) {
		this.playerId = playerId;
	}
	
	public int getColor() {
		return color;
	}
	
	public User getUser() {
		return user;
	}
	
	public int getPlayerId() {
		return playerId;
	}
	
	public void setPlayerId(int id) {
		playerId = id;
	}
}