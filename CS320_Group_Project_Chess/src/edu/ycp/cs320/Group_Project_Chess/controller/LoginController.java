package edu.ycp.cs320.Group_Project_Chess.controller;

import java.util.ArrayList;

import edu.ycp.cs320.Group_Project_Chess.database.FakeDatabase;
import edu.ycp.cs320.Group_Project_Chess.model.Credentials;
import edu.ycp.cs320.Group_Project_Chess.model.User;

public class LoginController {
	private ArrayList<User> userList;
	
	public LoginController(FakeDatabase database) {
		userList = database.getUserList();
	}
	
	public boolean validLogin(Credentials login) {
		for (User user : userList) {
			if (user.getCredentials().getUsername().equals(login.getUsername()) &&
					user.getCredentials().getPassword().equals(login.getPassword())) {
				return true;
			}
		}
		
		
		
		
		return false;
	}
}