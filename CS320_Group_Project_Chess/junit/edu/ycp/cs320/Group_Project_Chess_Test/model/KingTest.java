package edu.ycp.cs320.Group_Project_Chess_Test.model;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.Group_Project_Chess.model.Board;
import edu.ycp.cs320.Group_Project_Chess.model.King;
import edu.ycp.cs320.Group_Project_Chess.model.Rank;





public class KingTest {
	private King king;
	private Board board = new Board();
	
	@Before
	public void SetUp() {
		king = new King(Rank.KING, 0, new Point(4, 0));
		board.newGameBoard();
		board.setPiece(new King(Rank.KING, 0, new Point(4, 7)));
		board.setPiece(new King(Rank.KING, 0, new Point(3, 4))); //central piece
	}
	
	@Test
	public void TestGetRank() {
		assertEquals(king.getRank(), Rank.KING);
	}
	
	@Test
	public void TestGetColor() {
		assertEquals(king.getColor(), 0);
	}
	
	@Test
	public void TestGetLocation() {
		assertEquals(king.getLocation(), new Point(4, 0));
	}
	
	@Test
	public void TestValidMove() {
		
		/////START KING/////
		
		//check move 1 space forward +y
		assertTrue(board.getPiece(4, 7).validMove(new Point(4, 6), board));
		
		//check move 1 space backward -y
		assertTrue(board.getPiece(3, 4).validMove(new Point(3, 5), board));
		
		//check move 1 space right +x
		assertTrue(board.getPiece(4, 7).validMove(new Point(5, 7), board));
		
		//check move 1 space left -x
		assertTrue(board.getPiece(4, 7).validMove(new Point(3, 7), board));
		
		//check move 1 space diagonally forward +x
		assertTrue(board.getPiece(4, 7).validMove(new Point(5, 6), board));
		
		//check move 1 space diagonally forward -x
		assertTrue(board.getPiece(4, 7).validMove(new Point(3, 6), board));
		
		//check move 1 space diagonally backward +x
		assertTrue(board.getPiece(3, 4).validMove(new Point(4, 5), board));
		
		//check move 1 space diagonally backward -x
		assertTrue(board.getPiece(3, 4).validMove(new Point(2, 5), board));
		
		//check move 2 space forward +y
		assertFalse(board.getPiece(4, 7).validMove(new Point(4, 5), board));
		
		//check move 2 space backward -y
		assertFalse(board.getPiece(3, 4).validMove(new Point(3, 6), board));
		
		//check move 2 space right +x
		assertFalse(board.getPiece(4, 7).validMove(new Point(6, 7), board));
		
		//check move 2 space left -x
		assertFalse(board.getPiece(4, 7).validMove(new Point(2, 7), board));
		
		//check move 2 space diagonally forward +x
		assertFalse(board.getPiece(4, 7).validMove(new Point(6, 5), board));
		
		//check move 2 space diagonally forward -x
		assertFalse(board.getPiece(4, 7).validMove(new Point(2, 5), board));
		
		//check move 2 space diagonally backward +x
		assertFalse(board.getPiece(3, 4).validMove(new Point(5, 6), board));
		
		//check move 2 space diagonally backward -x
		assertFalse(board.getPiece(3, 4).validMove(new Point(1, 6), board));
		
		//check move forward if piece is in the way
		board.setPiece(new King(Rank.KING, 0, new Point(3, 3)));
		assertFalse(board.getPiece(3, 4).validMove(new Point(3, 3), board));
		board.getSpace(3, 3).setPiece(null);
		
		//check move backward if piece is in the way
		board.setPiece(new King(Rank.KING, 0, new Point(3, 5)));
		assertFalse(board.getPiece(3, 4).validMove(new Point(3, 5), board));
		board.getSpace(3, 5).setPiece(null);
		
		//check move right if piece is in the way
		board.setPiece(new King(Rank.KING, 0, new Point(4, 4)));
		assertFalse(board.getPiece(3, 4).validMove(new Point(4, 4), board));
		board.getSpace(4, 4).setPiece(null);
		
		//check move left if piece is in the way
		board.setPiece(new King(Rank.KING, 0, new Point(2, 4)));
		assertFalse(board.getPiece(3, 4).validMove(new Point(2, 4), board));
		board.getSpace(2, 4).setPiece(null);
		
		//check move forward diagonally +x if piece is in the way
		board.setPiece(new King(Rank.KING, 0, new Point(4, 3)));
		assertFalse(board.getPiece(3, 4).validMove(new Point(4, 3), board));
		board.getSpace(4, 3).setPiece(null);
		
		//check move forward diagonally -x if piece is in the way
		board.setPiece(new King(Rank.KING, 0, new Point(2, 3)));
		assertFalse(board.getPiece(3, 4).validMove(new Point(2, 3), board));
		board.getSpace(2, 3).setPiece(null);
		
		//check move backward diagonally +x if piece is in the way
		board.setPiece(new King(Rank.KING, 0, new Point(4, 5)));
		assertFalse(board.getPiece(3, 4).validMove(new Point(4, 5), board));
		board.getSpace(4, 5).setPiece(null);
		
		//check move backward diagonally -x if piece is in the way
		board.setPiece(new King(Rank.KING, 0, new Point(2, 5)));
		assertFalse(board.getPiece(3, 4).validMove(new Point(2, 5), board));
		board.getSpace(2, 5).setPiece(null);
		
		//check capture forward
		board.setPiece(new King(Rank.KING, 1, new Point(3, 3)));
		assertTrue(board.getPiece(3, 4).validMove(new Point(3, 3), board));
		board.getSpace(3, 3).setPiece(null);
		
		//check capture backward
		board.setPiece(new King(Rank.KING, 1, new Point(3, 5)));
		assertTrue(board.getPiece(3, 4).validMove(new Point(3, 5), board));
		board.getSpace(3, 5).setPiece(null);
		
		//check capture right
		board.setPiece(new King(Rank.KING, 1, new Point(4, 4)));
		assertTrue(board.getPiece(3, 4).validMove(new Point(4, 4), board));
		board.getSpace(4, 4).setPiece(null);
		
		//check capture left
		board.setPiece(new King(Rank.KING, 1, new Point(2, 4)));
		assertTrue(board.getPiece(3, 4).validMove(new Point(2, 4), board));
		board.getSpace(2, 4).setPiece(null);
		
		//check capture forward diagonally +x 
		board.setPiece(new King(Rank.KING, 1, new Point(4, 3)));
		assertTrue(board.getPiece(3, 4).validMove(new Point(4, 3), board));
		board.getSpace(4, 3).setPiece(null);
		
		//check capture forward diagonally -x
		board.setPiece(new King(Rank.KING, 1, new Point(2, 3)));
		assertTrue(board.getPiece(3, 4).validMove(new Point(2, 3), board));
		board.getSpace(2, 3).setPiece(null);
		
		//check capture backward diagonally +x
		board.setPiece(new King(Rank.KING, 1, new Point(4, 5)));
		assertTrue(board.getPiece(3, 4).validMove(new Point(4, 5), board));
		board.getSpace(4, 5).setPiece(null);
		
		//check capture backward diagonally -x
		board.setPiece(new King(Rank.KING, 1, new Point(2, 5)));
		assertTrue(board.getPiece(3, 4).validMove(new Point(2, 5), board));
		board.getSpace(2, 5).setPiece(null);
		
		/////END KING/////
		
	}
}