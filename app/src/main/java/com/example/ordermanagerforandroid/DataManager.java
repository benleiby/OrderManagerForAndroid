package com.example.ordermanagerforandroid;

import java.util.ArrayList;

/**
 * Singleton implementation for managing the currentOrder and orderList objects.
 * To use this class, call DataManager.getDataManager();.
 * This only needs to be called once during first initialization.
 * @author Benjamin Leiby
 */
public final class DataManager {

    private  Order currentOrder;
    private final ArrayList<Order> orderList;
    private static DataManager dataManager;

    private DataManager() {
        currentOrder = new Order(1);
        orderList = new ArrayList<Order>();
    }

    public synchronized static DataManager getDataManager() {
        if (dataManager == null) dataManager = new DataManager();
        return dataManager;
    }

    public ArrayList<Order> getOrderList() {
        return orderList;
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(Order order) {
        currentOrder = order;
    }

}
