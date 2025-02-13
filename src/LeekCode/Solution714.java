package LeekCode;

class Solution714 {
    public int maxProfit(int[] prices, int fee) {
        int n = prices.length;
        int[][] profits = new int[n][2];

        profits[0][0] = 0;
        profits[0][1] = -prices[0];//第一天买入不出手，不减fee
        for (int i = 1; i < prices.length; i++) {
            //表示第i + 1天交易完后，手里没有股票的最大收益
            profits[i][0] = Math.max(profits[i - 1][0], prices[i] + profits[i - 1][1] - fee);
            //表示第i + 1天交易完后，手里还有股票的最大收益
            profits[i][1] = Math.max(profits[i - 1][0] - prices[i], profits[i - 1][1]);
        }
        return profits[n - 1][0];
    }
}