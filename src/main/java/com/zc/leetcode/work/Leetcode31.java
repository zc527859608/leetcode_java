package com.zc.leetcode.work;

import java.util.Arrays;

public class Leetcode31 {
    public static void main(String[] args) {
    int []is={1,3,2};
    nextPermutation(is);
    for (int i:is){
        System.out.println(i);
        }

    }

    public static void nextPermutation(int[] nums) {
       int left=-1,right=-1;
        for(int i=nums.length-1;i>0;i--){
           if(nums[i-1]<nums[i]){
               left=i-1;
               for(int x=i+1;x<nums.length;x++){
                   if(nums[x]<=nums[left]){
                       break;
                   }else{
                       right=x;
                   }
               }
               if(right==-1){
                   right=i;
               }
               break;
           }
       }
        if(left==-1){
            Arrays.sort(nums);
        }else{
            nums[left]=nums[left]+nums[right];
            nums[right]=nums[left]-nums[right];
            nums[left]=nums[left]-nums[right];
        }
    }

}
