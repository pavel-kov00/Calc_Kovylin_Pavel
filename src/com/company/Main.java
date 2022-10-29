package com.company;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Main {

    public static void main(String[] args) {
        String arith;
        String aRim, bRim, operator;
        int aInt, bInt;

        try {
            Scanner in = new Scanner(System.in);

            if (in.hasNextInt()) {
                // проверка  операнда и всей подстроки за ним , на  правильность ввода / на наличие двух и более операторов
                String lastInput = in.nextLine();
                String[] subStr;
                subStr = lastInput.split("\\s");
                aInt = Integer.parseInt(subStr[0]);
                operator = subStr[1];
                bInt = Integer.parseInt(subStr[2]);
//                System.out.println("aint = " + aInt + " bint= " + bInt + "oper=" + operator);
                if (subStr.length >= 4)
                    throw new ScannerException("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
                // -------------------------------
                if ((aInt <= 10 && aInt >= -10) && (bInt <= 10 && bInt >= -10)) {
                    MathOperations math = new MathOperations(aInt, bInt, operator);
                    System.out.println(math.calc());
                } else throw new ScannerException("Калькулятор должен принимать на вход числа от 1 до 10 включительно");
            } else {

                if (in.hasNextInt()) {
                    throw new InputMismatchException();
                }

                // проверка операнда и всей подстроки за ним , на  правильность ввода / на наличие двух и более операторов
                String lastInput = in.nextLine();
                String[] subStr;
                subStr = lastInput.split("\\s");
                if (subStr.length >= 4)
                    throw new ScannerException("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");

                bRim = subStr[2];
                aRim = subStr[0];

                operator = subStr[1];
                RomanNumerals romanA = RomanNumerals.valueOf(aRim);
                RomanNumerals romanB = RomanNumerals.valueOf(bRim);
                if ((romanA.getValue() <= 10 && romanA.getValue() >= 1) && (romanB.getValue() <= 10 && romanB.getValue() >= 1)) {
                    MathOperations math = new MathOperations(romanA.getValue(), romanB.getValue(), operator);
//                    System.out.println(math.calc());
                    int answerAr = math.calc();
                    if (answerAr < 1) throw new ScannerException("в римской системе нет отрицательных чисел и 0");
                    int celoe, ostatok;
                    RomanNumerals[] romanAnswer = RomanNumerals.values();
                    String answerPart1 = "", answerPart2 = "";
                    for (RomanNumerals r : romanAnswer) {
                        celoe = answerAr / 10;
                        ostatok = answerAr % 10;
                        if (r.getValue() == celoe * 10)
                            answerPart1 = r.name();
                        if (r.getValue() == ostatok)
                            answerPart2 = r.name();
                    }
                    System.out.println(answerPart1 + answerPart2);
                } else throw new IllegalArgumentException();


            }
//
            in.close();
        } catch (ScannerException e) {
            System.out.println(e.getMessage());
//            e.printStackTrace();
        } catch (InputMismatchException e) {
            System.out.println("используются одновременно разные системы счисления");
        } catch (NumberFormatException e) {
            System.out.println("используются одновременно разные системы счисления");
        } catch (IllegalArgumentException e) {
            System.out.println("Калькулятор должен принимать на вход числа от I до X включительно");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("строка не является математической операцией");
        }

    }
}
