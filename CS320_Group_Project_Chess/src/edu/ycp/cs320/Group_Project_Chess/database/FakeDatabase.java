package edu.ycp.cs320.Group_Project_Chess.database;

import java.io.IOException;
import java.util.ArrayList;

import edu.ycp.cs320.Group_Project_Chess.model.Board;
import edu.ycp.cs320.Group_Project_Chess.model.Game;
import edu.ycp.cs320.Group_Project_Chess.model.User;
import edu.ycp.cs320.Group_Project_Chess.database.InitialData;

public class FakeDatabase{
	private ArrayList<User> userList;
	private ArrayList<Board> boardList;
	private ArrayList<Game> gameList;
	
	
	public FakeDatabase() {
		userList = new ArrayList<User>();
		boardList = new ArrayList<Board>();
		gameList = new ArrayList<Game>();
		
		readInitalData();
	}

	private void readInitalData() {
		try {
			userList.addAll(InitialData.getUsers());
			boardList.addAll(InitialData.getBoards());
			gameList.addAll(InitialData.getGames(boardList));
		} catch (IOException e) {
			throw new IllegalStateException("Couldn't read initial data", e);
		}
		
	}
	
	public ArrayList<User> getUserList(){
		return userList;
	}
}