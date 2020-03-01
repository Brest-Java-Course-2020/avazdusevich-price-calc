package com.epam.brest;

import com.epam.brest.model.BigDecimalPriceCalc;
import com.epam.brest.model.Calculator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext("com.epam.brest.model");

        Calculator calculator = context.getBean(BigDecimalPriceCalc.class);
        calculator.makeCalculation();
    }

}