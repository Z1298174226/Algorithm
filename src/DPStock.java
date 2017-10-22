import java.util.Random;

/**
 * Created by qtfs on 2017/10/22.
 */
public class DPStock {
    public static int compute(int[] prices, int fee) {
        int len = prices.length;
        int[] buy = new int[len];
        int[] hold = new int[len];
        int[] skip = new int[len];
        int[] sell = new int[len];
        int max = 0;
        buy[0] = 0 - prices[0];
        hold[0] = 0 - prices[0];
        for(int i = 1; i < len; i++) {
            buy[i] = Math.max(sell[i - 1], skip[i - 1]) - prices[i];
            hold[i] = Math.max(hold[i - 1], buy[i - 1]);
            skip[i] = Math.max(sell[i - 1], skip[i - 1]);
            sell[i] = Math.max(hold[i - 1], buy[i - 1]) + prices[i] - fee;
        }
        max = Math.max(skip[len - 1], sell[len - 1]);
        max = Math.max(max, 0);
        return max;
    }

    public static void main(String[] args) {
        Random rand = new Random();
        int days = 10000;
        int[] prices = new int[days];
        for(int i = 0; i < days; i++) {
            prices[i] = rand.nextInt(200);
        }

        System.out.println(DPStock.compute(prices, 2));
    }
}