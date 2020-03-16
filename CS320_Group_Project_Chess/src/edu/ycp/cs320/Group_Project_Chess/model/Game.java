package edu.ycp.cs320.Group_Project_Chess.model;

import java.awt.Point;

public class Game {
	
	private Board board;
	private Player player1;
	private Player player2;
	
	//constructor for a new game of chess
	public Game(Player player1, Player player2) {
		board = new Board();
		this.player1 = player1;
		this.player2 = player2;
		
		//pieces need rank, color, location
		//white = 0 black = 1
		
		//create  pawns
		for(int i = 0; i < 8; i++){
			board.setPiece(new Pawn(Rank.PAWN, 1, new Point(i, 1)));
			board.setPiece(new Pawn(Rank.PAWN, 0, new Point(i, 6)));
		}
		
		//create knights
		for(int i = 0; i < 2; i++){
			board.setPiece(new Pawn(Rank.KNIGHT, 1, new Point((i*5)+1, 0)));
			board.setPiece(new Pawn(Rank.KNIGHT, 0, new Point((i*5)+1, 7)));		
		}
		
		//create rooks
		for(int i = 0; i < 2; i++){
			board.setPiece(new Pawn(Rank.ROOK, 1, new Point((i*7), 0)));
			board.setPiece(new Pawn(Rank.ROOK, 0, new Point((i*7), 7)));
		}
		
		//create bishops
		for(int i = 0; i < 2; i++){
			board.setPiece(new Pawn(Rank.BISHOP, 1, new Point((i*3)+2, 0)));
			board.setPiece(new Pawn(Rank.BISHOP, 0, new Point((i*3)+2, 7)));	
		}
		
		//create kings
		board.setPiece(new Pawn(Rank.KING, 1, new Point(3, 0)));
		board.setPiece(new Pawn(Rank.KING, 0, new Point(3, 7)));
		
		//create queens
		board.setPiece(new Pawn(Rank.QUEEN, 1, new Point(4, 0)));
		board.setPiece(new Pawn(Rank.QUEEN, 0, new Point(4, 7)));
		
	}
	
	//constructor to load a previous game
	public Game(Player player1, Player player2, Board board) {
		this.board = board;
		this.player1 = player1;
		this.player2 = player2;
	}
	
	public Player getPlayer1() {
		return player1;
	}
	
	public Player getPlayer2() {
		return player2;
	}
	
	public Board getBoard() {
		return board;
	}
	
	
	
}
