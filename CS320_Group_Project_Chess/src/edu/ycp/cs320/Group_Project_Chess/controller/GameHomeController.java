package edu.ycp.cs320.Group_Project_Chess.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import edu.ycp.cs320.Group_Project_Chess.database.DerbyDatabase;
import edu.ycp.cs320.Group_Project_Chess.model.Game;
import edu.ycp.cs320.Group_Project_Chess.model.User;


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
	
	public void deleteGame(int gameId) throws SQLException {
		db.deleteGame(gameId);
	}
	
	public User enterMatchMaking(String name) throws SQLException {
		User match = db.findUserinMatchMaking();
		if (match.getCredentials().getUsername() != null && !match.getCredentials().getUsername().contentEquals(name)) {
			System.out.println("starting game with user " + match.getCredentials().getUsername());
			db.updateMatchMaking(0, match.getUserId());
			// start game with this user
			return match;
		} else {
			System.out.println("entering user " + name + " into matchmaking");
			db.updateMatchMaking(1, db.findUserwithUsername(name).getUserId());
			// user is now in matchmaking
			return null;
		}
	}
	
	public int isUserInMatchMaking(String name) {
		return db.findMatchMakingforUsername(name);
	}
}