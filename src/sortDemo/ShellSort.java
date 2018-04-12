package sortDemo;

import jdk.nashorn.tools.Shell;
//import org.jetbrains.annotations.Contract;

import java.util.Random;

/**
 * @author XDZ
 * Created by qtfs on 2017/10/13.
 */
public class ShellSort {

    private void exc(int[] array, int p, int q) {
        int temp = array[p];
        array[p] = array[q];
        array[q] = temp;
    }

   // @Contract(pure = true)
    private boolean less(int[] array, int p, int q) {
        return array[p] - array[q] < 0;
    }

    public void sort(int[] array) {
        int len = array.length;
        int h = 1;
        while(h < len / 3) h = h * 3 + 1;
        while(h >= 1) {
            for(int i = h; i < len; i++) {
                for(int j = i; j >= h&& less(array, j, j - h); j -= h)
                    exc(array, j, j - h);
            }
            h  /= 3;
        }
    }

    public static void main(String[] args) {
        ShellSort shellSort = new ShellSort();
        int[] array = QuickDemo.ProduceArray.prodecu(100);
        for(int i : array)
            System.out.print(" " + i);
        System.out.println();
        shellSort.sort(array);
        for(int i : array)
            System.out.print(" " + i);
    }

    public static class ProduceArray {
        public static int[] prodecu(int num) {
            int[] array = new int[num];
            Random rand = new Random();
            for(int i = 0; i < num; i++)
                array[i] = rand.nextInt(200);
            return array;
        }
    }


}
