package lcslis;

/**
 * Created by qtfs on 2018/4/12.
 */

import java.util.Scanner;

public class LongestCommonSubstring {
    public static int longestCommonSubstring(String s1, String s2) {
        int length1 = s1.length();
        int length2 = s2.length();
        int[][] dp = new int[length1 + 1][length2 + 1];
        int end = 0;
        int num = Integer.MIN_VALUE;
        for(int i = 1; i <= length1; i++) {
            for(int j = 1; j <= length2; j++) {
                if(s1.charAt(i - 1) == s2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    if(num < dp[i][j]) {
                        num = dp[i][j];
                        end = i;
                    }
            }
        }
        System.out.println(num + " " + "=>" + " " + s1.substring(end - num, end));
        return num;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while(true) {
            String s1 = scanner.next();
            String s2 = scanner.next();
            longestCommonSubstring(s1, s2);
        }
    }
}
