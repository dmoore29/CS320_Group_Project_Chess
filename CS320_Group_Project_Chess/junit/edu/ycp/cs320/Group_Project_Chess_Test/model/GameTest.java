package edu.ycp.cs320.Group_Project_Chess_Test.model;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.Group_Project_Chess.model.Board;
import edu.ycp.cs320.Group_Project_Chess.model.Game;
import edu.ycp.cs320.Group_Project_Chess.model.Knight;
import edu.ycp.cs320.Group_Project_Chess.model.Player;
import edu.ycp.cs320.Group_Project_Chess.model.Queen;
import edu.ycp.cs320.Group_Project_Chess.model.Rank;
import edu.ycp.cs320.Group_Project_Chess.model.User;





public class GameTest {
	private Game game;
	private Board oldBoard;
	private Player player1;
	private Player player2;
	private User user1;
	private User user2;
	private int turn;
	
	@Before
	public void SetUp() {
		player1 = new Player(user1, 0);
		player2 = new Player(user2, 1);
		oldBoard = new Board();
		oldBoard.newGameBoard();
		turn = 3;
		oldBoard.setPiece(new Knight(Rank.KNIGHT, 0, new Point(4, 4)));
		oldBoard.setPiece(new Queen(Rank.QUEEN, 1, new Point(5, 4)));
	}
	
	@Test
	public void newGameTest() {
		game = new Game(player1, player2);
		assertEquals(game.getBoard().getPiece(0, 0).getRank(), Rank.ROOK);
		assertEquals(game.getBoard().getPiece(5, 6).getRank(), Rank.PAWN);
		assertEquals(game.getBoard().getPiece(0, 0).getColor(), 1);
		assertEquals(game.getBoard().getPiece(5, 6).getColor(), 0);
		assertEquals(game.getTurn(), 0);
	}
	
	@Test
	public void previousGameTest() {
		game = new Game(player1, player2, oldBoard, turn);
		assertEquals(game.getBoard().getPiece(4, 4).getRank(), Rank.KNIGHT);
		assertEquals(game.getBoard().getPiece(5, 4).getRank(), Rank.QUEEN);
		assertEquals(game.getBoard().getPiece(4, 4).getColor(), 0);
		assertEquals(game.getBoard().getPiece(5, 4).getColor(), 1);
		assertEquals(game.getTurn(), 3);
	}
}