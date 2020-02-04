package com.epam.brest;

import com.epam.brest.util.DoubleConverter;
import com.epam.brest.util.MyFileWriter;

import java.io.IOException;
import java.util.Scanner;

public class PriceCalculator {

    private PriceList priceList = new PriceList();
    private static final String ENTER_DISTANCE = "Please, enter distance or Q for exit: ";
    private static final String ENTER_WEIGHT = "Please, enter weight or Q for exit: ";
    private static final String EXCEPTION_MESSAGE = "Something went wrong!";
    private static final String QUIT_VALUE = "Q";
    private Double[] enteredValues = new Double[2];
    private int iterator = 0;
    private MyFileWriter resultWriter = new MyFileWriter("src/main/java/com/epam/brest/sources/Results");


    public void startCalculating() throws IOException {
        Scanner scanner = new Scanner(System.in);
        String inputValue = "";
        while (isNotExitValue(inputValue)){
            dataEntryRequest(iterator);
            inputValue = scanner.next();
            fillUpEnteredValues(inputValue);
            finishCalculations();
        }
        System.out.println("Finish!");
    }

    private static boolean isNotExitValue(String value) {
        return !value.equalsIgnoreCase(QUIT_VALUE);
    }

    private double calculatePrice(double distance, double weight) {
        return (distance * priceList.getDistancePrice(distance)) + (weight * priceList.getLoadPrice(weight));
    }

    private void writeResultToFile(double distance, double weight, double result) {
        MyFileWriter writer = new MyFileWriter("src/main/java/com/epam/brest/sources/Results");
        writer.writeStrToFile("Distance-"+distance+" , costPerKm-"
                + priceList.getDistancePrice(distance) +
                " , weight-" + weight+ " , costPerKg-" + priceList.getLoadPrice(weight)
                + " , totalCost-" + result);
    }

    private void dataEntryRequest(int requestNumber) {
        switch (requestNumber) {
            case (0) :
                System.out.println(ENTER_DISTANCE);
                break;
            case (1) :
                System.out.println(ENTER_WEIGHT);
                break;
            default:
                System.out.println(EXCEPTION_MESSAGE);
        }
    }

    private void fillUpEnteredValues(String value) {
        if (isNotExitValue(value)) {
            if (DoubleConverter.isCorrectDoubleValue(value)) {
                enteredValues[iterator] = Double.parseDouble(value);
                iterator++;
            }
        }
    }

    private void finishCalculations() throws IOException {
        if (iterator == 2) {
            System.out.println("Price: $" + calculatePrice(enteredValues[0], enteredValues[1]));
            writeResultToFile(enteredValues[0], enteredValues[1], calculatePrice(enteredValues[0], enteredValues[1]));
            iterator = 0;
        }
    }

}
