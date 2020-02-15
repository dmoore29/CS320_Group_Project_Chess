
public class Piece {
	private Rank rank;
	private int player;
	
	// Each piece will contain a rank and a player value. Higher 
	// ranking pieces will be able to over take lower ranking pieces
	// as long as the piece has a different "player" value.
	
	public Piece(Rank rank, int player) {
		this.rank = rank;
		this.player = player;
	}
	
	public Rank getRank() {
		return this.rank;
	}
	
	public int getPlayer() {
		return this.player;
	}
}
