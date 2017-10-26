package sortDemo;

public class ShellDemo <Key extends Comparable<Key>>{
	public ShellDemo(){}
	public void shellSort(Key[] array){
		int n = array.length;
		int h = 1;
		while(h < n/3)
			h = h*3 + 1;
		for(int i = h;i < n;i++){
			for(int j = i;j >= h && less(array,j,j-h);j -= h){
				exch(array,j,j-h);
				
			}
		}
		h /=3;
	}
	private void exch(Key[] array, int j, int i) {
		Key tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
		
	}
	private boolean less(Key[] array, int j, int i) {
		return array[i].compareTo(array[j])  < 0;
	}
	
	public static void main(String[] args){
		 String[] a = {"G","N","V","B","R","R","T","V","G","X","X","E","E","C","G","N","T","R"};
	     ShellSort<String> shell = new ShellSort<String>();
	     shell.sort(a);
	     for(String s: a){
	    	 System.out.println(s);
	     }
	}
	

}
