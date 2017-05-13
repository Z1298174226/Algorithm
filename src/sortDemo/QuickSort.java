package sortDemo;



public class QuickSort<Key extends Comparable<Key>> {
	public QuickSort(){}
	public void sort(Key[] array){
		sort(array,0,array.length - 1);
	}
	private void sort(Key[] array,int head,int tail){
		if(head >= tail) return;
		int j = partition(array,head,tail);
		sort(array,head,j - 1);
		sort(array,j + 1,tail);
	}
	public Key find(Key[] array,int s){
		return find(array,0,array.length -1 ,s);
	}
	private Key find(Key[] array,int head,int tail,int s){
		if(head >= tail) return null;
		int j = partition(array,head,tail);
		find(array,head,j - 1,s);
		find(array,j + 1,tail,s);
		if(j == s) return array[j];
		else return null;
	
	}
	private int partition(Key[] array,int head,int tail){
		int i = head;
		int j = tail + 1;
		Key standard = array[head];
		while(true){
			while(less(array[++i],standard))
				if(i == tail) break;
			while(less(standard,array[--j]))
				if(j == head) break;
			if(i >= j) break;
			exch(array,i,j);
			
		}
		exch(array,head,j);
		return j;
	}
	private boolean less(Key key1,Key key2){
		return key1.compareTo(key2) < 0;
	}
	private void exch(Key[] array,int i,int j){
		Key tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}
	public static void main(String[] args){
		String[] a = {"G","N","V","B","R","R","T","V","G","X","X","E","E","C","G","N","T","R"};
		QuickSort<String> quick = new QuickSort<String>();
		System.out.println(quick.find(a, 3));
		quick.sort(a);
		for(String s:a){
			System.out.println(s);
		}
	}

}
