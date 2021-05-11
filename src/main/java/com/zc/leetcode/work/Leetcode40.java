package com.zc.leetcode.work;

import java.util.*;

public class Leetcode40 {
    public static void main(String[] args) {
        int [] is ={10,1,2,7,6,1,5};
        System.out.println(combinationSum2(is,8).size());
    }
    //深度优先
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);//预排序

        List<List<Integer>> re =new ArrayList<>();
        List<Integer> rel =new ArrayList<>();
        for(int i=0;i<candidates.length;i++){
            if(candidates[i]>target){//排序过 后面不可能匹配
                break;
            }else if(i>0&&candidates[i]==candidates[i-1]){
                continue;
            } else if(candidates[i]==target){//与39不同，存在重复数字，仅大于才退出
                rel.add(candidates[i]);
                re.add(new ArrayList<>(rel));//同样需要值复制
                rel.remove(rel.size()-1);
                break;
            }else{
                rel.add(candidates[i]);
                combination2(candidates,re,rel,i,candidates[i], target);//注意 key应当先减后加
                rel.remove(rel.size()-1);//记得还原
            }
        }
        return re;
    }

    public static void  combination2(int[] candidates,List<List<Integer>> re,List<Integer> rel,int index,int sum,int target){
        for(int i=index+1;i<candidates.length;i++){//！！注意 可重复 应当从index处开始，不可重复则从index+1处开始
            if(i>index+1&&candidates[i]==candidates[i-1]){//同样的 重复数据跳过
                continue;
            }
            if(candidates[i]+sum>target){
                break;
            } else if(candidates[i]+sum==target){//同样无法排除重复数字，不可直接退出
                rel.add(candidates[i]);
                re.add(new ArrayList<Integer>(rel));//注意应当复制值
                rel.remove(rel.size()-1);//还原
            } else {
                rel.add(candidates[i]);
                sum += candidates[i];
                combination2(candidates, re, rel, i, sum, target);
                sum -= candidates[i];//记得还原
                rel.remove(rel.size() - 1);//记得还原
            }
        }
    }

    //广度优先
    public  List<List<Integer>> combinationSum21(int[] candidates, int target) {//广度优先写法
        Arrays.sort(candidates);//预排序
        List<List<Integer>> re =new ArrayList<>();
        Queue<kis> qd =new LinkedList<>();
        for (int i =0;i< candidates.length;i++){
            if(i>0&&candidates[i]==candidates[i-1]){//重复的跳过判断
                continue;
            }
            if(candidates[i]==target){ //相等时直接加入结果集
                List lt =new ArrayList<>();
                lt.add(candidates[i]);
                re.add(lt);
            }else if(candidates[i]>target){
                break;
            }
            kis k =new kis();//构建对象  加入队列
            k.add(candidates[i],i);
            qd.offer(k);
        }
        while (!qd.isEmpty()){//广度优先写法
            kis k=qd.poll();
            for (int i=k.index+1;i<candidates.length;i++ ){
                if(k.sum+candidates[i]>target){//过大剪枝
                    break;
                }else if(i>k.index+1&&candidates[i]==candidates[i-1]){//对于重复元素跳过判断
                    continue;
                }else if(k.sum+candidates[i]==target){//加入结果集
                    List li =k.lv;
                    li.add(candidates[i]);
                    re.add(li);
                }else{//重复迭代
                    kis ki =new kis(k);
                    ki.add(candidates[i],i);
                    qd.offer(ki);
                }
            }
        }
        return re;
    }

    class kis{
        int sum;//list求和
        int index;//最后一个元素对应的index
        List<Integer> lv;//list缓存

        kis (){
            sum=0;
            index=-1;
            lv=new ArrayList<>();
        }

        kis(kis k){
            sum=k.sum;
            index=k.index;
            lv=new ArrayList<>(k.lv);
        }
        public void add(int value,int in){
            lv.add(value);
            index=in;
            sum+=value;
        }
    }


}
