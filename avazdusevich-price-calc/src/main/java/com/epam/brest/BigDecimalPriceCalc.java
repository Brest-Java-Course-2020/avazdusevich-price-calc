package com.epam.brest;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class BigDecimalPriceCalc implements Calculator{

    private BigDecimal distance;
    private BigDecimal weight;
    private PriceList priceList = new PriceList();

    private final static String EXIT_VALUE = "Q";

    private final static String ENTER_DISTANCE_MESSAGE = "Please, enter distance or Q for exit: ";
    private final static String ENTER_WEIGHT_MESSAGE = "Please, enter weight or Q for exit: ";
    private final static String EXCEPTION_MESSAGE = "Something went wrong!";
    private final static String INVALID_VALUE_MESSAGE = "Invalid value entered.";

    private final static int DISTANCE = 0;
    private final static int WEIGHT = 1;
    private final static int FINISH = 2;

    private int iterator = 0;

    @Override
    public void makeCalculation() {
        Scanner scanner = new Scanner(System.in);
        String enteredValue = "";

        while (isNotExitValue(enteredValue)) {
            dataEntryRequest(iterator);
            enteredValue = scanner.next();
            fillUpEnteredValues(enteredValue);
            finishCalculation();
        }

        System.out.println("_____________________________________\nAll calculations done.");
    }

    private boolean isNotExitValue(String value) {
        return !value.equalsIgnoreCase(EXIT_VALUE);
    }

    private boolean isCorrectValue(String value) {
        try {
            BigDecimal enteredValue = new BigDecimal(value);
            return enteredValue.compareTo(BigDecimal.valueOf(0)) > 0;
        } catch (NumberFormatException e) {
            System.out.println(INVALID_VALUE_MESSAGE);
            return false;
        }
    }

    private void dataEntryRequest(int requestNumber) {
        switch (requestNumber) {
            case (DISTANCE) :
                System.out.println(ENTER_DISTANCE_MESSAGE);
                break;
            case (WEIGHT) :
                System.out.println(ENTER_WEIGHT_MESSAGE);
                break;
            default:
                System.out.println(EXCEPTION_MESSAGE);
        }
    }

    private void fillUpEnteredValues(String value) {
        if (isNotExitValue(value)) {
            if (isCorrectValue(value)){
                if (iterator==DISTANCE) {
                    distance = new BigDecimal(value);
                }
                if (iterator==WEIGHT) {
                    weight = new BigDecimal(value);
                }
                iterator++;
            } else {
                System.out.println(INVALID_VALUE_MESSAGE);
            }
        }
    }

    private void finishCalculation() {
        if (iterator==FINISH) {
            BigDecimal result = getResult().setScale(2, RoundingMode.HALF_UP);
            System.out.println("\nPrise: $" + result);
            System.out.println("_____________________________________\n");
            iterator=0;
        }
    }

    private BigDecimal getResult() {
        BigDecimal distancePrice = distance.multiply(priceList.getDistancePrice(distance));
        BigDecimal weightPrice = weight.multiply(priceList.getWeightPrice(weight));
        return distancePrice.add(weightPrice);
    }

}
