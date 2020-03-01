package com.epam.brest.model;

import java.math.BigDecimal;


public class PriceArgument {
    BigDecimal smallestValue;
    BigDecimal biggestValue;
    BigDecimal cost;

    public PriceArgument(String smallestValue, String biggestValue, String cost) {
        this.smallestValue = new BigDecimal(smallestValue);
        this.biggestValue = new BigDecimal(biggestValue);
        this.cost = new BigDecimal(cost);
    }

    public static PriceArgument fromLine(String line) {
        String[] argument = line.split(";");
        return new PriceArgument(argument[0], argument[1], argument[2]);
    }

    public BigDecimal getSmallestValue() {
        return smallestValue;
    }

    public BigDecimal getBiggestValue() {
        return biggestValue;
    }

    public BigDecimal getCost() {
        return cost;
    }
}
