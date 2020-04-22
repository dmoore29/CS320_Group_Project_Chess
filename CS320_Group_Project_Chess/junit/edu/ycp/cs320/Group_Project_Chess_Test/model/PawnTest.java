package edu.ycp.cs320.Group_Project_Chess_Test.model;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.Group_Project_Chess.model.Board;
import edu.ycp.cs320.Group_Project_Chess.model.Pawn;
import edu.ycp.cs320.Group_Project_Chess.model.Piece;
import edu.ycp.cs320.Group_Project_Chess.model.Rank;





public class PawnTest {
	private Pawn pawn;
	private Board board = new Board();
	private Board promoBoard = new Board();
	
	
	@Before
	public void SetUp() {
		pawn = new Pawn(Rank.PAWN, 0, new Point(4, 0));
		board.newGameBoard();
		promoBoard.newGameBoard();
		board.setPiece(new Pawn(Rank.PAWN, 0, new Point(3, 6)));
		board.setPiece(new Pawn(Rank.PAWN, 1, new Point(3, 1)));
	}
	
	@Test
	public void TestGetRank() {
		assertEquals(pawn.getRank(), Rank.PAWN);
	}
	
	@Test
	public void TestGetColor() {
		assertEquals(pawn.getColor(), 0);
	}
	
	@Test
	public void TestGetLocation() {
		assertEquals(pawn.getLocation(), new Point(4, 0));
	}
	
	@Test
	public void TestValidMove() {
		/////START WHITE PAWN/////
		
		//check move 1 space forward 
		assertTrue(board.getPiece(3, 6).validMove(new Point(3, 5), board));
		
		//check move 2 spaces forwards from starting location
		assertTrue(board.getPiece(3, 6).validMove(new Point(3, 4), board));
		
		//check move if another piece is in front of it
		board.setPiece(new Pawn(Rank.PAWN, 0, new Point(3, 5)));
		assertFalse(board.getPiece(3, 6).validMove(new Point(3, 5), board));
		
		//check move 2 spaces if another piece is in front of it
		board.setPiece(new Pawn(Rank.PAWN, 0, new Point(3, 5)));
		assertFalse(board.getPiece(3, 6).validMove(new Point(3, 4), board));
		
		//check move on the x-axis
		assertFalse(board.getPiece(3, 6).validMove(new Point(2, 6), board));
		assertFalse(board.getPiece(3, 6).validMove(new Point(2, 6), board));
		
		//check move 1 space backwards 
		assertFalse(board.getPiece(3, 6).validMove(new Point(3, 7), board));
		
		//check move forward diagonally +x -y
		assertFalse(board.getPiece(3, 6).validMove(new Point(4, 5), board));
		
		//check move forward diagonally -x -y
		assertFalse(board.getPiece(3, 6).validMove(new Point(2, 5), board));
		
		//check move backward diagonally +x +y
		assertFalse(board.getPiece(3, 6).validMove(new Point(4, 7), board));
		
		//check move backward diagonally -x +y
		assertFalse(board.getPiece(3, 6).validMove(new Point(2, 7), board));
		
		//check capture +x -y
		board.setPiece(new Pawn(Rank.PAWN, 1, new Point(4, 5)));
		assertTrue(board.getPiece(3, 6).validMove(new Point(4, 5), board));
		
		//check capture -x -y
		board.setPiece(new Pawn(Rank.PAWN, 1, new Point(2, 5)));
		assertTrue(board.getPiece(3, 6).validMove(new Point(2, 5), board));
		
		/////END WHITE PAWN/////
		
		/////START BLACK PAWN/////
		
		//check move 1 space forward 
		assertTrue(board.getPiece(3, 1).validMove(new Point(3, 2), board));
		
		//check move 2 spaces forwards from starting location
		assertTrue(board.getPiece(3, 1).validMove(new Point(3, 3), board));
		
		//check move if another piece is in front of it
		board.setPiece(new Pawn(Rank.PAWN, 1, new Point(3, 2)));
		assertFalse(board.getPiece(3, 1).validMove(new Point(3, 2), board));
		
		//check move 2 spaces if another piece is in front of it
		board.setPiece(new Pawn(Rank.PAWN, 1, new Point(3, 2)));
		assertFalse(board.getPiece(3, 1).validMove(new Point(3, 3), board));
		
		//check move on the x-axis
		assertFalse(board.getPiece(3, 1).validMove(new Point(2, 1), board));
		assertFalse(board.getPiece(3, 1).validMove(new Point(2, 1), board));
		
		//check move 1 space backwards 
		assertFalse(board.getPiece(3, 1).validMove(new Point(3, 0), board));
		
		//check move forward diagonally +x -y
		assertFalse(board.getPiece(3, 1).validMove(new Point(4, 2), board));
		
		//check move forward diagonally -x -y
		assertFalse(board.getPiece(3, 1).validMove(new Point(2, 2), board));
		
		//check move backward diagonally +x +y
		assertFalse(board.getPiece(3, 1).validMove(new Point(4, 0), board));
		
		//check move backward diagonally -x +y
		assertFalse(board.getPiece(3, 1).validMove(new Point(2, 0), board));
		
		//check capture +x -y
		board.setPiece(new Pawn(Rank.PAWN, 0, new Point(4, 2)));
		assertTrue(board.getPiece(3, 1).validMove(new Point(4, 2), board));
		
		//check capture -x -y
		board.setPiece(new Pawn(Rank.PAWN, 0, new Point(2, 2)));
		assertTrue(board.getPiece(3, 1).validMove(new Point(2, 2), board));
		
		/////END BLACK PAWN/////
		
		/////START PROMOTION TEST/////
		//Can Promote
		promoBoard.setPiece(new Pawn(Rank.PAWN, 0, new Point(0,0)));
		assertTrue(((Pawn)promoBoard.getPiece(0, 0)).promotion(promoBoard));
		promoBoard.setPiece(new Pawn(Rank.PAWN, 0, new Point(1,0)));
		assertTrue(((Pawn)promoBoard.getPiece(1, 0)).promotion(promoBoard));
		promoBoard.setPiece(new Pawn(Rank.PAWN, 0, new Point(2,0)));
		assertTrue(((Pawn)promoBoard.getPiece(2, 0)).promotion(promoBoard));
		promoBoard.setPiece(new Pawn(Rank.PAWN, 0, new Point(3,0)));
		assertTrue(((Pawn)promoBoard.getPiece(3, 0)).promotion(promoBoard));
		promoBoard.setPiece(new Pawn(Rank.PAWN, 0, new Point(4,0)));
		assertTrue(((Pawn)promoBoard.getPiece(4, 0)).promotion(promoBoard));
		promoBoard.setPiece(new Pawn(Rank.PAWN, 0, new Point(5,0)));
		assertTrue(((Pawn)promoBoard.getPiece(5, 0)).promotion(promoBoard));
		promoBoard.setPiece(new Pawn(Rank.PAWN, 0, new Point(6,0)));
		assertTrue(((Pawn)promoBoard.getPiece(6, 0)).promotion(promoBoard));
		promoBoard.setPiece(new Pawn(Rank.PAWN, 0, new Point(7,0)));
		assertTrue(((Pawn)promoBoard.getPiece(7, 0)).promotion(promoBoard));
		promoBoard.setPiece(new Pawn(Rank.PAWN, 1, new Point(0,7)));
		assertTrue(((Pawn)promoBoard.getPiece(0, 7)).promotion(promoBoard));
		promoBoard.setPiece(new Pawn(Rank.PAWN, 1, new Point(1,7)));
		assertTrue(((Pawn)promoBoard.getPiece(1, 7)).promotion(promoBoard));
		promoBoard.setPiece(new Pawn(Rank.PAWN, 1, new Point(2,7)));
		assertTrue(((Pawn)promoBoard.getPiece(2, 7)).promotion(promoBoard));
		promoBoard.setPiece(new Pawn(Rank.PAWN, 1, new Point(3,7)));
		assertTrue(((Pawn)promoBoard.getPiece(3, 7)).promotion(promoBoard));
		promoBoard.setPiece(new Pawn(Rank.PAWN, 1, new Point(4,7)));
		assertTrue(((Pawn)promoBoard.getPiece(4, 7)).promotion(promoBoard));
		promoBoard.setPiece(new Pawn(Rank.PAWN, 1, new Point(5,7)));
		assertTrue(((Pawn)promoBoard.getPiece(5, 7)).promotion(promoBoard));
		promoBoard.setPiece(new Pawn(Rank.PAWN, 1, new Point(6,7)));
		assertTrue(((Pawn)promoBoard.getPiece(6, 7)).promotion(promoBoard));
		promoBoard.setPiece(new Pawn(Rank.PAWN, 1, new Point(7,7)));
		assertTrue(((Pawn)promoBoard.getPiece(7, 7)).promotion(promoBoard));
		
		//Can't promote
		promoBoard.setPiece(new Pawn(Rank.PAWN, 0, new Point(3,5)));
		assertFalse(((Pawn)promoBoard.getPiece(3, 5)).promotion(promoBoard));
		promoBoard.setPiece(new Pawn(Rank.PAWN, 0, new Point(4,6)));
		assertFalse(((Pawn)promoBoard.getPiece(4, 6)).promotion(promoBoard));
		promoBoard.setPiece(new Pawn(Rank.PAWN, 0, new Point(5,4)));
		assertFalse(((Pawn)promoBoard.getPiece(5, 4)).promotion(promoBoard));
		promoBoard.setPiece(new Pawn(Rank.PAWN, 0, new Point(2,1)));
		assertFalse(((Pawn)promoBoard.getPiece(2, 1)).promotion(promoBoard));
		promoBoard.setPiece(new Pawn(Rank.PAWN, 0, new Point(3,3)));
		assertFalse(((Pawn)promoBoard.getPiece(3, 3)).promotion(promoBoard));
		promoBoard.setPiece(new Pawn(Rank.PAWN, 0, new Point(4,5)));
		assertFalse(((Pawn)promoBoard.getPiece(4, 5)).promotion(promoBoard));
		promoBoard.setPiece(new Pawn(Rank.PAWN, 0, new Point(2,3)));
		assertFalse(((Pawn)promoBoard.getPiece(2, 3)).promotion(promoBoard));
		/////END PROMOTION TEST/////
		
				
	}
}