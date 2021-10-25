package utils;

import java.util.Arrays;

public class Judge {

    //判断是否重复,传入两个逆波兰式
    public static boolean isRepetitive(char[] xin,char[] old){

        if (Calculate.getAnswer(xin) != Calculate.getAnswer(old)) {//若答案不一致则不重复

            return false;
        }
        if (xin.length != old.length) { //两个数组长度不一致则不重复

            return false;
        }


        String[] xinNum = new String[4];
        String[] xinOpe = new String[3];
        String[] oldNum = new String[4];
        String[] oldOpe = new String[3];
        int xnTop = 0; int xoTop = 0;
        int onTop = 0; int ooTop = 0;
        String str = "";

        //把新逆波兰式中的数字和符号分开
        for (int i = 0; i < xin.length; i++) {
            if (xin[i] == ' ' || xin[i] == '\u0000')
                continue;
            if (Calculate.isOperator(xin[i])){
                xinOpe[xoTop++] = String.valueOf(xin[i]);
            }else {
                while (xin[i] != ' ') {
                    if (xin[i+1] == ' '){
                        str = str + String.valueOf(xin[i]);
                        break;
                    }else {
                        str = str + String.valueOf(xin[i]);
                        i++;
                    }
                }
                xinNum[xnTop++] = str;
                str = "";
            }

        }

        //把原有的逆波兰式中的数字和符号分开
        for (int i = 0; i < old.length; i++) {
            if (old[i] == ' ' || old[i] == '\u0000')
                continue;
            if (Calculate.isOperator(old[i])){
                oldOpe[ooTop++] = String.valueOf(old[i]);
            }else {
                while (old[i] != ' '){
                    if (old[i+1] == ' '){
                        str = str + String.valueOf(old[i]);
                        break;
                    }else {
                        str = str + String.valueOf(old[i]);
                        i++;
                    }
                }
                oldNum[onTop++] = str;
                str = "";
            }
        }

        if (getLength(xinNum) != getLength(oldNum) || getLength(xinOpe) != getLength(oldOpe))
            return false;

        //对数组进行排序
        String[] xinNum1 = arraySort(jianhua(xinNum));
        String[] xinOpe1 = arraySort(jianhua(xinOpe));
        String[] oldNum1 = arraySort(jianhua(oldNum));
        String[] oldOpe1 = arraySort(jianhua(oldOpe));



        //判断数字和操作符是否都一致
        if (Arrays.equals(xinNum1, oldNum1) && Arrays.equals(xinOpe1,oldOpe1))
            return true;
        else
            return false;
    }

    //计算数组非空的长度
    public static int getLength(String[] str){
        int length = 0;
        for (int i = 0; i < str.length; i++) {
            if (str[i] != null){
                length = length + 1;
            }
        }
        return length;
    }

    //简化数组
    public static String[] jianhua(String[] strings){
        int length = getLength(strings);
        String[] out = new String[length];
        for (int i = 0; i < length; i++) {
            out[i] = strings[i];
        }
        return out;
    }

    //字符串数组排序
    public static String[] arraySort(String[] input){
        for (int i=0;i<input.length-1;i++){
            for (int j=0;j<input.length-i-1;j++) {
                if(input[j].compareTo(input[j+1])>0){
                    String temp=input[j];
                    input[j]=input[j+1];
                    input[j+1]=temp;
                }
            }
        }
        return input;
    }

}
