package com.example.ordermanagerforandroid;

/**
 * Meat available for sandwiches.
 * @author Benjamin Leiby
 */
public enum Meat {

    BEEF(10.99),
    CHX(8.99),
    FISH(9.99);

    private final double PRICE;

    Meat(double price) {
        this.PRICE = price;
    }

    public double getPRICE() {
        return PRICE;
    }

}
