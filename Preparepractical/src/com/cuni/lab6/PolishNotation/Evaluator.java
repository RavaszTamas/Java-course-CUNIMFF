package com.cuni.lab6.PolishNotation;

import java.util.Stack;

public class Evaluator {

    static long evaluate(String expression)
    {
        Stack<Integer> st = new Stack<>();
        int val;
        for(int i = 0; i < expression.length();i++)
        {
            if(Character.isDigit(expression.charAt(i))) {
                val = 0;
                while (Character.isDigit(expression.charAt(i))) {
                    val = val*10 + Integer.parseInt(expression.charAt(i)+"");
                    i++;
                }
                st.push(val);
            }
            else {
                int val1 = st.pop();
                int val2 = st.pop();
                switch (expression.charAt(i))
                {
                    case '+':
                        st.push(val1+val2);
                        break;
                    case '-':
                        st.push(val1-val2);
                        break;
                    case '*':
                        st.push(val1*val2);
                        break;
                    case '/':
                        st.push(val1/val2);
                        break;

                }
                i++;
            }
        }
        return st.pop();
    }
}
