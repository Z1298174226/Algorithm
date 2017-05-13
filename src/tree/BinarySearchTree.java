package tree;

//import java.util.AbstractQueue;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class BinarySearchTree<Key extends Comparable<Key>, Value> {
	private Node root;
	class Node{
		 Key key;
		 Value value;
		 Node left;
		 Node right;
		 int N;
		 public Node(Key key,Value value, int N){
			 this.key= key;
			 this.value = value;
			 this.N =N;
		 }
	}
	
	public int size(){
		return size(root);
	}
	
	private int size(Node node){
		if(node == null)
			return 0;
		return node.N;
	}
	
	public Value get(Key key){
		return get(root,key);
	}
	
	private Value get(Node node,Key key){
		if(node == null) return null;
		int cmp = key.compareTo(node.key);
		if(cmp<0) return get(node.left,key);
		if(cmp>0) return get(node.right,key);
		return node.value;
	}
	
	public void put(Key key,Value value){
		put(root,key,value);
	}
	
	private Node put(Node node,Key key,Value value){
		if(node == null)  return new Node(key,value,1);
		int cmp = key.compareTo(node.key);
		if(cmp<0) return put(node.left,key,value);
		if(cmp>0) return put(node.right,key,value);
		node.value = value;
		node.N = size(node.left) + size(node.right) + 1;
		return node;
	}
	
	public Key min(){
		return min(root).key;
	}
	private Node min(Node node){
		if(node == null ) return null;
		if(node.left == null) return node;
		return min(node.left);
		
	}
	public Key max(){
		return max(root).key;
	}
	
	private Node max(Node node){
		if(node == null) return null;
		if(node.right == null) return node;
		return max(node.right);
	}
	
	public Key floor(Key key){
		Node x = floor(root,key);
		if(x==null) return null;
		return x.key;
	}
	
	private Node floor(Node node,Key key){
		if(node == null) return null;
		int cmp = key.compareTo(node.key);
		if(cmp<0) return floor(node.left,key);
		if(cmp == 0) return node;
		Node x = floor(node.right,key);
		if(x!=null) return x;
		return node;
  	}
	
	public Key ceiling(Key key){
		Node x = ceiling(root,key);
		if(x == null) return null;
		return x.key;
	}
	
	private Node ceiling(Node node,Key key){
		if(node == null) return null;
		int cmp = key.compareTo(node.key);
		if(cmp>0) return floor(node.right,key);
		if(cmp == 0) return node;
		Node x = floor(node.left,key);
		if(x!=null) return x;
		return node;
	}
	
	public Key select(int k){
		return select(root,k);
	}
	
	private Key select(Node node ,int k){
		if(node == null) return null;
		int t = size(node.left);
		if(t>k) return select(node.left,k);
		if(t<k) return select(node.right,k-t-1);
		return node.key;	
	}
	
	public int rank(Key key){
		return rank(root,key);
	}
	
	private int rank(Node node,Key key){
		if(node == null) return 0;
		int cmp = key.compareTo(node.key);
		if(cmp<0) return rank(node.left,key);
		if(cmp>0) return 1+size(node.left)+rank(node.right,key);
		return size(node.left);
	}
	
	public void deleteMin(){
		deleteMin(root);
	}
	
	private Node deleteMin(Node node){
		if(node.left == null) return node.right;
		node.left = deleteMin(node.left);
		node.N = size(node.left) + size(node.right) +1;
		return node;
	}
	
	public void deleteMax(){
		deleteMax(root);
	}
	
	private Node deleteMax(Node node){
		if(node.right == null) return node.left;
		node.right = deleteMax(node.right);
		node.N = size(node.left) + size(node.right) +1;
		return node;
	}
	
	public void delete(Key key){
		delete(root,key);
	}
	
	private Node delete(Node node,Key key){
		if(node == null) return null;
		int cmp = key.compareTo(node.key);
		if(cmp<0) node.left = delete(node.left,key);
		if(cmp>0) node.right = delete(node.right,key);
		else{
			if(node.right == null) return node.left;
			if(node.left == null) return node.right;
			Node t = node;
			node = min(t.right);
			node.right = deleteMin(t.right);
			node.left = t.left;
			/*
			 * node = max(t.left);
			 * node.left = deleteMax(t.left)
			 * node.right = t.right;
			 */
			
			}
		node.N = size(node.right) + size(node.left) +1;
		return node;
	}
	public Iterable<Key> key(){
		return key(min(),max());
	}
	public Iterable<Key> key(Key clo,Key cli){
		Queue<Key> queue = new LinkedBlockingQueue<Key>();
		key(root,queue,clo,cli);
		return queue;
	}
	public void key(Node node,Queue<Key> queue,Key clo,Key cli){
		if(node == null) return;
		int cmpclo = clo.compareTo(node.key);
		int cmpcli = cli.compareTo(node.key);
		if(cmpclo<0) key(node.left,queue,clo,cli);
		if(cmpcli>0) key(node.right,queue,clo,cli);
		if(cmpclo>=0 && cmpcli<=0) queue.add(node.key);
		
	}
	public static void main(String[] args){
		
	}
}
