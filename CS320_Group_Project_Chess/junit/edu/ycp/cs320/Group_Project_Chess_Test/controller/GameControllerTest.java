package edu.ycp.cs320.Group_Project_Chess_Test.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.Group_Project_Chess.controller.GameController;
import edu.ycp.cs320.Group_Project_Chess.model.Game;
import edu.ycp.cs320.Group_Project_Chess.model.Player;
import edu.ycp.cs320.Group_Project_Chess.model.Rank;
import edu.ycp.cs320.Group_Project_Chess.model.Space;

public class GameControllerTest {

	private GameController controller;
	private Game game;
	private Player player1, player2;
	
	@Before
	public void setUp() {
		game = new Game(player1, player2);
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
		throw new UnsupportedOperationException("TODO - implement");
	}
	
	@Test
	public void testCheckMate() {
		throw new UnsupportedOperationException("TODO - implement");
	}
	
	
}
