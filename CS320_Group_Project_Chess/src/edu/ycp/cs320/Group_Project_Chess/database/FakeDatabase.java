package edu.ycp.cs320.Group_Project_Chess.database;

import java.io.IOException;
import java.util.ArrayList;

import edu.ycp.cs320.Group_Project_Chess.model.Game;
import edu.ycp.cs320.Group_Project_Chess.model.User;
import edu.ycp.cs320.Group_Project_Chess.database.InitialData;

public class FakeDatabase{
	private ArrayList<User> userList;
	private ArrayList<Game> gameList;
	
	public FakeDatabase() {
		userList = new ArrayList<User>();
		gameList = new ArrayList<Game>();
		
		readInitalData();
	}

	private void readInitalData() {
		try {
			userList.addAll(InitialData.getUsers());
			gameList.addAll(InitialData.getGames());
		} catch (IOException e) {
			throw new IllegalStateException("Couldn't read initial data", e);
		}
		
	}
}