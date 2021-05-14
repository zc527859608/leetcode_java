package com.zc.leetcode.work;

import java.util.LinkedList;

public class Leetcode41 {
    public static void main(String[] args) {
        int[] is = new int[]{1,2,0};
        System.out.println(firstMissingPositive2(is));
    }

    //双向链表记录所有未遍历的数字 起点即llist[0][1]
    public static int firstMissingPositive(int[] nums) {
        int[][] llist = new int[nums.length + 2][2];//llist[0]相当于root节点，root下一位指向最小未包含正整数，llist[len-1]对应极限情况 1，2，3，4
        for (int i : nums) {//缓存前后未遍历到的数 [x][0]=0则前为前一位 [x][1]=则后为后一位
            if (i < 1 || i > nums.length) continue;//当当前数字大于nums.length时  可以确定差值必然在前面产生
            if (llist[i][0] == -1) continue;//重复数字跳过
            //更新前序节点
            llist[llist[i][0] == 0 && llist[i - 1][0] != -1 ? i - 1 : llist[i][0]][1] = llist[i][1] == 0 ? i + 1 : llist[i][1];//对于[x][0]=0情况需要区分是仅初始化还是的确指向root节点
//            if (llist[i][0] == 0 && llist[i - 1][0] != -1) {
//                llist[i - 1][1] = llist[i][1] == 0 ? i + 1 : llist[i][1];
//            } else {
//                llist[llist[i][0]][1] = llist[i][1] == 0 ? i + 1 : llist[i][1];
//            }
            //更新后续节点
            llist[llist[i][1] == 0 ? i + 1 : llist[i][1]][0] = llist[i][0] == 0 && llist[i - 1][0] != -1 ? i - 1 : llist[i][0];//[x][1]=0情况仅需区分初始化
//            if (llist[i][1] == 0) {
//                llist[i + 1][0] = llist[i][0] == 0 && llist[i - 1][0] != -1 ? i - 1 : llist[i][0];
//            } else {
//                llist[llist[i][1]][0] = llist[i][0] == 0 && llist[i - 1][0] != -1 ? i - 1 : llist[i][0];
//            }
            llist[i][0] = -1;//避免重复处理
        }
        return llist[0][1] == 0 ? 1 : llist[0][1];
    }
    //缓存法
    public static int firstMissingPositive1(int[] nums) {
        int [] data=new int[nums.length+1];  //差的值最大为nums.len+1  例1 2 3  为4
        for (int i:nums){
            if(i>0&&i<=nums.length){
                data[i-1]=1;
            }
        }
        for(int i=0;i< data.length;i++){
            if(data[i]==0){
                return i+1;
            }
        }
        return -1;
    }
    //自相缓存法，将大于0的数x放到num[x-1]处
    public static int firstMissingPositive2(int[] nums) {
        int rindex=0;//指向未缓存的最小数字
        for(int i=0;i<nums.length;i++){
            if(nums[i]>0&&nums[i]<=nums.length){
                int x=nums[i];
                if(nums[i]!=i+1&&nums[nums[i]-1]!=nums[i]){//仅当不在对应位置或对应位置上已有对应数字
                    nums[i]=nums[nums[i]-1];//将当前数字x与index为x-1处的数字互换
                    nums[x-1]=x;
                    i--;
                }
                if(rindex==x-1){//是否是对应数字
                    for(int m=rindex+1;m<=nums.length;m++){//向后找第一个未存放对应数字的位置  注意存在  为 1,2,3,4的情况，需要比遍历多一位
                        if(m==nums.length)return m+1;//已遍历所有，则需提前返回当前index+1的值
                        if(nums[m]!=m+1){
                            rindex=m;
                            break;
                        }
                    }
                }
            }
        }
        return rindex+1;//
    }
}
