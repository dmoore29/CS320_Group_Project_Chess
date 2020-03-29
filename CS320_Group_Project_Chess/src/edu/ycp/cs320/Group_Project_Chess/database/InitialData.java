package edu.ycp.cs320.Group_Project_Chess.database;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.ycp.cs320.Group_Project_Chess.model.Credentials;
import edu.ycp.cs320.Group_Project_Chess.model.FriendsList;
import edu.ycp.cs320.Group_Project_Chess.model.Game;
import edu.ycp.cs320.Group_Project_Chess.model.Profile;
import edu.ycp.cs320.Group_Project_Chess.model.Stats;
import edu.ycp.cs320.Group_Project_Chess.model.User;

public class InitialData {
	public static List<User> getUsers() throws IOException {
		List<User> userList = new ArrayList<User>();
		ReadCSV readUsers = new ReadCSV("users.csv");
		try {
			// auto-generated primary key for users table
			int userId = 1;
			while (true) {
				List<String> tuple = readUsers.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				int friendsId = Integer.parseInt(i.next());
				User user = new User(userId++, friendsId, new Credentials(i.next(), i.next(), i.next()), new Stats(Integer.parseInt(i.next()), Integer.parseInt(i.next()), Integer.parseInt(i.next())), new FriendsList(friendsId), new Profile(i.next(), Integer.parseInt(i.next())));
				userList.add(user);
			}
			return userList;
		} finally {
			readUsers.close();
		}
	}
	
	public static List<Game> getGames() throws IOException {
		List<Game> gameList = new ArrayList<Game>();
		/*ReadCSV readBooks = new ReadCSV("books.csv");
		try {
			// auto-generated primary key for books table
			Integer bookId = 1;
			while (true) {
				List<String> tuple = readBooks.next();
				if (tuple == null) {
					break;
				}
				Iterator<String> i = tuple.iterator();
				Book book = new Book();
				book.setBookId(bookId++);
				book.setAuthorId(Integer.parseInt(i.next()));
				book.setTitle(i.next());
				book.setIsbn(i.next());
				book.setPublished(Integer.parseInt(i.next()));
				bookList.add(book);
			}
			return bookList;
		} finally {
			readBooks.close();
		}*/
		return null;
	}
}
