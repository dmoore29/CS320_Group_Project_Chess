package edu.ycp.cs320.Group_Project_Chess_Test.model;

import static org.junit.Assert.*;

import java.awt.Point;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.Group_Project_Chess.model.Bishop;
import edu.ycp.cs320.Group_Project_Chess.model.Board;
import edu.ycp.cs320.Group_Project_Chess.model.King;
import edu.ycp.cs320.Group_Project_Chess.model.Queen;
import edu.ycp.cs320.Group_Project_Chess.model.Rank;
import edu.ycp.cs320.Group_Project_Chess.model.Rook;





public class QueenTest {
	private Queen queen;
	private Board board = new Board();
	
	@Before
	public void SetUp() {
		queen = new Queen(Rank.QUEEN, 0, new Point(4, 0));
		board.newGameBoard();
		board.setPiece(new Queen(Rank.QUEEN, 0, new Point(4, 7)));
		board.setPiece(new Queen(Rank.QUEEN, 0, new Point(3, 4))); //central piece
	}
	
	@Test
	public void TestGetRank() {
		assertEquals(queen.getRank(), Rank.QUEEN);
	}
	
	@Test
	public void TestGetColor() {
		assertEquals(queen.getColor(), 0);
	}
	
	@Test
	public void TestGetLocation() {
		assertEquals(queen.getLocation(), new Point(4, 0));
	}
	
	@Test
	public void TestValidMove() {
		
		/////START QUEEN/////
		
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
		board.setPiece(new Queen(Rank.BISHOP, 0, new Point(3, 6)));
		assertFalse(board.getPiece(2, 7).validMove(new Point(3, 6), board));
		board.getSpace(3, 6).setPiece(null);
		
		//check move 1 space forward diagonally -x with piece in the way
		board.setPiece(new Queen(Rank.BISHOP, 0, new Point(1, 6)));
		assertFalse(board.getPiece(2, 7).validMove(new Point(1, 6), board));
		board.getSpace(1, 6).setPiece(null);
		
		//check move 1 space backward diagonally +x with piece in the way 
		board.setPiece(new Queen(Rank.BISHOP, 0, new Point(4, 5)));
		assertFalse(board.getPiece(3, 4).validMove(new Point(4, 5), board));
		board.getSpace(4, 5).setPiece(null);
		
		//check move 1 space backward diagonally -x with piece in the way 
		board.setPiece(new Queen(Rank.BISHOP, 0, new Point(2, 5)));
		assertFalse(board.getPiece(3, 4).validMove(new Point(2, 5), board));
		board.getSpace(2, 5).setPiece(null);
		
		//check move 2 spaces forward diagonally +x with piece in the way
		board.setPiece(new Queen(Rank.BISHOP, 0, new Point(3, 6)));
		assertFalse(board.getPiece(2, 7).validMove(new Point(4, 5), board));
		board.getSpace(3, 6).setPiece(null);
		
		//check move 2 spaces forward diagonally -x with piece in the way
		board.setPiece(new Queen(Rank.BISHOP, 0, new Point(1, 6)));
		assertFalse(board.getPiece(2, 7).validMove(new Point(0, 5), board));
		board.getSpace(1, 6).setPiece(null);
		
		//check move 2 spaces backward diagonally +x with piece in the way 
		board.setPiece(new Queen(Rank.BISHOP, 0, new Point(4, 5)));
		assertFalse(board.getPiece(3, 4).validMove(new Point(5, 6), board));
		board.getSpace(4, 5).setPiece(null);
		
		//check move 2 spaces backward diagonally -x with piece in the way 
		board.setPiece(new Queen(Rank.BISHOP, 0, new Point(2, 5)));
		assertFalse(board.getPiece(3, 4).validMove(new Point(1, 6), board));
		board.getSpace(2, 5).setPiece(null);
		
		//check capture forward 1 space diagonally +x
		board.setPiece(new Queen(Rank.BISHOP, 1, new Point(3, 6)));
		assertTrue(board.getPiece(2, 7).validMove(new Point(3, 6), board));
		board.getSpace(3, 6).setPiece(null);
		
		//check capture forward 1 space diagonally -x
		board.setPiece(new Queen(Rank.BISHOP, 1, new Point(1, 6)));
		assertTrue(board.getPiece(2, 7).validMove(new Point(1, 6), board));
		board.getSpace(1, 6).setPiece(null);
		
		//check capture backward 1 space diagonally +x
		board.setPiece(new Queen(Rank.BISHOP, 1, new Point(4, 5)));
		assertTrue(board.getPiece(3, 4).validMove(new Point(4, 5), board));
		board.getSpace(4, 5).setPiece(null);
		
		//check capture backward 1 space diagonally -x
		board.setPiece(new Queen(Rank.BISHOP, 1, new Point(2, 5)));
		assertTrue(board.getPiece(3, 4).validMove(new Point(2, 5), board));
		board.getSpace(2, 5).setPiece(null);
		
		//check capture forward 3 spaces diagonally +x
		board.setPiece(new Queen(Rank.BISHOP, 1, new Point(5, 4)));
		assertTrue(board.getPiece(2, 7).validMove(new Point(5, 4), board));
		board.getSpace(5, 4).setPiece(null);
		
		//check capture forward 3 spaces diagonally -x
		board.setPiece(new Queen(Rank.BISHOP, 1, new Point(0, 1)));
		assertTrue(board.getPiece(3, 4).validMove(new Point(0, 1), board));
		board.getSpace(0, 1).setPiece(null);
		
		//check capture backward 3 spaces diagonally +x
		board.setPiece(new Queen(Rank.BISHOP, 1, new Point(6, 7)));
		assertTrue(board.getPiece(3, 4).validMove(new Point(6, 7), board));
		board.getSpace(6, 7).setPiece(null);
		
		//check capture backward 3 spaces diagonally -x
		board.setPiece(new Queen(Rank.BISHOP, 1, new Point(0, 7)));
		assertTrue(board.getPiece(3, 4).validMove(new Point(0, 7), board));
		board.getSpace(0, 7).setPiece(null);
		
		//check move 1 space forward vertically +y 
		assertTrue(board.getPiece(4, 3).validMove(new Point(4, 2), board));
		
		//check move 1 space backward vertically -y 
		assertTrue(board.getPiece(4, 3).validMove(new Point(4, 4), board));
		
		//check move 1 space forward horizontally +x 
		assertTrue(board.getPiece(4, 3).validMove(new Point(5, 3), board));
		  
		//check move 1 space backward horizontally -x 
		assertTrue(board.getPiece(4, 3).validMove(new Point(3, 3), board));
		  
		//check move entirety of the board +y 
		assertTrue(board.getPiece(0, 7).validMove(new Point(0, 0), board));
		  
		//check move entirety of the board -y 
		board.setPiece(new Rook(Rank.ROOK, 0, new Point(7, 0))); 
		assertTrue(board.getPiece(7, 0).validMove(new Point(7, 7), board));
		board.getSpace(7, 0).setPiece(null);
		  
		//check move entirety of the board +x 
		assertTrue(board.getPiece(0, 7).validMove(new Point(7, 7), board));
		  
		//check move entirety of the board -x 
		board.setPiece(new Rook(Rank.ROOK, 0, new Point(7, 4))); 
		assertTrue(board.getPiece(7, 4).validMove(new Point(0, 4), board));
		board.getSpace(7, 4).setPiece(null);
		
		//check move 1 space forward if piece is in the way +y
		board.setPiece(new Rook(Rank.ROOK, 0, new Point(0, 6))); 
		assertFalse(board.getPiece(0, 7).validMove(new Point(0, 6), board));
		board.getSpace(0, 6).setPiece(null);
		
		//check move 1 space backward if piece is in the way -y
		board.setPiece(new Rook(Rank.ROOK, 0, new Point(4, 4))); 
		assertFalse(board.getPiece(4, 3).validMove(new Point(4, 4), board));
		board.getSpace(4, 4).setPiece(null);
		
		//check move 1 space horizontally if piece is in the way +x
		board.setPiece(new Rook(Rank.ROOK, 0, new Point(1, 7))); 
		assertFalse(board.getPiece(0, 7).validMove(new Point(1, 7), board));
		board.getSpace(1, 7).setPiece(null);
		
		//check move 1 space horizontally if piece is in the way -x
		board.setPiece(new Rook(Rank.ROOK, 0, new Point(3, 3))); 
		assertFalse(board.getPiece(4, 3).validMove(new Point(3, 3), board));
		board.getSpace(3, 3).setPiece(null);
		
		//check move 2 spaces forward if piece is in the way +y
		board.setPiece(new Rook(Rank.ROOK, 0, new Point(0, 6))); 
		assertFalse(board.getPiece(0, 7).validMove(new Point(0, 5), board));
		board.getSpace(0, 6).setPiece(null);
		
		//check move 2 spaces backward if piece is in the way -y
		board.setPiece(new Rook(Rank.ROOK, 0, new Point(4, 4))); 
		assertFalse(board.getPiece(4, 3).validMove(new Point(4, 5), board));
		board.getSpace(4, 4).setPiece(null);
		
		//check move 2 spaces horizontally if piece is in the way +x
		board.setPiece(new Rook(Rank.ROOK, 0, new Point(1, 7))); 
		assertFalse(board.getPiece(0, 7).validMove(new Point(2, 7), board));
		board.getSpace(1, 7).setPiece(null);
		
		//check move 2 spaces horizontally if piece is in the way -x
		board.setPiece(new Rook(Rank.ROOK, 0, new Point(3, 3))); 
		assertFalse(board.getPiece(4, 3).validMove(new Point(2, 3), board));
		board.getSpace(3, 3).setPiece(null);
		
		//capture piece +y
		board.setPiece(new Rook(Rank.ROOK, 1, new Point(0, 6))); 
		assertTrue(board.getPiece(0, 7).validMove(new Point(0, 6), board));
		board.getSpace(0, 6).setPiece(null);
		
		//capture piece -y
		board.setPiece(new Rook(Rank.ROOK, 1, new Point(4, 4))); 
		assertTrue(board.getPiece(4, 3).validMove(new Point(4, 4), board));
		board.getSpace(4, 4).setPiece(null);
		
		//capture piece +x
		board.setPiece(new Rook(Rank.ROOK, 1, new Point(1, 7))); 
		assertTrue(board.getPiece(0, 7).validMove(new Point(1, 7), board));
		board.getSpace(1, 7).setPiece(null);
		
		//capture piece -x
		board.setPiece(new Rook(Rank.ROOK, 1, new Point(3, 3))); 
		assertTrue(board.getPiece(4, 3).validMove(new Point(3, 3), board));
		board.getSpace(3, 3).setPiece(null);
		
		//capture piece long distance +y
		board.setPiece(new Rook(Rank.ROOK, 1, new Point(0, 0))); 
		assertTrue(board.getPiece(0, 7).validMove(new Point(0, 0), board));
		board.getSpace(0, 0).setPiece(null);
		
		//capture piece long distance -y
		board.setPiece(new Rook(Rank.ROOK, 1, new Point(7, 7))); 
		board.setPiece(new Rook(Rank.ROOK, 0, new Point(7, 0))); 
		assertTrue(board.getPiece(7, 0).validMove(new Point(7, 7), board));
		board.getSpace(7, 7).setPiece(null);
		board.getSpace(7, 0).setPiece(null);
		
		//capture piece long distance +x
		board.setPiece(new Rook(Rank.ROOK, 1, new Point(7, 7))); 
		assertTrue(board.getPiece(0, 7).validMove(new Point(7, 7), board));
		board.getSpace(7, 7).setPiece(null);
		
		//capture piece long distance -x
		board.setPiece(new Rook(Rank.ROOK, 1, new Point(0, 0))); 
		board.setPiece(new Rook(Rank.ROOK, 0, new Point(7, 0))); 
		assertTrue(board.getPiece(7, 0).validMove(new Point(0, 0), board));
		board.getSpace(0, 0).setPiece(null);
		board.getSpace(7, 0).setPiece(null);
	}
}