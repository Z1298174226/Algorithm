import java.util.Arrays;

/**
 * Created by qtfs on 2017/9/28.
 */
public class ArraysSortDemo {
    public static void main(String[] args) {
        // int[] array = new int[]{43,56,32,12,5,7,123};
        int[][] array = new int[][]{{3, 6}, {17, 45}, {1, 16}, {6, 0}, {414, 34}};
        Arrays.sort(array, (a, b) -> (b[0] - a[0]));
        for(int[] arrays : array) {
            for(int num : arrays) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }
}
