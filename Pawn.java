public class Pawn extends Piece{
	public Pawn(int x, int y, Side side, Board board) {
        // TODO: Call super constructor
    	super(x,y,side,board);
	}
	
	
	
	
@Override
public boolean canMove(int destX, int destY) {
   Side side=this.getSide();
   String s= side.toString();
   Side otherSide = Side.negate(side);
   boolean x = false;
   if(s.equals("BLACK")) {
     if(this.x>0 && this.x<7) {
      if(this.board.get(this.x-1, this.y+1)!=null && this.board.get(this.x-1, this.y+1).getSide()==otherSide) {
       if (this.x-destX==1 && destY-this.y==1) {
        x = true;
          }
        }
      if(this.board.get(this.x+1, this.y+1)!=null && this.board.get(this.x+1, this.y+1).getSide()==otherSide) {
    	  if(destX-this.x==1 && destY-this.y==1) {
    		  x = true;
    	  }
      }

   } 
     	         else  if(this.x==0) {
    	    		  if(this.board.get(this.x+1, this.y+1)!=null && this.board.get(this.x+1, this.y+1).getSide()==otherSide) {
    	    			 if(destX-this.x==1 && destY-this.y==1) {
    	    				 x = true;
    	    			 }
    	    		  }
    	    	  }
     	            else if(this.x==7) {
    	    		  if(this.board.get(this.x-1, this.y+1)!=null && this.board.get(this.x-1, this.y+1).getSide()==otherSide) {
    	    			  if(this.x-destX==1 && destY-this.y==1) {
    	    				  x = true;
    	    			  }
    	    		  }
    	    	  }
                  if(this.board.get(this.x, this.y+1)==null) {
                	  if(this.x==destX && destY-this.y==1) {
                		  x = true;
                	  }
                	  if(this.y==1 && this.board.get(this.x,this.y+2)==null) {
                		  if(this.x==destX && destY-this.y==2) {
                			  x = true;
                		  }
                	  }
                  }
    	       }  
    	       else if(s.equals("WHITE")) {
    	    	   if(this.x==0) {
    	    		   if(this.board.get(this.x+1, this.y-1)!=null) {
    	    			   if(this.board.get(this.x+1, this.y-1).getSide()==otherSide) {
    	    				   if(destX-this.x==1 && this.y-destY==1) {
    	    					   x = true;
    	    				   }
    	    			   }
    	    		   }
    	    	   }
    	    	   if(this.x==7) {
    	    		   if(this.board.get(this.x-1, this.y-1)!=null) {
    	    			   if(this.board.get(this.x-1, this.y-1).getSide()==otherSide) {
    	    				   if(this.x-destX==1 && this.y-destY==1) {
    	    					   x = true;
    	    				   }
    	    			   }
    	    		   }
    	    	   }
    	    	   else if(this.x>0 && this.x<7) {
        	    	   if(this.board.get(this.x-1, this.y-1)!=null &&this.board.get(this.x-1,this.y-1).getSide()==otherSide) {
                     	  if (this.x-destX==1 && this.y-destY==1) {
                     		  x = true;
                     	  }
                       }
        	    	   if(this.board.get(this.x+1, this.y-1)!=null && this.board.get(this.x+1,this.y-1).getSide()==otherSide) {
        	    		   if(destX-this.x==1 && this.y-destY==1) {
        	    			   x= true;
        	    		   }
        	    	   }
        	    	   
        	       }
                   if(this.board.get(this.x, this.y-1)==null) {
                 	  if(this.x==destX && this.y-destY==1) {
                 		  x = true;
                 	  }
                 	  if(this.y==6 && this.board.get(this.x,this.y-2)==null) {
                 		  if(this.x==destX && this.y-destY==2) {
                 			  x = true;
                 		  }
                 	  }
                   }
    	       }
    	       
    	    return x;
    	 }
    	 
    	 
    	 @Override
    	    public String getSymbol() {
    	        return this.getSide() == Side.BLACK ? "♟" : "♙";
    	    }
}

