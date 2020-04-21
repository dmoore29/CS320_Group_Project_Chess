package edu.ycp.cs320.Group_Project_Chess.controller;

import edu.ycp.cs320.Group_Project_Chess.database.DerbyDatabase;
import edu.ycp.cs320.Group_Project_Chess.model.User;

public class ProfileController {
	private User user;
	private DerbyDatabase db;
	
	public ProfileController() {
		db = new DerbyDatabase();
	}
	
	public User getProfile(String name) {
		user = db.findUserwithUsername(name);
		return user;
	}
}