package calculator;

import java.util.*;

public class CalculatorDemo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入计算式:");
        String strs = scanner.next();
        //得到strs的后缀表达式d
        List<Object> rpn = getRpn(strs);
        System.out.println("该式的后缀表达式为: " + rpn);
        //计算后缀表达式
        Double ans = calculate(rpn);
        System.out.println("计算结果为: " + ans);
    }

    public static List<Object> getRpn(String strs) {
        Stack<Character> op = new Stack<>();    //符号栈
        Stack res = new Stack();      //结果栈
        op.push('#');
        for (int i = 0; i <= strs.length(); i++) {
             if (i == strs.length()) {
                while (op.peek() != '#') {
                    char op1 = op.pop();
                    res.push(op1);
                }
                break;
            }
            char temp = strs.charAt(i);
            if (Character.isDigit(temp)) {  //是数字就找出完整数并入栈
                double ans = 0;
                for (int j = i; j < strs.length(); j++) {
                    if (!Character.isDigit(strs.charAt(j))) {
                        i = j - 1;     //向前退1
                        break;
                    }
                    int num = strs.charAt(j) - '0';
                    ans = ans * 10 + num;
                }
                res.push(ans);
            } else if (temp == '(') {
                op.push(temp);
                //System.out.println(op);
            } else if (temp == ')') {
                while (op.peek() != '(') {
                    char op1 = op.pop();
                    res.push(op1);
                }
                op.pop();
            } else if (temp == '+' || temp == '-' || temp == '*' || temp == '/') {
                if (temp == '*' || temp == '/') {
                    op.push(temp);
                } else if ((temp == '+' || temp == '-') && (op.peek() == '*' || op.peek() == '/')) {
                    while(op.peek() == '*' || op.peek() == '/') {
                        res.push(op.pop());
                    }
                    op.push(temp);
                }else{
                    op.push(temp);
                }
            }else{
                throw new RuntimeException("你输入的表达式不正确！");
            }
        }
        List<Object> list = new LinkedList<>();
        while(!res.isEmpty()){
            list.add(res.pop());
        }
        Collections.reverse(list);
        return list;
    }

    public static Double calculate(List<Object> list){
        Stack stk = new Stack();
        for(int i = 0; i < list.size(); i++) {
            Object temp = list.get(i);
            if(temp instanceof Double){
                stk.push(temp);
            }else{
                char temp1 = (Character)temp;
                Double num1 = (Double) stk.pop();
                Double num2 = (Double) stk.pop();
                if(temp1 == '+') stk.push(num1 + num2);
                else if(temp1 == '-') stk.push(num2 - num1);
                else if(temp1 == '*') stk.push(num1 * num2);
                else if(temp1 == '/') stk.push(num2 * 1.0 / num1);
            }
        }
        return (Double) stk.peek();
    }
}
