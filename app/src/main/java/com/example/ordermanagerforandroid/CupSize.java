package com.example.ordermanagerforandroid;

/**
 * Cup sizes available for coffee.
 * @author Benjamin Leiby
 */
public enum CupSize {

    SHORT(1.99),
    TALL(2.49),
    GRANDE(2.99),
    VENTI(3.49);

    private final double PRICE;

    CupSize(Double price) {
        this.PRICE = price;
    }

    public double getPRICE() {
        return this.PRICE;
    }

}
