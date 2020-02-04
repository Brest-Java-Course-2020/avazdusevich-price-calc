package com.epam.brest.util;

public class DoubleConverter {

    public static boolean isCorrectDoubleValue(String value) {
        try {
            double enteredDoubleValue = Double.parseDouble(value);
            return enteredDoubleValue > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
