package com.zc.leetcode.work;

public class Leetcode32 {
    public static void main(String[] args) {
        System.out.println(longestValidParentheses1("(()())"));
    }

    public static int longestValidParentheses(String s) {
        int max = 0;
        int len = 0;//当前连续有效长度
        int count = 0;//计数标志
        int bre = 0;//下次循环继续节点前一位
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ')') {  //由'（'开始匹配
                continue;
            }
            bre = 0;//还原
            len = 0;
            count = 0;
            for (int x = i; x < s.length(); x++) {
                if (s.charAt(x) == '(') {
                    count++;
                } else {
                    count--;
                }
                if (count == 0) {//左右括号数量相同即有效括号
                    len = x - i + 1;//有效括号长度
                    bre = x + 1;//退出循环时应当从最长有效括号的后两位位置继续判断
                } else if (count < 0) {//当前为'）'且前面为有效括号
                    bre = x;//此时可直接跳到'）'的后一位继续判断
                    break;
                }
            }
            max = len > max ? len : max;
            i = bre > 0 ? bre : i;//判断是否需要跳过
        }
        return max;
    }

    public static int longestValidParentheses1(String s) {
        if (s.length() < 2) {
            return 0;
        }

        int[] dp = new int[s.length()];//包含到前位置的最大有效括号长度
        int max = 0;
        dp[0] = 0;
        if (s.charAt(0) == '(' && s.charAt(1) == ')') {
            dp[1] = 2;
            max = 2;
        } else {
            dp[1] = 0;
        }

        for (int i = 2; i < s.length(); i++) {
            if (s.charAt(i) == '(') {//'（'不会是有效括号的结束
                dp[i] = 0;
            } else {
                if (s.charAt(i - 1) == '(') {//与前一位直接构成有效括号
                    dp[i] = dp[i - 2] + 2;
                } else if (i - dp[i - 1] - 1 >= 0 && s.charAt(i - dp[i - 1] - 1) == '(') {//匹配是否是包含前序有效括号的有效括号
                    if (i - dp[i - 1] - 2 >= 0) {//是有效括号时  前序是否还可能有有效括号
                        dp[i] = dp[i - 1] + 2 + dp[i - dp[i - 1] - 2];
                    } else {
                        dp[i] = dp[i - 1] + 2;
                    }
                }
            }
            max = dp[i] > max ? dp[i] : max;
        }
        return max;
    }
}
