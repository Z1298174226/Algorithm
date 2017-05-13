package sortDemo;



public class InsertSort<Key extends Comparable<Key>> {
	public InsertSort(){}
	public void  simpleSort(Key[] array){
		int n = array.length;
		for(int i = 1;i < n;i++){
			for(int j = i;j > 0 && less(array[j],array[j-1]);j--){
				exch(array,j ,j - 1);
			}
		}
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
		InsertSort<String> insert = new InsertSort<String>();
		insert.simpleSort(a);
		for(String s: a){
			System.out.println(s);
		}
	}

}
