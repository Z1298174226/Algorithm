package sortDemo;

import java.lang.reflect.Array;

public class ShellSort<Key extends Comparable<Key>> {
	public ShellSort(){}
	public void sort(Key[] array){
		//1,4,13,40,121,364,1093
		int n = array.length;
		int h = 1;
		while(h < n/3) h = h*3 + 1;
		while(h >= 1){
			for(int i = h;i<n;i++){
				for(int j = i;j >= h&& less(array[j],array[j-h]);j -= h){
					exch(array,j,j-h);
					
				}
			}
			h /= 3;		
		}
	}
	
	

	
	public void sortCopy(Key[] array){
		int n = array.length;
		int h = 1;
		while(h < n/3) h = h*3 + 1;
		while(h > 0){
			for(int i = h;i < n;i++){
				for(int j = i;j > h && less(array[j],array[j - h]);j -= h)
					exch(array,j,j-h);
			}
		}
		h /= 3;
	}
	
	private boolean less(Key key1 ,Key key2){
		return key1.compareTo(key2) < 0;
	}
	private void exch(Key[] array,int i,int j){
		Key tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
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
