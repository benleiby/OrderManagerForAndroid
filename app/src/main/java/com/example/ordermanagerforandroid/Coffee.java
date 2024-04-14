package com.example.ordermanagerforandroid;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Coffee of a particular size with a list of add ins.
 * @author Benjamin Leiby
 */
public class Coffee extends MenuItem {

    private CupSize cupSize;
    private final ArrayList<CoffeeAddIn> addIns;

    public Coffee(CupSize cupSize, int quantity) {
        this.cupSize = cupSize;
        addIns = new ArrayList<CoffeeAddIn>();
        this.quantity = quantity;
    }

    public CupSize getCupSize() {
        return cupSize;
    }
    public void setCupSize(CupSize cupSize) {
        this.cupSize = cupSize;
    }

    public ArrayList<CoffeeAddIn> getAddIns() {
        return addIns;
    }

    /**
     * Adds an add-in to this coffee. Checks to see if the add-in is already in the list.
     * @param addIn to be added to the list.
     * @return False if the add-in is already in the list. True if it isn't.
     */
    public boolean add(CoffeeAddIn addIn) {
        if (addIns.contains(addIn)) {
            return false;
        }
        addIns.add(addIn);
        return true;
    }

    /**
     * Remove an add-in from this coffee. Checks to see if the add-in is in the list.
     * @param addIn to be removed from the list.
     * @return False if add-in is not in the list. True if it is.
     */
    public boolean remove(CoffeeAddIn addIn) {
        if (!addIns.contains(addIn)) {
            return false;
        }
        addIns.remove(addIn);
        return true;
    }

    /**
     * Override equals method. Compares cupSize and addIns.
     * @param o Other coffee to compare with this one.
     * @return True if the coffees are the same. False if they aren't.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || !o.getClass().equals(Coffee.class)) {
            return false;
        }
        Coffee other = (Coffee) o;
        return this.cupSize.equals(other.cupSize) && this.addIns.equals(other.addIns);
    }

    /**
     * Gets the price of the coffee item.
     * @return The price of the cup size and add-ins times the quantity.
     */
    @Override
    public double price() {

        DecimalFormat form = new DecimalFormat("0.00");
        double price = (cupSize.getPRICE() + (addIns.size() * .30)) * quantity;
        return Double.parseDouble(form.format(price));
    }

    /**
     * Override toString method.
     * @return String of format "cupSize[add-ins] : Quantity = [quantity] : Price = [price]"
     */
    @Override
    public String toString() {
        return cupSize + addIns.toString() + " : Quantity = " + quantity + " : Price = " + price();
    }

}
