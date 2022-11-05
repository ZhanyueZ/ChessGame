

public class Rook extends  Piece {
	public Rook(int x, int y, Side side, Board board) {
        // TODO: Call super constructor
    	super(x,y,side,board);
    }
	
	
	@Override
    public boolean canMove(int destX, int destY) {
       boolean x = false;
       if (this.x == destX || this.y == destY) {
    	   x = true;
       }
        return x;
    }

	@Override
    public String getSymbol() {
        return this.getSide() == Side.BLACK ? "♜" : "♖";
    }
}
