import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.EmptyStackException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

class InfixEvaluator
{
    HashMap<String,Double> variables = new HashMap<>();
    HashMap<String,Double> previous;
    InfixEvaluator(){variables.put("last",0.0);}

    double applyOperation(char operation, double operand2,double operand1)
    {
        switch (operation)
        {
            case '+':
                return operand1+operand2;
            case '-':
                return  operand1-operand2;
            case '*':
                return operand1*operand2;
            case '/':
            {
                if(operand2 == 0)
                    throw new UnsupportedOperationException();
                return operand1/operand2;
            }
        }
        throw new NumberFormatException();
    }

    void setToPrevious()
    {
        variables.put("last",0.0);
        variables = previous;
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    double evaluateString(String stringToEvaluate)
    {
        previous = new HashMap<>(variables);


        char[] tokens = stringToEvaluate.toCharArray();
        ///! NO IMPLEMENTATION FOR VARIABLES
        //Stack for Values
        Stack<Double> values = new Stack<Double>();

        // Stack for Operators
        Stack<Character> operators = new Stack<Character>();

        boolean variableAssign = false;
        String variableToAssign = "";



        int index = 0;
        if(!Character.isDigit(tokens[index]) && Character.isAlphabetic(tokens[index])) {
            StringBuilder variableBuild = new StringBuilder();
            while (index < tokens.length && !Character.isDigit(tokens[index]) && !Character.isWhitespace(tokens[index]) && tokens[index] != '=') {
                variableBuild.append(tokens[index++]);
            }
            variableToAssign = variableBuild.toString();
            while (index < tokens.length && Character.isWhitespace(tokens[index])){index++;}
            if(index < tokens.length && tokens[index] == '=') {
                variableAssign = true;
                index++;
            }
            else {
                if (variables.containsKey(variableToAssign)) {
                    values.push(variables.get(variableToAssign));

                } else {
                    variables.put(variableToAssign, 0.0);
                    values.push(0.0);
                }
            }

        }
        boolean unaryMinus = false;
        while (index < tokens.length) {

            if (Character.isWhitespace(tokens[index]))
            {
                index++;
                continue;
            }
            if(Character.isDigit(tokens[index])) {
                StringBuilder newNumber = new StringBuilder();
                int exponentNotation = 0;
                int doubleNotation = 0;
                while (index < tokens.length && (Character.isDigit(tokens[index]) || (tokens[index] == 'e'  || tokens[index] == '.') && exponentNotation < 2 && doubleNotation < 2))
                {
                    if (tokens[index] == 'e')
                    {
                        exponentNotation++;
                        if(tokens[index+1] == '-')
                            newNumber.append(tokens[index++]);
                        newNumber.append(tokens[index++]);
                    }
                    if (tokens[index] == '.') {
                        doubleNotation++;
                        newNumber.append(tokens[index++]);
                    }
                    else
                        newNumber.append(tokens[index++]);
                }
                index--;
                if (exponentNotation == 2 || doubleNotation == 2){
                    throw new NumberFormatException();
                }
                if(unaryMinus) {
                    values.push(-Double.parseDouble(newNumber.toString()));
                    unaryMinus = false;
                }
                else
                    values.push(Double.parseDouble(newNumber.toString()));
            }
            else if(tokens[index] == '(')
            {
                if(unaryMinus)
                {
                    values.push(-1.0);

                    while (!operators.empty() && this.hasPrecedence('*', operators.peek())) {
                        values.push(applyOperation(operators.pop(), values.pop(), values.pop()));
                    }
                    operators.push('*');
                    unaryMinus = false;

                }
                operators.push(tokens[index]);
            }
            else if(tokens[index] == ')')
            {
                while (operators.peek() != '(')
                {
                    values.push(applyOperation(operators.pop(),values.pop(),values.pop()));
                }
                operators.pop();
            }
            else if(this.isOperator(tokens[index]))
            {
                if(tokens[index] == '-') {
                    int i = index-1;
                    while (i >= 0 && Character.isWhitespace(tokens[i])) {
                        i--;
                    }
                    if (i < 0 || tokens[i] == '(') ///TODO solve unary situation as : -1 or (-1) or -(1) or -(-1) or -(1+1)
                    {
                        unaryMinus = true;
                    }

                }
                if(!unaryMinus) {
                    while (!operators.empty() && this.hasPrecedence(tokens[index], operators.peek())) {
                        values.push(applyOperation(operators.pop(), values.pop(), values.pop()));
                    }
                    operators.push(tokens[index]);
                }
            }
            else
            {
                StringBuilder variableBuild = new StringBuilder();
                while (index < tokens.length && Character.isAlphabetic(tokens[index]) && !Character.isDigit(tokens[index]) && !Character.isWhitespace(tokens[index]) && tokens[index] != '=' && !this.isOperator(tokens[index])) {
                    variableBuild.append(tokens[index++]);
                }
                index--;
                String newVariable = variableBuild.toString();
                if (variables.containsKey(newVariable)) {
                    if(unaryMinus) {
                        values.push(-variables.get(newVariable));
                        unaryMinus = false;
                    }
                    else
                        values.push(variables.get(newVariable));


                } else {
                    unaryMinus = false;
                    variables.put(newVariable, 0.0);
                    values.push(0.0);
                }

            }
            index++;
        }
        while (!operators.empty())
            values.push(applyOperation(operators.pop(), values.pop(), values.pop()));


        double returnValue = values.pop();

        if(!values.empty() || !operators.empty())
            throw new UnsupportedOperationException();

        if(variableAssign)
        {
            variables.put(variableToAssign,returnValue);
        }
        variables.put("last",returnValue);
        return returnValue;
    }

    private boolean hasPrecedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')')
            return false;
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-'))
            return false;
        else
            return true;

    }

    private boolean isOperator(char token) {
        if(token == '*' || token == '/' || token == '+'|| token == '-')
            return true;
        return false;
    }

}

public class Main {

    public static void main(String[] args) {
        InfixEvaluator eval = new InfixEvaluator();
        try(BufferedReader input = new BufferedReader(new InputStreamReader(System.in))) {
            String line;
            while ((line = input.readLine()) != null) {
                line = line.trim();
                if(line.length() == 0)
                    continue;
                try {
                    String output = String.format("%.5f",eval.evaluateString(line));
                    //output = output.replace(',','.');
                    System.out.println(output);
                }
                catch (EmptyStackException | NumberFormatException | UnsupportedOperationException e)
                {
                    eval.setToPrevious();
                    System.out.println("ERROR");
                }

            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }


    }
}
