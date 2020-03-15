package edu.ycp.cs320.Group_Project_Chess.model;

public class Credentials {
	private String email;
	private String username;
	private String password;
	
	
	public Credentials(String email, String username, String password) {
		this.email = email;
		this.username = username;
		this.password = password;
	}
	
	public String email() {
		return this.email;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setEmail(String providedEmail) {
		this.email = providedEmail;
	}
	
	public void setUsername(String providedUsername) {
		this.username = providedUsername;
	}
	
	public void setPassword(String providedPassword) {
		this.password = providedPassword;
	}
	
	public boolean isValidLogin(String providedUsername, String providedPassword) {
		if(this.username == providedUsername && this.password == providedPassword) {
			return true;
		}
		else {
			return false;
		}
	}
}
