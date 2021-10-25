package utils;

import java.util.Stack;

public class Calculate {

    //计算逆波兰式的结果
    public static double getAnswer(char[] polish){
        Stack<String> stack = new Stack<>();    //存放数字的每次计算的结果的栈
        int p = 0;
        for (int i = 0; i < polish.length; i++) {
            if (polish[i] == ' ' || polish[i] == '\u0000'){      //空格或空跳过
                continue;
            }else if (!isOperator(polish[i])){      //若是数字则入栈
                while (polish[i] != ' '){
                    p = p * 10 + Character.getNumericValue(polish[i]);
                    i++;
                }
                stack.push(String.valueOf(p));
                p = 0;

                //若是操作符则运算栈顶两个数字并将结果入栈，若过程中出现负数则直接返回-1.0
            }else if (isOperator(polish[i])){
                try {
                    String num1 = stack.pop();
                    String num2 = stack.pop();
                    String num3 = jisuan(num1,num2,polish[i]);
                    if (num3.equals("Negative"))
                        return -1.0;
                    else
                        stack.push(num3);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        String result = stack.pop();
        return Double.parseDouble(result);
    }

    //将表达式转换为逆波兰式
    public static char[] getRePolish(char exp[]){
        char[] stack = new char[10];
        int len = exp.length;
        char[] out = new char[2*len-1];
        int outlen = 0 , top = 0;
        for (int i = 0; i < len; i++) {
            if (whichOperator(exp[i]) == 0){       //是操作数则直接加入输出串
                out[outlen++] = exp[i];
                while (i + 1 < len && whichOperator(exp[i+1]) == 0){
                    out[outlen++] = exp[i+1];
                    ++i;
                }
                out[outlen++] = ' ';
            }
            if (whichOperator(exp[i]) == 2){       //是开括号则压入栈
                stack[top++] = exp[i];
            }
            while (whichOperator(exp[i]) == 1)		//如果是运算符，执行算法对应操作；
            {
                if (top == 0 || stack[top-1]=='(' || priority(exp[i]) > priority(stack[top-1])) //空栈||或者栈顶为)||新来的元素优先级更高
                {
                    stack[top++] = exp[i];
                    break;
                }
                else
                {
                    out[outlen++] = stack[top-1];
                    out[outlen++] = ' ';
                    --top;
                }
            }
            if (exp[i] == 3){       //如果是闭括号，栈中逐个输出，直到遇到开括号，将开括号丢弃
                while (stack[top] != '('){
                    if (stack[top-1] == '('){
                        top--;
                    }else {
                        out[outlen++] = stack[top-1];
                        out[outlen++] = ' ';
                        top--;
                    }
                }
            }
        }
        while (top != 0){       //将栈中剩余的输出
           out[outlen++] = stack[top-1];
           out[outlen++] = ' ';
           --top;
        }
        return out;
    }

    //判断字符是什么类型
    public static int whichOperator(char c){
        if (isOperator(c))
            return 1;   //1为操作符
        else if (c == '(')
            return 2;   //2为开括号
        else if (c == ')')
            return 3;   //3为闭括号
        else
            return 0;   //0为操作数
    }

    //设定运算符的优先级
    public static int priority(char c){
        if (c == '+' || c == '-')
            return 1;
        else if (c == '*' || c == '÷')
            return 2;
        else
            return 0;
    }

    //判断是否为操作符
    public static boolean isOperator(char c){
        if (c == '+' || c == '-' || c == '*' || c == '÷')
            return true;
        else
            return false;
    }

    //加减乘除基本运算
    public static String jisuan(String num1,String num2,char operator){
        double d1 = Double.parseDouble(num1);
        double d2 = Double.parseDouble(num2);
        double d3 = 0.0;
        String str = "";
        if (operator == '+')
            d3 = d1 + d2;
        else if (operator == '*')
            d3 = d1 * d2;
        else if (operator == '÷')
            d3 = d2 / d1;
        else {
            d3 = d2 - d1;
            if (d3 < 0)
               return "Negative";
        }
        str = String.valueOf(d3);
        return str;
    }

    //将一个char型数组转换成字符串
    public static String charToString(char[] chars){
        String str = "";
        for (int i = 0; i < chars.length; i++) {
            str = str + chars[i];
        }
        return str;
    }


}
