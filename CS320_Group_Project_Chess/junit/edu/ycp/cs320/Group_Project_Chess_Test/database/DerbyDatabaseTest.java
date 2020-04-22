package edu.ycp.cs320.Group_Project_Chess_Test.database;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Point;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.Group_Project_Chess.database.DerbyDatabase;
import edu.ycp.cs320.Group_Project_Chess.model.Board;
import edu.ycp.cs320.Group_Project_Chess.model.Credentials;
import edu.ycp.cs320.Group_Project_Chess.model.FriendsList;
import edu.ycp.cs320.Group_Project_Chess.model.Game;
import edu.ycp.cs320.Group_Project_Chess.model.Piece;
import edu.ycp.cs320.Group_Project_Chess.model.Profile;
import edu.ycp.cs320.Group_Project_Chess.model.Rank;
import edu.ycp.cs320.Group_Project_Chess.model.Stats;
import edu.ycp.cs320.Group_Project_Chess.model.User;
import edu.ycp.cs320.Group_Project_Chess.model.Rook;

public class DerbyDatabaseTest{
	
	private DerbyDatabase db;
	private ArrayList<Game> games;
	private User u1;
	private User u2;
	private Board board;
	private ArrayList<User> u;
	ArrayList<Piece> pieces;
	//1|user1@email.com|user1|password1|2|1|110|I like to play chess|1
	//2|user2@email.com|user2|password2|1|2|104|I dont like to play chess|2
	//1|1|2|0
	//1|1|2|1
	//1|2|1|0
	//1|2|1|1
	
	@Before
	public void setUp() {
		db = new DerbyDatabase();
		u1 = new User(1, 1, new Credentials("user1@email.com", "user1", "password1"), new Stats(2, 1, 110), new FriendsList(1), new Profile("I like to play chess", 1));
		u2 = new User(2, 2, new Credentials("user2@email.com", "user2", "password2"), new Stats(1, 2, 104), new FriendsList(2), new Profile("I dont like to play chess", 2));
		u = new ArrayList<User>();
		u.add(u1);
		u.add(u2);
		pieces = new ArrayList<Piece>();
		for (int y = 0; y < 7; y++) {
			for (int x = 0; x < 7; x++) {
				Piece piece = new Rook(Rank.ROOK, 1, new Point(x, y));
				pieces.add(piece);
			}
		}
		board = new Board(1);
		board.newGameBoard();
		for (Piece piece : pieces) {
			board.setPiece(piece);
		}
	}
	
	@Test
	public void testFindAllUsers() {
		ArrayList<User> users = db.findAllUsers();
		
		for (int i = 0; i < users.size(); i++) {
			assertTrue(u.get(i).getUserId() == users.get(i).getUserId());
			assertEquals(u.get(i).getCredentials().getEmail(), users.get(i).getCredentials().getEmail());
			assertEquals(u.get(i).getCredentials().getPassword(), users.get(i).getCredentials().getPassword());
			assertEquals(u.get(i).getCredentials().getUsername(), users.get(i).getCredentials().getUsername());
			assertTrue(u.get(i).getStats().getWins() == users.get(i).getStats().getWins());
			assertTrue(u.get(i).getStats().getLosses() == users.get(i).getStats().getLosses());
			assertTrue(u.get(i).getStats().getElo() == users.get(i).getStats().getElo());
			assertEquals(u.get(i).getProfile().getBio(), users.get(i).getProfile().getBio());
			assertTrue(u.get(i).getProfile().getPictureNumber() == users.get(i).getProfile().getPictureNumber());
		}
	}
	
	@Test
	public void testFindUserWithUsername() {
		ArrayList<User> users = new ArrayList<User>();
		for (int i = 0; i < u.size(); i++) {
			User user = db.findUserwithUsername(u.get(i).getCredentials().getUsername());
			users.add(user);
		}
		
		for (int i = 0; i < users.size(); i++) {
			assertTrue(u.get(i).getUserId() == users.get(i).getUserId());
			assertEquals(u.get(i).getCredentials().getEmail(), users.get(i).getCredentials().getEmail());
			assertEquals(u.get(i).getCredentials().getPassword(), users.get(i).getCredentials().getPassword());
			assertEquals(u.get(i).getCredentials().getUsername(), users.get(i).getCredentials().getUsername());
			assertTrue(u.get(i).getStats().getWins() == users.get(i).getStats().getWins());
			assertTrue(u.get(i).getStats().getLosses() == users.get(i).getStats().getLosses());
			assertTrue(u.get(i).getStats().getElo() == users.get(i).getStats().getElo());
			assertEquals(u.get(i).getProfile().getBio(), users.get(i).getProfile().getBio());
			assertTrue(u.get(i).getProfile().getPictureNumber() == users.get(i).getProfile().getPictureNumber());
		}
	}
	
	@Test
	public void testFindUserWithUserId() {
		ArrayList<User> users = new ArrayList<User>();
		for (int i = 0; i < u.size(); i++) {
			User user = db.findUserwithUserId(u.get(i).getUserId());
			users.add(user);
		}
		
		for (int i = 0; i < users.size(); i++) {
			assertTrue(u.get(i).getUserId() == users.get(i).getUserId());
			assertEquals(u.get(i).getCredentials().getEmail(), users.get(i).getCredentials().getEmail());
			assertEquals(u.get(i).getCredentials().getPassword(), users.get(i).getCredentials().getPassword());
			assertEquals(u.get(i).getCredentials().getUsername(), users.get(i).getCredentials().getUsername());
			assertTrue(u.get(i).getStats().getWins() == users.get(i).getStats().getWins());
			assertTrue(u.get(i).getStats().getLosses() == users.get(i).getStats().getLosses());
			assertTrue(u.get(i).getStats().getElo() == users.get(i).getStats().getElo());
			assertEquals(u.get(i).getProfile().getBio(), users.get(i).getProfile().getBio());
			assertTrue(u.get(i).getProfile().getPictureNumber() == users.get(i).getProfile().getPictureNumber());
		}
	}
	
	@Test
	public void testFindGamesWithUser() {
		
	}
	
	@Test
	public void testFindGameWithGameId() {
		
	}
	
	@Test
	public void testFindBoardWithBoardId() {
		Board b = db.findBoardwithBoardId(board.getBoardId());
		
		assertTrue(board.getBoardId() == b.getBoardId());
		for (int y = 0; y < 7; y++) {
			for (int x = 0; x < 7; x++) {
				assertEquals(board.getPiece(x, y).getRank(), b.getPiece(x, y).getRank());
				assertTrue(board.getPiece(x, y).getColor() == b.getPiece(x, y).getColor());
			}
		}
	}
}