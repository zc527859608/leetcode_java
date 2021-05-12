package com.zc.leetcode.work;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Leetcode39 {
    public static void main(String[] args) {
        int [] is ={10,1,2,7,6,1,5};
        Leetcode40 l =new Leetcode40();
        System.out.println(l.combinationSum2(is,8).size());
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);//预排序
        List<List<Integer>> re =new ArrayList<>();
        List<Integer> rel =new ArrayList<>();
        for(int i=0;i<candidates.length;i++){
            if(candidates[i]>target){//排序过 后面不可能匹配
                break;
            }else if(candidates[i]==target){//同样的 此处为最后一个单一匹配
                rel.add(candidates[i]);
                re.add(rel);
                break;
            }
            rel.add(candidates[i]);
            combination(candidates,re,rel,i,candidates[i],target);
            rel.remove(rel.size()-1);//记得还原
        }
        return re;
    }

    public static void  combination(int[] candidates,List<List<Integer>> re,List<Integer> rel,int index,int sum,int target){
        for(int i=index;i<candidates.length;i++){//！！注意 可重复 应当从index处开始，不可重复则从index+1处开始
            if(candidates[i]+sum>target){
                break;
            }else if(candidates[i]+sum==target){
                rel.add(candidates[i]);
                re.add(new ArrayList<>(rel));//注意应当复制值
                rel.remove(rel.size()-1);//还原
                break;
            }
            rel.add(candidates[i]);
            sum+=candidates[i];
            combination(candidates,re,rel,i,sum,target);
            sum-=candidates[i];//记得还原
            rel.remove(rel.size()-1);//记得还原
        }
    }

}
