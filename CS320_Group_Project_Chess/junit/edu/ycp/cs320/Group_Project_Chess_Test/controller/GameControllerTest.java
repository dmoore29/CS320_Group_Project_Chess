package edu.ycp.cs320.Group_Project_Chess_Test.controller;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.Group_Project_Chess.controller.GameController;
import edu.ycp.cs320.Group_Project_Chess.model.Game;
import edu.ycp.cs320.Group_Project_Chess.model.King;
import edu.ycp.cs320.Group_Project_Chess.model.Piece;
import edu.ycp.cs320.Group_Project_Chess.model.Player;
import edu.ycp.cs320.Group_Project_Chess.model.Rank;
import edu.ycp.cs320.Group_Project_Chess.model.Rook;
import edu.ycp.cs320.Group_Project_Chess.model.Space;

public class GameControllerTest {

	private GameController controller;
	private Game game, gameCheck;
	private Player player1, player2;
	
	@Before
	public void setUp() {
		game = new Game(player1, player2);
		gameCheck = new Game();
		gameCheck.getBoard().newGameBoard();
		controller = new GameController(game);
	}
	
	
	@Test
	public void testStartingPositions() {
		// Test to see if the Pawns are in their correct starting positions.
		for(int i = 0; i < 8; i++) {
				assertEquals(Rank.PAWN, game.getBoard().getSpace(i, 1).getPiece().getRank());
				assertEquals(Rank.PAWN, game.getBoard().getSpace(i, 6).getPiece().getRank());
		}
		
		// Test to see if Black Knights, Rooks, and Bishops are in their correct starting positions.
		assertEquals(Rank.ROOK, game.getBoard().getSpace(0, 0).getPiece().getRank());
		assertEquals(Rank.ROOK, game.getBoard().getSpace(0, 7).getPiece().getRank());
		
		assertEquals(Rank.KNIGHT, game.getBoard().getSpace(1, 0).getPiece().getRank());
		assertEquals(Rank.KNIGHT, game.getBoard().getSpace(6, 0).getPiece().getRank());
		
		assertEquals(Rank.BISHOP, game.getBoard().getSpace(2, 0).getPiece().getRank());
		assertEquals(Rank.BISHOP, game.getBoard().getSpace(5, 0).getPiece().getRank());
		
		assertEquals(Rank.KING, game.getBoard().getSpace(3, 0).getPiece().getRank());
		assertEquals(Rank.QUEEN, game.getBoard().getSpace(4, 0).getPiece().getRank());
		
	}
	
	@Test
	public void testMovePawn() {
		// Move the PAWN piece to space(0,2)
		Space originSpace = game.getBoard().getSpace(0, 1);
		Space destinationSpace = game.getBoard().getSpace(0, 2);
		controller.movePiece(originSpace, destinationSpace);
		
		// Test to see if space(0,2) contains a PAWN.
		assertEquals(Rank.PAWN, game.getBoard().getSpace(0, 2).getPiece().getRank());
		
		// Test to see if space(0,1) is now empty.
		assertNull(game.getBoard().getSpace(0, 1).getPiece());
	}
	
	@Test
	// This test should fail once we implement the validateMove() functions
	public void testMoveKnight() {
		// Move the KNIGHT piece to space(1,4)
		Space originSpace = game.getBoard().getSpace(1, 0);
		Space destinationSpace = game.getBoard().getSpace(1, 4);
		controller.movePiece(originSpace, destinationSpace);
		
		// Test to see if space(1,4) contains a KNIGHT.
		assertEquals(Rank.KNIGHT, game.getBoard().getSpace(1, 4).getPiece().getRank());
		
		// Test to see if space(1,0) is now empty.
		assertNull(game.getBoard().getSpace(1, 0).getPiece());
	}
	
	@Test
	public void testCheck() {
		// Create a scenario; king is adjacent to an opposing rook.
		gameCheck.getBoard().setPiece(new King(Rank.KING, 1, new Point(1,0)));
		gameCheck.getBoard().setPiece(new Rook(Rank.ROOK, 0, new Point(0,0)));
		controller = new GameController(gameCheck);
		
		// The rook is able to capture the king, the king should be in check.
		assertTrue(controller.check(1));
		
		// Remove the opposing rook.
		gameCheck.getBoard().getSpace(0,0).setPiece(null);
		controller = new GameController(gameCheck);
		
		// The king should no longer be in check.
		assertFalse(controller.check(1));
	}
	
	@Test
	public void testCheckMate() {
		// Create a scenario; king is surrounded by rooks on all sides.
		game.getBoard().setPiece(new King(Rank.KING, 1, new Point(1,0)));
		game.getBoard().setPiece(new Rook(Rank.ROOK, 0, new Point(0,0)));
		game.getBoard().setPiece(new Rook(Rank.ROOK, 0, new Point(2,0)));
		game.getBoard().setPiece(new Rook(Rank.ROOK, 0, new Point(0,1)));
		game.getBoard().setPiece(new Rook(Rank.ROOK, 0, new Point(2,1)));
		
		// King should be in check.
		assertTrue(controller.checkmate(1));
		
		// Get rid of the piece directly in front of the king.
		game.getBoard().getSpace(1, 1).setPiece(null);
		
		// The king should NOT be in check.
		assertFalse(controller.checkmate(1));
	}
	
	
}