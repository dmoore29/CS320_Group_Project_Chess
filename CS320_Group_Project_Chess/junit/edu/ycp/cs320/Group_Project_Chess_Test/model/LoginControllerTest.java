package edu.ycp.cs320.Group_Project_Chess_Test.model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.Group_Project_Chess.controller.LoginController;
import edu.ycp.cs320.Group_Project_Chess.model.Credentials;


public class LoginControllerTest {
	private LoginController model;
	
	
	@Before
	public void setUp() {
		model = new LoginController();
	}
	
	@Test
	public void testValidLogin() {
		assertTrue(model.validLogin(new Credentials("user1@email.com", "user1", "password1")));
		assertTrue(model.validLogin(new Credentials("user2@email.com", "user2", "password2")));
		assertFalse(model.validLogin(new Credentials("Anthony@email.com", "Anthony", "passwurd")));
		assertFalse(model.validLogin(new Credentials("Beddia@email.com", "Beddia", "elpasso")));
	}

}
