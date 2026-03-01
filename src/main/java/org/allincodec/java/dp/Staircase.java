package org.allincodec.java.dp;

/**
 * Optimal substructure: optimal solution to actual can be constructed from optimal solutions of its subproblems.
 * Overlapping subproblems: same subproblems are solved multiple times.
 * State transition: solution to a problem can be expressed as a function of solutions to smaller problems.
 *
 */
public class Staircase {

    public int numberOfWaysToReachNthStep(int n) {
        // here for each step, number of ways depends on the number of ways to reach the previous step and the step before that
        // n  =0 -> 1 way (do nothing)
        // n = 1 -> 1 way (take 1 step)
        int[] dp = new int[n+1];
        dp[0] =0;
        dp[1] = 1;
        dp[2] = 2;

        for(int i=3; i <=n; i++) {
            dp[i]= dp[i-2] + dp[i-1];
        }
        return dp[n];
    }

    public static void main(String[] args) {
        Staircase s = new Staircase();
        System.out.println(s.numberOfWaysToReachNthStep(101));
    }
}
