package proj;

import utils.Calculate;

import java.util.Random;

public class Product {

    Random rand = new Random();
    public static char[] operator = new char[]{'+','-','*','÷'};

    //生成一个随机数1~n
    public int getNum(int n){
        return rand.nextInt(n) + 1;
    }

    //生成一个运算符
    public char getOperator(){
        int i = getNum(4);
        return operator[i-1];
    }

    //生成一个中缀表达式(已判断是否有负值 和 是否有假分数)
    public  char[] getExpression(int range,int countNum){
        int countChar = 2*countNum-1;
        String[] exp = new String[countChar];
        String a;   //临时值
        int n = 0;  //char字符个数
        exp[0] = String.valueOf(getNum(range));
        for (int i = 1; i < exp.length-1; i+=2) {

            exp[i] = String.valueOf(getOperator());
            exp[i+1] = String.valueOf(getNum(range));
            //若出现假分数则调换分子分母
            if (exp[i].equals("÷") && Integer.parseInt(exp[i-1]) > Integer.parseInt(exp[i+1])){
                a = exp[i-1];
                exp[i-1] = exp[i+1];
                exp[i+1] = a;
            }
        }
        for (int i = 0; i < exp.length; i++) {   //计算char字符个数
            n += exp[i].toCharArray().length;
        }
        char[] exp2 = new char[2*n];
        int c = 0;
        for (int i = 0; i < exp.length; i++) {  //将String数组转换成char数组
            char[] b = exp[i].toCharArray();
            for (int j = 0; j < b.length; j++) {
                exp2[c+j] = b[j];
            }
            c += b.length+1;
            exp2[c-1] = ' ';
        }
        char[] b = Calculate.getRePolish(exp2);
        if (Calculate.getAnswer(b) == -1.0)      //出现负值则重新生成一个表达式
            return getExpression(range, countNum);
        else
            return exp2;
    }



}
