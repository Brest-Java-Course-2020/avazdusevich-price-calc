package com.epam.brest;

import com.epam.brest.util.DoubleConverter;
import com.epam.brest.util.MyFileWriter;

import java.io.IOException;
import java.util.Scanner;

public class PriceCalculator {

    private PriceList priceList = new PriceList();
    private final String filePath = "/home/lehansun/development/avazdusevich-price-calc/" +
            "avazdusevich-price-calc/src/main/java/com/epam/brest/sources/Results";

    public void calculatePrice() throws IOException {
        Scanner scanner = new Scanner(System.in);
        MyFileWriter writer = new MyFileWriter(filePath);

        Double[] enteredValues = new Double[2];
        String inputValue;
        int i = 0;

        do {
            switch (i) {
                case (0) :
                    System.out.println("Please, enter distance or Q for exit: ");
                    break;
                case (1) :
                    System.out.println("Please, enter weight or Q for exit: ");
                    break;
            }

            inputValue = scanner.next();

            if (isNotExitValue(inputValue)) {
                if (DoubleConverter.isCorrectDoubleValue(inputValue)) {
                    enteredValues[i] = Double.parseDouble(inputValue);
                    i++;
                }
            }

            if (i == 2) {

                Double calcResult = calculatePrice(enteredValues[0], enteredValues[1]);
                System.out.println("Price: $" + calcResult);
                writeResult(enteredValues[0], enteredValues[1], calcResult);
                System.out.println("Price: $" + calcResult);
                i = 0;
            }

        } while (isNotExitValue(inputValue));

        System.out.println("Finish!");
    }

    private static boolean isNotExitValue(String value) {
        return !value.equalsIgnoreCase("Q");
    }

    private double calculatePrice(double distance, double weight) {
        return (distance * priceList.getDistancePrice(distance)) + (weight * priceList.getLoadPrice(weight));
    }

    private void writeResult(double distance, double weight, double result) throws IOException {
        MyFileWriter writer = new MyFileWriter(filePath);
        writer.writeStrToFile("Distance-"+distance+" , costPerKm-"
                + priceList.getDistancePrice(distance) +
                " , weight-" + weight+ " , costPerKg-" + priceList.getLoadPrice(weight)
                + " , totalCost-" + result);
    }

}
