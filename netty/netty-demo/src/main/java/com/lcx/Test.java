package com.lcx;

import java.util.Arrays;

public class Test {

    public static void main(String[] args) {

    }

    public static int minDeletionSize(String[] A) {
        // 宽度
        int W = A[0].length();
        // 动态规划
        int[] dp = new int[W];
        // 数组填充 1
        Arrays.fill(dp, 1);
        // 从后往前来遍历
        for (int i = W-2; i >= 0; --i)
            search: for (int j = i+1; j < W; ++j) {
                for (String row: A)
                    if (row.charAt(i) > row.charAt(j))
                        continue search;

                dp[i] = Math.max(dp[i], 1 + dp[j]);
            }

        int kept = 0;
        for (int x: dp)
            kept = Math.max(kept, x);
        return W - kept;

    }
    public static int repeatedNTimes(int[] nums) {
        int count =  nums.length >> 1;
        int temp[] = new int[10];
        Arrays.fill(temp,0);
        for(int i = 0;i < nums.length;i++){
            if(++temp[nums[i]] == count){
                return nums[i];
            }
        }
        return 0;
    }
    public int maxWidthRamp(int[] nums) {
        int m,n;
        int max = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            
        }
        return max;

    }
}
