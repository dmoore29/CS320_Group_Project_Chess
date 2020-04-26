package edu.ycp.cs320.Group_Project_Chess.controller;

import java.util.ArrayList;

import edu.ycp.cs320.Group_Project_Chess.database.DerbyDatabase;
import edu.ycp.cs320.Group_Project_Chess.model.Credentials;
import edu.ycp.cs320.Group_Project_Chess.model.User;

public class LoginController {
	private ArrayList<User> userList;
	private DerbyDatabase db;
	
	public LoginController() {
		db = new DerbyDatabase();
	}
	
	public boolean validLogin(Credentials login) {
		userList = db.findAllUsers();
		for (User user : userList) {
			if (user.getCredentials().getUsername().equals(login.getUsername()) &&
					user.getCredentials().getPassword().equals(login.getPassword())) {
				return true;
			}
		}
		
		
		
		return false;
	}

	// returns true if a user already exists in the database with the given username
	public boolean existingUsername(String username) {
		if (db.findUserwithUsername(username).getUserId() > 0) {
			System.out.println("User with username of " + username + " already exists");
			return true;
		}
		return false;
	}

	// returns true if a user already exists in the database with the given email
	public boolean existingEmail(String email) {
		if (db.findUserwithEmail(email).getUserId() > 0) {
			System.out.println("User with email of " + email + " already exists");
			return true;
		}
		return false;
	}

	// generates a new user into the database with the given credentials
	public void registerNewUser(Credentials credentials) {
		// TODO Auto-generated method stub
		
	}
}