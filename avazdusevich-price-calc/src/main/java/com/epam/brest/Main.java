package com.epam.brest;

import com.epam.brest.util.Calculator;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        Calculator calculator = new PriceCalculator();
        calculator.makeCalculations();
    }

}