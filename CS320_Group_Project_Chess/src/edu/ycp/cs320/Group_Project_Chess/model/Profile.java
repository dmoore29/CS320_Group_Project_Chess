package edu.ycp.cs320.Group_Project_Chess.model;
import java.awt.image.BufferedImage;

// model class for Numbers
// only the controller should be allowed to call the set methods
// the JSP will call the "get" and "is" methods implicitly
// when the JSP specifies game.min, that gets converted to
//    a call to model.getMin()

public class Profile {
	private String bio;
	private BufferedImage picture;
	
	public Profile() {
	}
	
	public void setBio(String bio) {
		this.bio = bio;
	}
	
	public String getBio() {
		return bio;
	}
	
	public void setPicture(BufferedImage picture) {
		this.picture = picture;
	}
	
	public BufferedImage getPicture() {
		return picture;
	}
}
