package edu.ycp.cs320.Group_Project_Chess_Test.model;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.Group_Project_Chess.model.Bishop;
import edu.ycp.cs320.Group_Project_Chess.model.Board;
import edu.ycp.cs320.Group_Project_Chess.model.Pawn;
import edu.ycp.cs320.Group_Project_Chess.model.Rank;





public class BishopTest {
	private Bishop bishop;
	private Board board = new Board();
	
	@Before
	public void SetUp() {
		bishop = new Bishop(Rank.BISHOP, 0, new Point(4, 0));
		board.newGameBoard();
		board.setPiece(new Pawn(Rank.BISHOP, 0, new Point(2, 7)));
		board.setPiece(new Pawn(Rank.BISHOP, 1, new Point(2, 0)));
		board.setPiece(new Pawn(Rank.BISHOP, 0, new Point(4, 3))); //central piece
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
		/////START WHITE BISHOP/////
		
		//check move 1 space forward diagonally +x 
		assertTrue(board.getPiece(2, 7).validMove(new Point(3, 6), board));
		
		//check move 1 space forward diagonally -x 
		assertTrue(board.getPiece(2, 7).validMove(new Point(1, 6), board));
		
		//check move 1 space backward diagonally +x 
		assertTrue(board.getPiece(4, 3).validMove(new Point(5, 4), board));
		
		//check move 1 space backward diagonally -x 
		assertTrue(board.getPiece(4, 3).validMove(new Point(5, 2), board));
		
		//check move 1 space forward vertically +y 
		assertFalse(board.getPiece(4, 3).validMove(new Point(3, 3), board));
		
		//check move 1 space backward vertically -y 
		assertFalse(board.getPiece(4, 3).validMove(new Point(5, 3), board));
		
		//check move 1 space horizontally +x
		assertFalse(board.getPiece(4, 3).validMove(new Point(4, 4), board));
		
		//check move 1 space horizontally -x
		assertFalse(board.getPiece(4, 3).validMove(new Point(4, 2), board));
		
				
		/////END WHITE BISHOP/////
	}
}