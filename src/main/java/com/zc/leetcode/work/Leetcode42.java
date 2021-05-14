package com.zc.leetcode.work;

import java.util.ArrayList;
import java.util.List;

public class Leetcode42 {
    public static void main(String[] args) {

    }

    public int trap(int[] height) {
        int left =-1;
        int right =-1;
        int sum=0;
        int orSum =0;
        int flag =-1;
        List<Integer> tops =new ArrayList<>();
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

        
        
        return sum;
    }
}
