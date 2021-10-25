package utils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class IOUtils {

    //读取答题人的文件并转换成字符串数组
    public static String[] readYourText(File file) {
        StringBuilder text = new StringBuilder();
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);
            char[] cbuf = new char[5];
            int len;
            while ( (len = fileReader.read(cbuf)) != -1){
                for (int i = 0; i < len; i++) {
                    text.append(cbuf[i]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String string = text.toString();
        String[] strings = string.split("\r\n");
        return strings;
    }

    //读取答案文件并转换成字符串数组
    public static String[] readText(File file) {
        StringBuilder text = new StringBuilder();
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(file);
            char[] cbuf = new char[5];
            int len;
            while ( (len = fileReader.read(cbuf)) != -1){
                for (int i = 0; i < len; i++) {
                    text.append(cbuf[i]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        String string = text.toString();
        String[] strings = string.split("\n");
        return strings;
    }

    //写出答案文件
    public static void writeAnswers(String[] answers,String path)  {
        File file = new File(path);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            for (int i = 0; i < answers.length; i++) {
                fileWriter.write((i+1) + "." + answers[i] + " " + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //写出题目文件
    public static void writeTest(String[] test,String path)  {
        File file = new File(path);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            for (int i = 0; i < test.length; i++) {
                fileWriter.write((i+1) + "." + test[i] + "= " + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //写出结果文件
    public static void writeResults(String result,String path)  {
        File file = new File(path);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            fileWriter.write(result);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
