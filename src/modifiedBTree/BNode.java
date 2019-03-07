package modifiedBTree;

import model.game.AIMove;

public class BNode {

   protected BNode left;
   protected BNode right; 
   protected AIMove move;
   protected int eval;
   

    public BNode(int eval,AIMove move ) {
        this.eval=eval;
    	this.move= move;
        this.left = null;
        this.right = null;
    }


}	