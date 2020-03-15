package edu.ycp.cs320.Group_Project_Chess.model;

public class User {
	private Credentials credentials;
	private Stats stats;
	private FriendsList friends;
	private Profile profile;
	
	public User(Credentials credentials, Stats stats, FriendsList friends, Profile profile) {
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
	
}
