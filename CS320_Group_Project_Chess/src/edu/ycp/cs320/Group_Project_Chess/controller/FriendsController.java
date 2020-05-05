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
		return user.getFriends();
	}

}