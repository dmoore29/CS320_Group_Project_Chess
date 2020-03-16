package edu.ycp.cs320.Group_Project_Chess_Test.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import edu.ycp.cs320.Group_Project_Chess.model.Profile;

public class BoardTest {
	private Profile model;
	private BufferedImage img;
	
	@Before
	public void setUp() {
		model = new Profile();
		
		img = null;
		try {
		    img = ImageIO.read(new File("ProfilePictureSample.jpg"));
		} catch (IOException e) {
		}
	}
	
	@Test
	public void TestSetBio() {
		model.setBio("This is my bio");
		assertTrue("This is my bio" == model.getBio());
	}
	
	@Test
	public void TestSetPicture() {
		model.setPicture(img);
		assertTrue(img == model.getPicture());
	}	
}
