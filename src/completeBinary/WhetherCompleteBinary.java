 package completeBinary;

import edu.princeton.cs.algs4.Queue;

public class WhetherCompleteBinary {
	private Node root;
	private Queue<Node> q = new Queue<Node>();
	private volatile boolean leftMost = false;
	public WhetherCompleteBinary(){
		root = new Node();
		if(whetherCompleteBinary(root)){
			System.out.println("The binary is complete");
		}
	}

	private boolean whetherCompleteBinary(Node root) {
		
		if(root == null)
			return true;
		else
			q.enqueue(root);
		while(!q.isEmpty()){
			Node node = q.dequeue();
			if(!processChild(node.left()))
				return false;
			if(!processChild(node.right()))
				return false;
		}
		return true;		
	}

	private boolean processChild(Node child) {
		synchronized(this){
		if(child != null){
			if(!leftMost){
				q.enqueue(child);
			}
			else{
				return false;
			}
		}
		else{
			leftMost = true;
		}
		return true;
	}
	}
}
