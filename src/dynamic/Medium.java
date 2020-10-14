package dynamic;
import org.junit.Test;

public class Medium {
    public int findMaxForm(String[] strs, int m, int n) {
        int[] ones = new int[strs.length];
        int[] zeros = new int[strs.length];
        int len = strs.length;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < strs[i].length(); j++) {
                if (strs[i].charAt(j) == '0') {
                    zeros[i]++;
                } else {
                    ones[i]++;
                }
            }
        }

        int[][] dp  = new int[m+1][n+1];
        for (int i = 1; i < len+1; i++) {
            int one = ones[i - 1];
            int zero = zeros[i - 1];

            for (int j = m; j >= zero; j--) {
                for (int k = n; k >= one; k--) {
                    dp[j][k] = Math.max(dp[j][k], dp[j - zero][k - one] + 1);
                }
            }
        }

        return dp[m][n];
    }

//    https://leetcode-cn.com/problems/counting-bits/
    public int[] countBits(int num) {
        int[] ans = new int[num+1];
        for (int i = 1; i <= num; i++) {
            int n = i;
            while (n != 0) {
                n = n & (n-1);
                ans[i]++;
            }
        }
        return ans;
    }
}
