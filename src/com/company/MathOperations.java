package com.company;

public class MathOperations {
    int a,b;
    int result;
    char operator;
    MathOperations(int a, int b, String operator){
        this.a = a;
        this.b = b;
        this.operator = operator.charAt(0);

    }

    int calc () throws ScannerException {

        switch (operator) {
            case '+' : result = a + b; break;
            case '-' : result = a - b; break;
            case '/' : if (b == 0) throw new ScannerException("деление на ноль запрещно"); result = a / b; break;
            case '*' : result = a * b; break;
        }

        return result;
    }
}
