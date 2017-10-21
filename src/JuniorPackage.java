import java.util.Arrays;

/**
 * Created by qtfs on 2017/9/26.
 */
public class JuniorPackage {

    public static void main(String[] args) {
        int[] nums = {2, 3, 5, 7, 11};
        System.out.println(JuniorPackage.combination(nums, 17638274));
    }
    public static int combination(int[] nums, int target) {
        int[] F = new int[target+ 1];
        Arrays.fill(F, 10000000);
        F[0] = 0;
        for(int i = 0; i < nums.length; i++) {
           // for(int j = target; j >= nums[i]; j--) {
            for(int j = nums[i]; j <= target; j++) {
                F[j] = Math.min(F[j], F[j - nums[i]] + 1);
            }

        }
        return F[target];
    }
}
