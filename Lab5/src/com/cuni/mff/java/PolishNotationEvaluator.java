package com.cuni.mff.java;

import java.util.Arrays;
import java.util.Stack;

public class PolishNotationEvaluator {

    interface Operation
    {
        double apply(double x, double y);
    }
    Stack<Double> numbers;

    double calculate(String input) {
        numbers = new Stack<>();
        Arrays.asList(input.split(" ")).stream().forEach(number -> {
            switch (number) {
                case "+":
                    calculateOperand(numbers,( x,  y) -> x + y);
                    break;
                case "-":
                    calculateOperand(numbers,( x,  y) -> x - y);
                    break;
                case "*":
                    calculateOperand(numbers,( x,  y) -> x * y);
                    break;
                case "/":
                    calculateOperand(numbers,( x,  y) -> x / y);
                    break;
                default:
                    numbers.push(Double.parseDouble(number));

            }
        });
        return numbers.pop();
    }
    void calculateOperand(Stack<Double> numbers, Operation oper)
    {
        numbers.push(oper.apply(numbers.pop(),numbers.pop()));
    }
}