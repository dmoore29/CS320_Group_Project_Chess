package edu.ycp.cs320.Group_Project_Chess.model;

public class Game {
	
	private Board board;
	private Player player1;
	private Player player2;
	
	//constructor for a new game of chess
	public Game(Player player1, Player player2) {
		board = new Board();
		this.player1 = player1;
		this.player2 = player2;
		
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
