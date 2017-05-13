package tree;

import java.util.AbstractQueue;
import java.util.Iterator;

public class SimplePriorityQueue extends AbstractQueue{
	
	static final int MAXINUM = 10;
	
	private int[] array ;
	
	private volatile int border = -1;
	public SimplePriorityQueue(int[] array){
		this.array = array;
	}
	public int size(){
		return border+1;
	}
	public void add(int t){
	  border++;
	  if(size()>MAXINUM)
		  throw new RuntimeException();
	  if(border==0)
		  array[border] = t;
	  else{
		  shiftUp(border,t);
	  }
	}
	private void shiftUp(int border, int t) {
		while (border > 0) {
	        int parent = (border - 1) >>> 1;//parentNo = (nodeNo-1)/2
	        int e = array[parent];
	        if (t<array[parent])//调用比较器的比较方法
	            break;
	        array[border] = e;
	        border = parent;
	    }
	    array[border] = t;
	}
	public Object poll(){
		if(size()==0)
			throw new RuntimeException();
		int result = array[0];
		border--;
		if(size()!=0){
		int t = array[size()];
		shiftDonw(0,t);
		}
		return result;
	}
	private void shiftDonw(int index,int t){
		int half = size()>>>1;
		while(index<half){
			int child = (index<<1) + 1;
			int left = array[child];
			int right = child + 1;
			if(right<size() && left<array[right]){
				left = array[child = right];
			}
			if(t>left)
				break;
			array[index] = left;
			index = child;		
		}
		array[index] = t;
		
	}
	@Override
	public Object peek(){
		if(size()==0)
			throw new NullPointerException();
		return array[0];
	}
	
	public static void main(String[] args){
		int[] array = new int[MAXINUM];
		SimplePriorityQueue p = new SimplePriorityQueue(array);
		p.add(10);
		p.add(15);
    	p.add(13);
    	p.add(106);
    	p.add(34);
    	p.add(56);
    	System.out.println(p.poll());
		System.out.println(p.peek());
		for(int i:p.array){
			System.out.println(i);
		}
	
	}
	@Override
	public boolean offer(Object e) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return null;
	}

}
