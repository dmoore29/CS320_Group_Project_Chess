package edu.ycp.cs320.Group_Project_Chess.database;

import java.util.ArrayList;

import edu.ycp.cs320.Group_Project_Chess.model.Board;
import edu.ycp.cs320.Group_Project_Chess.model.Game;
import edu.ycp.cs320.Group_Project_Chess.model.User;

public interface IDatabase {
	public ArrayList<User> findAllUsers();
	public User findUserwithUsername(final String username);
	public User findUserwithUserId(final int userId);
	public ArrayList<Game> findGameswithUser(final String username);
	public Game findGamewithGameId(final int gameId);
	public Board findBoardwithBoardId(final int boardId);
}