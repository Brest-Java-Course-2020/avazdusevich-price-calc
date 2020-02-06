package com.epam.brest;
import com.epam.brest.util.MyFileReader;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class PriceList {

    static {
        MyFileReader reader = new MyFileReader("src/main/java/com/epam/brest/resources/PriceList");
        ArrayList<String> priceList = reader.readFromFile();
        shortDistancePrice = new BigDecimal(priceList.get(1)).setScale(2, RoundingMode.HALF_UP);
        mediumDistancePrice = new BigDecimal(priceList.get(2)).setScale(2, RoundingMode.HALF_UP);
        longDistancePrice = new BigDecimal(priceList.get(3)).setScale(2, RoundingMode.HALF_UP);

        lightLoadPrice = new BigDecimal(priceList.get(5)).setScale(2, RoundingMode.HALF_UP);
        mediumLoadPrice = new BigDecimal(priceList.get(6)).setScale(2, RoundingMode.HALF_UP);
        hardLoadPrice = new BigDecimal(priceList.get(7)).setScale(2, RoundingMode.HALF_UP);

    }

    private static BigDecimal shortDistancePrice;
    private static BigDecimal mediumDistancePrice;
    private static BigDecimal longDistancePrice;


    private static BigDecimal lightLoadPrice;
    private static BigDecimal mediumLoadPrice;
    private static BigDecimal hardLoadPrice;

    public BigDecimal getDistancePrice(BigDecimal distance) {
        if (distance.compareTo(BigDecimal.valueOf(1000)) < 0) {
            return shortDistancePrice;
        }
        if (distance.compareTo(BigDecimal.valueOf(1000)) > 0 && distance.compareTo(BigDecimal.valueOf(5000)) < 0) {
            return mediumDistancePrice;
        }
        if (distance.compareTo(BigDecimal.valueOf(5000)) > 0) {
            return longDistancePrice;
        }

        return mediumDistancePrice;
    }

    public BigDecimal getLoadPrice(BigDecimal weight) {
        if (weight.compareTo(BigDecimal.valueOf(500)) < 0) {
            return lightLoadPrice;
        }
        if (weight.compareTo(BigDecimal.valueOf(500)) > 0 && weight.compareTo(BigDecimal.valueOf(1000)) < 0) {
            return mediumLoadPrice;
        }
        if (weight.compareTo(BigDecimal.valueOf(1000)) > 0) {
            return hardLoadPrice;
        }

        return mediumLoadPrice;
    }
}
