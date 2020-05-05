package edu.ycp.cs320.Group_Project_Chess.controller;

import edu.ycp.cs320.Group_Project_Chess.database.DerbyDatabase;
import edu.ycp.cs320.Group_Project_Chess.model.FriendsList;
import edu.ycp.cs320.Group_Project_Chess.model.User;

public class FriendsController {
	private User user;
	private DerbyDatabase db;
	
	public FriendsController() {
		db = new DerbyDatabase();
	}
	
	public FriendsList getFriends(String name) {
		user = db.findUserwithUsername(name);
		for (User users : user.getFriends().getFriendsList()) {
			System.out.println("Friend Found: " + users.getCredentials().getUsername());
		}
		return user.getFriends();
	}

}