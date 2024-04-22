package com.example.ordermanagerforandroid;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Window that allows the user to view the current order.
 * Allows the user to place order and cancel items from the order.
 * @author Benjamin Leiby
 */
public class CurrentOrderActivity extends AppCompatActivity implements ItemAdapter.OnItemRemovedListener {

    DataManager globalData;
    TextView header;
    TextView subtotalDisplay, salesTaxDisplay, totalDisplay;
    ArrayList<MenuItem> items;
    RecyclerView rcView;
    ItemAdapter adapter;

    /**
     * Initialization of header, RCView, and totalDisplays.
     * Creates a listener to detect a removed item action from a ViewHolder.
     * @param savedInstanceState savedInstanceState.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_order);
        globalData = DataManager.getDataManager();

        // Set header txt
        header = findViewById(R.id.orderListHeader);
        String headerStr = "Order # " + globalData.getCurrentOrder().getORDER_NUMBER();
        header.setText(headerStr);

        // RCView Config
        rcView = findViewById(R.id.orderRecyclerView);
        items = globalData.getCurrentOrder().getItems();
        adapter = new ItemAdapter(this, items);
        rcView.setAdapter(adapter);
        rcView.setLayoutManager(new LinearLayoutManager(this));

        // Total displays
        subtotalDisplay = findViewById(R.id.subTotalDisplay);
        salesTaxDisplay = findViewById(R.id.salesTaxDisplay);
        totalDisplay = findViewById(R.id.totalDisplay);

        adapter.setOnItemRemovedListener(this);

        updatePrices();

    }

    /**
     * Sets text of price displays according to the current state of the current order.
     */
    private void updatePrices() {

        @SuppressLint("DefaultLocale") String subTotalStr = String.format("$ %.2f", globalData.getCurrentOrder().subTotal());
        subtotalDisplay.setText(subTotalStr);
        @SuppressLint("DefaultLocale") String taxStr = String.format("$ %.2f", globalData.getCurrentOrder().salesTax());
        salesTaxDisplay.setText(taxStr);
        @SuppressLint("DefaultLocale") String totalStr = String.format("$ %.2f", globalData.getCurrentOrder().totalAmount());
        totalDisplay.setText(totalStr);

    }

    /**
     * Sets the RcView to account for changes in the state of the currentOrder.
     * For example, if an item is added or removed.
     */
    private void updateRcView() {

        items = globalData.getCurrentOrder().getItems();
        adapter = new ItemAdapter(this, items);
        rcView.setAdapter(adapter);

    }

    /**
     * Navigate back to the main activity. End the current activity.
     * @param view Button view of back button.
     */
    public void back(View view) {
        finish();
    }

    @Override
    public void onItemRemoved() {
        updatePrices();
        updateRcView();
    }

    /**
     * Handle order placement. Prompts user for confirmation and finishes the activity.
     * @param view Button view of the place order button.
     */
    public void onPlaceOrderButtonClicked(View view) {

        if (globalData.getCurrentOrder().getItems().isEmpty()) {
            String toastMessage = "Order is empty.";
            Toast.makeText(view.getContext(), toastMessage, Toast.LENGTH_SHORT).show();
            return;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setMessage("Are you sure you want to place order # " + globalData.getCurrentOrder().getORDER_NUMBER() + "?" );
        builder.setPositiveButton("PLACE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                globalData.getOrderList().add(globalData.getCurrentOrder());
                globalData.setCurrentOrder(new Order(globalData.getCurrentOrder().getORDER_NUMBER() + 1));

                finish();

                // Display a toast indicating that the order was placed
                String toastMessage = "Order Placed.";
                Toast.makeText(view.getContext(), toastMessage, Toast.LENGTH_SHORT).show();

            }
        });

        builder.setNegativeButton("Cancel", null);
        builder.show();

    }

}