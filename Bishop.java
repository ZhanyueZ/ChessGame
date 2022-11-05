

public class Bishop extends Piece {
    public Bishop(int x, int y, Side side, Board board) {
    	super(x,y,side,board);
    }

    @Override
    public boolean canMove(int destX, int destY) {
       boolean x = false;
       if (Math.abs(this.x - destX) == Math.abs(this.y  - destY)) {
    	   x = true;
       }
        return x;
    }

    @Override
    public String getSymbol() {
        return this.getSide() == Side.BLACK ? "♝" : "♗";
    }
}
