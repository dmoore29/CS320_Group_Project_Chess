
public enum Rank {
	// The ranks, in order, of the available pieces in chess.
	// Having an "Empty" piece is not a good idea because each
	// empty place on the board has to be associated with a player.
	// 
	// The empty spaces will be represented with a "null" value,
	// which is something we can easily check for during the game.
	
	/** Pawn */
	PAWN(0),
	/** Rook */
	ROOK(1),
	/** Knight */
	KNIGHT(2),
	/** Bishop */
	BISHOP(3),
	/** Queen */
	QUEEN(4),
	/** King */
	KING(5);
	
	private int rank;
	
	private Rank(int rank) {
		this.rank = rank;
	}
	
	public int getRank() {
		return this.rank;
	}
	
}