package com.epam.brest;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Double[] enteredValues = new Double[4];

        Scanner scanner = new Scanner(System.in);
        String inputValue;
        int i = 0;

        do {
            switch (i) {
                case (0) :
                    System.out.println("Please, enter distance or Q for exit: ");
                    break;
                case (1) :
                    System.out.println("Please, enter price per km or Q for exit: ");
                    break;
                case (2) :
                    System.out.println("Please, enter weight or Q for exit: ");
                    break;
                case (3) :
                    System.out.println("Please, enter price per kg or Q for exit: ");
                    break;
            }

            inputValue = scanner.next();

            if (isNotExitValue(inputValue)) {
                if (isCorrectDoubleValue(inputValue)) {
                    enteredValues[i] = Double.parseDouble(inputValue);
                    i++;
                }
            }

            if (i == 4) {
                Double calcResult = enteredValues[0]*enteredValues[1] + enteredValues[2]*enteredValues[3];
                System.out.println("Price: $" + calcResult);
                i = 0;
            }

        } while (isNotExitValue(inputValue));

        System.out.println("Finish!");

    }

    private static boolean isNotExitValue(String value) {
        return !value.equalsIgnoreCase("Q");
    }

    private static boolean isCorrectDoubleValue(String value) {
        boolean checkResult;
        try {
            double enteredDoubleValue = Double.parseDouble(value);
            checkResult = enteredDoubleValue > 0;
        } catch (NumberFormatException ex) {
            checkResult = false;
        }
        return checkResult;
    }


}