package quickSort;

import edu.princeton.cs.algs4.StdRandom;

public class QuickSort<Key extends Comparable<Key>> {
	public int cnt = 0;
	public int cntPop = 0;
	public void sorted(Key[] array){
		sorted(array,0,array.length - 1);
	}
	private void sorted(Key[] array,int head,int tail){
		if(head >= tail) return;
		//int j = partition(array,head,tail);
		int j = partitionPlus(array,head,tail);
		sorted(array,head,j-1);
		sorted(array,j+1,tail);
	}
	private int partition(Key[] array,int head,int tail) {
		int i = head;
		int j = tail + 1;
		Key standard = array[head];
		while(true){
			while(less(array[++i],standard))
				if(i == tail) break;
			while(less(standard,array[--j]))
				if(j == head) break;
			if(i >= j) break;
			exc(array,i,j);
		}
		exc(array,head,j);
		return j;
		
	}
	private int partitionPlus(Key[] array,int head,int tail) {
		int i = head - 1;
		Key standard = array[tail];
		for(int j = head; j < tail; j++) {
			if(less(array[j],standard)){
				exc(array,++i,j);
			}
		}
		exc(array, ++i ,tail);
		return i;
	}
	private void exc(Key[] array, int i, int j) {
		Key tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
		
	}
	private boolean less(Key key1 ,Key key2){
		cnt++;
		return key1.compareTo(key2) < 0;
		
	}
	private boolean lessPop(Key key1,Key key2){
		cntPop++;
		return key1.compareTo(key2) < 0;
	}
	
	public void sortPop(Key[] array){
		int tail = array.length - 1;
		for(int i = 0;i < tail ; i++){
			for(int j = 0;j < tail - i;j++){
				if(lessPop(array[j+1],array[j]))
					exc(array,j+1,j);
			}
		}
	}
	
	public static void main(String[] args){
		QuickSort<String> quick = new QuickSort<String>();
		String[] array = {"F","B","D","Q","Z","G","A","J","L","T"};
		//quick.sorted(array);
		quick.sorted(array);
		for(String s:array){
			System.out.println(s);
		}
		System.out.println(quick.cntPop);
//		StdRandom.shuffle(array);
//		StdRandom.shuffle(array);
//		StdRandom.shuffle(array);
//		StdRandom.shuffle(array);
//		StdRandom.shuffle(array);
//		StdRandom.shuffle(array);
//		quick.sorted(array);
//		//quick.sortPop(array);
//		for(String s:array){
//			System.out.println(s);
//		}
//		System.out.println(quick.cnt);
		//System.out.println(quick.cntPop);
	}

}
