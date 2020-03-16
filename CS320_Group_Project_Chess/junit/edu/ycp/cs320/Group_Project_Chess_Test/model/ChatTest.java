package edu.ycp.cs320.Group_Project_Chess_Test.model;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import edu.ycp.cs320.Group_Project_Chess.model.Chat;
import edu.ycp.cs320.Group_Project_Chess.model.Message;


public class ChatTest {
	private Chat model;
	
	
	@Before
	public void setUp() {
		model = new Chat();
	}
	
	
	
	@Test
	public void testAddMessage() {
		Message message = new Message("Greetings", "rblack5", "dmoore29");
		model.addMessage(message);
		ArrayList<Message> messages = new ArrayList<Message>();
		messages.add(message);

		assertEquals(messages, model.getMessages());
	}
}
