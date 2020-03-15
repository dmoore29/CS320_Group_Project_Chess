package edu.ycp.cs320.Group_Project_Chess_Test.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.Group_Project_Chess.model.Credentials;

public class CredentialsTest {
	private Credentials model;
	
	
	@Before
	public void setUp() {
		model = new Credentials(null, null, null);
	}
	
	
	
	@Test
	public void testEmail() {
		model.setEmail("rblack5@ycp.edu");
		assertEquals("rblack5@ycp.edu", model.getEmail());
	}
	
	@Test
	public void testUsername() {
		model.setUsername("ChessMaster11");
		assertEquals("ChessMaster11", model.getUsername());
	}

	@Test
	public void testPassword() {
		model.setPassword("CoronaVirusFreeFluffyDogs2020");
		assertEquals("CoronaVirusFreeFluffyDogs2020", model.getPassword());
	}

}
