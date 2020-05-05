package edu.ycp.cs320.Group_Project_Chess.model;

import java.util.ArrayList;

public class FriendsList {
	private ArrayList<String> friendsList;
	
	public FriendsList() {
		friendsList = new ArrayList<String>();
	}
	
	public ArrayList<String> getFriendsList() {
		return this.friendsList;
	}
	
	
	public void addFriend(String friend) {
		this.friendsList.add(friend);
	}
	
	public void removeFriend(String friend) {
		this.friendsList.remove(friend);
	}
	
	public String getIndex(int indexNumber) {
		return this.friendsList.get(indexNumber);
	}
	

}
