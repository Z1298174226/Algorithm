package sortDemo;

import java.util.Random;

/**
 * Created by qtfs on 2017/10/13.
 */
public class QuickDemo {

    private int partition(int[] array, int start, int end) {
        int standard = array[end];
        int i = start - 1;
        for(int j = start; j < end; j++) {
            if(array[j] < standard)
                exc(array, ++i, j);
        }
        exc(array, ++i, end);
        return i;
    }

//    private int partitionUpdate(int[] array, int start, int end) {
//        int standard = array[start];
//        int i = start; int j = end + 1;
//       while(true) {
//           while(array[++i] < standard)
//               if(i == end) break;
//           while(array[--j] > standard)
//               if(j == start) break;
//           if(i >= j) break;
//           exc(array, i, j);
//       }
//       exc(array, i, start);
//        return i;
//    }

    private int partitionUpdate(int[] array, int start, int end) {
        int standard = array[start];
        int i = start; int j = end + 1;
        while(true) {
            if(i >= j) break;
            while(array[++i] > standard) {
                if(i == end) break;
            }
            while(array[--j] < standard) {
                if(j == start) break;
            }
            exc(array, i, j);
        }
        exc(array, i , start);
        return i;
    }

    private void exc(int[] array, int p, int q) {
        int temp = array[p];
        array[p] = array[q];
        array[q] = temp;
    }
    public void sort(int[] array, int start, int end) {
        if(start > end) return;
        //int j = partition(array, start, end);
        int j = partitionUpdate(array, start, end);
        sort(array, start, j - 1);
        sort(array, j+1, end);
    }

    public static void main(String[] args) {
            QuickDemo quickDemo = new QuickDemo();
            int[] array =ProduceArray.prodecu(10000);
            quickDemo.sort(array, 0, array.length - 1);
            for(int i : array)
                System.out.print(" " + i);
        }

        public static class ProduceArray {
            public static int[] prodecu(int num) {
                int[] array = new int[num];
                Random rand = new Random();
                for(int i = 0; i < num; i++)
                    array[i] = rand.nextInt(20000);
                return array;
            }
    }
}
