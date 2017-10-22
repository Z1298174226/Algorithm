import java.util.Arrays;
import java.util.Random;

/**
 * Created by qtfs on 2017/10/22.
 */
public class DPStockII {
    public static int maxProfit(int[] prices) {
        int buyFirst = Integer.MIN_VALUE;
        int sellFirst = 0;
        int buySecond = Integer.MIN_VALUE;
        int sellSecond = 0;
        for(int price : prices) {
            buyFirst = Math.max(buyFirst, -price);
            sellFirst = Math.max(sellFirst, price + buyFirst);
            buySecond = Math.max(buySecond, sellFirst - price);
            sellSecond = Math.max(sellSecond, price + buySecond);
        }
        return sellSecond;
    }
    public static int maxProfit(int k, int[] prices) {
        if(prices == null || prices.length == 0) return 0;
        if(k == 0) return 0;
        int[] buy = new int[k];
        int[] sell = new int[k];
        Arrays.fill(buy, Integer.MIN_VALUE);
        Arrays.fill(sell, 0);
        for(int price : prices) {
            buy[0] = Math.max(buy[0], -price);
            sell[0] = Math.max(sell[0], price + buy[0]);
            for(int i = 1; i < k; i++) {
                buy[i] = Math.max(buy[i], sell[i - 1] - price);
                sell[i] = Math.max(sell[i], price + buy[i]);
            }
        }
        return sell[k - 1];
    }

    public static int maxProfitt(int k, int[] prices) {
        int len = prices.length;
        if (k >= len / 2) return quickSolve(prices);

        int[][] t = new int[k + 1][len];
        for (int i = 1; i <= k; i++) {
            int tmpMax =  -prices[0];
            for (int j = 1; j < len; j++) {
                t[i][j] = Math.max(t[i][j - 1], prices[j] + tmpMax);
                tmpMax =  Math.max(tmpMax, t[i - 1][j - 1] - prices[j]);
            }
        }
        return t[k][len - 1];
    }


    private static int quickSolve(int[] prices) {
        int len = prices.length, profit = 0;
        for (int i = 1; i < len; i++)
            // as long as there is a price gap, we gain a profit.
            if (prices[i] > prices[i - 1]) profit += prices[i] - prices[i - 1];
        return profit;
    }
    public static void main(String[] args) {
        int num = 1000000;
        int[] prices = new int[num];
        Random rand = new Random();
        for(int i = 0; i < num; i++) {
            prices[i] = rand.nextInt(300);
        }
        System.out.println(maxProfit(1000, prices));
    }
}
