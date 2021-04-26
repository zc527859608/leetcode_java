package com.zc.leetcode.work;

public class Leetcode35 {
    public static void main(String[] args) {

    }

    public static int searchInsert(int[] nums, int target) {
        int left =0;
        int right = nums.length-1;

        if (target<=nums[0]){//小于等于最小值，插入0
            return 0;
        }else if (target==nums[right]){//=最大，插入原最后一位
            return right;
        }else if (target>nums[right]){//>最大，插入最后面
            return right+1;
        }

        int middle =0;
        while (left+1<right){//标准二分
            middle=(right+left)/2;
            if(nums[middle]>target){
                right=middle;
            }else if(nums[middle]<target){
                left=middle;
            }else{
                return middle;
            }
        }
        return right;//无匹配应当插入当前right位
    }

}
