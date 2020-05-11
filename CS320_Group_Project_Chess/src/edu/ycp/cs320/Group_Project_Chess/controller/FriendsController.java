package edu.ycp.cs320.Group_Project_Chess.controller;

import java.sql.SQLException;

import edu.ycp.cs320.Group_Project_Chess.database.DerbyDatabase;
import edu.ycp.cs320.Group_Project_Chess.model.FriendsList;
import edu.ycp.cs320.Group_Project_Chess.model.User;

public class FriendsController {
	private User user;
	private DerbyDatabase db;
	
	// initializes the database
	public FriendsController() {
		db = new DerbyDatabase();
	}
	
	// uses the database to find a user with given username to set the stored User field
	public void setUser(String username) {
		user = db.findUserwithUsername(username);
	}
	
	// returns the FriendsList of all the found user's friends
	public FriendsList getFriends(String name) {
		
		// uses the given name to load a user (which also inherently loads that user's friends list)
		user = db.findUserwithUsername(name);
		
		// prints all the found friends
		for (User users : user.getFriends().getFriendsList()) {
			System.out.println("Friend Found: " + users.getCredentials().getUsername());
		}
		
		// returns the FriendsList
		return user.getFriends();
	}
	
	// adds a friend to the stored User's FriendsList
	public void addFriend(String username) throws SQLException {
		
		// locates the new friend using the given username
		User newFriend = db.findUserwithUsername(username);
		
		// tests if a user was returned
		if (newFriend != null) {
			
			// a boolean for determining if the found user already exists in the current FriendsList
			boolean existingFriend = false;
			
			// compares the new friend to each friend in the current FriendsList
			for (User friend : user.getFriends().getFriendsList()) {
				if (newFriend.getUserId() == friend.getUserId()) {
					
					// set to true if the new friend already was in the FriendsList
					existingFriend = true;
				}
			}
			
			// tests existingFriend and that the new friend is not equal to the stored User
			if (newFriend.getUserId() != user.getUserId() && !existingFriend) {
				
				// adds the new friend to the FriendsList
				db.addToFriends(user.getUserId(), newFriend.getUserId());
			}
		}
	}
	
	// removes a friend from the FriendsList given the friends username
	public void removeFriend(String username) throws SQLException {
		db.removeFromFriends(user.getUserId(), db.findUserwithUsername(username).getUserId());
	}

}