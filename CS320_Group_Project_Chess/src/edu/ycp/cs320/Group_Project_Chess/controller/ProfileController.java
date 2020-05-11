package edu.ycp.cs320.Group_Project_Chess.controller;

import java.sql.SQLException;

import edu.ycp.cs320.Group_Project_Chess.database.DerbyDatabase;
import edu.ycp.cs320.Group_Project_Chess.model.User;

public class ProfileController {
	private User user;
	private DerbyDatabase db;
	
	// initialize the database
	public ProfileController() {
		db = new DerbyDatabase();
	}
	
	// returns the given user's data
	public User getProfile(String name) {
		user = db.findUserwithUsername(name);
		return user;
	}

	// updates a user's bio given the new bio and that user's id
	public void updateBio(String bio, int Id) throws SQLException {
		db.updateBio(bio, Id);
		
	}

	// updates a user's picture given the new picture and that user's id
	public void updatePic(int picNum, int Id) throws SQLException {
		db.updatePicNum(picNum, Id);
		
	}
}