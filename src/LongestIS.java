import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by qtfs on 2017/10/19.
 */
public class LongestIS {
    public static final int getLIS(int[] sequence) {
        if(sequence == null || sequence.length == 0) return 0;
        int[] B = new int[sequence.length + 1];
        int len = 1;
        int result = 1;
        B[len] = sequence[0];
        for(int i = 0; i < sequence.length; i++) {
                len = help(B, 1, result, sequence[i]);
                B[len] = sequence[i];
                result = Math.max(len, result);
        }
        return result;
    }

    public static final int getLISS(int[] sequence) {
        if(sequence == null || sequence.length == 0) return 0;
        int[] dp = new int[sequence.length];
        int result = 1;
        for(int i = 0; i < sequence.length; i++) {
            for(int j = 0; j < i; j++) {
                if(sequence[i] > sequence[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    result = Math.max(result, dp[i]);
                }
            }
        }
        return result;
    }

    private static final int help(int[] B, int start, int end, int target) {
        if(B[end] < target) return end + 1;
        while(start < end) {
            int mid = start + (end - start) / 2;
            if(B[mid] < target) start = mid + 1;
            else end = mid;
        }
        return start;
    }

    public static void main(String[] args) {
        int num = 100000;
        int[] sequence = new int[num];
        for(int i = 0; i < num; i++) {
            Random rand = new Random();
            sequence[i] = rand.nextInt(10000000);
        }
        System.out.println(LongestIS.getLIS(sequence));
    }
}
