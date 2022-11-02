package com.company;

import java.io.IOException;
import java.text.Format;
import java.text.NumberFormat;
import java.text.ParseException;
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
                System.out.println(calc(lastInput));
            } else {

                if (in.hasNextInt()) {
                    throw new InputMismatchException();
                }
                String lastInput = in.nextLine();
                System.out.println(calc(lastInput));
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
            System.out.println(e.getStackTrace());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("строка не является математической операцией");
        }
    }

    public static String calc(String inString) throws ScannerException, NumberFormatException, IllegalArgumentException {
        String arith;
        String aRim, bRim, operator;
        int aInt, bInt;
        String[] subStr;
        subStr = inString.split("\\s");
        if (subStr.length >= 4)
            throw new ScannerException("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        else {

            if (!subStr[0].matches("[A-Z].") && !subStr[0].matches("[A-Z]")) {       //"[-+]?[\\d]|[-+]?[10]"
//                System.out.println(subStr[0].matches("[A-Z]"));
                aInt = Integer.parseInt(subStr[0]);
                operator = subStr[1];
                bInt = Integer.parseInt(subStr[2]);
                if ((aInt < 11 && aInt > -11) && (bInt < 11 && bInt > -11)) {
                    MathOperations math = new MathOperations(aInt, bInt, operator);
                    Integer answer = math.calc();
                    // System.out.println(math.calc());
                    return answer.toString();
                } else {
                    throw new ScannerException("Калькулятор должен принимать на вход числа от 1 до 10 включительно");
                }
            } else {
//                System.out.println(subStr[2]);
                bRim = subStr[2];
                if (!subStr[2].matches("[A-Z].") && !subStr[2].matches("[A-Z]"))
                    throw new ScannerException("используются одновременно разные системы счисления");
//                System.out.println(subStr[0] + " " + subStr[2]);
                aRim = subStr[0];
                operator = subStr[1];
                RomanNumerals romanA = RomanNumerals.valueOf(aRim);
                RomanNumerals romanB = RomanNumerals.valueOf(bRim);
                if ((romanA.getValue() <= 10 && romanA.getValue() >= 1) && (romanB.getValue() <= 10 && romanB.getValue() >= 1)) {
                    MathOperations math = new MathOperations(romanA.getValue(), romanB.getValue(), operator);

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
                    return answerPart1 + answerPart2;
                } else throw new IllegalArgumentException();
            }

        }

    }
}
