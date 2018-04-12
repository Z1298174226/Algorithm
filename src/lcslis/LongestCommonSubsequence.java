package lcslis;

/**
 * Created by qtfs on 2018/4/12.
 */

import java.util.Scanner;

public class LongestCommonSubsequence {
    public static int longestCommonSubsequence(String s1, String s2) {
        int length1 = s1.length();
        int length2 = s2.length();
        int[][] dp = new int[length1+ 1][length2 + 1];
        int num = Integer.MIN_VALUE;
        StringBuilder builder = new StringBuilder();
        for(int i = 1; i <= length1; i++) {
            for(int j = 1; j <= length2; j++) {
                if(s1.charAt(i - 1) == s2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                else
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                num = Math.max(num, dp[i][j]);
            }
        }
        return num;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            String s1 = scanner.next();
            String s2 = scanner.next();
            System.out.println(longestCommonSubsequence(s1, s2));
        }
    }
}
