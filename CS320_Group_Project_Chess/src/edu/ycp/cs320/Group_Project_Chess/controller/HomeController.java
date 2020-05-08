package edu.ycp.cs320.Group_Project_Chess.controller;

import java.util.ArrayList;

import edu.ycp.cs320.Group_Project_Chess.database.DerbyDatabase;
import edu.ycp.cs320.Group_Project_Chess.model.User;

public class HomeController {
	private ArrayList<User> userList;
	private DerbyDatabase db;
	
	public HomeController() {
		db = new DerbyDatabase();
	}
	
	
	
	
}