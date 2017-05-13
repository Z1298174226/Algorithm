package sortDemo;

public class MergeDemo <Key extends Comparable<Key>>{
	@SuppressWarnings("unchecked")
	public void mergeSort(Key[] array){
		int n = array.length;
		Key[] tmp = (Key[]) new Comparable[n];
		mergeSort(array,0,n-1,tmp);
		
	}
	private void mergeSort(Key[] array,int first,int last,Key[] tmp){
		if(first < last){
		   int mid = (first + last) /2;
		   mergeSort(array,first,mid,tmp);
		   mergeSort(array,mid + 1,last,tmp);
		   mergeArray(array,first,mid,last,tmp);
		}
		
	}
	
	private void mergeArray(Key[] array,int first,int mid,int last,Key[] tmp){
		int i = first; int j = mid + 1;
		int k = 0;
		int m = mid; int n = last;
		while(i <= m && j <= n){
			if(array[i].compareTo(array[j]) <= 0)
				tmp[k++] = array[i++];
			else
				tmp[k++] = array[j++];
		}
		while(i <= m)
			tmp[k++] = array[i++];
		while(j <= n)
			tmp[k++] = array[j++];
		for(int s = 0; s < k;s++){
			array[first + s] = tmp[s];
		}
	}
	
	public static void main(String[] args){
		String[] a = {"G","N","V","Z","Z","B","R","R","T","V","G","X","X","E","E","C","G","N","T","R"};
		MergeDemo<String> merge = new MergeDemo<String>();
		merge.mergeSort(a);
		for(String s : a){
			System.out.println(s);
		}		
	}

}
