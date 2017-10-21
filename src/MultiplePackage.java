import java.util.Arrays;

/**
 * Created by qtfs on 2017/9/26.
 */
public class MultiplePackage {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3};
        System.out.println(MultiplePackage.multiplePackage(nums, 5));
    }

    private static int multiplePackage(int[] nums, int target) {
        int count = 0;
        int[] numbers = new int[nums.length];
        for(int i = 0; i < nums.length; i++) {
            numbers[i] = target / nums[i];
        }
        int[][] F = new int[nums.length + 1][target + 1];
        for(int i = 0; i < F.length; i++) Arrays.fill(F[i], -1);
        F[0][0] = 0;
        for(int i = 1; i < nums.length; i++) {
            for(int j = 0; j <= target; j++) {
                if(F[i-1][j] >= 0)  F[i][j] = numbers[i];
                else F[i][j] = -1;
            }
            for(int j = 0; j < target - nums[i]; j++) {
                if(F[i][j] > 0)
                    F[i][j + nums[i]] = Math.max(F[i][j + nums[i]], F[i][j] - 1);
            }
        }
        for(int i = 1; i < nums.length; i++) {
            if(F[i][target] >= 0) count++;
        }
        return count;
    }
}
