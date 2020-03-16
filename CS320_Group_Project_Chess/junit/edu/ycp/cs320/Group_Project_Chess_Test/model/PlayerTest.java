package edu.ycp.cs320.Group_Project_Chess_Test.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.Group_Project_Chess.model.Credentials;
import edu.ycp.cs320.Group_Project_Chess.model.FriendsList;
import edu.ycp.cs320.Group_Project_Chess.model.Player;
import edu.ycp.cs320.Group_Project_Chess.model.Profile;
import edu.ycp.cs320.Group_Project_Chess.model.Stats;
import edu.ycp.cs320.Group_Project_Chess.model.User;

public class PlayerTest {
	private Player model;
	private User user;
	private int color;
	private Credentials credentials;
	private Stats stats;
	private FriendsList friends;
	private Profile profile;
	
	

	@Before
	public void playerSetup() {
		credentials = new Credentials("test@123.com", "user123", "password123");
		stats = new Stats();
		stats.setWins(32);
		stats.setLosses(15);
		stats.setElo(1000);
		stats.getWins();
		stats.getLosses();
		stats.getElo();
		friends = new FriendsList();
		friends.addFriend("abeddia2");
		friends.addFriend("abeddia3");
		friends.addFriend("abeddia4");
		friends.getIndex(0);
		friends.getIndex(2);
		friends.removeFriend("abeddia4");
		profile = new Profile();
		profile.setBio("yo yo yo this is a bio");
		profile.setPicture(null);
		color = 1;
		user = new User(user.getCredentials(), user.getStats(), user.getFriends(), user.getProfile());
		model = new Player(user, color);
	}
	
	@Test
	public void testUser() {
		assertEquals(user, model.getUser());
	}
	
	@Test
	public void testGetColor() {
		assertEquals(color, model.getColor());
	}

}