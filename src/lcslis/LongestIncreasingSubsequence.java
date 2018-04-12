package lcslis;

/**
 * Created by qtfs on 2018/4/12.
 */

import java.util.Scanner;
public class LongestIncreasingSubsequence {
    public static int longestIncreasingSubsequence(String s) {
        int length = s.length();
        char[] array = s.toCharArray();
        char[] dp = new char[length + 1];
        int point = 1;
        int len = 1;
        dp[point] = array[0];
        for(int i = 0; i < length; i++) {
            len = help(1, point, dp, array[i]);
            dp[len] = array[i];
            point = Math.max(len, point);
        }
        return point;
    }

    private static int help(int start, int end, char[] dp, char comp) {
        if(dp[end] < comp) return end + 1;
        while (start < end) {
            int mid = (end - start) / 2 + start;
            if (dp[mid] < comp)
                start = mid + 1;
            else
                end = mid;
        }
        return start;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            String s = scanner.next();
            System.out.println(longestIncreasingSubsequence(s));
        }
    }
}
