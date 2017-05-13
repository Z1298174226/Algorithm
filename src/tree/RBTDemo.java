package tree;

public class RBTDemo<Key extends Comparable<Key>,Value> {
	
	public Node root;
	
	private class Node{
		public Node right;
		public Node left;
		public int N;
		public Key key;
		public Value value;
		public Color color;
		public Node(Key key,Value value,int N,Color color){
			this.key = key;
			this.value = value;
			this.N = N;
			this.color = color;
		}
	}
	
	public enum Color{
		Red,Black
	}
	
	private boolean isRed(Node node){
		return node.color == Color.Red;
	}
	public int size(){
		return size(root);
	}
	
	private int size(Node node){
		return node.N;
	}
	
	public Value get(Key key){
		root.color = Color.Black;
		return get(root,key);
	}
	
	private Value get(Node node,Key key){
		if(node == null) return null;
		int cmp = key.compareTo(node.key);
		if(cmp < 0) return get(node.left,key);
		else if(cmp > 0) return get(node.right,key);
		else return node.value;
	}
	
	public void put(Key key,Value value){
		put(root,key,value);
		root.color = Color.Black;
	}
	
	private Node put(Node node,Key key,Value value){
		if(node == null) return new Node(key,value,1,Color.Red);
		int cmp = key.compareTo(node.key);
		if(cmp < 0) node.left = put(node.left,key,value);
		if(cmp > 0) node.right = put(node.right,key,value);
		node.value = value;
		node.N = size(node.left) + size(node.right) + 1;
		fixUp(node);
		return node;
	}
	
	private void fixUp(Node node){
		if(isRed(node.right) && !isRed(node.left)) leftRotate(node);
		if(isRed(node.left) && isRed(node.left.left)) rightRotate(node);
		if(isRed(node.right) && isRed(node.left)) flip(node);
	}
	
	private Node leftRotate(Node node){
		if(node == null) return null;
		Node t = node.right;
		if(t == null) return node;
		node.right = t.left;
		t.left = node;
		node.color = Color.Red;
		t.N = node.N;
		node.N = 1 + size(node.right) + size(node.left) + 1;
		return t;
		
	}
	
	
	private Node rightRotate(Node node){
	    if(null == null) return null;
	    Node t = node.left;
	    if(t == null) return node;
	    t.right = node.left;
	    t.right = node;
	    node.color = Color.Red;
	    t.N = node.N;
	    node.N = 1 + size(node.right) + size(node.left);
	    return t;
	}
	
	private void flip(Node node){
		node.color = Color.Red;
		node.left.color = Color.Black;
		node.right.color = Color.Black;
	}
	
	public Node min(){
		root = min(root);
		root.color = Color.Black;
		return root;
		
	}
	
	private Node min(Node node){
		if(node == null) return null;
		if(node.left == null) return node;
		return min(node.left);
	}
	
	public Node max(){
		root.color = Color.Black;
		return max(root);
	}
	
	private Node max(Node node){
		if(node == null) return null;
		if(node.right == null) return node;
		return max(node.right);
	}
	
	public Node deleteMax(){
		root.color = Color.Black;
		return deleteMax(root);
	}
	
	private Node deleteMax(Node node){
		if(node == null) return null;
		if(node.right == null) return node.left;
		node.right = deleteMax(node.right);
		node.N = size(node.right) + size(node.left) + 1;
		deleteFixUp(node);
		fixUp(node);
		return node;
	}
	
	public Node deleteMin(){
		root.color = Color.Black;
		return deleteMin(root);
		
	}
	
	private Node deleteMin(Node node){
		if(node == null) return null;
		if(node.left == null) return node.right;
		node.left = deleteMin(node.left);
		node.N = size(node.right) + size(node.left) + 1;
		return node;
	}
	
	public void delete(Key key){
		delete(root,key);
		root.color = Color.Black;
	}
	
	private Node delete(Node node,Key key){
		if(node == null) return null;
		int cmp = key.compareTo(node.key);
		if(cmp < 0) node.left = delete(node.left,key);
		else if(cmp > 0) node.right = delete(node.right,key);
		else{
			if(node.left == null) return node.right;
			if(node.right == null) return node.left;
			Node t = node;
			node = min(node.right);
			node.left = t.left;
			node.right = deleteMin(t.right);
		}
		node.N = size(node.right) + size(node.left) + 1;
		return node;
	}
	
	private void deleteFixUp(Node node){
		if(isRed(node.right) && !isRed(node.left)) leftRotate(node);
		if(!isRed(node.right) && !isRed(node.left) && !isRed(node.right.left) && !isRed(node.right.right)) node.right.color = Color.Red;
		if(!isRed(node.right) && !isRed(node.left) && isRed(node.right.left) && !isRed(node.right.right)) {
			node.right.color = Color.Red;
			node.right.left.color = Color.Black;
			
		}
	}

}
