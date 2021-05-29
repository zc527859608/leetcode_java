package com.zc.leetcode.work;

public class Leetcode45 {
    public static void main(String[] args) {
        int []is={2,3,1,1,4};
        System.out.println(jump1(is));
    }

    public static int jump(int[] nums) {
        if (nums.length<2){
            return 0;
        }
        int dp[] =new int[nums.length];
        dp[0]=0;
        dp[1]=1;
        for(int i=2;i<dp.length;i++){
            int min=dp[i-1]+1;
            for(int x=i-2;x>=0;x--){
                if(nums[x]<i-x){
                    continue;
                }else {
                    min=min<dp[x]+1?min:dp[x]+1;
                }
            }
            dp[i]=min;
        }
        return dp[dp.length-1];
    }

    public static int jump1(int[] nums) {
        int count =0;
        int max =0;
        int nextstep=0;
        int i=0;
        while (max<nums.length-1){
            max =max>nums[i]+1?max:nums[i]+i;
            if(i==nextstep){
                max=max>i+1?max:i+1;
                count++;
                nextstep=max;
            }
            i++;
        }
        return nextstep==max?count:count+1;
    }
}
