package DynamicProgramming;

import java.util.ArrayList;

/**
 * @author rainbow
 * @time 2025-02-12 14:51
 * @description ...
 */
public class LeekCode343 {
    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        dp[2] = 1;
        for (int i = 3; i <= n ; i++) {
            for (int j = 1; j < i-j; j++){
                dp[i] = Math.max(dp[i],Math.max(j*(i-j),j*dp[i-j]));
            }
        }
        return dp[n];
    }

}
