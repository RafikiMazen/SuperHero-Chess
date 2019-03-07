package modifiedBTree;

import model.game.AIMove;

public class BTree {
	private BNode root;

	public BTree() {
		root = null;
	}


	protected BNode insertAB(BNode rootNode, BNode newNode) {
		if (rootNode == null) {
			rootNode = newNode;		
		} else if (newNode.eval < rootNode.eval) {
			rootNode.left = insertAB(rootNode.left, newNode);
		} else {
			rootNode.right = insertAB(rootNode.right, newNode);
		}
		return rootNode;
	}

	public void insertBST(int eval, AIMove move) {
		BNode evalBTNode = new BNode(eval, move);
		root = insertAB(root, evalBTNode);
	}
	
	protected static AIMove getMaxhelper(BNode node) {
		if(node.right==null)
			return node.move;
		else
			return getMaxhelper(node.right);
	}
	public AIMove getMax() {
		return getMaxhelper(this.root);
	}

}
