package edu.ycp.cs320.Group_Project_Chess.controller;

import java.util.ArrayList;

import edu.ycp.cs320.Group_Project_Chess.database.DerbyDatabase;
import edu.ycp.cs320.Group_Project_Chess.model.Game;


public class GameHomeController {
	private ArrayList<Game> games;
	private DerbyDatabase db;
	
	public GameHomeController() {
		db = new DerbyDatabase();
	}
	
	public ArrayList<Game> getGameswithUsername(String name) {
		games = db.findGameswithUser(name);
		return games;
	}
}