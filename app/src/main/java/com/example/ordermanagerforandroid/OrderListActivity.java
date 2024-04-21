package com.example.ordermanagerforandroid;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class OrderListActivity extends AppCompatActivity {

    ListView ordersListView;
    Spinner orderSpinner;
    String [] orderNumbers;
    String [] orderArray;
    DataManager globalData;
    ArrayAdapter<String> spinnerAdapter;
    ArrayAdapter<String> listAdapter;
    Context context;
    TextView totalAmountDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_list);
        globalData = DataManager.getDataManager();
        context = this;

        // Spinner Config
        orderSpinner = findViewById(R.id.orderSpinner);
        orderNumbers = getOrderNumbers();
        spinnerAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, orderNumbers);
        orderSpinner.setAdapter(spinnerAdapter);

        // List Config
        ordersListView = findViewById(R.id.ordersListView);

        // Total Amount Display
        totalAmountDisplay = findViewById(R.id.totalDisplay);

        orderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                orderArray = getOrderAsArray(Integer.parseInt(orderNumbers[position]));
                listAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, orderArray);
                ordersListView.setAdapter(listAdapter);

                for (Order o : globalData.getOrderList()) {
                    if (o.getORDER_NUMBER() == Integer.parseInt(orderNumbers[position])) {
                        @SuppressLint("DefaultLocale") String totalStr = String.format("$ %.2f", o.totalAmount());
                        totalAmountDisplay.setText(totalStr);
                        break;
                    }
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                String toastMessage = "No orders have been placed.";
                Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show();
            }

        });

        if (orderNumbers.length == 0) {
            String toastMessage = "No orders have been placed.";
            Toast.makeText(context, toastMessage, Toast.LENGTH_LONG).show();
        }

    }

    private String [] getOrderNumbers() {

        ArrayList<String> output = new ArrayList<>();
        for (Order o : globalData.getOrderList()) {
            output.add(String.valueOf(o.getORDER_NUMBER()));
        }
        return output.toArray(new String [0]);

    }

    private String [] getOrderAsArray(int orderNumber) {

        ArrayList<String> output = new ArrayList<>();
        for (Order o : globalData.getOrderList()) {
            if (o.getORDER_NUMBER() == orderNumber) {
                for (MenuItem item : o.getItems()) {
                    output.add(item.toString());
                }
                break;
            }
        }
        return output.toArray(new String[0]);

    }

    public void onCancelOrderButtonClicked(View view) {

        if (orderSpinner.getSelectedItem() == null) {
            String toastMessage = "No order selected";
            Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show();
            return;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
        builder.setMessage("Are you sure you want to cancel order # " + orderSpinner.getSelectedItem().toString() + "?");
        builder.setPositiveButton("CANCEL ORDER", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                globalData.getOrderList().removeIf(o -> o.getORDER_NUMBER() == Integer.parseInt(orderSpinner.getSelectedItem().toString()));

                finish();

                // Display a toast indicating that the order was cancelled
                String toastMessage = "Order Cancelled.";
                Toast.makeText(view.getContext(), toastMessage, Toast.LENGTH_SHORT).show();

            }
        });

        builder.setNegativeButton("Go back", null);
        builder.show();

    }


    public void back(View view) {
        finish();
    }

}