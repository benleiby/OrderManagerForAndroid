package com.example.ordermanagerforandroid;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Order which is basically a list of menu items.
 * @author Benjamin Leiby
 */
public class Order {

    private final int ORDER_NUMBER;
    private final ArrayList<MenuItem> items;

    public Order(int orderNumber) {
        ORDER_NUMBER = orderNumber;
        items = new ArrayList<MenuItem>();
    }

    public int getORDER_NUMBER() {
        return ORDER_NUMBER;
    }

    public ArrayList<MenuItem> getItems() {
        return items;
    }

    /**
     * Adds an item to the order. If the item is already in the order, increase the quantity.
     * @param item to be added to the order.
     */
    public void add(MenuItem item) {
        if (items.contains(item)) {
            items.get(items.indexOf(item)).increaseQuantity(item.getQuantity());
        }
        else {
            items.add(item);
        }
    }

    /**
     * Removes an item from the order. Removes the whole instance of the item, regardless of quantity.
     * @param item to be removed from the order.
     * @return True if the item is on the order. False if it isn't.
     */
    public boolean remove(MenuItem item) {
        if (!items.contains(item)) {
            return false;
        }
        items.remove(item);
        return true;
    }

    /**
     * Gets subtotal of the order.
     * @return Sum of menu item prices.
     */
    public double subTotal() {
        DecimalFormat form = new DecimalFormat("0.00");
        double total = 0;
        for (MenuItem item : items) {
            total += item.price();
        }
        return Double.parseDouble(form.format(total));
    }

    /**
     * Gets the total amount (tax included) for the order.
     * @return subTotal with added tax.
     */
    public double totalAmount() {
        DecimalFormat form = new DecimalFormat("0.00");
        final double NJ_TAX = .00625;
        double total = subTotal() + subTotal() * NJ_TAX;
        return Double.parseDouble(form.format(total));
    }

    /**
     * Gets the sales tax of the order. NJ_TAX = .00625
     * @return sales tax.
     */
    public double salesTax() {
        DecimalFormat form = new DecimalFormat("0.00");
        final double NJ_TAX = .00625;
        double tax = subTotal() * NJ_TAX;
        return Double.parseDouble(form.format(tax));
    }

    /**
     * Override toString method.
     * @return String of format "[items] : Subtotal = [subTotal] : Total amount : [totalAmount]"
     */
    @Override
    public String toString() {
        return ORDER_NUMBER + " " + items.toString() + " : Subtotal = " + subTotal() + " : Total amount = " + totalAmount();
    }

}
