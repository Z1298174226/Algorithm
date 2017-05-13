package tree;

public class SimplePriorityQueueTest<T extends Comparable<T>> {
	private T[] array;
	static final int MAXINUM = 1000;
	public SimplePriorityQueueTest(T[] array){
		this.array = array;
	}
	private volatile int border = -1;
	public int size(){
		return border + 1;
	}
	public T peek(){
		if(size()==0){
			throw new RuntimeException();
		}
		return array[0];
	}
	public void add(T element){
		if(size()>MAXINUM || element == null){
			throw new RuntimeException();
		}
		border++;
		if(border == 0)
			array[border] = element;
		else
			shiftUp(border,element);
	}
	private void shiftUp(int k, T element) {
		while(k > 0){
		int parent = (k - 1)>>>1;
		T p = array[parent];
		if(p.compareTo(element)<0){
			array[k] = p;
			k = parent;
		}
		else
			break;
		}	
		array[k] = element;
	}
	public T poll(){
		if(size() == 0){
			throw new RuntimeException();
		}
		T result = array[0];
		T tail = array[border];
		border--;
		shiftDown(0,tail);		
		return result;
	}
	private void shiftDown(int i, T tail) {
		int harf = size()>>>1;
		while(i<harf){
			int left = (i<<1)+1;
			T leftelement = array[left];
			int right = left + 1;
			if(leftelement.compareTo(array[right])<0 && right<size()){
				array[left] = array[right];
		//		array[right] = leftelement;
			}
			if(tail.compareTo(array[left])>0)
				break;
			else{
				array[i] = array[left];
				i = left;
			}
			
		}
		array[i] = tail;
		
	}
	
	static class Element implements Comparable<Element> {
        public int elements;
        public Element(int elements){
        	this.elements = elements;
        }
		@Override
		public int compareTo(Element arg0) {
			if(elements>arg0.elements)
				return +1;
			else if(elements == arg0.elements)
				return 0;
			else
				return -1;				
		}
		
	}
	public static void main(String[] args){
	    Element[] array = new Element[MAXINUM];
		SimplePriorityQueueTest<Element> queue = new SimplePriorityQueueTest<Element>(array);
		for(int i= 0;i<10;i++){
		queue.add(new Element((int)(Math.random()*100)));
		}
		
		System.out.println(queue.poll().elements);
		System.out.println(queue.poll().elements);
		System.out.println(queue.poll().elements);
		System.out.println(queue.poll().elements);
		System.out.println(queue.poll().elements);
	
		
	
		
		
		
	}
	
	
	

}
