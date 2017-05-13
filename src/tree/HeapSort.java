package tree;

import edu.princeton.cs.algs4.StdOut;

public class HeapSort<Key extends Comparable<Key>>{

    // This class should not be instantiated.
    private HeapSort() { }

    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param pq the array to be sorted
     */
    public  void sort(Key[] pq) {
        int n = pq.length;
        for (int k = n/2; k >= 1; k--)
            sinkDemo(pq, k, n);
        while (n > 1) {
            exch(pq, 1, n--);
            sinkDemo(pq, 1, n);
        }
    }
    
    public void sortDemo(Key[] pq) {
    	int n = pq.length;
    	for (int k = n/2; k >= 1; k--)
            sinkDemoCopy(pq, k, n);
    	while (n > 1) {
    		exch(pq, 1, n--);
    		sinkDemoCopy(pq,1,n);
    	}
    }
    
   

   /***************************************************************************
    * Helper functions to restore the heap invariant.
    ***************************************************************************/

    private  void sink(Key[] pq, int k, int n) {
        while (2*k <= n) {
            int j = 2*k;
            if (j < n && less(pq, j, j+1)) j++;
            if (!less(pq, k, j)) break;
            exch(pq, k, j);
            k = j;
        }
    }
 
    
    private void sinkDemo(Key[] pq, int k, int n){
    	int half = n >>> 1;
    	while(k <= half){
    		int child = k<<1;
    		if(child < n && less(pq,child,child + 1)) child++;
    		if(less(pq,k,child)) exch(pq,k,child);
    		k = child;
    	}
    	
    }
    
    private void sinkDemoCopy(Key[] pq, int k, int n) {
    	int half = n >>> 1;
    		while(k <= half){
    			int child = k <<1;
    			if(child <n && less(pq,child + 1,child))  exch(pq,child,child + 1); 
    			if(less(pq,child,k)) exch(pq,k,child);
    			k = child;
    		}
    }

   /***************************************************************************
    * Helper functions for comparisons and swaps.
    * Indices are "off-by-one" to support 1-based indexing.
    ***************************************************************************/
    private boolean less(Key[] pq, int i, int j) {
        return pq[i-1].compareTo(pq[j-1]) < 0;
    }

    private static void exch(Object[] pq, int i, int j) {
        Object swap = pq[i-1];
        pq[i-1] = pq[j-1];
        pq[j-1] = swap;
    }

    // print array to standard output
    private  void show(Key[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }

    /**
     * Reads in a sequence of strings from standard input; heapsorts them; 
     * and prints them to standard output in ascending order. 
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        String[] a = {"G","N","V","B","R","R","T","V","G","X","X","E","E","C","G","N","T","R"};
        HeapSort<String> heap = new HeapSort<String>();
        heap.sortDemo(a);
        heap.show(a);
    }
}