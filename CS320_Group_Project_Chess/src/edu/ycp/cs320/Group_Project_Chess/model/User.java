package edu.ycp.cs320.Group_Project_Chess.model;

public class User {
	private int userId;
	private Credentials credentials;
	private Stats stats;
	private FriendsList friends;
	private Profile profile;
	
	public User() {
		friends = new FriendsList();
		credentials = new Credentials();
		stats = new Stats();
		profile = new Profile();
	}
	
	public User(Credentials credentials, Stats stats, FriendsList friends, Profile profile) {
		this.credentials = credentials;
		this.stats = stats;
		this.friends = friends;
		this.profile = profile;
	}
	
	public User(int userId, Credentials credentials, Stats stats, FriendsList friends, Profile profile) {
		this.userId = userId;
		this.credentials = credentials;
		this.stats = stats;
		this.friends = friends;
		this.profile = profile;
	}
	
	public Credentials getCredentials() {
		return this.credentials;
	}
	
	public Stats getStats() {
		return this.stats;
	}
	
	public FriendsList getFriends() {
		return this.friends;
	}
	
	public Profile getProfile() {
		return this.profile;
	}
	
	public void setUserId(int id) {
		userId = id;
	}
	
	public int getUserId() {
		return userId;
	}
}
