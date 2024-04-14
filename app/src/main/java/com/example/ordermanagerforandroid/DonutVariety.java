package com.example.ordermanagerforandroid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Donut varieties. Contains the list of flavors available for each variety.
 * @author Benjamin Leiby
 */
public enum DonutVariety {

    YEAST(Arrays.asList(
            DonutFlavor.CINNAMON, DonutFlavor.CHOCOLATE, DonutFlavor.STRAWBERRY,
            DonutFlavor.POWDERED, DonutFlavor.PLAIN, DonutFlavor.VANILLA
    ), 1.79),

    CAKE(Arrays.asList(
            DonutFlavor.BACON, DonutFlavor.BLUEBERRY, DonutFlavor.BOSTON
    ), 1.89),

    HOLE(Arrays.asList(
            DonutFlavor.PUMPKIN, DonutFlavor.POWDERED, DonutFlavor.CHOCOLATE
    ), 0.39);

    private final ArrayList<DonutFlavor> FLAVORS;
    private final double PRICE;

    DonutVariety(List<DonutFlavor> flavors, double price) {
        this.FLAVORS = new ArrayList<>(flavors);
        this.PRICE = price;
    }

    public ArrayList<DonutFlavor> getFLAVORS() {
        return FLAVORS;
    }

    public double getPRICE() {
        return PRICE;
    }

}