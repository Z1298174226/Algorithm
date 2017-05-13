package sortDemo;

public class HeapSort<Key extends Comparable<Key>> {
	public HeapSort(){}
	public void sort(Key[] array){
		int n = array.length;
		for (int k = n/2; k >=1; k--)
            sink(array,k,n);
		while(n > 1){
			exch(array,1,n--);
			sink(array,1,n);
		}
	}
	
//	public void sortCopy(Key[] array){
//		int n = array.length;
//		for(int i = n;i >= 1;i--){
//			sink(array,i,n);
//		}
//		while(n > 1){
//			exch(array,1,n--);
//			sink(array,1,n);
//		}
//	}
	private void sink(Key[] array,int i,int j){
		int harf = j >>> 1;
		while(i <= harf){
			int child = i << 1;
		    if(child < j &&less(array,child,child + 1))
		    	child ++;
		    if(less(array,i,child))
		    	exch(array,i,child);
		    i = child;
		    /*
		     * int child = k<<1;
    		if(child < n && less(pq,child,child + 1)) child++;
    		if(less(pq,k,child)) exch(pq,k,child);
    		k = child;
		     */
		}
	}
	private boolean less(Key[] array,int i,int j){
		return array[i-1].compareTo(array[j-1]) < 0;
	}
	private void exch(Key[] array,int i,int j){
		Key tmp = array[i - 1];
		array[i-1] = array[j-1];
		array[j-1] = tmp;
	}
	public static void main(String[] args){
		String[] a = {"G","N","V","B","R","R","T","V","G","X","X","E","E","C","G","N","T","R","Z"};
		HeapSort<String> heap = new HeapSort<String>();
		heap.sort(a);
		for(String s: a){
			System.out.println(s);
		}
	}

}
