package com.epam.brest;

import com.epam.brest.util.DoubleConverter;
import com.epam.brest.util.MyFileWriter;

import java.io.IOException;
import java.util.Scanner;

public class PriceCalculator {

    private PriceList priceList = new PriceList();

    public void calculatePrice() throws IOException {
        Scanner scanner = new Scanner(System.in);
        MyFileWriter writer = new MyFileWriter("/home/lehansun/development/avazdusevich-price-calc/" +
                "avazdusevich-price-calc/src/main/java/com/epam/brest/sources/Results");

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
                Double costPerDistance = (enteredValues[0] * priceList.getDistancePrice(enteredValues[0]));
                Double costPerWeight = (enteredValues[1] * priceList.getLoadPrice(enteredValues[1]));
                Double calcResult = costPerDistance + costPerWeight;

                System.out.println("Price: $" + calcResult);

                writer.writeStrToFile("Distance-"+enteredValues[0]+" , costPerKm-"
                        + priceList.getDistancePrice(enteredValues[0]) +
                        " , weight-" + enteredValues[1]+ " , costPerKg-" + priceList.getLoadPrice(enteredValues[1])
                        + " , totalCost-" + calcResult
                );

                i = 0;
            }

        } while (isNotExitValue(inputValue));

        System.out.println("Finish!");
    }

    private static boolean isNotExitValue(String value) {
        return !value.equalsIgnoreCase("Q");
    }

}
