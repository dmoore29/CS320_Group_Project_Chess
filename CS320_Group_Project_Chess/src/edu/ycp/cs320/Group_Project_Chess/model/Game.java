package edu.ycp.cs320.Group_Project_Chess.model;

import java.awt.Point;

public class Game {
	private int gameId;
	private int userId;
	private Board board;
	private Player player1;
	private Player player2;
	private int turn;
	private int promo;
	private int enPx;
	private int enPy;
	private boolean moved001; //black left rook
	private boolean moved701; //black right rook
	private boolean moved401; //black king
	private boolean moved070; //white left rook
	private boolean moved770; //white right rook
	private boolean moved470; //white king
	
	//constructor for a new game of chess
	public Game(Player player1, Player player2) {

		board = new Board();
		board.newGameBoard();
		this.player1 = player1;
		this.player2 = player2;
		turn = 0;
		promo = 0;
		enPx = 8;
		enPy = 8;
		moved001 = false;
		moved701 = false;
		moved401 = false;
		moved070 = false;
		moved770 = false;
		moved470 = false;
		
		//pieces need rank, color, location
		//white = 0 black = 1
		
		//create  pawns
		for(int i = 0; i < 8; i++){
			board.setPiece(new Pawn(Rank.PAWN, 1, new Point(i, 1)));
			board.setPiece(new Pawn(Rank.PAWN, 0, new Point(i, 6)));
		}
		
		//create knights, rooks, and bishops
		for(int i = 0; i < 2; i++){
			board.setPiece(new Knight(Rank.KNIGHT, 1, new Point((i*5)+1, 0)));
			board.setPiece(new Knight(Rank.KNIGHT, 0, new Point((i*5)+1, 7)));
			board.setPiece(new Rook(Rank.ROOK, 1, new Point((i*7), 0)));
			board.setPiece(new Rook(Rank.ROOK, 0, new Point((i*7), 7)));
			board.setPiece(new Bishop(Rank.BISHOP, 1, new Point((i*3)+2, 0)));
			board.setPiece(new Bishop(Rank.BISHOP, 0, new Point((i*3)+2, 7)));
		}
		
		//create kings
		board.setPiece(new King(Rank.KING, 1, new Point(4, 0)));
		board.setPiece(new King(Rank.KING, 0, new Point(4, 7)));
		
		//create queens
		board.setPiece(new Queen(Rank.QUEEN, 1, new Point(3, 0)));
		board.setPiece(new Queen(Rank.QUEEN, 0, new Point(3, 7)));
		
	}
	
	//constructor to load a previous game
	public Game(Player player1, Player player2, Board board, int turn) {
		this.turn = turn;
		this.board = board;
		this.player1 = player1;
		this.player2 = player2;
	}
	
	public Game() {
		player1 = new Player();
		player2 = new Player();
		board = new Board();
	}
	
	public Player getPlayer1() {
		return player1;
	}
	
	public Player getPlayer2() {
		return player2;
	}
	
	public void setPlayer1(Player player) {
		player1 = player;
	}
	
	public void setPlayer2(Player player) {
		player2 = player;
	}
	
	public Board getBoard() {
		return board;
	}
	
	public void setBoard(Board board) {
		this.board = board;
	}
	
	public void setUserId(int id) {
		this.userId = id;
	}
	
	public void setGameId(int id) {
		this.gameId = id;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public int getGameId() {
		return gameId;
	}
	
	public void setTurn(int turn) {
		this.turn = turn;
	}
	
	public int getTurn() {
		return turn;
	}	
	
	public int getPromo(){
		return promo;
	}
	
	public void setPromo(int promo) {
		this.promo = promo;
	}
	
	public int getEnPx(){
		return enPx;
	}
	
	public void setEnPx(int enPx) {
		this.enPx = enPx;
	}
	
	public int getEnPy(){
		return enPy;
	}
	
	public void setEnPy(int enPy) {
		this.enPy = enPy;
	}
	
	public void setMoved001(boolean moved) {
		moved001 = moved;
	}
	
	public boolean getMoved001() {
		return moved001;
	}
	
	public void setMoved701(boolean moved) {
		moved701 = moved;
	}
	
	public boolean getMoved701() {
		return moved701;
	}	
	public void setMoved401(boolean moved) {
		moved401 = moved;
	}
	
	public boolean getMoved401() {
		return moved401;
	}	
	public void setMoved070(boolean moved) {
		moved070 = moved;
	}
	
	public boolean getMoved070() {
		return moved070;
	}	
	public void setMoved770(boolean moved) {
		moved770 = moved;
	}
	
	public boolean getMoved770() {
		return moved770;
	}	
	public void setMoved470(boolean moved) {
		moved470 = moved;
	}
	
	public boolean getMoved470() {
		return moved470;
	}	
}
