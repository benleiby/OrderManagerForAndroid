package com.example.ordermanagerforandroid;

/**
 * General representation of an order-able item.
 * @author Benjamin Leiby
 */
public abstract class MenuItem {

    public int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Increase quantity of item by specified amount.
     * @param plus Amount to increase by.
     */
    public void increaseQuantity(int plus) {
        quantity += plus;
    }
    /**
     * Decrease quantity of item by specified amount.
     * @param minus Amount to decrease by.
     */
    public void decreaseQuantity(int minus) {
        quantity -= minus;
    }

    /**
     * Get total price of item.
     * @return double value of item price.
     */
    public abstract double price ();

}
