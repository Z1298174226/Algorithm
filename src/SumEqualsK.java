import java.util.HashMap;

/**
 * Created by qtfs on 2017/12/18.
 */
public class SumEqualsK {
    public static int sumEqualsK(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        map.put(0, 1);
        int sum = 0;
        int result = 0;
        for(int num : nums) {
            sum += num;
            if(target != 0) sum %= target;
            Integer pre = map.get(sum);
          //  Integer pre = map.get(sum - target);
            if(pre != null) {
                result += pre;
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{12, 12, 24, 1, 11, 4, 8, 12, 36, 7, 5, 4, 6, 8, 5, 6, 9, 1, 2};
        System.out.println(sumEqualsK(nums, 12));
    }
}
