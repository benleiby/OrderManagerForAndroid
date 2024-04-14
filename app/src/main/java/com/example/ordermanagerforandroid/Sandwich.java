package com.example.ordermanagerforandroid;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Sandwich menu item.
 * @author Benjamin Leiby
 */
public class Sandwich extends MenuItem {

    private Bread bread;
    private Meat meat;
    private boolean cheese;
    private final ArrayList<SandwichAddOn> addOns;

    public Sandwich() {
        addOns = new ArrayList<SandwichAddOn>();
        quantity = 1;
    }

    public Sandwich(Bread bread, Meat meat, boolean cheese, int quantity) {
        addOns = new ArrayList<SandwichAddOn>();
        this.bread = bread;
        this.meat = meat;
        this.cheese = cheese;
        this.quantity = quantity;

    }

    public Bread getBread() {
        return bread;
    }
    public void setBread(Bread bread) {
        this.bread = bread;
    }

    public Meat getMeat() {
        return meat;
    }
    public void setMeat(Meat meat) {
        this.meat = meat;
    }

    public boolean hasCheese() {
        return cheese;
    }
    public void setCheese(boolean cheese) {
        this.cheese = cheese;
    }

    public ArrayList<SandwichAddOn> getAddOns() {
        return addOns;
    }

    /**
     * Adds an add-on to the sandwich list.
     * @param addOn to be added to this sandwich.
     * @return False if the add-on is already in the list. True if it isn't.
     */
    public boolean add(SandwichAddOn addOn) {
        if (addOns.contains(addOn)) {
            return false;
        }
        addOns.add(addOn);
        return true;
    }

    /**
     * Remove selected add-on from the list.
     * @param addOn to be removed.
     * @return True if the add-on is in the list. False if it isn't.
     */
    public boolean remove(SandwichAddOn addOn) {
        if (!addOns.contains(addOn)) {
            return false;
        }
        addOns.remove(addOn);
        return true;
    }

    /**
     * Override equals method. Checks bread, meat, cheese and addOn equality.
     * @param o Other sandwich to compare with this one.
     * @return True if the sandwiches are the same. False if they aren't.
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || !o.getClass().equals(Sandwich.class)) {
            return false;
        }
        Sandwich other = (Sandwich) o;
        return this.bread.equals(other.bread) && this.meat.equals(other.meat)
            && this.cheese == other.cheese && this.addOns.equals(other.addOns);
    }

    /**
     * Gets the price of this sandwich.
     * @return Meat, cheese, addons price times quantity.
     */
    @Override
    public double price() {
        DecimalFormat form = new DecimalFormat("0.00");
        double price = (meat.getPRICE() + (cheese ? 1.00 : 0.00) + (addOns.size() * .30)) * quantity;
        return Double.parseDouble(form.format(price));
    }

    /**
     * Override toString method.
     * @return String of format " meat[bread][addons] cheese? : Quantity = [quantity] : Price = [price]"
     */
    @Override
    public String toString() {
        return meat + "[" + bread + "]" + addOns.toString() +
            (cheese ? " With cheese" : " No cheese") +
            " : Quantity = " + quantity + " : Price = " + price();
    }

}
