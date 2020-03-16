package edu.ycp.cs320.Group_Project_Chess.model;

// model class for Numbers
// only the controller should be allowed to call the set methods
// the JSP will call the "get" and "is" methods implicitly
// when the JSP specifies game.min, that gets converted to
//    a call to model.getMin()

public class Stats {
	private int wins, losses, elo;
	
	public Stats() {
	}
	
	public void setWins(int wins) {
		this.wins = wins;
	}
	
	public double getWins() {
		return wins;
	}
	
	public void setLosses(int losses) {
		this.losses = losses;
	}
	
	public double getLosses() {
		return losses;
	}
	
	public void setElo(int elo) {
		this.elo = elo;
	}
	
	public int getElo() {
		return elo;
	}
}
