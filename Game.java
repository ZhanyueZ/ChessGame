


import java.util.List;
import java.util.Stack;

public class Game {
    Board b;
    Stack<String> moveHistory = new Stack<String>();
    Side currentTurn;

    public Game(){
    
    	this.b= new Board();
        this.currentTurn = Side.WHITE;
    	
    }

    public static String getName() {
        return "Queen's Game-bit";
    }
    public boolean canMove(int x, int y, int destX, int destY, Side s){
      
    	Boolean move = true;
    	if(x<0||x>7||y<0||y>7||destX<0||destX>7||destY<0||destY>7) {
    		move = false;
    	}
    	else if(b.get(x, y)==null) {
    		move = false;
    	}
    	else if(b.get(x, y).getSide()!=s) {
    		move= false;
    	}
    	else if(b.get(x, y).canMove(destX, destY)!=true) {
    		move= false;
    	}
    	else if(b.get(destX, destY)!=null && b.get(destX, destY).getSide()==s) {
    		move= false;
    	}
    	else if(isVisible(x,y,destX,destY)==false) {
    		if(!(b.get(x,y) instanceof Knight)) {
    			move = false;
    		}
    	}
        return move;
    }

    
    private boolean isVisible(int x, int y, int destX, int destY) {
        int diffX = destX - x;
        int diffY = destY - y;

        boolean validCheck = (diffX == 0 || diffY == 0) || (Math.abs(diffX) == Math.abs(diffY));
        if (!validCheck) {
            try {
                throw new Exception("The 'path' between the squares (" + x + ", " + y + ") and (" + destX + ", " + destY +") is undefined");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //Diagonal
        if (Math.abs(diffX) == Math.abs(diffY) && Math.abs(diffX) > 1) {
            //Determine direction of move
            int dirX = diffX > 0 ? 1 : -1;
            int dirY = diffY > 0 ? 1 : -1;
            for (int i = x + dirX, j = y + dirY; i != destX && j != destY; i += dirX, j += dirY) {
                if (b.get(i, j) != null) {
                    return false;
                }
            }
        }

        //Linear
        if ((diffX == 0 && Math.abs(diffY) > 1) || (diffY == 0 && Math.abs(diffX) > 1)) {
            if (diffX == 0) {
                int dirY = diffY > 0 ? 1 : -1;
                for (int j = y + dirY; j != destY; j += dirY) {
                    if (b.get(x, j) != null) {
                        return false;
                    }
                }
            } else {
                int dirX = diffX > 0 ? 1 : -1;
                for (int i = x + dirX; i != destX; i += dirX) {
                    if (b.get(i, y) != null) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private void appendCheckToHistory(Side side){
        moveHistory.push(side.toString()+ " is in check");
    }

    private void appendWinToHistory(Side side){
        moveHistory.push(side.toString()+ " has won");
    }

    private void appendMoveToHistory(int x, int y, int destX, int destY, Side side){
        Piece toMove = b.get(x,y);
        Piece toCapture = b.get(destX, destY);
        if(toCapture == null){
            moveHistory.push(side.toString() + toMove.getSymbol() + " at "+ x + ", " + y + " to " + destX + ", " + destY);
        }else{
            moveHistory.push(side.toString() + toMove.getSymbol() + " at " + x + ", " + y + " captures " + toCapture.getSymbol() + " at " + destX + ", " + destY);
        }
    }


    /**
     * This method takes as input the coordinates of the piece to move and of the destination to move to.
     *
     * It returns true if the move is valid and false otherwise.
     *
     * Conditions for a valid move are determined in canMove()
     *
     * Upon a successful move, this method also:
     * - updates the board to reflect the move
     * - tracks the move in game history
     *   - also tracks in history if the move was:
     *     - a capture
     *     - result in check
     * - updates the current player's turn
     *
     *  x The x coordinate of the piece to move
     *  y The y coordinate of the piece to move
     *  destX The x coordinate of the destination to move to
     *  destY The y coordinate of the destination to move to
     */
    public boolean move(int x, int y, int destX, int destY){
        // TODO write this method
    	Boolean move = false;
    	if(canMove(x,y,destX,destY,currentTurn)==false) {
    		move = false;
    	}
    	else if(x == destX && y==destY) {
    		move = false;
    	}
    	else { 	
    		if(b.get(x, y).canMove(destX,destY)) {
    		appendMoveToHistory(x,y,destX,destY,currentTurn);
    		}
    		if(b.get(x, y).move(destX, destY)) {
    		move = true;
    	    Side oppo= Side.negate(currentTurn);
    	    if(isInCheck(currentTurn)) {
    	    	appendCheckToHistory(currentTurn);
    	    }
    		if(isInCheck(oppo)) {
    			appendCheckToHistory(oppo);
    		}
    		if(b.getKing(oppo)==null) {
    			appendWinToHistory(currentTurn);
    		}
    		currentTurn=Side.negate(currentTurn);
    	  }
    	}
        return move;
    }

    /**
     * Return true if the King of Side side can be captured by any of
     * the opponent's pieces.
     *
     */
    public boolean isInCheck(Side side) {
      
    	Boolean check = false;
    	King king =b.getKing(side);
        Side oppo = Side.negate(side);
        List<Piece> out = b.getPieces(oppo);
        for(int i =0;i<out.size();i++) {
        Piece p = out.get(i);
        if(king!=null) {
    	if(canMove(p.x,p.y ,king.x,king.y,oppo)==true) {
    		check = true;
    	  }
    	}
       }
        return check;
    }

    /**
     * Ensures that the game is in the exact same state as a new game
     */
    public void reset(){
        while(!moveHistory.empty()){
            System.out.println(moveHistory.pop());
        }
        b.fillBoard();
        currentTurn = Side.WHITE;
    }


    public static void main(String[] args){
        Board b = new Board();
        System.out.println(b);
    }

    /**
     * Return an array of Strings containing every successful move made during the game,
     * and every time a move resulted in check.
     */
    public String[] moveHistory(){
        String[] out = new String[moveHistory.size()];
        for(int i = 0; i < moveHistory.size(); i++){
            out[i] = moveHistory.get(i);
        }
        return out;
    }

    public Side getCurrentTurn(){
        return currentTurn;
    }
}
