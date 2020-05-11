package edu.ycp.cs320.Group_Project_Chess.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import edu.ycp.cs320.Group_Project_Chess.database.DerbyDatabase;
import edu.ycp.cs320.Group_Project_Chess.model.Game;
import edu.ycp.cs320.Group_Project_Chess.model.User;


public class GameHomeController {
	private ArrayList<Game> games;
	private DerbyDatabase db;
	
	// initialize the database
	public GameHomeController() {
		db = new DerbyDatabase();
	}
	
	// returns a list of all the games that the given user is playing
	public ArrayList<Game> getGameswithUsername(String name) {
		games = db.findGameswithUser(name);
		return games;
	}
	
	// removes a game given that game's id
	public void deleteGame(int gameId) throws SQLException {
		db.deleteGame(gameId);
	}
	
	// tries to find a player already in match making and sets the current user to matchmaking if no opponent was found
	public User enterMatchMaking(String name) throws SQLException {
		
		// tries to find an opponent already in matchmaking
		User match = db.findUserinMatchMaking();
		
		// if a user that is not equal to the current user was found in matchmaking, then the opponent is removed from matchmaking and is returned such that a new game can be created
		if (match.getCredentials().getUsername() != null && !match.getCredentials().getUsername().contentEquals(name)) {
			System.out.println("starting game with user " + match.getCredentials().getUsername());
			db.updateMatchMaking(0, match.getUserId());
			// start game with this user
			return match;
		} else {
			
			// the current user is added to matchmaking if no opponent was found
			System.out.println("entering user " + name + " into matchmaking");
			db.updateMatchMaking(1, db.findUserwithUsername(name).getUserId());
			// user is now in matchmaking
			return null;
		}
	}
	
	// determines if the given user is in matchmaking
	public int isUserInMatchMaking(String name) {
		return db.findMatchMakingforUsername(name);
	}
}