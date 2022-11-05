

public class King extends Piece{
	 public King(int x, int y, Side side, Board board) {
	    	super(x,y,side,board);
	    }
	 
	 
	 @Override
	 public boolean canMove(int destX, int destY) {
		 boolean x = false;
		 if(Math.abs(this.x - destX) <= 1 && Math.abs(this.y  - destY) <=1) {
			 x = true;
		 }
		 return x;
	 }
	 @Override
	 public String getSymbol() {
	        return this.getSide() == Side.BLACK ? "♚" : "♔";
	   }
	 
}
