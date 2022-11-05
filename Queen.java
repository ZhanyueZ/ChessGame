

public class Queen extends Piece {
	public Queen(int x, int y, Side side, Board board) {
        // TODO: Call super constructor
    	super(x,y,side,board);
    }
	
     @Override
	 public boolean canMove(int destX, int destY) {
		 Boolean x = false;
		 if (Math.abs(this.x - destX) == Math.abs(this.y  - destY) || (this.x == destX) || (this.y == destY)){
			 x = true;
		 }
		return x;
	 }
     
     @Override
     public String getSymbol() {
         return this.getSide() == Side.BLACK ? "♛" : "♕";
     }
	 
	 
	
}
