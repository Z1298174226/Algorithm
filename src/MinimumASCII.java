/**
 * Created by qtfs on 2017/10/22.
 */
public class MinimumASCII {
    public static int compute(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];
        return findMaxASCII(s1, s2, s1.length(), s2.length(), dp);
    }
    private static int findMaxASCII(String s1, String s2, int len1, int len2, int[][] dp) {
        if(dp[len1][len2] != 0) return dp[len1][len2];
        if(len1 == 0 && len2 == 0) {
            dp[len1][len2] = 0;
            return 0;
        }
        else if(len1 == 0) {
            int tmp = findMaxASCII(s1, s2, 0, len2 - 1, dp);
            dp[0][len2] = (int) tmp + s2.charAt(len2 - 1);
            return dp[0][len2];
        }
        else if(len2 == 0) {
            int tmp = findMaxASCII(s1, s2, len1 - 1, 0, dp);
            dp[len1][0] = (int) tmp + s1.charAt(len1 - 1);
            return dp[len1][0];
        }
        if(s1.charAt(len1 - 1) == s2.charAt(len2 - 1)) {
            dp[len1][len2] = findMaxASCII(s1, s2, len1 - 1, len2 - 1, dp);
        }
        else {
            int tmp1 = findMaxASCII(s1, s2, len1 - 1, len2, dp);
            int tmp2 = findMaxASCII(s1, s2, len1, len2 -1, dp);
            dp[len1][len2] = Math.min((tmp1 + (int)s1.charAt(len1 - 1)), (tmp2 + (int)s2.charAt(len2 - 1)));
        }
        return dp[len1][len2];
    }

    public static void main(String[] args) {
        String s1 = "mynameiszhaoxudong";
        String s2 = "hisnameiswangdali";
        System.out.println(compute(s1, s2));
    }
}
