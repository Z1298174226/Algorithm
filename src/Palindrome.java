/**
 * Created by qtfs on 2017/9/24.
 */
public class Palindrome {

        public int minCut(String s) {
            return minCutStep(s);
        }
        private int minCutStep(String s) {
            if(s == null || s.length() == 0 || s.length() == 1) return 0;
            int n = s.length();
            int[] cutNum = new int[n];
            boolean[][] F = new boolean[n][n];
            for(int i = 0; i < n; i++) {
                int min = i;
                for(int j = 0; j <= i; j++) {
                    F[j][i] = (s.charAt(i) == s.charAt(j)) && (i - j < 2 || F[j+1][i-1]);
                    if(F[j][i]) {
                        min = (j == 0) ? 0 : Math.min(min, cutNum[j-1] + 1);
                    }
                }
                cutNum[i] = min;
            }
            return cutNum[n-1];
        }

    public static void main(String[] args) {
            Palindrome palindrome = new Palindrome();
            String s = "adabdcaebdcebdcacaaaadbbcadabcbeabaadcbcaaddebdbddcbda" +
                    "cdbbaedbdaaecabdceddccbdeeddccdaabbabbdedaaabcdadbdabeacbeadba" +
                    "ddcbaacdbabcccbaceedbcccedbeecbccaecadccbdbdccbcbaacccbddcccbaedbacd" +
                    "bcaccdcaadcbaebebcceabbdcdeaabdbabadeaaaaedbdbcebcbddebccacacddebecabcc" +
                    "bbdcbecbaeedcdacdcbdbebbacddddaabaedabbaaabaddcdaadcccdeebcabacdadbaacdcc" +
                    "beceddeebbbdbaaaaabaeecccaebdeabddacbedededebdebabdbcbdcbadbeeceecdcdbbdcbd" +
                    "beeebcdcabdeeacabdeaedebbcaacdadaecbccbededceceabdcabdeabbcdecdedadcaebaababee" +
                    "dcaacdbdacbccdbcecefgeergdfdsfdsssssssssssdfewfdwwwwwwwwwwwgewabreacceaccada" +
                    "bdcaebdcebdcacaaaadbbcadabcbeabaadcbcaaddebdbddcbda" +
                    "cdbbaedbdaaecabdceddccbdeeddccdaabbabbdedaaabcdadbdabeacbeadba" +
                    "ddcbaacdbabcccbaceedbcccedbeecbccaecadccbdbdccbcbaacccbddcccbaedbacd" +
                    "bcaccdcaadcbaebebcceabbdcdeaabdbabadeaaaaedbdbcebcbddebccacacddebecabcc" +
                    "bbdcbecbaeedcdacdcbdbebbacddddaabaedabbaaabaddcdaadcccdeebcabacdadbaacdcc" +
                    "beceddeebbbdbaaaaabaeecccaebdeabddacbedededebdebabdbcbdcbadbeeceecdcdbbdcbd" +
                    "beeebcdcabdeeacabdeaedebbcaacdadaecbccbededceceabdcabdeabbcdecdedadcaebaababee" +
                    "dcaacdbdacbccdbcecefgeergdfdsf";
            System.out.println(palindrome.minCut(s));
    }
}
