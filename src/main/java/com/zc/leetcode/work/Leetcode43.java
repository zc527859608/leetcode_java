package com.zc.leetcode.work;

import java.util.Arrays;

public class Leetcode43 {
    public static void main(String[] args) {
        System.out.println(multiply("999","999"));
    }

    public static String multiply(String num1, String num2) {
        if("0".equals(num1)||"0".equals(num2)) return "0";
        int [] n1=new int[num1.length()];
        int [] n2=new int[num2.length()];
        char[] re=new char[num1.length()+num2.length()];
        for(int i=0;i<num1.length();i++){//  123-》[3,2,1]
            n1[num1.length()-i-1]=num1.charAt(i)-'0';
        }
        for(int i=0;i<num2.length();i++){
            n2[num2.length()-i-1]=num2.charAt(i)-'0';
        }
        int target=0;//记录进位
        for(int i=0;i<re.length;i++){//index为i 对应倒数第i+1位
            //其应该由所有符合的 x+y=i+2,num1倒数第x位与num2倒数第y位的乘积之和与前面的进位共同决定
            for(int m=i;m>=0;m--){
                if(m>=n1.length)continue;
                int n=i-m;//注意换算一下  倒数第x位到index
                if(n==n2.length){
                    break;
                }
                target=target+n1[m]*n2[n];
            }

            int r=target%10;
            re[re.length-1-i]= (char) ('0'+r);//进位取最后一位
            target=target/10;//其余继续进位
        }
        return re[0]=='0'?String.valueOf(re).replaceFirst("0",""):String.valueOf(re);//处理最高位未进位
    }


}
