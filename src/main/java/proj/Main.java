package proj;

import utils.*;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        Main main = new Main();
        if (args[0].contains("-n") || args[0].contains("-r")){
            if (args[0].length() == 2 || args[1].length() == 2){
                MyException myException = new MyException("请输入题目数量以及数值范围！");
                myException.printStackTrace();
            }
            int count = Integer.parseInt(args[0].substring(2));
            int range = Integer.parseInt(args[1].substring(2));
            main.productTest(count,range);
        }else {
            String yourName = "D:/jiedui/" + args[0].substring(2);
            String answersName = "D:/jiedui/" + args[1].substring(2);
            main.checkAnswers(yourName,answersName);
        }
    }

    //根据题目数和数字范围出题
    public void productTest(int count, int range) {
        Product product = new Product();
        String[] test = new String[count];      //存储最终题目字符串
        ArrayList<char[]> list = new ArrayList<>(); //存储临时题目char数组
        Double[] answers = new Double[count];   //存储临时表达式答案
        String[] ans = new String[count];       //存储最终答案
        int countNum;                           //随机产生的一个表达式中的数字个数
        int listCount = 0;                      //test中已有元素个数
        for (int i = 0; i < count; i++) {
            countNum = product.getNum(3)+1;
            listCount = list.size();
            char[] chars = product.getExpression(range, countNum);
            list.add(chars);

            //判断是否与之前的表达式重复
            char[] a = Calculate.getRePolish(chars);
            for (int j = 0; j < listCount; j++) {
                char[] b = Calculate.getRePolish(list.get(j));
                if (Judge.isRepetitive(a,b)) {
                    char[] laji = list.remove(listCount);
                    i--;
                    j = listCount;
                }
            }

            if (list.size() > listCount) {
                String str = Calculate.charToString(chars);
                test[i] = str;
                answers[i] = Calculate.getAnswer(a);
            }

        }
        IOUtils.writeTest(test,"D:/jiedui/Exercises.txt");
        for (int i = 0; i < answers.length; i++) {
            if (answers[i] == 0.0)
                ans[i] = String.valueOf(answers[i]);
            else
                ans[i] = Fraction.doubleToFraction(String.valueOf(answers[i]));
        }

        IOUtils.writeAnswers(ans,"D:/jiedui/Answers.txt");
    }

    //阅卷
    public void checkAnswers(String yourAnswers,String answers){
        File file1 = new File(yourAnswers);
        File file2 = new File(answers);
        String[] your = IOUtils.readYourText(file1);
        String[] ans = IOUtils.readText(file2);
        int[] corrects = new int[your.length];
        int[] wrongs = new int[your.length];
        int corTop = 0;
        int wrongTop = 0;
        int correct = 0;
        int wrong = 0;
        for (int i = 0; i < your.length; i++) {
            if (your[i].equals(ans[i])){
                correct = correct + 1;
                corrects[corTop++] = i+1;
            }else {
                wrong = wrong + 1;
                wrongs[wrongTop++] = i+1;
            }
        }
        String cor = "";
        String wro = "";

        if (correct == 0){
            cor = "()";
            wro = "(1,2,3,4,5,6,7,8,9,10)";
        }else if (wrong == 0){
            wro = "()";
            cor = "(1,2,3,4,5,6,7,8,9,10)";
        }else {
            for (int i = 0; i < corrects.length; i++) {
                if (corrects[i+1] == 0) {
                    cor = cor + corrects[i];
                    break;
                }
                else
                    cor = cor + corrects[i] + ",";
            }
            for (int i = 0; i < wrongs.length ; i++) {
                if (wrongs[i+1] == 0) {
                    wro = wro + wrongs[i];
                    break;
                }
                else
                    wro = wro + wrongs[i] + ",";
            }
            cor = "(" + cor + ")";
            wro = "(" + wro + ")";
        }

        String result = "Correct:" + correct + cor + "\n" + "Wrong:"+ wrong + wro;
        IOUtils.writeResults(result,"D:/jiedui/Grade.txt");
    }
}





