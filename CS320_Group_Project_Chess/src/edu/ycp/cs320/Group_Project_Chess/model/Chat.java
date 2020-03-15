package edu.ycp.cs320.Group_Project_Chess.model;

import java.util.ArrayList;

public class Chat {
	
	// Each Message will contain the actual message, as well as a recipient and sender.
	// A unique recipient and sender will be linked to each specific message, so there is a separate class.
	
	private ArrayList<Message> messages = new ArrayList<Message>();
	
	public Chat() {
		
	}
	
	public void addMessage(Message message) {
		this.messages.add(message);
	}
	
	public ArrayList<Message> getMessages() {
		return this.messages;
	}
}
