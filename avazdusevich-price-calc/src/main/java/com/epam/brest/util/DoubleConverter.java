package com.epam.brest.util;

public class DoubleConverter {

    public static boolean isCorrectDoubleValue(String value) {
        try {
            double enteredDoubleValue = Double.parseDouble(value);
            return enteredDoubleValue > 0;
        } catch (NumberFormatException ex) {
            return false;
        }
    }

    public static double convertToDouble(String value) {
        if (isCorrectDoubleValue(value)) {
            return Double.parseDouble(value);
        } else throw new IllegalArgumentException("Введено не корректное значение");
    }

}
