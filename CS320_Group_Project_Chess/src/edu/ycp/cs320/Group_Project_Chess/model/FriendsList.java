package edu.ycp.cs320.Group_Project_Chess.model;

import java.util.ArrayList;

public class FriendsList {
	private ArrayList<User> friendsList;
	
	public FriendsList() {
		friendsList = new ArrayList<User>();
	}
	
	public ArrayList<User> getFriendsList() {
		return this.friendsList;
	}
	
	
	public void addFriend(User friend) {
		this.friendsList.add(friend);
	}
	
	public void removeFriend(User friend) {
		this.friendsList.remove(friend);
	}
	
	public User getIndex(int indexNumber) {
		return this.friendsList.get(indexNumber);
	}
	

}
