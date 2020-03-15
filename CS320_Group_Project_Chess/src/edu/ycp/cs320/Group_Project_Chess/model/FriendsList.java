package edu.ycp.cs320.Group_Project_Chess.model;

import java.util.ArrayList;

public class FriendsList {
	private ArrayList<Friend> friendsList = new ArrayList<Friend>();
	
	public FriendsList() {
		
	}
	
	public void addFriend(Friend friend) {
		this.friendsList.add(friend);
	}
	
	public void removeFriend(Friend friend) {
		this.friendsList.remove(friend);
	}

}
