package sortDemo;



public class ExchSort<Key extends Comparable<Key>> {
	public ExchSort(){}
	public void sort(Key[] array){
		int n = array.length - 1;
		for(int i = 1;i < n;i++){
			for(int j = 0;j <= n-i;j++){
				if(less(array[j+1],array[j])){
					exch(array,j+1,j);
				}
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
		ExchSort<String> exch = new ExchSort<String>();
		exch.sort(a);
		for(String s:a){
			System.out.println(s);
		}
	}

}
