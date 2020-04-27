package edu.ycp.cs320.Group_Project_Chess.controller;

import java.sql.SQLException;

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

	public void updateBio(String bio, int Id) throws SQLException {
		db.updateBio(bio, Id);
		
	}

	public void updatePic(int picNum, int Id) throws SQLException {
		db.updatePicNum(picNum, Id);
		
	}
}