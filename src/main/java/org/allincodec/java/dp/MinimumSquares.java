package org.allincodec.java.dp;

import java.util.ArrayList;
import java.util.List;

/*
Given an integer A. Return minimum count of numbers, sum of whose squares is equal to A.
*/
public class MinimumSquares {

    public int countMinSquares(int A) {
        int[] dp = new int[A+1];
        dp[0] = 0;
        dp[1] = 1;
        for(int i=2; i <=A; i++) {
            List<Integer> perfectSquares = findPerfectSquares(i);
            int min = Integer.MAX_VALUE;
            for (int square : perfectSquares) {
                min = Math.min(dp[i - square] + 1, min);
            }
            dp[i] = min;
        }

        return dp[A];
    }

    List<Integer> findPerfectSquares(int n) {
        List<Integer> perfectSquares = new ArrayList<>();
        for(int i=1; i * i <= n; i++) {
            perfectSquares.add(i*i);
        }
        return perfectSquares;
    }

     public static void main(String[] args) {
         MinimumSquares m = new MinimumSquares();
         System.out.println(m.countMinSquares(12));
     }

}
