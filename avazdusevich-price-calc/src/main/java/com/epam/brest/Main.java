package com.epam.brest;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        PriceCalculator calculator = new PriceCalculator();
        calculator.calculatePrice();
    }

}