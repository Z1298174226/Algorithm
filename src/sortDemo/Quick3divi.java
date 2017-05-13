package sortDemo;

public class Quick3divi<Key extends Comparable<Key>> {
	public Quick3divi(){}
	public void sort(Key[] array){
		if(array == null) return;
		sort(array,0,array.length - 1);
	}
	private void sort(Key[] array,int head,int tail){
		if(head >= tail) return;
		int i = head;
		int j = head + 1;
		int k = tail;
		Key standard = array[head];
		while(true){
	       int cmp = standard.compareTo(array[j]);
	       if(cmp > 0) exch(array,i++,j++);
	       else if(cmp < 0) exch(array,j,k--);
	       else j++;
	       if(j >  k) break;
		}
		sort(array,head,i - 1);
		sort(array,k + 1,tail);
		
	}
	private void exch(Key[] array,int i,int j){
		Key tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}
	public static void main(String[] args){
		String[] a = {"G","N","V","B","R","R","T","V","G","X","X","E","E","C","G","N","T","R"};
		Quick3divi<String> quick = new Quick3divi<String>();
		quick.sort(a);
		for(String s: a){
			System.out.println(s);
		}
	}

}
