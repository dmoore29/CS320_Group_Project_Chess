package edu.ycp.cs320.Group_Project_Chess.database;

import java.sql.SQLException;
import java.util.ArrayList;

import edu.ycp.cs320.Group_Project_Chess.model.Board;
import edu.ycp.cs320.Group_Project_Chess.model.Credentials;
import edu.ycp.cs320.Group_Project_Chess.model.Game;
import edu.ycp.cs320.Group_Project_Chess.model.Stats;
import edu.ycp.cs320.Group_Project_Chess.model.User;

public interface IDatabase {
	public ArrayList<User> findAllUsers();
	public User findUserwithUsername(final String username);
	public User findUserwithUserId(final int userId);
	public User findUserwithFriendId(final int userId);
	public User findUserwithEmail(final String email);
	public User findUserinMatchMaking();
	public Integer findMatchMakingforUsername(final String username);
	public ArrayList<User> findUserswithMostWins();
	public ArrayList<User> findUserswithHighestElo();
	public ArrayList<Game> findGameswithUser(final String username);
	public Game findGamewithGameId(final int gameId);
	public Board findBoardwithBoardId(final int boardId);
	public ArrayList<User> findFriendswithUserId(final int userId);
	public Integer addToFriends(final int user1, final int user2) throws SQLException;
	public Integer removeFromFriends(final int user1, final int user2) throws SQLException;
	public Integer registerUser(final Credentials creds) throws SQLException;
	public Integer updateBio(final String newBio, final int Id) throws SQLException;
	public Integer updateStats(final Stats newStats, final int Id) throws SQLException;
	public Integer updatePicNum(final int picNum, final int Id) throws SQLException;
	public Integer updateMatchMaking(final int matchMaking, final int Id) throws SQLException;
	public Integer newGame(final Game game) throws SQLException;
	public Integer updateGame(final Game game) throws SQLException;
	public Integer deleteGame(final int gameId) throws SQLException;
	public Integer newBoard(final Board board) throws SQLException;
	public Integer updateBoard(final Board board) throws SQLException;
}