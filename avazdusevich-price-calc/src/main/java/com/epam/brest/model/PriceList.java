package com.epam.brest.model;
import com.epam.brest.util.MyFileReader;

import java.math.BigDecimal;
import java.util.ArrayList;

public class PriceList {
    private static ArrayList<PriceArgument> distanceArguments = new ArrayList<>();
    private static ArrayList<PriceArgument> weightArguments = new ArrayList<>();

    static {
        MyFileReader reader = new MyFileReader("src/main/java/com/epam/brest/resources/Price per KM");
        ArrayList<String> lines = reader.readFromFile();
        for (String argument : lines) {
            distanceArguments.add(PriceArgument.fromLine(argument));
        }

        reader = new MyFileReader("src/main/java/com/epam/brest/resources/Price per KG");
        lines = reader.readFromFile();
        for (String argument : lines) {
            weightArguments.add(PriceArgument.fromLine(argument));
        }
    }


    public BigDecimal getDistancePrice(BigDecimal distance) {
        for (PriceArgument argument : distanceArguments) {
            if (distance.compareTo(argument.getSmallestValue()) > 0
                && distance.compareTo(argument.getBiggestValue()) <= 0) {
                return argument.getCost();
            }
        }
        throw new IllegalArgumentException("Distance limit exceeded.");
    }

    public BigDecimal getWeightPrice(BigDecimal weight) {
        for (PriceArgument argument : weightArguments) {
            if (weight.compareTo(argument.getSmallestValue()) > 0
                    && weight.compareTo(argument.getBiggestValue()) <= 0) {
                return argument.getCost();
            }
        }
        throw new IllegalArgumentException("Weight limit exceeded.");
    }
}
