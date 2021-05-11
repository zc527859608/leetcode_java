package com.zc.leetcode.work;

public class Leetcode38 {
    public static void main(String[] args) {
        System.out.println(countAndSay(6));
    }

    public static String countAndSay(int n) {
        if (n==1){
            return "1";
        }
        StringBuilder rel =new StringBuilder(countAndSay(n-1));//递归获取
        StringBuilder re =new StringBuilder();
        int count =1;
        for (int i=1;i<rel.length();i++){
            if(rel.charAt(i)==rel.charAt(i-1)){
                count++;
            }else{
                re.append(count);
                re.append(rel.charAt(i-1));
                count=1;
            }
        }
        re.append(count);
        re.append(rel.charAt(rel.length()-1));

        return re.toString();
    }

    public static String countAndSay1(int n) {
        StringBuilder re =new StringBuilder("1");
        while(n>1){
            n--;
            StringBuilder rel =new StringBuilder();
            int count =1;
            for (int i=1;i<re.length();i++){
                if(re.charAt(i)==re.charAt(i-1)){
                    count++;
                }else{
                    rel.append(count);
                    rel.append(re.charAt(i-1));
                    count=1;
                }
            }
            rel.append(count);
            rel.append(re.charAt(re.length()-1));
            re=rel;
        }
        return re.toString();
    }

}
