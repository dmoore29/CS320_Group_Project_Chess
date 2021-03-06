package edu.ycp.cs320.Group_Project_Chess_Test.controller;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.Group_Project_Chess.controller.GameController;
import edu.ycp.cs320.Group_Project_Chess.database.DerbyDatabase;
import edu.ycp.cs320.Group_Project_Chess.model.Bishop;
import edu.ycp.cs320.Group_Project_Chess.model.Board;
import edu.ycp.cs320.Group_Project_Chess.model.Game;
import edu.ycp.cs320.Group_Project_Chess.model.King;
import edu.ycp.cs320.Group_Project_Chess.model.Knight;
import edu.ycp.cs320.Group_Project_Chess.model.Pawn;
import edu.ycp.cs320.Group_Project_Chess.model.Piece;
import edu.ycp.cs320.Group_Project_Chess.model.Player;
import edu.ycp.cs320.Group_Project_Chess.model.Queen;
import edu.ycp.cs320.Group_Project_Chess.model.Rank;
import edu.ycp.cs320.Group_Project_Chess.model.Rook;
import edu.ycp.cs320.Group_Project_Chess.model.Space;
import edu.ycp.cs320.Group_Project_Chess.model.User;

public class GameControllerTest {

	private GameController controller;
	private Game game, gameCheck, gameCheckmate, gameCastle;
	private Player player1, player2;
	private Board board;
	private User user;
	private DerbyDatabase database;
	
	@Before
	public void setUp() {
		game = new Game(player1, player2);
		gameCheck = new Game(player1, player2);
		gameCheck.getBoard().newGameBoard();
		gameCheckmate = new Game(player1, player2);
		gameCheckmate.getBoard().newGameBoard();
		controller = new GameController(game);
		gameCastle = new Game(player1, player2);
		user = new User();
		database = new DerbyDatabase();
	}
	
	@Test
	public void testMovePiece() {
		Space originSpace = game.getBoard().getSpace(1, 0);
		Space destinationSpace = game.getBoard().getSpace(2, 2);
		controller.movePiece(originSpace, destinationSpace);
		
		//Knight
		// Test to see if space(2,2) contains a Knight.
		assertEquals(Rank.KNIGHT, game.getBoard().getSpace(2, 2).getPiece().getRank());
		
		// Test to see if space(1,0) is now empty.
		assertNull(game.getBoard().getSpace(1, 0).getPiece());
		
		game.getBoard().getSpace(2,2).setPiece(null);
		
		//Rook
		originSpace = game.getBoard().getSpace(0, 0);
		destinationSpace = game.getBoard().getSpace(0, 4);
		controller.movePiece(originSpace, destinationSpace);
		
		assertEquals(Rank.ROOK, game.getBoard().getSpace(0, 4).getPiece().getRank());
		
		assertNull(game.getBoard().getSpace(0, 0).getPiece());
		
		game.getBoard().getSpace(0,4).setPiece(null);
		
		//Queen
		originSpace = game.getBoard().getSpace(3, 0);
		destinationSpace = game.getBoard().getSpace(7, 4);
		controller.movePiece(originSpace, destinationSpace);
		
		assertEquals(Rank.QUEEN, game.getBoard().getSpace(7, 4).getPiece().getRank());
		
		assertNull(game.getBoard().getSpace(3, 0).getPiece());
		
		game.getBoard().getSpace(7,4).setPiece(null);
		
		//Bishop
		originSpace = game.getBoard().getSpace(5, 0);
		destinationSpace = game.getBoard().getSpace(2, 3);
		controller.movePiece(originSpace, destinationSpace);
		
		assertEquals(Rank.BISHOP, game.getBoard().getSpace(2, 3).getPiece().getRank());
		
		assertNull(game.getBoard().getSpace(5, 0).getPiece());
		
		game.getBoard().getSpace(2,3).setPiece(null);
		
		//King
		originSpace = game.getBoard().getSpace(4, 0);
		destinationSpace = game.getBoard().getSpace(4, 1);
		controller.movePiece(originSpace, destinationSpace);
		
		assertEquals(Rank.KING, game.getBoard().getSpace(4, 1).getPiece().getRank());
		
		assertNull(game.getBoard().getSpace(4, 0).getPiece());
		
		game.getBoard().getSpace(4,1).setPiece(null);
		
		
	}
	
	@Test
	public void testValidCastle() {
		board = new Board();
		controller = new GameController(gameCastle);
		gameCastle.getBoard().setPiece(new King(Rank.KING, 1, new Point(4,7)));
		gameCastle.getBoard().setPiece(new Rook(Rank.ROOK, 1, new Point(7,7)));
		//not sure how to go about this... -Anthony
		//assertTrue(board.getPiece(4, 7).validMove(new Point(6, 7), board));
		//assertTrue(board.getPiece(7, 7).validMove(new Point(5, 7), board));
		
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
		
		assertEquals(Rank.KING, game.getBoard().getSpace(4, 0).getPiece().getRank());
		assertEquals(Rank.QUEEN, game.getBoard().getSpace(3, 0).getPiece().getRank());
		
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
		//PLACE KING
		gameCheck.getBoard().setPiece(new King(Rank.KING, 1, new Point(1,0)));
		controller = new GameController(gameCheck);
		
		//START CHECK ROOK
		gameCheck.getBoard().setPiece(new Rook(Rank.ROOK, 0, new Point(0,0)));
		
		// The piece is able to capture the king, the king should be in check.
		assertTrue(controller.check(1));
		
		// Remove the opposing piece.
		gameCheck.getBoard().getSpace(0,0).setPiece(null);
		
		// The king should no longer be in check.
		assertFalse(controller.check(1));
		//END CHECK ROOK
		
		//START CHECK PAWN
		gameCheck.getBoard().setPiece(new Pawn(Rank.PAWN, 0, new Point(0,1)));
		
		// The piece is able to capture the king, the king should be in check.
		assertTrue(controller.check(1));
		
		// Remove the opposing piece.
		gameCheck.getBoard().getSpace(0,1).setPiece(null);
		controller = new GameController(gameCheck);
		
		// The king should no longer be in check.
		assertFalse(controller.check(1));
		//END CHECK PAWN
		
		//START CHECK BISHOP
		gameCheck.getBoard().setPiece(new Bishop(Rank.BISHOP, 0, new Point(4,3)));
		
		// The piece is able to capture the king, the king should be in check.
		assertTrue(controller.check(1));
		
		// Remove the opposing piece.
		gameCheck.getBoard().getSpace(4,3).setPiece(null);
		
		// The king should no longer be in check.
		assertFalse(controller.check(1));
		//END CHECK BISHOP
		
		//START CHECK KNIGHT
		gameCheck.getBoard().setPiece(new Knight(Rank.KNIGHT, 0, new Point(2,2)));
		
		// The piece is able to capture the king, the king should be in check.
		assertTrue(controller.check(1));
		
		// Remove the opposing piece.
		gameCheck.getBoard().getSpace(2,2).setPiece(null);
		
		// The king should no longer be in check.
		assertFalse(controller.check(1));
		//END CHECK KNIGHT
		
		//START CHECK QUEEN
		gameCheck.getBoard().setPiece(new Queen(Rank.QUEEN, 0, new Point(5,4)));
		
		// The piece is able to capture the king, the king should be in check.
		assertTrue(controller.check(1));
		
		// Remove the opposing piece.
		gameCheck.getBoard().getSpace(5,4).setPiece(null);
		
		// The king should no longer be in check.
		assertFalse(controller.check(1));
		//END CHECK QUEEN
	}
	
	@Test
	public void testCheckMate() {
		//PLACE KING 
		gameCheckmate.getBoard().setPiece(new King(Rank.KING, 1, new Point(1,0)));
		controller = new GameController(gameCheckmate);
		
		//CHECKMATE SCENARIO 1
		gameCheckmate.getBoard().setPiece(new Rook(Rank.ROOK, 0, new Point(0,2)));
		gameCheckmate.getBoard().setPiece(new Rook(Rank.ROOK, 0, new Point(1,2)));
		gameCheckmate.getBoard().setPiece(new Rook(Rank.ROOK, 0, new Point(2,2)));
		
		// King should be in checkmate.
		assertTrue(controller.checkmate(1));
		
		// Remove the opposing piece.
		gameCheckmate.getBoard().getSpace(0,2).setPiece(null);
		gameCheckmate.getBoard().getSpace(1,2).setPiece(null);
		gameCheckmate.getBoard().getSpace(2,2).setPiece(null);
		
		// The king should no longer be in check.
		assertFalse(controller.checkmate(1));
		
		//END CHACKMATE SCENARIO 1
		
		//CHECKMATE SCENARIO 2
		gameCheckmate.getBoard().setPiece(new Queen(Rank.QUEEN, 0, new Point(7,5)));
		gameCheckmate.getBoard().setPiece(new Queen(Rank.QUEEN, 0, new Point(7,6)));
		gameCheckmate.getBoard().setPiece(new Queen(Rank.QUEEN, 0, new Point(7,7)));
		gameCheckmate.getBoard().setPiece(new Queen(Rank.QUEEN, 0, new Point(6,7)));
		
		// King should be in checkmate.
		assertTrue(controller.checkmate(1));
		
		// Remove the opposing piece.
		gameCheckmate.getBoard().getSpace(7,5).setPiece(null);
		gameCheckmate.getBoard().getSpace(7,6).setPiece(null);
		gameCheckmate.getBoard().getSpace(7,7).setPiece(null);
		gameCheckmate.getBoard().getSpace(6,7).setPiece(null);
		
		// The king should no longer be in check.
		assertFalse(controller.checkmate(1));
		
		//END CHACKMATE SCENARIO 2
		
		//CHECKMATE SCENARIO 3
		gameCheckmate.getBoard().setPiece(new Queen(Rank.QUEEN, 0, new Point(7,5)));
		gameCheckmate.getBoard().setPiece(new Queen(Rank.QUEEN, 0, new Point(7,6)));
		gameCheckmate.getBoard().setPiece(new Queen(Rank.QUEEN, 0, new Point(7,7)));
		gameCheckmate.getBoard().setPiece(new Queen(Rank.QUEEN, 0, new Point(6,7)));
		
		// King should be in checkmate.
		assertTrue(controller.checkmate(1));
		
		// Remove the opposing piece.
		gameCheckmate.getBoard().getSpace(7,5).setPiece(null);
		gameCheckmate.getBoard().getSpace(7,6).setPiece(null);
		gameCheckmate.getBoard().getSpace(7,7).setPiece(null);
		gameCheckmate.getBoard().getSpace(6,7).setPiece(null);
		
		// The king should no longer be in check.
		assertFalse(controller.checkmate(1));
		
		//END CHACKMATE SCENARIO 3
		
		//CHECKMATE SCENARIO 4
		gameCheckmate.getBoard().setPiece(new Pawn(Rank.PAWN, 1, new Point(0,0)));
		gameCheckmate.getBoard().setPiece(new Pawn(Rank.PAWN, 1, new Point(2,0)));
		gameCheckmate.getBoard().setPiece(new Pawn(Rank.PAWN, 1, new Point(0,1)));
		gameCheckmate.getBoard().setPiece(new Pawn(Rank.PAWN, 1, new Point(2,1)));
		gameCheckmate.getBoard().setPiece(new Rook(Rank.ROOK, 0, new Point(1,7)));
		gameCheckmate.getBoard().setPiece(new Queen(Rank.QUEEN, 0, new Point(7,6)));
		
		// King should be in checkmate.
		assertTrue(controller.checkmate(1));
		
		// Remove the opposing piece.
		gameCheckmate.getBoard().getSpace(0,0).setPiece(null);
		gameCheckmate.getBoard().getSpace(2,0).setPiece(null);
		gameCheckmate.getBoard().getSpace(0,1).setPiece(null);
		gameCheckmate.getBoard().getSpace(2,1).setPiece(null);
		gameCheckmate.getBoard().getSpace(1,7).setPiece(null);
		gameCheckmate.getBoard().getSpace(7,6).setPiece(null);
		
		// The king should no longer be in check.
		assertFalse(controller.checkmate(1));
		
		//END CHACKMATE SCENARIO 4
		
		//CHECKMATE SCENARIO 5
		gameCheckmate.getBoard().setPiece(new Pawn(Rank.PAWN, 1, new Point(0,0)));
		gameCheckmate.getBoard().setPiece(new Pawn(Rank.PAWN, 1, new Point(2,0)));
		gameCheckmate.getBoard().setPiece(new Pawn(Rank.PAWN, 1, new Point(0,1)));
		gameCheckmate.getBoard().setPiece(new Pawn(Rank.PAWN, 1, new Point(2,1)));
		gameCheckmate.getBoard().setPiece(new Knight(Rank.KNIGHT, 0, new Point(2,2)));
		gameCheckmate.getBoard().setPiece(new Knight(Rank.KNIGHT, 0, new Point(3,2)));
		
		// King should be in checkmate.
		assertTrue(controller.checkmate(1));
		
		// Remove the opposing piece.
		gameCheckmate.getBoard().getSpace(0,0).setPiece(null);
		gameCheckmate.getBoard().getSpace(2,0).setPiece(null);
		gameCheckmate.getBoard().getSpace(0,1).setPiece(null);
		gameCheckmate.getBoard().getSpace(2,1).setPiece(null);
		gameCheckmate.getBoard().getSpace(2,3).setPiece(null);
		gameCheckmate.getBoard().getSpace(3,2).setPiece(null);
		
		// The king should no longer be in check.
		assertFalse(controller.checkmate(1));
		
		//END CHACKMATE SCENARIO 5
		
		
	}
	
	
}