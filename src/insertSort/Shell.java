package insertSort;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Shell<Key extends Comparable<Key>> {

    // This class should not be instantiated.
    public Shell() { }
    public int cnt = 0;
    /**
     * Rearranges the array in ascending order, using the natural order.
     * @param a the array to be sorted
     */
    public  void sort(Key[] a) {
        int n = a.length;

        // 3x+1 increment sequence:  1, 4, 13, 40, 121, 364, 1093, ... 
        int h = 1;
        while (h < n/3) h = 3*h + 1; 

        while (h >= 1) {
            // h-sort the array
            for (int i = h; i < n; i++) {
                for (int j = i; j >= h && less(a[j], a[j-h]); j -= h) {
                    exch(a, j, j-h);
                }
            }
            assert isHsorted(a, h); 
            h /= 3;
        }
        assert isSorted(a);
    }
    
    public void sorted(Key[] array){
    	int n = array.length;
    	int h = 1;
    	while(h < n/3) h = h*3 + 1;
    	while(h >= 1){
    		for(int i = h;i < n; i++) {
    			for(int j = i; j >= h &&less(array[j],array[j-h]); j -= h){
    				exch(array,j,j-h);
    			}
    		}
    	    h /= 3;
    	}
    }



   /***************************************************************************
    *  Helper sorting functions.
    ***************************************************************************/
    
    // is v < w ?
    private  boolean less(Key v, Key w) {
    	cnt++;
        return v.compareTo(w) < 0;
    }
        
    // exchange a[i] and a[j]
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }


   /***************************************************************************
    *  Check if array is sorted - useful for debugging.
    ***************************************************************************/
    private boolean isSorted(Key[] a) {
        for (int i = 1; i < a.length; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }

    // is the array h-sorted?
    private  boolean isHsorted(Key[] a, int h) {
        for (int i = h; i < a.length; i++)
            if (less(a[i], a[i-h])) return false;
        return true;
    }

    // print array to standard output
    private  void show(Key[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }

    /**
     * Reads in a sequence of strings from standard input; Shellsorts them; 
     * and prints them to standard output in ascending order. 
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        //String[] a = StdIn.readAllStrings();
        String[] a = {"G","N","V","B","R","R","T","V","G","X","X","E","E","C","G","N","T","R"};
    	Shell<String> shell = new Shell<String>();
        shell.sort(a);
        shell.show(a);
        System.out.println(shell.cnt);
    }

}
