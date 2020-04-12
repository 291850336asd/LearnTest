package com.meng.datastrcture;


public class Test递归八皇后 {


    static int[][] map = new int[8][8];//表示棋盘，0表示没有皇后， 1表示放了皇后
    static int count = 1;// 结果数
    static int length = map[0].length;
    public static void main(String[] args) {
        play(0);
    }

    static void play(int row){
        for (int i = 0; i < length; i++) {
            if(checkout(row, i)){
                map[row][i] = 1;
                if(row == length-1){
                    //完成八皇后
                    show();
                    //当最后一个皇后摆好，去掉皇后继续摆放
                    map[row][i] = 0;
                }
                else {
                    play(row + 1);
                    //当调用自己结束时，去掉当前设置的皇后
                    map[row][i] = 0;

                }
            }
        }
    }

    //验证是否可以放皇后
    static boolean checkout(int row, int col){
        //上面
        for(int i = row-1; i >=0; i--){
            if(map[i][col] == 1){
                return false;
            }
        }

        //左斜上方
        for(int i = row-1,j = col-1;i>=0&& j>=0;i--,j--){
            if(map[i][j] == 1){
                return false;
            }
        }

        //右斜上方
        for(int i = row-1,j = col+1;i>=0&& j<length;i--,j++){
            if(map[i][j] == 1){
                return false;
            }
        }
        return true;
    }


    //展示棋盘
    static void show(){
        System.out.println("第" + count + "种摆法");
        count ++;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}
