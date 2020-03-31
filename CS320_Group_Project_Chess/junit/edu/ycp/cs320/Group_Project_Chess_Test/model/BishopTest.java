package edu.ycp.cs320.Group_Project_Chess_Test.model;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.Group_Project_Chess.model.Bishop;
import edu.ycp.cs320.Group_Project_Chess.model.Board;
import edu.ycp.cs320.Group_Project_Chess.model.Rank;





public class BishopTest {
	private Bishop bishop;
	private Board board = new Board();
	
	@Before
	public void SetUp() {
		bishop = new Bishop(Rank.BISHOP, 0, new Point(4, 0));
		board.newGameBoard();
		board.setPiece(new Bishop(Rank.BISHOP, 0, new Point(2, 7)));
		board.setPiece(new Bishop(Rank.BISHOP, 0, new Point(4, 3))); //central piece
	}
	
	@Test
	public void TestGetRank() {
		assertEquals(bishop.getRank(), Rank.BISHOP);
	}
	
	@Test
	public void TestGetColor() {
		assertEquals(bishop.getColor(), 0);
	}
	
	@Test
	public void TestGetLocation() {
		assertEquals(bishop.getLocation(), new Point(4, 0));
	}
	
	@Test
	public void TestValidMove() {
		/////START BISHOP/////
		
		//check move 1 space forward diagonally +x 
		assertTrue(board.getPiece(2, 7).validMove(new Point(3, 6), board));
		
		//check move 1 space forward diagonally -x 
		assertTrue(board.getPiece(2, 7).validMove(new Point(1, 6), board));
		
		//check move 3 space forward diagonally +x 
		assertTrue(board.getPiece(3, 4).validMove(new Point(6, 1), board));
		
		//check move 3 space forward diagonally -x 
		assertTrue(board.getPiece(3, 4).validMove(new Point(0, 1), board));
		
		//check move 1 space backward diagonally +x 
		assertTrue(board.getPiece(3, 4).validMove(new Point(4, 5), board));
		
		//check move 1 space backward diagonally -x 
		assertTrue(board.getPiece(3, 4).validMove(new Point(2, 5), board));
		
		//check move 3 space backward diagonally +x 
		assertTrue(board.getPiece(3, 4).validMove(new Point(6, 7), board));
		
		//check move 3 space backward diagonally -x 
		assertTrue(board.getPiece(3, 4).validMove(new Point(0, 7), board));
		
		//check move 1 space forward vertically +y 
		assertFalse(board.getPiece(3, 4).validMove(new Point(3, 3), board));
		
		//check move 1 space backward vertically -y 
		assertFalse(board.getPiece(3, 4).validMove(new Point(3, 5), board));
		
		//check move 1 space horizontally +x
		assertFalse(board.getPiece(3, 4).validMove(new Point(4, 4), board));
		
		//check move 1 space horizontally -x
		assertFalse(board.getPiece(3, 4).validMove(new Point(2, 4), board));
		
		//check move 1 space forward diagonally +x with piece in the way
		board.setPiece(new Bishop(Rank.BISHOP, 0, new Point(3, 6)));
		assertFalse(board.getPiece(2, 7).validMove(new Point(3, 6), board));
		
		//check move 1 space forward diagonally -x with piece in the way
		board.setPiece(new Bishop(Rank.BISHOP, 0, new Point(1, 6)));
		assertFalse(board.getPiece(2, 7).validMove(new Point(1, 6), board));
		
		//check move 1 space backward diagonally +x with piece in the way 
		board.setPiece(new Bishop(Rank.BISHOP, 0, new Point(4, 5)));
		assertFalse(board.getPiece(3, 4).validMove(new Point(4, 5), board));
		
		//check move 1 space backward diagonally -x with piece in the way 
		board.setPiece(new Bishop(Rank.BISHOP, 0, new Point(2, 5)));
		assertFalse(board.getPiece(3, 4).validMove(new Point(2, 5), board));
		
		//check move 2 spaces forward diagonally +x with piece in the way
		board.setPiece(new Bishop(Rank.BISHOP, 0, new Point(3, 6)));
		assertFalse(board.getPiece(2, 7).validMove(new Point(4, 5), board));
		
		//check move 2 spaces forward diagonally -x with piece in the way
		board.setPiece(new Bishop(Rank.BISHOP, 0, new Point(1, 6)));
		assertFalse(board.getPiece(2, 7).validMove(new Point(0, 5), board));
		
		//check move 2 spaces backward diagonally +x with piece in the way 
		board.setPiece(new Bishop(Rank.BISHOP, 0, new Point(4, 5)));
		assertFalse(board.getPiece(3, 4).validMove(new Point(5, 6), board));
		
		//check move 2 spaces backward diagonally -x with piece in the way 
		board.setPiece(new Bishop(Rank.BISHOP, 0, new Point(2, 5)));
		assertFalse(board.getPiece(3, 4).validMove(new Point(1, 6), board));
		
		//check capture forward 1 space diagonally +x
		board.setPiece(new Bishop(Rank.BISHOP, 1, new Point(3, 6)));
		assertTrue(board.getPiece(2, 7).validMove(new Point(3, 6), board));
		
		//check capture forward 1 space diagonally -x
		board.setPiece(new Bishop(Rank.BISHOP, 1, new Point(1, 6)));
		assertTrue(board.getPiece(2, 7).validMove(new Point(1, 6), board));
		
		//check capture backward 1 space diagonally +x
		board.setPiece(new Bishop(Rank.BISHOP, 1, new Point(4, 5)));
		assertTrue(board.getPiece(3, 4).validMove(new Point(4, 5), board));
		
		//check capture backward 1 space diagonally -x
		board.setPiece(new Bishop(Rank.BISHOP, 1, new Point(2, 5)));
		assertTrue(board.getPiece(3, 4).validMove(new Point(2, 5), board));
		
		//check capture forward 3 spaces diagonally +x
		board.setPiece(new Bishop(Rank.BISHOP, 1, new Point(5, 4)));
		assertTrue(board.getPiece(2, 7).validMove(new Point(5, 4), board));
		
		//check capture forward 3 spaces diagonally -x
		board.setPiece(new Bishop(Rank.BISHOP, 1, new Point(0, 1)));
		assertTrue(board.getPiece(3, 4).validMove(new Point(0, 1), board));
		
		//check capture backward 3 spaces diagonally +x
		board.setPiece(new Bishop(Rank.BISHOP, 1, new Point(6, 7)));
		assertTrue(board.getPiece(3, 4).validMove(new Point(6, 7), board));
		
		//check capture backward 3 spaces diagonally -x
		board.setPiece(new Bishop(Rank.BISHOP, 1, new Point(0, 7)));
		assertTrue(board.getPiece(3, 4).validMove(new Point(0, 7), board));
		
				
		/////END BISHOP/////
	}
}