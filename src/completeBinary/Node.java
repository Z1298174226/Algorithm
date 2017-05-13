package completeBinary;

public class Node {
	private Node right ;
	private Node left;
	public Node(){
		right = new Node();
		left = new Node();
	}
	public Node left(){
		return left;
	}
	public Node right(){
		return right;
	}

}
