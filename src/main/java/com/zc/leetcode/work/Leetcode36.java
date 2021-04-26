package com.zc.leetcode.work;

public class Leetcode36 {
    public static void main(String[] args) {
            System.out.println(3&2);
    }

    public static boolean isValidSudoku(char[][] board) {
        for (int x=0;x<9;x++){
            int  xn=0;//横向缓存   987654321 9位按位记录数字情况
            int  yn=0;//纵向缓存
            for(int y=0;y<9;y++){
                if(board[x][y]!='.'){
                    int n1=1<<(board[x][y]-'1');
                    if((yn&n1)==n1){
                        return false;
                    }else{
                        yn+=n1;
                    }
                }
                if(board[y][x]!='.'){
                    int n2=1<<(board[y][x]-'1');
                    if((xn&n2)==n2){
                        return false;
                    }else{
                        xn+=n2;
                    }
                }

            }
        }
        for(int x =0;x<3;x++){
            for (int y=0;y<3;y++){
                int nn=0;//当前3*3缓存
                for(int a=x*3;a<x*3+3;a++){
                    for(int b=y*3;b<y*3+3;b++){
                        if (board[a][b]!='.'){
                            int n =1<<(board[a][b]-'1');
                            if((nn&n)==n){
                                return false;
                            }else{
                                nn+=n;
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}
