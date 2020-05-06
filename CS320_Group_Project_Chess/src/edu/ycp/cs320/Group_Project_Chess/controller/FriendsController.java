package edu.ycp.cs320.Group_Project_Chess.controller;

import java.sql.SQLException;

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
	
	public void addFriend(String username) throws SQLException {
		User newFriend = db.findUserwithUsername(username);
		boolean existingFriend = false;
		for (User friend : user.getFriends().getFriendsList()) {
			if (newFriend.getUserId() == friend.getUserId()) {
				existingFriend = true;
			}
		}
		if (newFriend.getUserId() != user.getUserId() && !existingFriend) {
			db.addToFriends(user.getUserId(), newFriend.getUserId());
		}
	}
	
	public void removeFriend(int index) throws SQLException {
		db.removeFromFriends(user.getUserId(), user.getFriends().getIndex(index).getUserId());
	}

}