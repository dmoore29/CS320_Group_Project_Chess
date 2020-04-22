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
}