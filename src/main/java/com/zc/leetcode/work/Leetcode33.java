package com.zc.leetcode.work;

public class Leetcode33 {
    public static void main(String[] args) {
        System.out.println(search(new int[]{5, 1, 2, 3, 4},
                1));
    }

    public static int search(int[] nums, int target) {
        if ((nums[0] > nums[nums.length - 1] && target < nums[0] && target > nums[nums.length - 1]) ||  //在范围外的直接排除
                (nums[0] < nums[nums.length - 1] && (target < nums[0] || target > nums[nums.length - 1]))) {
            return -1;
        } else if (nums[0] == target) {
            return 0;
        } else if (nums[nums.length - 1] == target) {
            return nums.length - 1;
        }
        int left = 0;
        int right = nums.length - 1;
        int middle = 0;
        while (right > left + 1) {
            middle = (left + right) / 2;
            if (nums[middle] > target) {
                if (nums[right] < nums[left] && target < nums[0] && nums[middle] > nums[0]) {//特殊情况  456123  target 1 middle上为6
                    //首先判断 left-》right范围内上有翻转   //后续判断middle与target位于哪半段
                    left = middle;
                } else {//其他为标准的二分查找
                    right = middle;
                }
            } else if (nums[middle] < target) {
                if (nums[right] < nums[left] && target > nums[0] && nums[middle] < nums[0]) {//特殊情况 45123 target 5 middle上为1
                    //同样是先判断有翻转          //后续分别判断middle与target位于哪段
                    right = middle;
                } else {//同样是标准的二分查找
                    left = middle;
                }
            } else {
                return middle;
            }
        }
        return -1;
    }

}
