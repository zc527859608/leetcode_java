package com.zc.leetcode.work;

import java.util.Arrays;

public class Leetcode34 {

    public static void main(String[] args) {
        int[] nums = new int[]{5, 7, 7, 8, 8, 10};
        int off = -1;
        // System.out.println(2 + off + off << 1);
        System.out.println(Arrays.toString(searchRange(nums, 8)));
        //System.out.println(findboundary(nums, 0, 1, 0));
    }

    public static int[] searchRange(int[] nums, int target) {
        if (nums.length == 0) {
            return new int[]{-1, -1};
        }
        int left = 0;
        int right = nums.length - 1;
        int middle = -1;
        int inx = -1;
        if (nums[left] == target) {
            inx = left;
        } else if (nums[right] == target) {
            inx = right;
        } else {
            while (right > left + 1) {//二分法查找合适的数
                middle = (left + right) / 2;
                if (nums[middle] < target) {
                    left = middle;
                } else if (nums[middle] > target) {
                    right = middle;
                } else {
                    inx = middle;
                    break;
                }
            }
        }

        if (inx == -1) {
            return new int[]{-1, -1};
        }
        return new int[]{findboundary(nums, inx, -1, target), findboundary(nums, inx, 1, target)};//递归查找边界
    }

    public static int findboundary(int[] nums, int index, int off, int target) {
        if ((off == 1 && (index + 1 == nums.length || nums[index + 1] > target)) || (off == -1 && (index == 0 || nums[index - 1] < target))) {
            return index;//边界上或者下一位都不是target ，即退出条件
        }

        if (index + off < 0 || index + off >= nums.length || nums[index + off] < target || nums[index + off] > target) {
            return findboundary(nums, index, off > 0 ? 1 : -1, target);//越界或者不等于target则重置off
        } else {//标准情况下 将off*2  加快遍历速度
            return findboundary(nums, index + off, off << 1, target);
        }
    }

}

//if (off < 0) {
//        if (index + off < 0 || nums[index + off] < target) {//越界或者不等于
//        return findboundary(nums, index, -1, target);
//        } else {
//        return findboundary(nums, index + off, off << 1, target);
//        }
//        }
//        if (off > 0) {
//        if (index + off >= nums.length || nums[index + off] > target) {
//        return findboundary(nums, index, +1, target);
//        } else {
//        return findboundary(nums, index + off, off << 1, target);
//        }
//        }
//        return 0;