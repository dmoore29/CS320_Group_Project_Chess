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
import edu.ycp.cs320.Group_Project_Chess.model.Player;
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
		games = new ArrayList<Game>();
		for (int i = 0; i < 2; i++) {
			Game game = new Game(new Player(u1, 0, u1.getUserId()), new Player(u2, 1, u2.getUserId()), board, i);
			game.setGameId(i + 1);
			games.add(game);			
		}
		for (int i = 0; i < 2; i++) {
			Game game = new Game(new Player(u2, 0, u2.getUserId()), new Player(u1, 1, u1.getUserId()), board, i);
			game.setGameId(i + 3);
			games.add(game);			
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
		ArrayList<Game> g = new ArrayList<Game>();
		ArrayList<Game> game = db.findGameswithUser(games.get(0).getPlayer1().getUser().getCredentials().getUsername());
		g.addAll(game);
		game = db.findGameswithUser(games.get(2).getPlayer1().getUser().getCredentials().getUsername());
		g.addAll(game);
		
		
		for (int i = 0; i < games.size(); i++) {
			assertTrue(games.get(i).getGameId() == g.get(i).getGameId());
			assertTrue(games.get(i).getBoard().getBoardId() == g.get(i).getBoard().getBoardId());
			
			assertTrue(games.get(i).getPlayer1().getUser().getUserId() == g.get(i).getPlayer1().getUser().getUserId());
			assertEquals(games.get(i).getPlayer1().getUser().getCredentials().getEmail(), g.get(i).getPlayer1().getUser().getCredentials().getEmail());
			assertEquals(games.get(i).getPlayer1().getUser().getCredentials().getPassword(), g.get(i).getPlayer1().getUser().getCredentials().getPassword());
			assertEquals(games.get(i).getPlayer1().getUser().getCredentials().getUsername(), g.get(i).getPlayer1().getUser().getCredentials().getUsername());
			assertTrue(games.get(i).getPlayer1().getUser().getStats().getWins() == g.get(i).getPlayer1().getUser().getStats().getWins());
			assertTrue(games.get(i).getPlayer1().getUser().getStats().getLosses() == g.get(i).getPlayer1().getUser().getStats().getLosses());
			assertTrue(games.get(i).getPlayer1().getUser().getStats().getElo() == g.get(i).getPlayer1().getUser().getStats().getElo());
			assertEquals(games.get(i).getPlayer1().getUser().getProfile().getBio(), g.get(i).getPlayer1().getUser().getProfile().getBio());
			assertTrue(games.get(i).getPlayer1().getUser().getProfile().getPictureNumber() == g.get(i).getPlayer1().getUser().getProfile().getPictureNumber());
			assertTrue(games.get(i).getPlayer1().getPlayerId() == g.get(i).getPlayer1().getPlayerId());
			assertTrue(games.get(i).getPlayer1().getColor() == g.get(i).getPlayer1().getColor());
			
			assertTrue(games.get(i).getPlayer2().getUser().getUserId() == g.get(i).getPlayer2().getUser().getUserId());
			assertEquals(games.get(i).getPlayer2().getUser().getCredentials().getEmail(), g.get(i).getPlayer2().getUser().getCredentials().getEmail());
			assertEquals(games.get(i).getPlayer2().getUser().getCredentials().getPassword(), g.get(i).getPlayer2().getUser().getCredentials().getPassword());
			assertEquals(games.get(i).getPlayer2().getUser().getCredentials().getUsername(), g.get(i).getPlayer2().getUser().getCredentials().getUsername());
			assertTrue(games.get(i).getPlayer2().getUser().getStats().getWins() == g.get(i).getPlayer2().getUser().getStats().getWins());
			assertTrue(games.get(i).getPlayer2().getUser().getStats().getLosses() == g.get(i).getPlayer2().getUser().getStats().getLosses());
			assertTrue(games.get(i).getPlayer2().getUser().getStats().getElo() == g.get(i).getPlayer2().getUser().getStats().getElo());
			assertEquals(games.get(i).getPlayer2().getUser().getProfile().getBio(), g.get(i).getPlayer2().getUser().getProfile().getBio());
			assertTrue(games.get(i).getPlayer2().getUser().getProfile().getPictureNumber() == g.get(i).getPlayer2().getUser().getProfile().getPictureNumber());
			assertTrue(games.get(i).getPlayer2().getPlayerId() == g.get(i).getPlayer2().getPlayerId());
			assertTrue(games.get(i).getPlayer2().getColor() == g.get(i).getPlayer2().getColor());
			
			assertTrue(games.get(i).getTurn() == g.get(i).getTurn());
		}
	}
	
	@Test
	public void testFindGameWithGameId() {
		ArrayList<Game> g = new ArrayList<Game>();
		for (int i = 0; i < games.size(); i++) {
			Game game = db.findGamewithGameId(games.get(i).getGameId());
			g.add(game);
		}
		
		for (int i = 0; i < games.size(); i++) {
			assertTrue(games.get(i).getGameId() == g.get(i).getGameId());
			assertTrue(games.get(i).getBoard().getBoardId() == g.get(i).getBoard().getBoardId());
			for (int y = 0; y < 7; y++) {
				for (int x = 0; x < 7; x++) {
					assertEquals(games.get(i).getBoard().getPiece(x, y).getRank(), g.get(i).getBoard().getPiece(x, y).getRank());
					assertTrue(games.get(i).getBoard().getPiece(x, y).getColor() == g.get(i).getBoard().getPiece(x, y).getColor());
				}
			}
			assertTrue(games.get(i).getPlayer1().getUser().getUserId() == g.get(i).getPlayer1().getUser().getUserId());
			assertEquals(games.get(i).getPlayer1().getUser().getCredentials().getEmail(), g.get(i).getPlayer1().getUser().getCredentials().getEmail());
			assertEquals(games.get(i).getPlayer1().getUser().getCredentials().getPassword(), g.get(i).getPlayer1().getUser().getCredentials().getPassword());
			assertEquals(games.get(i).getPlayer1().getUser().getCredentials().getUsername(), g.get(i).getPlayer1().getUser().getCredentials().getUsername());
			assertTrue(games.get(i).getPlayer1().getUser().getStats().getWins() == g.get(i).getPlayer1().getUser().getStats().getWins());
			assertTrue(games.get(i).getPlayer1().getUser().getStats().getLosses() == g.get(i).getPlayer1().getUser().getStats().getLosses());
			assertTrue(games.get(i).getPlayer1().getUser().getStats().getElo() == g.get(i).getPlayer1().getUser().getStats().getElo());
			assertEquals(games.get(i).getPlayer1().getUser().getProfile().getBio(), g.get(i).getPlayer1().getUser().getProfile().getBio());
			assertTrue(games.get(i).getPlayer1().getUser().getProfile().getPictureNumber() == g.get(i).getPlayer1().getUser().getProfile().getPictureNumber());
			assertTrue(games.get(i).getPlayer1().getPlayerId() == g.get(i).getPlayer1().getPlayerId());
			assertTrue(games.get(i).getPlayer1().getColor() == g.get(i).getPlayer1().getColor());
			
			assertTrue(games.get(i).getPlayer2().getUser().getUserId() == g.get(i).getPlayer2().getUser().getUserId());
			assertEquals(games.get(i).getPlayer2().getUser().getCredentials().getEmail(), g.get(i).getPlayer2().getUser().getCredentials().getEmail());
			assertEquals(games.get(i).getPlayer2().getUser().getCredentials().getPassword(), g.get(i).getPlayer2().getUser().getCredentials().getPassword());
			assertEquals(games.get(i).getPlayer2().getUser().getCredentials().getUsername(), g.get(i).getPlayer2().getUser().getCredentials().getUsername());
			assertTrue(games.get(i).getPlayer2().getUser().getStats().getWins() == g.get(i).getPlayer2().getUser().getStats().getWins());
			assertTrue(games.get(i).getPlayer2().getUser().getStats().getLosses() == g.get(i).getPlayer2().getUser().getStats().getLosses());
			assertTrue(games.get(i).getPlayer2().getUser().getStats().getElo() == g.get(i).getPlayer2().getUser().getStats().getElo());
			assertEquals(games.get(i).getPlayer2().getUser().getProfile().getBio(), g.get(i).getPlayer2().getUser().getProfile().getBio());
			assertTrue(games.get(i).getPlayer2().getUser().getProfile().getPictureNumber() == g.get(i).getPlayer2().getUser().getProfile().getPictureNumber());
			assertTrue(games.get(i).getPlayer2().getPlayerId() == g.get(i).getPlayer2().getPlayerId());
			assertTrue(games.get(i).getPlayer2().getColor() == g.get(i).getPlayer2().getColor());
			
			assertTrue(games.get(i).getTurn() == g.get(i).getTurn());
		}
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