package utils;

import java.util.Arrays;
import java.util.Scanner;

public class Fraction {

    //求两个数的最大公约数
    public static int getGongYueShu(int a, int b) {
        int t = 0;
        if(a < b){
            t = a;
            a = b;
            b = t;
        }
        int c = a % b;
        if(c == 0){
            return b;
        }else{
            return getGongYueShu(b, c);
        }
    }

    public static String doubleToFraction(String db) {
        String xiaoshu = db;
        String[] array ;
        array = xiaoshu.split("\\.");
        long a = Long.parseLong(array[0]);//获取整数部分
        long b = Long.parseLong(array[1]);//获取小数部分
        long length = array[1].length();

        char[] c = array[1].toCharArray();
        if (length > 10){
            if (c[0] == c[1] && c[1] == c[2] && c[2] == c[3] && c[3] == c[4]){
                int linshi = c[0];
                int max = getGongYueShu(linshi,9);
                return linshi / max + "/" + 9 / max;
            }else {
                return db;
            }
        }

        int FenZi = (int) (a * Math.pow(10, length) + b);
        int FenMu = (int) Math.pow(10, length);
        int MaxYueShu = getGongYueShu(FenZi, FenMu);
        int zi = FenZi / MaxYueShu;
        int mu = FenMu / MaxYueShu;
        int sang = zi/mu;
        int yu = zi % mu;
        if (yu == 0)
            return String.valueOf(zi);
        if (sang == 0)
            return zi + "/" + mu;
        else {
            int newZi = zi - (sang*mu);
            return sang + "'" + newZi + "/" + mu;
        }

    }

}
