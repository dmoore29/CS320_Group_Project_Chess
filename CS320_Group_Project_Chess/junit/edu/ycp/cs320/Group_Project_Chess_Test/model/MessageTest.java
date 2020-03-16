package edu.ycp.cs320.Group_Project_Chess_Test.model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.Group_Project_Chess.model.Message;


public class MessageTest {
	private Message model;
	
	
	//generation of a message, recipient, sender
	@Before
	public void setUp() {
		model = new Message("Greetings", "rblack5", "dmoore29");
	}
	
	
	//test of message being sent
	@Test
	public void testGetMessage() {
		assertEquals("Greetings", model.getMessage());
	}
	
	//grabbing the recipient of the message
	@Test
	public void testGetRecipient() {
		assertEquals("rblack5", model.getRecipient());
	}
	
	//grabbing the sender of the message
	@Test
	public void testGetSender() {
		assertEquals("dmoore29", model.getSender());
	}
	
}
