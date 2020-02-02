package com.epam.brest;

import com.epam.brest.util.MyFileReader;

import java.util.ArrayList;

public class PriceList {

    static {
        MyFileReader reader = new MyFileReader("/home/lehansun/development/avazdusevich-price-calc/" +
                "avazdusevich-price-calc/src/main/java/com/epam/brest/sources/PriceList" +
                "");
        ArrayList<String> priceList = reader.readFromFile();
        shortDistancePrice = Double.parseDouble(priceList.get(1));
        mediumDistancePrice = Double.parseDouble(priceList.get(2));
        longDistancePrice = Double.parseDouble(priceList.get(3));

        lightLoadPrice = Double.parseDouble(priceList.get(5));
        mediumLoadPrice = Double.parseDouble(priceList.get(6));
        hardLoadPrice = Double.parseDouble(priceList.get(7));

    }

    private static double shortDistancePrice;
    private static double mediumDistancePrice;
    private static double longDistancePrice;


    private static double lightLoadPrice;
    private static double mediumLoadPrice;
    private static double hardLoadPrice;

    public double getDistancePrice(double distance) {
        if (distance < 1000) {
            return shortDistancePrice;
        }
        if (distance < 5000 && distance > 1000 ) {
            return mediumDistancePrice;
        }
        if (distance > 5000) {
            return longDistancePrice;
        }

        return mediumDistancePrice;
    }

    public double getLoadPrice(double kg) {
        if (kg < 500) {
            return lightLoadPrice;
        }
        if (kg < 1000 && kg > 500 ) {
            return mediumLoadPrice;
        }
        if (kg > 1000) {
            return hardLoadPrice;
        }

        return mediumLoadPrice;
    }
}



