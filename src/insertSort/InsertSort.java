package insertSort;

public class InsertSort<Key extends Comparable<Key>>{
	public InsertSort(){}
	public int cnt = 0;
	public void sort(Key[] array){
		if(array == null) return;
		int n = array.length;
		for(int i =1; i < n; i++){
			for(int j =i ;j > 0;j--){
				if(less(array[j],array[j-1]))
					exch(array,j,j-1);
			}
		}
			/*
			if(less(array[i],array[i-1])){
				Key x = array[i];
				array[i] = array[i-1];
				int j = i-1;
				while( j > 0 && less(x,array[j-1] ) ){
						array[j--] = array[j];			
					}				
				array[j] = x;
			}
		}
		*/
	}
	private void exch(Key[] array,int a,int b){
		Key tmp = array[a];
		array[a] = array[b];
		array[b] = tmp;
	}
	private boolean less(Key key1,Key key2){
		cnt++;
		return key1.compareTo(key2) < 0;
	}
	public static void main (String[] args){
		String[] a = {"G","N","V","B","R","R","T","V","G","X","X","E","E","C","G","N","T","R"};
		InsertSort<String> insert = new InsertSort<String>();
		insert.sort(a);
		for(String s:a){
			System.out.println(s);
		}
		System.out.println(insert.cnt);
	
		
	}
	

}
