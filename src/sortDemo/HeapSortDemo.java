package sortDemo;

public class HeapSortDemo <Key extends Comparable<Key>> {
	
	public HeapSortDemo(){}
	public void heapSort(Key[] array){
		int n = array.length;
		for(int k = n/2; k > 0; k--){
			sink(array,k,n);
		}
		while(n > 1){
			exch(array,1,n--);
			sink(array,1,n);
		}
	}
	private void exch(Key[] array, int i, int j) {
		Key tmp = array[i-1];
		array[i-1] = array[j-1];
		array[j-1] = tmp;
		
	}
	
	private boolean greater(Key[] array,int i,int j){
		return array[i-1].compareTo(array[j-1]) > 0;
	}
	private void sink(Key[] array, int k, int n) {
		int harf = n >>> 1;
		while(k < harf){
			int leftchild = k << 1;
			int rightchild = leftchild + 1;
			if(greater(array,leftchild,rightchild))
				exch(array,leftchild,rightchild);
			if(greater(array,k,leftchild))
				exch(array,k,leftchild);
			k = k << 1;		
		}
		
	}
	public static void main(String[] args){
		String[] a = {"G","N","V","B","R","R","T","V","G","X","X","E","E","C","G","N","T","R","Z"};
		HeapSortDemo<String> heap = new HeapSortDemo<String>();
		heap.heapSort(a);
		for(String s: a){
			System.out.println(s);
		}
	}

}
