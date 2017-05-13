package tree;

@SuppressWarnings("unused")                     
public class BlackRedTree<Key extends Comparable<Key>,Value> {
	private static final boolean RED = true;
	private static final boolean BLACK =false;
	private Node root;
	private class Node{
		Key key;
		Value value;
		boolean color;
		int N;
		Node left;
		Node right;
	
	public Node(Key key,Value value,int N,boolean color){
		this.key = key;
		this.value = value;
		this.N = N;
		this.color = color;
	  }
	}
	private boolean isRed(Node node){
		if(node == null ) return false;
		return node.color == RED;
	}
	public int size(){
		return size(root);
	}
	private int size(Node node){
		if(node == null) return 0;
		return node.N;
	}
	public Node rotateLeft(Node node){
		Node x = node.right;
		node.right = x.left;
		x.left = node;
		x.color = node.color;
		node.color = RED;
		x.N = node.N;
		node.N = 1+ size(node.left) + size(node.right) + 1;
		return x;
	}
	public Node rotateRight(Node node){
		Node x = node.left;
		node.left = x.right;
		x.right = node;
		x.color = node.color;
		node.color = RED;
		x.N = node.N;
		node.N = 1 + size(node.left) + size(node.right);
		return x;
	}
	
	void flipColors(Node node){
		node.color = RED;
		node.left.color = BLACK;
		node.right.color = BLACK;
		
	}
	
	public void put(Key key,Value value){
		root = put(root,key ,value);
		root.color = BLACK;
	}
	
	private Node put(Node node,Key key ,Value value){
		if(node == null)  return new Node(key,value,1,RED);
		int cmp = key.compareTo(node.key);
		if(cmp<0) node.left = put(node.left,key,value);
		else if(cmp>0) node.right = put(node.right,key,value);
		else node.value = value;	
		if(!isRed(node.left) && isRed(node.right)) node = rotateLeft(node);
		if(isRed(node.left)&&isRed(node.left.left)) node = rotateRight(node);
		if(isRed(node.right)&&isRed(node.left)) flipColors(node);
		
		node.N = size(node.right) + size(node.left) + 1;
		return node;
	}
	
	public  Node fixUp(Node node){
		if(!isRed(node.left)&&isRed(node.right)) node = rotateLeft(node);
		if(isRed(node.left)&&isRed(node.left.left)) node = rotateRight(node);
		if(isRed(node.right)&&isRed(node.left))  flipColors(node);
		return node;
	}
	
	private Node maveRedRight(Node node){
		flipColors(node);
		if(isRed(node.left.left)){
			node = rotateRight(node);
			flipColors(node);
		}
		return node;
	}
	public void deleteMax(){
		root = deleteMax(root);
		root.color = BLACK;
	}
	private Node deleteMax(Node node){
		if(isRed(node.left))
			node = rotateRight(node);
		if(node.right == null)
			return null;
		if(!isRed(node.right)&&!isRed(node.right.left))
			node = maveRedRight(node);
		node.right = deleteMax(node.right);
		fixUp(node);
		node.N = size(node.right) + size(node.left) + 1;
		return node;
		
	}
	private Node moveRedLeft(Node node){
		flipColors(node);
		if(isRed(node.right.left)){
			node.right = rotateRight(node);
			node = rotateLeft(node);
			flipColors(node);
		}
		return node;
	}
	
	public void deleteMin(){
		root = deleteMin(root);
		root.color = BLACK;
	}
	
	private Node deleteMin(Node node){
		if(node.left == null)
			return null;
		if(!isRed(node.left)&&!isRed(node.left.left))
			node = moveRedLeft(node);
		node.left = deleteMin(node.left);
		fixUp(node);
		node.N = size(node.right) + size(node.left) + 1;
		return node;
	}
	@SuppressWarnings("unused")
	private Node delete(Node h, Key key)
	{
		int cmp = key.compareTo(h.key);
	    if(cmp<0){
	    	if(!isRed(h.left)&&!isRed(h.left.left))
	    		h = moveRedLeft(h);
	    	h.left = delete(h.left,key);
	    }
	    else{
	    	if(isRed(h.left)) h = rotateRight(h);
	    	if(cmp == 0&&(h.right == null)){
	    		return null;
	    	}
	    	if(!isRed(h.right)&&!isRed(h.right.left)){
	    		h = maveRedRight(h);
	    	}
	    	if(cmp == 0){
	    		h.key = min(h.right);
	    		h.value = get(h.right, h.key);
	    		h.right = deleteMin(h.right);
	    	}
	    	else{
	    		h.right = delete(h.right, key);
	    	}
	    }
	    	 fixUp(h);
	    	 h.N = size(h.right) + size(h.left) + 1;
	    	 return h;
	    	
	    }
	
	
	

	private Value get(BlackRedTree<Key, Value>.Node right, Key key) {
		
		return null;
	}
	private Key min(BlackRedTree<Key, Value>.Node right) {
		// TODO Auto-generated method stub
		return null;
	}
}
