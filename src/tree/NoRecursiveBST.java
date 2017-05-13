package tree;

public class NoRecursiveBST<Key extends Comparable<Key>,Value> {
	private Node root;
	class Node{
		public Key key;
		public Value value;
		public Node left; 
		public Node right;
		public int N;
		public Node(){}
		public Node(Key key,Value value,int N){
			this.key = key;
			this.value = value;
			this.N = N;
		}
	}
	public int size(){
		return size(root);
	}
	private int size(Node node){
		if(node == null) return 0;
		return node.N;
	} 
	public Value get(Key key){
		Node x = root;
		while(x!=null){
		int cmp=key.compareTo(x.key);
		if(cmp<0) x = x.left;
		if(cmp>0) x = x.right;
		return x.value;
		}
		return null;
	}
	public Value getRe(Key key){
		return getRe(root,key);
	}
	private Value getRe(Node node,Key key){
		if(node == null) {return null;}
	    int cmp = key.compareTo(node.key);
	    if(cmp<0) return getRe(node.left,key);
	    else if(cmp>0) return getRe(node.right,key);
	    else
	    	return node.value;
	    
	}
	public void put(Key key,Value value){
		root = put(root,key,value);
	}
	public Node put(Node node,Key key,Value value){
		if(node == null) return new Node(key,value,1);
		int cmp = key.compareTo(node.key);
		if(cmp<0) node.left = put(node.left,key,value);
		else if(cmp>0) node.right = put(node.right,key,value);
		else node.value = value;
		node.N = size(node.left) + size(node.right) + 1;
		return node;
	}
	
	public void putNoRe(Key key ,Value value){
		Node node = root;
		Node t = root; int cmp = 0;
		if(node == null){
			root = new Node(key,value,1); return;}
		while(node!=null){
			cmp = key.compareTo(node.key);
			t = node;
			if(cmp<0)  {node.N++;node = node.left;}
			else if(cmp>0)  {node.N++;node = node.right;}
			else node.key = key;	
		}
		if(cmp<0) t.left = new Node(key,value,1);
		else t.right = new Node(key,value,1);
	
	}
	public Key select(int n){
		return select(root,n);
	}
	private Key select(Node node,int n){
		if(node == null) return null;
		int t = node.N;
		if(t>n) return select(node.left,n);
		else if(t<n) return select(node.right,n-t-1);
		else return node.key;
	}
	
	public int rank(Key key){
		return rank(root ,key);
	}
	private int rank (Node node,Key key){
		if(node == null) return 0;
		int cmp = key.compareTo(node.key);
		if(cmp<0) return rank(node.left,key);
		else if(cmp>0) return size(node.left) + 1 + rank(node.right,key);
		else return node.N;
		
	}
	
	public Node min(){
		return min(root);
	}
	private Node min(Node node){
		if(node == null) return null;
		if(node.left == null) return node;
		return min(node.left);
	}
	public Value max(){
		return max(root).value;
	}
	private Node max(Node node){
		if(node== null) return null;
		if(node.right == null) return node;
		return max(node.right);
	}
	
	public void deleteMin(){
		deleteMin(root);
	}
	private Node deleteMin(Node node){
		if(node == null) return null;
		if(node.left==null) return node.right;
		node.left = deleteMin(node.left);
		node.N = size(node.left) + size(node.right) +1;
		return node;	
	}
	public void deleteMax(){
		deleteMax(root);
	}
	private Node deleteMax(Node node){
		if(node==null) return null;
		if(node.right==null) return node.left;
		node.right = deleteMax(node.right);
		node.N = size(node.right) + size(node.left) +1;
		return node;
	}
	public void delete(Key key){
		delete(root,key);
	}
	private Node delete(Node node,Key key){
		if(node == null) return null;
		int cmp = key.compareTo(node.key);
		if(cmp<0) node.left=delete(node.left,key);
		if(cmp>0) node.right=delete(node.right,key);
		else{
			if(node.left==null) return node.right;
			if(node.right==null) return node.left;
			Node t = node;
			node = max(node.left);
		    node.left = deleteMax(node.left);
		    node.right = t.right;
		}
		node.N = size(node.right) + size(node.left) +1;
		return node;
	 	
	}
	public Node minNoRe(){
		Node node = root;
		while(node!=null){
			node = node.left;
		}
		if(node.N!=0)
			return node;
		else
			return null;
	}
	public Node maxNoRe(){
		Node node = root;
		while(node!=null){
			node = node.right;
		}
		if(node.N!=0)
			return node;
		else
			return null;
	}
	
	public Key selectNoRe(int n){
		Node node = root;
		while(node!=null){
			int t =node.N;
			if(t>n) node = node.left;
			if(t<n) node = node.right;
			return node.key;
		}
		return null;
		
	}
	
	public static void main(String[] args){
		NoRecursiveBST<Integer,String> bst = new NoRecursiveBST<Integer,String>();
		bst.putNoRe(12, "t");
		bst.putNoRe(13, "twelve");
		bst.putNoRe(1, "twelve");
		bst.put(15, "twelve");
		bst.put(1, "twelve");
		bst.putNoRe(17, "twel");
		bst.putNoRe(18, "twelv");
		bst.putNoRe(108, "108");
		bst.delete(18);
		bst.deleteMax();
		System.out.println(bst.getRe(12));
		System.out.println(bst.max());
		System.out.println(bst.min());
		System.out.println(bst.root.N);
		System.out.println(bst.min().N);
	//	System.out.println(bst.selectNoRe(3));
	}
}
