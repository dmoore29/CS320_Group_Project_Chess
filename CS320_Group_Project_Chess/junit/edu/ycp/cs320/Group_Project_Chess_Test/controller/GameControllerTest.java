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
	public void testStartingPosition() {
		// Test to see if space(0,1) contains a PAWN piece.
				assertEquals(Rank.PAWN, game.getBoard().getSpace(0, 1).getPiece().getRank());
	}
	
	@Test
	public void testMovePiece() {
		// Move the PAWN piece to space(0,2)
		Space originSpace = game.getBoard().getSpace(0, 1);
		Space destinationSpace = game.getBoard().getSpace(0, 2);
		controller.movePiece(originSpace, destinationSpace);
		
		// Test to see if space(0,2) contains a PAWN.
		assertEquals(Rank.PAWN, game.getBoard().getSpace(0, 2).getPiece().getRank());
		
		// Test to see if space(0,1) is now empty.
		assertNull(game.getBoard().getSpace(0, 1).getPiece());
	}
}
