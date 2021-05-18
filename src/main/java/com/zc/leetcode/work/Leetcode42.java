package com.zc.leetcode.work;

import java.util.ArrayList;
import java.util.List;

public class Leetcode42 {
    public static void main(String[] args) {
        int [] is = {};
        System.out.println(trap(is));
    }

    public static int trap(int[] height) {
        if(height.length<2){
            return 0;
        }
        int sum=0;
        int flag =-1; //0 递减 1  递增
        List<Integer> tops =new ArrayList<>();//存放峰顶的index
        for(int i=1;i<height.length;i++){
            if(height[i]<height[i-1]){
                    if(flag!=0) {
                        tops.add(i-1);
                    }
                    flag=0;
                }else if(height[i]>height[i-1]){
                flag=1;
                }
        }
        if (flag==1){
            tops.add(height.length-1);
        }
        if(tops.size()<2){
            return 0;
        }
        int l=0;
        int r=-1;
        while(l!=tops.size()-1){//向后遍历 按顺序山峰应当跟下一个比他高的山峰或者剩余区域的最高峰构成存水区域
            int max =l+1;
            for(int i=l+1;i<tops.size();i++){
                if(height[tops.get(i)]>height[tops.get(l)]){
                 r=i;
                 break;
                }
                max =height[tops.get(i)]>height[tops.get(max)]?i:max;
            }
            if(r!=-1){//与下一个更高峰构成储水区域
                sum+=sumRange(tops.get(l),tops.get(r),height);
                l=r;
            }else{//与剩余区域的最高峰构成储水区域
                sum+=sumRange(tops.get(l),tops.get(max),height);
                l=max;
            }
            r=-1;
        }
        return sum;
    }

    public static int sumRange(int left,int right,int []height){
        int sum =0;
        int h =height[left]<height[right]?height[left]:height[right];//水面高度
        for (int i=left;i<right;i++){
            sum=sum+(height[i]<h?h-height[i]:0);
        }
        return sum;
    }
}
