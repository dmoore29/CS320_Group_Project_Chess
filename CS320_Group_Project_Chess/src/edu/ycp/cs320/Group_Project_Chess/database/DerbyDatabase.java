package edu.ycp.cs320.Group_Project_Chess.database;

import java.awt.Point;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.ycp.cs320.Group_Project_Chess.model.Bishop;
import edu.ycp.cs320.Group_Project_Chess.model.Board;
import edu.ycp.cs320.Group_Project_Chess.model.Game;
import edu.ycp.cs320.Group_Project_Chess.model.King;
import edu.ycp.cs320.Group_Project_Chess.model.Knight;
import edu.ycp.cs320.Group_Project_Chess.model.Pawn;
import edu.ycp.cs320.Group_Project_Chess.model.Queen;
import edu.ycp.cs320.Group_Project_Chess.model.Rank;
import edu.ycp.cs320.Group_Project_Chess.model.Rook;
import edu.ycp.cs320.Group_Project_Chess.model.Space;
import edu.ycp.cs320.Group_Project_Chess.model.User;

public class DerbyDatabase{
	
// from library example
	static {
		try {
			Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
		} catch (Exception e) {
			throw new IllegalStateException("Could not load Derby driver");
		}
	}
	
	private interface Transaction<ResultType> {
		public ResultType execute(Connection conn) throws SQLException;
	}

	private static final int MAX_ATTEMPTS = 10;
	
	// wrapper SQL transaction function that calls actual transaction function (which has retries)
	public<ResultType> ResultType executeTransaction(Transaction<ResultType> txn) {
		try {
			return doExecuteTransaction(txn);
		} catch (SQLException e) {
			throw new PersistenceException("Transaction failed", e);
		}
	}
		
	// SQL transaction function which retries the transaction MAX_ATTEMPTS times before failing
	public<ResultType> ResultType doExecuteTransaction(Transaction<ResultType> txn) throws SQLException {
		Connection conn = connect();
		
		try {
			int numAttempts = 0;
			boolean success = false;
			ResultType result = null;
			
			while (!success && numAttempts < MAX_ATTEMPTS) {
				try {
					result = txn.execute(conn);
					conn.commit();
					success = true;
				} catch (SQLException e) {
					if (e.getSQLState() != null && e.getSQLState().equals("41000")) {
						// Deadlock: retry (unless max retry count has been reached)
						numAttempts++;
					} else {
						// Some other kind of SQLException
						throw e;
					}
				}
			}
			
			if (!success) {
				throw new SQLException("Transaction failed (too many retries)");
			}
			
			// Success!
			return result;
		} finally {
			DBUtil.closeQuietly(conn);
		}
	}
	
	private Connection connect() throws SQLException {
		Connection conn = DriverManager.getConnection("jdbc:derby:C:/CS320-Group_Project_Chess/chess.db;create=true");		
		
		// Set autocommit() to false to allow the execution of
		// multiple queries/statements as part of the same transaction.
		conn.setAutoCommit(false);
		
		return conn;
	}
// from library example
	
	private void loadUser(User user, ResultSet resultSet, int index) throws SQLException {
		user.setUserId(resultSet.getInt(index++));
		user.getFriends().setFriendsId(resultSet.getInt(index++));
		user.getCredentials().setEmail(resultSet.getString(index++));
		user.getCredentials().setUsername(resultSet.getString(index++));
		user.getCredentials().setPassword(resultSet.getString(index++));
		user.getStats().setWins(resultSet.getInt(index++));
		user.getStats().setLosses(resultSet.getInt(index++));
		user.getStats().setElo(resultSet.getInt(index++));
		user.getProfile().setBio(resultSet.getString(index++));
		user.getProfile().setPictureNumber(resultSet.getInt(index++));
	}
	
	private void loadGame(Game game, ResultSet resultSet, int index) throws SQLException {
		game.setGameId(resultSet.getInt(index++));
		game.getBoard().setBoardId(resultSet.getInt(index++));
		game.getPlayer1().setPlayerId(resultSet.getInt(index++));
		game.getPlayer1().setPlayerId(resultSet.getInt(index++));
		game.setTurn(resultSet.getInt(index++));
	}
	
	private void loadBoard(Board board, ResultSet resultSet, int index) throws SQLException {
		board.setBoardId(resultSet.getInt(index++));
		for (int y = 0; y < 8; y++) {
			for (int x = 0; x < 8; x++) {
				Point location = new Point(x, y);
				int rank = resultSet.getInt(index++);
				int color = resultSet.getInt(index++);
				switch(rank) {
				case 0:
					board.getSpace(x, y).setPiece(new Pawn(Rank.PAWN, color, location));
					break;
				case 1:
					board.getSpace(x, y).setPiece(new Rook(Rank.ROOK, color, location));
					break;
				case 2:
					board.getSpace(x, y).setPiece(new Knight(Rank.KNIGHT, color, location));
					break;
				case 3:
					board.getSpace(x, y).setPiece(new Bishop(Rank.BISHOP, color, location));
					break;
				case 4:
					board.getSpace(x, y).setPiece(new Queen(Rank.QUEEN, color, location));
					break;
				case 5:
					board.getSpace(x, y).setPiece(new King(Rank.KING, color, location));
					break;
				default:
					board.getSpace(x, y).setPiece(null);
				}
			}
		}
	}
	
	public void createTables() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				PreparedStatement stmt1 = null;
				PreparedStatement stmt2 = null;
				PreparedStatement stmt3 = null;				
			
				try {
					stmt1 = conn.prepareStatement(
						"create table users (" +
						"	user_id integer primary key " +
						"		generated always as identity (start with 1, increment by 1), " +	
						"	friendsId integer," +
						"	email varchar(40)," +
						"	username varchar(40)" +
						"	password varchar(40)," +
						"	wins integer," +
						"	losses integer," +
						"	elo integer," +
						"	bio varchar(100)," +
						"	pictureNumber integer" +
						")"
					);	
					stmt1.executeUpdate();
					
					System.out.println("Users table created");
					
					String boardStatement = "";
					
					for (int y = 0; y < 8; y++) {
						for (int x = 0; x < 8; x++) {
							boardStatement = boardStatement.concat(", rank" + x + y + " integer, color" + x + y + " integer");
						}
					}
					
					stmt2 = conn.prepareStatement(
							"create table boards (" +
							"	boards_id integer primary key " +
							"		generated always as identity (start with 1, increment by 1) " +
							boardStatement +
							")"
					);
					stmt2.executeUpdate();
					
					System.out.println("Boards table created");					
					
					stmt3 = conn.prepareStatement(
							"create table games (" +
							"	boards_id integer primary key " +
							"		generated always as identity (start with 1, increment by 1), " +
							"	board_id integer constraint board_id references boards " +
							"   player1 integer" +
							"   player2 integer" +
							"   turn integer" +
							")"
					);
					stmt3.executeUpdate();
					
					System.out.println("Games table created");					
										
					return true;
				} finally {
					DBUtil.closeQuietly(stmt1);
					DBUtil.closeQuietly(stmt2);
				}
			}
		});
	}
	
	public void loadInitialData() {
		executeTransaction(new Transaction<Boolean>() {
			@Override
			public Boolean execute(Connection conn) throws SQLException {
				ArrayList<User> userList = new ArrayList<User>();
				ArrayList<Board> boardList = new ArrayList<Board>();
				ArrayList<Game> gameList = new ArrayList<Game>();
				
				try {
					userList.addAll(InitialData.getUsers());
					boardList.addAll(InitialData.getBoards());
					gameList.addAll(InitialData.getGames(boardList));					
				} catch (IOException e) {
					throw new SQLException("Couldn't read initial data", e);
				}

				PreparedStatement insertUser = null;
				PreparedStatement insertBoard = null;
				PreparedStatement insertGame = null;

				try {
					insertUser = conn.prepareStatement("insert into users (friendsId, email, username, password, wins, losses, elo, bio, pictureNumber) values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
					for (User user : userList) {
						insertUser.setInt(1, user.getFriends().getFriendsId());
						insertUser.setString(2, user.getCredentials().getEmail());
						insertUser.addBatch();
					}
					insertUser.executeBatch();
					
					System.out.println("Users table populated");
					
					String boardStatement = "";
					String valueStatement = "";
					
					for (int y = 0; y < 8; y++) {
						for (int x = 0; x < 8; x++) {
							boardStatement = boardStatement.concat(", rank" + x + y + ", color" + x + y);
							valueStatement = valueStatement.concat(", ?, ?");
						}
					}
					
					insertBoard = conn.prepareStatement("insert into boards (" + boardStatement.substring(2) + ") values (" + valueStatement.substring(2) + ")");
			
 					for (Board board : boardList) {
 						for (int y = 0; y < 8; y++) {
 							for (int x = 0; x < 8; x++) {
 								if (board.getPiece(x, y) == null) {
 									// setting the piece's rank and color to 10 will trigger the default case of the loadBoard switch statement, rendering the piece as null
 									insertBoard.setInt(1, 10);
 									insertBoard.setInt(2, 10);
 								} else {
	 								switch(board.getPiece(x, y).getRank()) {
	 								case PAWN:
	 									insertBoard.setInt(1, 0);
	 									break;
	 								case ROOK:
	 									insertBoard.setInt(1, 1);
	 									break;
	 								case KNIGHT:
	 									insertBoard.setInt(1, 2);
	 									break;
	 								case BISHOP:
	 									insertBoard.setInt(1, 3);
	 									break;
	 								case QUEEN:
	 									insertBoard.setInt(1, 4);
	 									break;
	 								case KING:
	 									insertBoard.setInt(1, 5);
	 									break;
	 								}
	 								
	 								insertBoard.setInt(2, board.getPiece(x, y).getColor());
 								}
 							}
 						}
 						
						insertBoard.addBatch();
					}
					insertBoard.executeBatch();
					
					System.out.println("Boards table populated");					
					
					insertGame = conn.prepareStatement("insert into games (boardId, player1Id, player2Id, turn) values (?, ?, ?, ?)");
					for (Game game : gameList) {
						insertGame.setInt(1, game.getBoard().getBoardId());
						insertGame.setInt(2, game.getPlayer1().getPlayerId());
						insertGame.setInt(3, game.getPlayer2().getPlayerId());
						insertGame.setInt(4, game.getTurn());
						insertGame.addBatch();
					}
					insertGame.executeBatch();	
					
					System.out.println("Games table populated");					
					
					return true;
				} finally {
					DBUtil.closeQuietly(insertBoard);
					DBUtil.closeQuietly(insertUser);
					DBUtil.closeQuietly(insertGame);					
				}
			}
		});
	}
	
	
// from library example
	public static void main(String[] args) throws IOException {
		System.out.println("Creating tables...");
		DerbyDatabase db = new DerbyDatabase();
		db.createTables();
		
		System.out.println("Loading initial data...");
		db.loadInitialData();
		
		System.out.println("Library DB successfully initialized!");
	}
// from library example
}