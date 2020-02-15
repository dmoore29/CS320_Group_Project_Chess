
public class Space {
	private Piece piece;
	
	
	// Each space contains a single piece, and each piece will 
	// contain a rank. Spaces that are not filled will be null.

	public Space(Piece piece) {
		this.piece = piece;
	}
	
	public Piece getPiece() {
		return this.piece;
	}
	public void setPiece(Piece temp) {
		this.piece = temp;
	}
}
