package com.epam.brest.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

@Component
public class BigDecimalPriceCalc implements Calculator{
    // Инициализация логера
    private static final Logger log = LoggerFactory.getLogger(BigDecimalPriceCalc.class);

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
            log.error(INVALID_VALUE_MESSAGE + " " + e.getMessage());
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
                log.info(EXCEPTION_MESSAGE);
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
            try {
                BigDecimal result = getResult().setScale(2, RoundingMode.HALF_UP);
                System.out.println("\nPrise: $" + result);
                System.out.println("_______________________________________________\n");
                iterator = 0;
                log.info("Distance = " + distance + "km; Weight = " + weight + "kg; Prise: $" + result);
            } catch (InterruptedException e) {
                log.error(e.getMessage());
                iterator = 0;
                System.out.println("Please, check out our limits and repeat input.");
                System.out.println("===============================================\n");
            }
        }
    }

    private BigDecimal getResult() throws InterruptedException {
        try {
            BigDecimal distancePrice = distance.multiply(priceList.getDistancePrice(distance));
            BigDecimal weightPrice = weight.multiply(priceList.getWeightPrice(weight));
            return distancePrice.add(weightPrice);
        } catch (IllegalArgumentException e)  {
            log.error(e.getMessage());
        }
        throw new InterruptedException("Limits exceeded. Values are out of bounds.");
    }

}
