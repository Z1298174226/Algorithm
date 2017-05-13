package quickSort;

public class Quick3divi <Key extends Comparable<Key>> {
	
	public int cnt = 0;
	public int cntExc = 0;
	
	public  void sorted(Key[] array){
		if(array == null) return;
		sorted(array,0,array.length - 1);
	}
	private void sorted(Key[] array,int head,int tail){
		if(head >= tail) return;
		int lt = head;
		int i = head + 1;
		int gt = tail;
		Key standard = array[head];
		while(i <= gt){
			int cmp = array[i].compareTo(standard);
			cnt++;
			if(cmp < 0) exc(array,lt++,i++);
			else if(cmp > 0) exc(array,i,gt--);
			else i++;
		}
		sorted(array,head,lt-1);
		sorted(array,gt+1,tail);
	}
	
	private void exc(Key[] array,int a,int b){
		Key tmp = array[a];
		array[a] = array[b];
		array[b] = tmp;
		cntExc++;
	}
	
	public static void main(String[] args){
		String[] s = {"G","N","V","B","R","R","T","V","G","X","X","E","E","C","G","N","T","R"};
		Quick3divi<String> divi = new Quick3divi<String>();
		//QuickSort quick = new QuickSort();
		divi.sorted(s);
		//quick.sorted(s);
		for(String a:s){
			System.out.println(a);
		}
		System.out.println(divi.cnt);
		System.out.println(divi.cntExc);
		
	}
//	public void sorted(Key[] array){
//		if(array == null) return;
//		sorted(array,0,array.length-1);
//	}
//	
//	private void sorted(Key[] array,int head,int tail){
//		if(head >= tail) return;
//		int j = partition(array,head,tail);
//		sorted(array,head,j-1);
//		sorted(array,j+1,tail);
//	}
	
	private int partition(Key[] array,int head,int tail){
		int i = head ;
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
	
	private boolean less(Key key1 , Key key2){
		cnt++; 
		return key1.compareTo(key2) < 0;
	}

}
