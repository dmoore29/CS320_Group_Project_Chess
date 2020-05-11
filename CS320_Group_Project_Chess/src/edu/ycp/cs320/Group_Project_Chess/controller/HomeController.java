package edu.ycp.cs320.Group_Project_Chess.controller;

import java.util.ArrayList;

import edu.ycp.cs320.Group_Project_Chess.database.DerbyDatabase;
import edu.ycp.cs320.Group_Project_Chess.model.User;

public class HomeController {
	private ArrayList<User> userList;
	private DerbyDatabase db;
	
	// initialize the database
	public HomeController() {
		db = new DerbyDatabase();
	}
	
	// returns a list of the top users by wins
	public ArrayList<User> findTopWins(){
		return db.findUserswithMostWins();
	}
	
	// returns a list of the top users by elo
	public ArrayList<User> findTopElo(){
		return db.findUserswithHighestElo();
	}
	
	
}