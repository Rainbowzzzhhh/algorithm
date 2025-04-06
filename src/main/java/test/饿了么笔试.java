package test;

import com.sun.org.apache.bcel.internal.generic.NEW;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author rainbow
 * @time 2025-04-05 20:42
 * @description ...
 */
public class 饿了么笔试 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());   //n台服务器
        int m = Integer.parseInt(st.nextToken());   //选择m个
        int k = Integer.parseInt(st.nextToken());   //延迟不超过k

        st = new StringTokenizer(br.readLine());
        int[] servers = new int[n];
        for (int i = 0; i < n; i++) {
            servers[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(servers);

        int right = m - 1;
        long res = 0;

        for (int left = 0; left < n; left++) {  //每次固定最左边的一个以防止重复
            while (right < n && servers[right] - servers[left] <= k) {
                right++;    //指向最右端后一位
            }
            int count = right - left;
            if (count >= m)
                res += combination(m - 1, count - 1);
        }

        System.out.println(res);
    }

    public static long combination(int k, int n) {
        k = Math.min(k, n - k); // 利用互补性质减少计算量
        long result = 1;
        for (int i = 1; i <= k; i++) {
            result = result * (n - k + i) / i;
        }
        return result;
    }
}
