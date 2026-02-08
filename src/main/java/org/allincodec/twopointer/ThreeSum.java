package org.allincodec.twopointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Triplet Sum to Zero Problem Solution
 */
public class ThreeSum {

    public static List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ans = new ArrayList<>();
        int i=0;
        while(i <  nums.length-2) {
            if(i > 0 && nums[i] == nums[i-1]) {
                i++;
                continue;
            }
            int l = i+1;
            int h = nums.length-1;
            while(l < h) {
                int sum = nums[i] + nums[l] + nums[h];
                if(sum == 0) {
                    ans.add(List.of(nums[i], nums[l], nums[h]));
                    ++l;
                    --h;
                    while(l < h && nums[l] == nums[l-1]) l++;
                    while(l < h && nums[h] == nums[h+1]) h--;
                } else if(sum < 0) {
                    l++;
                } else {
                    h--;
                }
            }
            i++;
        }
        return ans;
    }

    public static void main(String[] args) {
        System.out.println(threeSum(new int[] {-2, 0, 2, -2, 1, -1}));
    }
}
