package edu.ycp.cs320.Group_Project_Chess.database;

import java.awt.Point;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import edu.ycp.cs320.Group_Project_Chess.model.Bishop;
import edu.ycp.cs320.Group_Project_Chess.model.Board;
import edu.ycp.cs320.Group_Project_Chess.model.Credentials;
import edu.ycp.cs320.Group_Project_Chess.model.Game;
import edu.ycp.cs320.Group_Project_Chess.model.King;
import edu.ycp.cs320.Group_Project_Chess.model.Knight;
import edu.ycp.cs320.Group_Project_Chess.model.Pawn;
import edu.ycp.cs320.Group_Project_Chess.model.Player;
import edu.ycp.cs320.Group_Project_Chess.model.Queen;
import edu.ycp.cs320.Group_Project_Chess.model.Rank;
import edu.ycp.cs320.Group_Project_Chess.model.Rook;
import edu.ycp.cs320.Group_Project_Chess.model.User;

public class DerbyDatabase implements IDatabase{
	
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
// from library example
	
	// transaction that retrieves all Users in database
	public ArrayList<User> findAllUsers() {
		return executeTransaction(new Transaction<ArrayList<User>>() {
			@Override
			public ArrayList<User> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							"select * from users"
					);
					
					ArrayList<User> result = new ArrayList<User>();
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						User user = new User();
						loadUser(user, resultSet, 1);
						
						result.add(user);
					}
					
					// check if any users were found
					if (!found) {
						System.out.println("No users were found in the database");
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	// transaction that retrieves Users with specific username
	public User findUserwithUsername(final String username) {
		return executeTransaction(new Transaction<User>() {
			@Override
			public User execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							" select * from users " +
							" where users.username = ? "
					);
					
					User result = new User();
					
					stmt.setString(1, username);
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						User user = new User();
						loadUser(user, resultSet, 1);
						
						result = user;
					}
					
					// check if any users were found
					if (!found) {
						System.out.println("No users with username " + username + " were found in the database");
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	// transaction that retrieves Users with specific user_id
	public User findUserwithUserId(final int userId) {
		return executeTransaction(new Transaction<User>() {
			@Override
			public User execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							" select * from users " +
							" where users.user_id = ? "
					);
					
					User result = new User();
					
					stmt.setInt(1, userId);
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						User user = new User();
						loadUser(user, resultSet, 1);
						
						result = user;
					}
					
					// check if any users were found
					if (!found) {
						System.out.println("No users with userId " + userId + " were found in the database");
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	// transaction that retrieves Users with specific email
	public User findUserwithEmail(final String email) {
		return executeTransaction(new Transaction<User>() {
			@Override
			public User execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {
					stmt = conn.prepareStatement(
							" select * from users " +
							" where users.email = ? "
					);
					
					User result = new User();
					
					stmt.setString(1, email);
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						User user = new User();
						loadUser(user, resultSet, 1);
						
						result = user;
					}
					
					// check if any users were found
					if (!found) {
						System.out.println("No users with email " + email + " were found in the database");
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	// transaction that retrieves all games with a specific user 
	public ArrayList<Game> findGameswithUser(final String username) {
		return executeTransaction(new Transaction<ArrayList<Game>>() {
			@Override
			public ArrayList<Game> execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				PreparedStatement stmt2 = null;
				ResultSet resultSet = null;
				
				try {
					User player1 = findUserwithUsername(username);
					
					stmt = conn.prepareStatement(
//							" select games.*, users.* from games, users "
//							+ " where games.PLAYER1ID = users.USER_ID and "
//							+ "((games.player2Id = 1 and games.player1Id = 2) or "
//							+ "(games.player2Id = 2 and games.player1Id = 1)) "


							" select games.*, users.* from games, users " +
							" where games.player2Id = users.user_id " +
							" 	and games.player1Id = ? "
					);
					
					ArrayList<Game> result = new ArrayList<Game>();
					
					stmt.setInt(1, player1.getUserId());
					
					resultSet = stmt.executeQuery();
										
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						Game game = new Game();

						loadGame(game, resultSet, 1);

						User player2 = new User();
						loadUser(player2, resultSet, 9);
						
						game.setPlayer1(new Player(player1, 0, game.getPlayer1().getPlayerId()));
						game.setPlayer2(new Player(player2, 1, game.getPlayer2().getPlayerId()));
						
						result.add(game);
					}
					
					// check if any games were found
					if (!found) {
						System.out.println("No games with user " + username + " were found in the database");
					}

						User player2 = findUserwithUsername(username);
						
						stmt2 = conn.prepareStatement(
//								" select games.*, users.* from games, users "
//								+ " where games.PLAYER2ID = users.USER_ID and "
//								+ "((games.player1Id = 1 and games.player2Id = 2) or "
//								+ "(games.player1Id = 2 and games.player2Id = 1)) "

								" select games.*, users.* from games, users " +
								" where games.player1Id = users.user_id " +
								" 	and games.player2Id = ? "
						);
						
						result = new ArrayList<Game>();
						
						stmt2.setInt(1, player2.getUserId());
						
						resultSet = stmt2.executeQuery();
											
						// for testing that a result was returned
						found = false;
						
						while (resultSet.next()) {
							found = true;
							
							Game game = new Game();

							loadGame(game, resultSet, 1);

							player1 = new User();
							loadUser(player1, resultSet, 9);
							
							game.setPlayer2(new Player(player2, 0, game.getPlayer2().getPlayerId()));
							game.setPlayer1(new Player(player1, 1, game.getPlayer1().getPlayerId()));
							
							result.add(game);
						}
						
						// check if any games were found
						if (!found) {
							System.out.println("No games with user " + username + " were found in the database");
						}
									
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	// transaction that retrieves a game with a specific game_id 
	public Game findGamewithGameId(final int gameId) {
		return executeTransaction(new Transaction<Game>() {
			@Override
			public Game execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {					
					stmt = conn.prepareStatement(
							" select * from games " +
							" where games.games_id = ? "
					);
					
					Game result = new Game();
					
					stmt.setInt(1, gameId);
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						Game game = new Game();
						loadGame(game, resultSet, 1);
												
						game.setPlayer1(new Player(findUserwithUserId(game.getPlayer1().getPlayerId()), 0, game.getPlayer1().getPlayerId()));
						game.setPlayer2(new Player(findUserwithUserId(game.getPlayer2().getPlayerId()), 1, game.getPlayer2().getPlayerId()));
						game.setBoard(findBoardwithBoardId(game.getBoard().getBoardId()));
						
						result = game;
					}
					
					// check if any games were found
					if (!found) {
						System.out.println("No games with game_id " + gameId + " were found in the database");
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	// transaction that retrieves a board with a specific boards_id 
	public Board findBoardwithBoardId(final int boardId) {
		return executeTransaction(new Transaction<Board>() {
			@Override
			public Board execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet resultSet = null;
				
				try {					
					stmt = conn.prepareStatement(
							" select * from boards " +
							" where boards.boards_id = ? "
					);
					
					Board result = new Board();
					
					stmt.setInt(1, boardId);
					
					resultSet = stmt.executeQuery();
					
					// for testing that a result was returned
					Boolean found = false;
					
					while (resultSet.next()) {
						found = true;
						
						Board board = new Board();
						board.newGameBoard();
						loadBoard(board, resultSet, 1);
						
						result = board;
					}
					
					// check if any boards were found
					if (!found) {
						System.out.println("No boards with board_id " + boardId + " were found in the database");
					}
					
					return result;
				} finally {
					DBUtil.closeQuietly(resultSet);
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
	// adds a new user to the database
	public Integer registerUser(final Credentials creds) throws SQLException {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				
				try {
					stmt = conn.prepareStatement(
							"insert into users (friendsId, email, username, password, wins, losses, elo, bio, pictureNumber) "
							+ " values (?, ?, ?, ?, ?, ?, ?, ?, ?) ", Statement.RETURN_GENERATED_KEYS
					);
					
					stmt.setInt(1, 1);
					stmt.setString(2, creds.getEmail());
					stmt.setString(3, creds.getUsername());
					stmt.setString(4, creds.getPassword());
					stmt.setInt(5, 0);
					stmt.setInt(6, 0);
					stmt.setInt(7, 100);
					stmt.setString(8, "Tell the world about yourself!");
					stmt.setInt(9, 1);
					
					stmt.execute();
					
					ResultSet rs = stmt.getGeneratedKeys();
					int generatedKey = 0;
					if (rs.next()) {
					    generatedKey = rs.getInt(1);
					}
					
					return generatedKey;
		
				} finally {
					DBUtil.closeQuietly(stmt);
				}
			}
		});
	}
	
//	@Override
	public Integer newGame(final Game game) throws SQLException {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
//				conn = connect();
				PreparedStatement stmt = null;
				
				stmt = conn.prepareStatement(
						"insert into games (boards_id, player1Id, player2Id, turn, promo, enPx, enPy) "
						+ " values (?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS
				);
				
				System.out.println("BOARD ID: " + game.getBoard().getBoardId());
				stmt.setInt(1, game.getBoard().getBoardId());
				stmt.setInt(2, game.getPlayer1().getPlayerId());
				stmt.setInt(3, game.getPlayer2().getPlayerId());
				stmt.setInt(4, game.getTurn());
				stmt.setInt(5, game.getPromo());
				stmt.setInt(6, game.getEnPx());
				stmt.setInt(7, game.getEnPy());
				
				stmt.execute();
				
				ResultSet rs = stmt.getGeneratedKeys();
				int generatedKey = 0;
				if (rs.next()) {
				    generatedKey = rs.getInt(1);
				}
								
				DBUtil.closeQuietly(stmt);
				DBUtil.closeQuietly(conn);
				System.out.println("THIS IS THE GENERATED ID: " + generatedKey);
				return generatedKey;
			}
		});
	}
	
	public Integer updateGame(final Game game) throws SQLException {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				
				stmt = conn.prepareStatement(
						"update games "
						+ "set boards_id = ?, "
						+ "player1Id = ?, "
						+ "player2Id = ?, "
						+ "turn = ?, "
						+ "promo = ?, "
						+ "enPx = ?, "
						+ "enPy = ? "
						+ "where games_id = ?"
				);
				
				stmt.setInt(1, game.getBoard().getBoardId());
				stmt.setInt(2, game.getPlayer1().getPlayerId());
				stmt.setInt(3, game.getPlayer2().getPlayerId());
				stmt.setInt(4, game.getTurn());
				stmt.setInt(5, game.getPromo());
				stmt.setInt(6, game.getEnPx());
				stmt.setInt(7, game.getEnPy());
				stmt.setInt(8, game.getGameId());
				
				stmt.executeUpdate();
		
				DBUtil.closeQuietly(stmt);
				DBUtil.closeQuietly(conn);
				return 1;
			}
		});
	}
	
	public Integer deleteGame(final int gameId) throws SQLException {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				ResultSet rs = null;
				int boards_id = 0;
				
				//get boards_id
				stmt = conn.prepareStatement(
						"select boards_id from games "
						+ "where games_id = ?"
				);
				
				stmt.setInt(1, gameId);
				
				rs = stmt.executeQuery();
				
				while (rs.next()) {
					boards_id = rs.getInt(1);
				}
				
				System.out.println("FOUND BOARD ID: " + boards_id);
				System.out.println("FOUND BOARD ID: " + boards_id);
				System.out.println("FOUND BOARD ID: " + boards_id);

				DBUtil.closeQuietly(stmt);
				DBUtil.closeQuietly(rs);
				
				//delete game
				stmt = conn.prepareStatement(
						"delete from games "
						+ "where games_id = ?"
				);
				
				stmt.setInt(1, gameId);
				
				stmt.executeUpdate();
		
				DBUtil.closeQuietly(stmt);
				DBUtil.closeQuietly(conn);
				
				//delete board
				stmt = conn.prepareStatement(
						"delete from boards "
						+ "where boards_id = ?"
				);
				
				stmt.setInt(1, boards_id);
				
				stmt.executeUpdate();
		
				DBUtil.closeQuietly(stmt);
				DBUtil.closeQuietly(conn);
				
				return 1;
			}
		});
	}
	
	public Integer newBoard(final Board board) throws SQLException {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				String sql = "insert into boards (";
				
				for(int i = 0; i<8; i++) {
					for(int j = 0; j<8; j++) {
						if(i != 7 || j !=7) {
							sql = sql + "RANK" + Integer.toString(i) + Integer.toString(j) + ", " + "COLOR" + Integer.toString(i) + Integer.toString(j) + ", ";
						}
					}
				}
				
					sql = sql + "RANK" + Integer.toString(7) + Integer.toString(7) + ", " + "COLOR" + Integer.toString(7) + Integer.toString(7) + ") Values(";
				
				for(int i = 0; i<8; i++) {
					for(int j = 0; j<8; j++) {
						if(i != 7 || j !=7) {
							sql = sql + "?, ?, ";
						}
					}
				}
				
					sql = sql + "?, ?)";
					
				System.out.print(sql);
				
				stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				
				int count = 1;
				for(int i = 0; i<8; i++) {
					for(int j = 0; j<8; j++) {
						if(board.getPiece(i, j) != null) {
							stmt.setInt(count, board.getPiece(i, j).getRank().getRank());
							count++;
							stmt.setInt(count, board.getPiece(i, j).getColor());
							count++;
						} else {
							stmt.setInt(count, 6);
							count++;
							stmt.setInt(count, 0);
							count++;
						}
					}
				}

				stmt.execute();
				
				ResultSet rs = stmt.getGeneratedKeys();
				int generatedKey = 0;
				if (rs.next()) {
				    generatedKey = rs.getInt(1);
				}
								
				DBUtil.closeQuietly(stmt);
				DBUtil.closeQuietly(conn);
				System.out.println("THIS IS THE GENERATED ID FOR BOARD: " + generatedKey);
				return generatedKey;
			}
		});
	}
	
	public Integer updateBoard(final Board board) throws SQLException {
		return executeTransaction(new Transaction<Integer>() {
			@Override
			public Integer execute(Connection conn) throws SQLException {
				PreparedStatement stmt = null;
				
				String sql = "update boards set ";
				
				for(int i = 0; i<8; i++) {
					for(int j = 0; j<8; j++) {
						if(i != 7 || j !=7) {
							sql = sql + "RANK" + Integer.toString(i) + Integer.toString(j) + " = ?, " + "COLOR" + Integer.toString(i) + Integer.toString(j) + " = ?, ";
						}
					}
				}
				
				sql = sql + "RANK" + Integer.toString(7) + Integer.toString(7) + " = ?, " + "COLOR" + Integer.toString(7) + Integer.toString(7) + " = ? "
						+ "where boards_id = ? ";
				
				stmt = conn.prepareStatement(sql);

				int count = 1;
				for(int i = 0; i<8; i++) {
					for(int j = 0; j<8; j++) {
						if(board.getPiece(i, j) != null) {
							stmt.setInt(count, board.getPiece(i, j).getRank().getRank());
							count++;
							stmt.setInt(count, board.getPiece(i, j).getColor());
							count++;
						} else {
							stmt.setInt(count, 6);
							count++;
							stmt.setInt(count, 0);
							count++;
						}
					}
				}
				
				stmt.setInt(count, board.getBoardId());
				
				stmt.executeUpdate();
		
				DBUtil.closeQuietly(stmt);
				DBUtil.closeQuietly(conn);
				return 1;
			}
		});
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
				case 6:
					board.getSpace(x, y).setPiece(null);
					break;
				default:
					board.getSpace(x, y).setPiece(null);
				}
			}
		}
	}
	
// from library example
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
//		Connection conn = DriverManager.getConnection("jdbc:derby:/Users/davidmoore777/CS-320/DB/chess.db;create=true");		
		Connection conn = DriverManager.getConnection("jdbc:derby:C:/CS320-Group_Project_Chess/chess.db;create=true");		
		///Users/davidmoore777/CS-320/DB/chess.db;
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
		game.getPlayer2().setPlayerId(resultSet.getInt(index++));
		game.setTurn(resultSet.getInt(index++));
		game.setPromo(resultSet.getInt(index++));
		game.setEnPx(resultSet.getInt(index++));
		game.setEnPy(resultSet.getInt(index++));
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
						"	friendsId integer, " +
						"	email varchar(40), " +
						"	username varchar(40), " +
						"	password varchar(40), " +
						"	wins integer, " +
						"	losses integer, " +
						"	elo integer, " +
						"	bio varchar(100), " +
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
							"	games_id integer primary key " +
							"		generated always as identity (start with 1, increment by 1), " +
							"	boards_id integer constraint boards_id references boards, " +
							"   player1Id integer, " +
							"   player2Id integer, " +
							"   turn integer, " +
							"   promo integer, " +
							"   enPx integer, " +
							"   enPy integer" +
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
						insertUser.setString(3, user.getCredentials().getUsername());
						insertUser.setString(4, user.getCredentials().getPassword());
						insertUser.setInt(5, user.getStats().getWins());
						insertUser.setInt(6, user.getStats().getLosses());
						insertUser.setInt(7, user.getStats().getElo());
						insertUser.setString(8, user.getProfile().getBio());
						insertUser.setInt(9, user.getProfile().getPictureNumber());
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
			
					int adjustment;
					
 					for (Board board : boardList) {
 						for (int y = 0; y < 8; y++) {
 							for (int x = 0; x < 8; x++) {
 								
 								// this is a function that calculates the correct offset for each index of the ? in SQL statement
 								adjustment = ((y * 8) + x) * 2;
 								
 								if (board.getPiece(x, y) == null) {
 									// setting the piece's rank and color to 10 will trigger the default case of the loadBoard switch statement, rendering the piece as null
 									insertBoard.setInt(adjustment + 1, 10);
 									insertBoard.setInt(adjustment + 2, 10);
 								} else {
	 								switch(board.getPiece(x, y).getRank()) {
	 								case PAWN:
	 									insertBoard.setInt(adjustment + 1, 0);
	 									break;
	 								case ROOK:
	 									insertBoard.setInt(adjustment + 1, 1);
	 									break;
	 								case KNIGHT:
	 									insertBoard.setInt(adjustment + 1, 2);
	 									break;
	 								case BISHOP:
	 									insertBoard.setInt(adjustment + 1, 3);
	 									break;
	 								case QUEEN:
	 									insertBoard.setInt(adjustment + 1, 4);
	 									break;
	 								case KING:
	 									insertBoard.setInt(adjustment + 1, 5);
	 									break;
	 								}
	 								
	 								insertBoard.setInt(adjustment + 2, board.getPiece(x, y).getColor());
 								}
 							}
 						}
 						
						insertBoard.addBatch();
					}
					insertBoard.executeBatch();
					
					System.out.println("Boards table populated");	
					
					
					insertGame = conn.prepareStatement(
							"insert into games (boards_id, player1Id, player2Id, turn, promo, enPx, enPy) "
							+ "  values (?, ?, ?, ?, ?, ?, ?)"
					);
					
					for (Game game : gameList) {					
						insertGame.setInt(1, game.getBoard().getBoardId());
						insertGame.setInt(2, game.getPlayer1().getPlayerId());
						insertGame.setInt(3, game.getPlayer2().getPlayerId());
						insertGame.setInt(4, game.getTurn());
						insertGame.setInt(5, game.getPromo());
						insertGame.setInt(6, game.getEnPx());
						insertGame.setInt(7, game.getEnPy());
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
		
		System.out.println("Chess DB successfully initialized!");
	}
// from library example
}