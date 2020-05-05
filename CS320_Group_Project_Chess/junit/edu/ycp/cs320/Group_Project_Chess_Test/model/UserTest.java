package edu.ycp.cs320.Group_Project_Chess_Test.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.Group_Project_Chess.model.Credentials;
import edu.ycp.cs320.Group_Project_Chess.model.FriendsList;
import edu.ycp.cs320.Group_Project_Chess.model.Profile;
import edu.ycp.cs320.Group_Project_Chess.model.Stats;
import edu.ycp.cs320.Group_Project_Chess.model.User;

public class UserTest {
	private User model;
	private Credentials credentials;
	private Stats stats;
	private FriendsList friends;
	private Profile profile;
	

	@Before
	public void userSetUp() {	
		credentials = new Credentials("test@123.com", "user123", "password123");
		stats = new Stats();
		stats.setWins(32);
		stats.setLosses(15);
		stats.setElo(1000);
		stats.getWins();
		stats.getLosses();
		stats.getElo();
/*		friends = new FriendsList();
		friends.addFriend("abeddia2");
		friends.addFriend("abeddia3");
		friends.addFriend("abeddia4");
		friends.getIndex(0);
		friends.getIndex(2);
		friends.removeFriend("abeddia4");
*/		profile = new Profile();
		profile.setBio("yo yo yo this is a bio");
		profile.setPicture(null);
		model = new User(credentials, stats, friends, profile);
	}
	
	@Test
	public void testGetCredentials() {
		assertEquals(credentials, model.getCredentials());
	}
	
	@Test
	public void testGetStats() {
		assertEquals(stats, model.getStats());
	}
	
/*	@Test
	public void testGetFriends() {
		assertEquals(friends, model.getFriends());
	}
*/	
	@Test
	public void testGetProfile() {
		assertEquals(profile, model.getProfile());
	}
	
	

}
