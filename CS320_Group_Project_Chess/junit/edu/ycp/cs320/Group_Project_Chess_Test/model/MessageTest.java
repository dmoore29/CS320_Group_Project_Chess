package edu.ycp.cs320.Group_Project_Chess_Test.model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.Group_Project_Chess.model.Message;


public class MessageTest {
	private Message model;
	
	
	@Before
	public void setUp() {
		model = new Message("Greetings", "rblack5", "dmoore29");
	}
	
	
	
	@Test
	public void testGetMessage() {
		assertEquals("Greetings", model.getMessage());
	}
	
	@Test
	public void testGetRecipient() {
		assertEquals("rblack5", model.getRecipient());
	}
	
	@Test
	public void testGetSender() {
		assertEquals("dmoore29", model.getSender());
	}
	
}
