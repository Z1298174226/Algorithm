import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by qtfs on 2017/10/21.
 */
class Solution {
    public static boolean canPartitionKSubsets(int[] nums, int k) {
        if(nums == null || nums.length == 0) return false;
        int sum = 0; int subSum = 0;
        for(int i = 0; i < nums.length; i++)
            sum += nums[i];
        if(sum % k != 0) return false;
        subSum = sum / k;
        int[] dp = new int[sum + 1];
        Arrays.fill(dp, -1000);
        dp[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = sum; j >= nums[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - nums[i]] + 1);
            }
        }
        int num = 0;
        for(int i = 1; i <= k; i++) {
            if(dp[i * subSum] < 0) return false;
            num += dp[i * subSum];
        }
        if(num != nums.length) return false;
        return true;
    }
    public static void main(String[] args) {
        int[] array = new int[]{4, 3, 2, 3, 5, 2, 1};
        System.out.println(Solution.canPartitionKSubsets(array, 4));
    }
}
