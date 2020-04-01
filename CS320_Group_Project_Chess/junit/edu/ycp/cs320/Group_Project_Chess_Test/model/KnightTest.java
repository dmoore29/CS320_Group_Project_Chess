package edu.ycp.cs320.Group_Project_Chess_Test.model;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.Group_Project_Chess.model.Bishop;
import edu.ycp.cs320.Group_Project_Chess.model.Board;
import edu.ycp.cs320.Group_Project_Chess.model.Knight;
import edu.ycp.cs320.Group_Project_Chess.model.Rank;





public class KnightTest {
	private Knight knight;
	private Board board = new Board();
	
	@Before
	public void SetUp() {
		knight = new Knight(Rank.KNIGHT, 0, new Point(4, 0));
		board.newGameBoard();
		board.setPiece(new Knight(Rank.KNIGHT, 0, new Point(1, 7)));
		board.setPiece(new Knight(Rank.KNIGHT, 0, new Point(3, 4))); //central piece
	}
	
	@Test
	public void TestGetRank() {
		assertEquals(knight.getRank(), Rank.KNIGHT);
	}
	
	@Test
	public void TestGetColor() {
		assertEquals(knight.getColor(), 0);
	}
	
	@Test
	public void TestGetLocation() {
		assertEquals(knight.getLocation(), new Point(4, 0));
	}
	
	@Test
	public void TestValidMove() {
		/////START KNIGHT/////
		
		//check move 2 spaces up 1 space +x 
		assertTrue(board.getPiece(3, 4).validMove(new Point(4, 2), board));
		
		//check move 2 spaces up 1 space -x 
		assertTrue(board.getPiece(3, 4).validMove(new Point(2, 2), board));
		
		//check move 2 spaces down 1 space +x 
		assertTrue(board.getPiece(3, 4).validMove(new Point(4, 6), board));
		
		//check move 2 spaces down 1 space -x 
		assertTrue(board.getPiece(3, 4).validMove(new Point(2, 6), board));
		
		//check move 2 spaces right 1 space +y 
		assertTrue(board.getPiece(3, 4).validMove(new Point(5, 3), board));
		
		//check move 2 spaces right 1 space -y 
		assertTrue(board.getPiece(3, 4).validMove(new Point(5, 5), board));
		
		//check move 2 spaces left 1 space +y 
		assertTrue(board.getPiece(3, 4).validMove(new Point(1, 3), board));
		
		//check move 2 spaces right 1 space -y 
		assertTrue(board.getPiece(3, 4).validMove(new Point(1, 5), board));
		
		//check jump piece forward
		board.setPiece(new Knight(Rank.KNIGHT, 0, new Point(3, 3)));
		assertTrue(board.getPiece(3, 4).validMove(new Point(4, 2), board));
		board.getSpace(3, 3).setPiece(null);
		
		//check jump piece backward
		board.setPiece(new Knight(Rank.KNIGHT, 0, new Point(3, 5)));
		assertTrue(board.getPiece(3, 4).validMove(new Point(4, 6), board));
		board.getSpace(3, 5).setPiece(null);
		
		//check jump piece right
		board.setPiece(new Knight(Rank.KNIGHT, 0, new Point(4, 4)));
		assertTrue(board.getPiece(3, 4).validMove(new Point(5, 5), board));
		board.getSpace(4, 4).setPiece(null);
		
		//check jump piece left
		board.setPiece(new Knight(Rank.KNIGHT, 0, new Point(2, 4)));
		assertTrue(board.getPiece(3, 4).validMove(new Point(1, 3), board));
		board.getSpace(2, 4).setPiece(null);
		
		//check capture piece forward +x
		board.setPiece(new Knight(Rank.KNIGHT, 1, new Point(4, 2)));
		assertTrue(board.getPiece(3, 4).validMove(new Point(4, 2), board));
		board.getSpace(4, 2).setPiece(null);
		
		//check capture piece forward -x
		board.setPiece(new Knight(Rank.KNIGHT, 1, new Point(2, 2)));
		assertTrue(board.getPiece(3, 4).validMove(new Point(2, 2), board));
		board.getSpace(2, 2).setPiece(null);
		
		//check capture piece backward +x
		board.setPiece(new Knight(Rank.KNIGHT, 1, new Point(4, 6)));
		assertTrue(board.getPiece(3, 4).validMove(new Point(4, 6), board));
		board.getSpace(4, 6).setPiece(null);
		
		//check capture piece backward -x
		board.setPiece(new Knight(Rank.KNIGHT, 1, new Point(2, 6)));
		assertTrue(board.getPiece(3, 4).validMove(new Point(2, 6), board));
		board.getSpace(2, 6).setPiece(null);
		
		//check capture piece right +y
		board.setPiece(new Knight(Rank.KNIGHT, 1, new Point(5, 3)));
		assertTrue(board.getPiece(3, 4).validMove(new Point(5, 3), board));
		board.getSpace(5, 3).setPiece(null);
		
		//check capture piece right -y
		board.setPiece(new Knight(Rank.KNIGHT, 1, new Point(5, 5)));
		assertTrue(board.getPiece(3, 4).validMove(new Point(5, 5), board));
		board.getSpace(5, 5).setPiece(null);
		
		//check capture piece left +y
		board.setPiece(new Knight(Rank.KNIGHT, 1, new Point(1, 3)));
		assertTrue(board.getPiece(3, 4).validMove(new Point(1, 3), board));
		board.getSpace(1, 3).setPiece(null);
		
		//check capture piece left -y
		board.setPiece(new Knight(Rank.KNIGHT, 1, new Point(1, 5)));
		assertTrue(board.getPiece(3, 4).validMove(new Point(1, 5), board));
		board.getSpace(1, 5).setPiece(null);
		
		
		
		/////END KNIGHT/////
		
	}
}