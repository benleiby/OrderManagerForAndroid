package com.example.ordermanagerforandroid;

import java.util.ArrayList;

/**
 * Singleton implementation for managing the currentOrder and orderList objects.
 * To use this class, call DataManager.getDataManager();.
 * This only needs to be called once during first initialization.
 * @author Benjamin Leiby
 */
final class DataManager {

    private static Order currentOrder;
    private static ArrayList<Order> orderList;
    private static DataManager dataManager;

    private DataManager() {
        currentOrder = new Order(1);
        orderList = new ArrayList<Order>();
    }

    public static DataManager getDataManager() {
        if (dataManager == null) dataManager = new DataManager();
        return dataManager;
    }

    public static ArrayList<Order> getOrderList() {
        return orderList;
    }

    public static Order getCurrentOrder() {
        return currentOrder;
    }

    public static void setCurrentOrder(Order order) {
        currentOrder = order;
    }

}
