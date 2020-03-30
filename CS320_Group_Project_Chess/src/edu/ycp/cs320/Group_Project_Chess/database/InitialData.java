package edu.ycp.cs320.Group_Project_Chess.database;

import java.awt.Point;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.ycp.cs320.Group_Project_Chess.model.Bishop;
import edu.ycp.cs320.Group_Project_Chess.model.Board;
import edu.ycp.cs320.Group_Project_Chess.model.Credentials;
import edu.ycp.cs320.Group_Project_Chess.model.FriendsList;
import edu.ycp.cs320.Group_Project_Chess.model.Game;
import edu.ycp.cs320.Group_Project_Chess.model.King;
import edu.ycp.cs320.Group_Project_Chess.model.Knight;
import edu.ycp.cs320.Group_Project_Chess.model.Pawn;
import edu.ycp.cs320.Group_Project_Chess.model.Player;
import edu.ycp.cs320.Group_Project_Chess.model.Profile;
import edu.ycp.cs320.Group_Project_Chess.model.Queen;
import edu.ycp.cs320.Group_Project_Chess.model.Rank;
import edu.ycp.cs320.Group_Project_Chess.model.Rook;
import edu.ycp.cs320.Group_Project_Chess.model.Space;
import edu.ycp.cs320.Group_Project_Chess.model.Stats;
import edu.ycp.cs320.Group_Project_Chess.model.User;

public class InitialData {
	public static List<User> getUsers() throws IOException {
		List<User> userList = new ArrayList<User>();
		ReadCSV readUsers = new ReadCSV("users.csv");
		try {
			// auto-generated primary key for users table
			int userId = 1;
			while (true) {
				List<String> tuple = readUsers.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				int friendsId = Integer.parseInt(i.next());
				User user = new User(userId++, friendsId, new Credentials(i.next(), i.next(), i.next()), new Stats(Integer.parseInt(i.next()), Integer.parseInt(i.next()), Integer.parseInt(i.next())), new FriendsList(friendsId), new Profile(i.next(), Integer.parseInt(i.next())));
				userList.add(user);
			}
			return userList;
		} finally {
			readUsers.close();
		}
	}
	
	public static List<Board> getBoards() throws IOException {
		List<Board> boardList = new ArrayList<Board>();
		ReadCSV readBoards = new ReadCSV("boards.csv");
		try {
			// auto-generated primary key for games table
			int boardId = 1;
			while (true) {
				List<String> tuple = readBoards.next();
				if (tuple == null) {
					break;
				}
				
				Iterator<String> i = tuple.iterator();
				Space[][] space = new Space[8][8];
				for (int y = 0; y < 8; y++) {
					for (int x = 0; x < 8; x++) {
						Point location = new Point(x, y);
						int rank = Integer.parseInt(i.next());
						int color = Integer.parseInt(i.next());
						switch(rank) {
						case 0:
							space[x][y] = new Space(new Pawn(Rank.PAWN, color, location), location);
							break;
						case 1:
							space[x][y] = new Space(new Rook(Rank.ROOK, color, location), location);
							break;
						case 2:
							space[x][y] = new Space(new Knight(Rank.KNIGHT, color, location), location);
							break;
						case 3:
							space[x][y] = new Space(new Bishop(Rank.BISHOP, color, location), location);
							break;
						case 4:
							space[x][y] = new Space(new Queen(Rank.QUEEN, color, location), location);
							break;
						case 5:
							space[x][y] = new Space(new King(Rank.KING, color, location), location);
							break;
						default:
							space[x][y] = new Space(null, location);
						}
					}
				}
				
				Board board = new Board(boardId++);
				board.setBoard(space);
				boardList.add(board);
			}
			return boardList;
		} finally {
			readBoards.close();
		}
	}
	
	public static List<Game> getGames() throws IOException {
		List<Game> gameList = new ArrayList<Game>();
		ReadCSV readGames = new ReadCSV("games.csv");
		try {
			// auto-generated primary key for games table
			int gameId = 1;
			while (true) {
				List<String> tuple = readGames.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				Game game = new Game(new Player(Integer.parseInt(i.next())), new Player(Integer.parseInt(i.next())), new Board(Integer.parseInt(i.next())));
				game.setGameId(gameId++);
				gameList.add(game);
			}
			return gameList;
		} finally {
			readGames.close();
		}
	}
}
