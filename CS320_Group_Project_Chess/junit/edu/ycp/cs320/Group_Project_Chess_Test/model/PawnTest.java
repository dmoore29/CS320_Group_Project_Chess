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
	
	
	@Before
	public void SetUp() {
		pawn = new Pawn(Rank.PAWN, 0, new Point(4, 0));
		board.newGameBoard();
		board.setPiece(new Pawn(Rank.PAWN, 0, new Point(3, 6)));
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
		/////WHITE PAWN/////
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
		assertFalse(board.getPiece(3, 5).validMove(new Point(2, 5), board));
		assertFalse(board.getPiece(3, 5).validMove(new Point(2, 5), board));
		
		//check move 1 space backwards 
		assertFalse(board.getPiece(3, 6).validMove(new Point(3, 7), board));
		
		//check move diagonally +x -y
		assertFalse(board.getPiece(3, 6).validMove(new Point(4, 5), board));
		
		//check move diagonally -x -y
		assertFalse(board.getPiece(3, 6).validMove(new Point(2, 5), board));
		
		//check capture +x -y
		board.setPiece(new Pawn(Rank.PAWN, 1, new Point(4, 5)));
		assertTrue(board.getPiece(3, 6).validMove(new Point(4, 5), board));
		
		//check capture -x -y
		board.setPiece(new Pawn(Rank.PAWN, 1, new Point(2, 5)));
		assertTrue(board.getPiece(3, 6).validMove(new Point(2, 5), board));
		/////WHITE PAWN/////
				
	}
}