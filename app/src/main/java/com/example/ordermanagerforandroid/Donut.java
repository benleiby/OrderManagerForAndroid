package com.example.ordermanagerforandroid;

import java.text.DecimalFormat;

/**
 * Donut of a particular variety and flavor.
 * @author Benjamin Leiby
 */
public class Donut extends MenuItem {

    private DonutVariety variety;
    private DonutFlavor flavor;

    public Donut(DonutVariety variety, int quantity) {
        this.variety = variety;
        this.quantity = quantity;
    }

    public void setVariety(DonutVariety variety) {
        this.variety = variety;
    }
    public DonutVariety getVariety() {
        return variety;
    }

    /**
     * Sets the flavor of the donut. Checks to see if the flavor is available for that variety.
     * @param flavor to set this donut to.
     * @return True if the flavor is available for the current variety. False if it isn't.
     */
    public boolean setFlavor(DonutFlavor flavor) {
        if (!variety.getFLAVORS().contains(flavor)) {
            this.flavor = null;
            return false;
        }
        this.flavor = flavor;
        return true;
    }
    public DonutFlavor getFlavor() {
        return flavor;
    }

    /**
     * Gets the price of the donut item.
     * @return The price of variety and flavor combo times the quantity.
     */
    @Override
    public double price() {
        DecimalFormat form = new DecimalFormat("0.00");
        double price = variety.getPRICE() * quantity;
        return Double.parseDouble(form.format(price));
    }

    /**
     * Override equals method. Compares variety and flavor.
     * @param o The other donut to compare with this one.
     * @return True if they are equal and false if not.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || !o.getClass().equals(Donut.class)) {
            return false;
        }
        Donut other = (Donut) o;
        return this.variety.equals(other.variety) && this.flavor.equals(other.flavor);
    }

    /**
     * Override toString method.
     * @return String of format : "[Items] : Price = [Price]"
     */
    @Override
    public String toString() {
        return variety + "[" + flavor + "] : Quantity = " + quantity + " : Price = $" + price();
    }

}