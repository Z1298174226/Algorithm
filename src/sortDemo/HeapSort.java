package sortDemo;

import java.util.Random;

/**
 * Created by qtfs on 2017/10/13.
 */
public class HeapSort<Key extends Comparable<Key>> {
    public void sort(Key[] array) {
        int len = array.length;
        for(int k = len / 2; k >= 1; k--)
            sink(array, k, len);
        while(len > 1) {
            exc(array, 1, len--);
            sink(array, 1, len);
        }
    }
    private boolean less(Key[] array, int p, int q) {
        return array[p - 1].compareTo(array[q - 1]) < 0;
    }

    private void exc(Key[] array, int p, int q) {
        Key temp = array[p - 1];
        array[p - 1] = array[q - 1];
        array[q - 1] = temp;
    }

    private void sink(Key[] array, int up, int down) {
        int harf = down >>> 1;
        while(up <= harf) {
            int child = up << 1;
            if(child < down && less(array, child, child + 1))
                child++;
            if(less(array, up, child))
                exc(array, up, child);
            up = child;
        }
    }

    public static class ProduceArray {
        public static int[] produce(int num) {
            int[] array = new int[num];
            Random rand = new Random();
            for(int i = 0; i < num; i++) {
                array[i] = rand.nextInt(200);
            }
            return array;
        }
    }

    public static void main(String[] args) {
        Integer[] array = new Integer[100];
        int[] arraySmall = ProduceArray.produce(100);
        for(int i = 0; i < 100; i++) {
            array[i] = new Integer(arraySmall[i]);
        }
        HeapSort<Integer> heapSort = new HeapSort<Integer>();
        heapSort.sort(array);
        for(int i : array)
            System.out.print(" " + i);
    }
}
