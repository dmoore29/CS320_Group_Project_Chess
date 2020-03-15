package edu.ycp.cs320.Group_Project_Chess.model;

public class Message {
	private String message, recipient, sender;
	
	public Message(String message, String recipient, String sender) {
		this.message = message;
		this.recipient = recipient;
		this.sender = sender;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	public String getRecipient() {
		return this.recipient;
	}
	
	public String getSender() {
		return this.sender;
	}
}
