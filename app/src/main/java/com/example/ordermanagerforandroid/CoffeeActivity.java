package com.example.ordermanagerforandroid;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * This activity allows users to customize and order coffee.
 * @author Matteus Coste
 */
public class CoffeeActivity extends AppCompatActivity {

    private CheckBox sweetCreamCheckBox, frenchVanillaCheckBox, irishCreamCheckBox, mochaCheckBox, caramelCheckBox;
    private Spinner coffeeSizeSpinner, coffeeQuantitySpinner;
    private TextView subTotalTextViewCoffee;

    /**
     * Sets up the activity layout and initializes UI components.
     *
     * @param savedInstanceState If the activity is being re-initialized after
     *     previously being shut down then this Bundle contains the data it most
     *     recently supplied in {@link #onSaveInstanceState}.  <b><i>Note: Otherwise it is null.</i></b>
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);

        // Initialize UI components
        initializeUI();

        // Set up spinner adapters
        setUpSpinnerAdapters();

        // Set up listeners for UI components
        setupListeners();
    }

    /**
     * Initializes UI components by finding views by their IDs.
     */
    private void initializeUI() {
        sweetCreamCheckBox = findViewById(R.id.sweetCreamCheckBox);
        frenchVanillaCheckBox = findViewById(R.id.frenchVanillaCheckBox);
        irishCreamCheckBox = findViewById(R.id.irishCreamCheckBox);
        mochaCheckBox = findViewById(R.id.mochaCheckBox);
        caramelCheckBox = findViewById(R.id.caramelCheckBox);
        coffeeSizeSpinner = findViewById(R.id.coffeeSizeSpinner);
        coffeeQuantitySpinner = findViewById(R.id.coffeeQuantitySpinner);
        subTotalTextViewCoffee = findViewById(R.id.subTotalTextViewCoffee);
    }

    /**
     * Sets up spinner adapters for coffee size and quantity spinners.
     */
    private void setUpSpinnerAdapters() {
        ArrayAdapter<CharSequence> sizeAdapter = ArrayAdapter.createFromResource(this, R.array.coffee_sizes_array, android.R.layout.simple_spinner_item);
        sizeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        coffeeSizeSpinner.setAdapter(sizeAdapter);

        ArrayAdapter<CharSequence> quantityAdapter = ArrayAdapter.createFromResource(this, R.array.quantity_array, android.R.layout.simple_spinner_item);
        quantityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        coffeeQuantitySpinner.setAdapter(quantityAdapter);
    }

    /**
     * Sets up listeners for UI components.
     */
    private void setupListeners() {
        // Listener for coffee size spinner
        coffeeSizeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateSubTotal();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        // Listener for coffee quantity spinner
        coffeeQuantitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateSubTotal();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        // Listeners for checkbox changes
        sweetCreamCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> updateSubTotal());
        frenchVanillaCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> updateSubTotal());
        irishCreamCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> updateSubTotal());
        mochaCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> updateSubTotal());
        caramelCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> updateSubTotal());
    }

    /**
     * Updates the subtotal based on selected options.
     */
    private void updateSubTotal() {
        double sizePrice = getSizePrice();
        double quantity = getQuantity();
        double toppingsPrice = getToppingsPrice();

        double subTotal = sizePrice * quantity + toppingsPrice;

        subTotalTextViewCoffee.setText(String.format("$%.2f", subTotal));
    }

    /**
     * Retrieves the price of the selected coffee size.
     *
     * @return The price of the selected coffee size.
     */
    private double getSizePrice() {
        int position = coffeeSizeSpinner.getSelectedItemPosition();

        CupSize cupSize;
        switch (position) {
            case 0:
                cupSize = CupSize.SHORT;
                break;
            case 1:
                cupSize = CupSize.TALL;
                break;
            case 2:
                cupSize = CupSize.GRANDE;
                break;
            case 3:
                cupSize = CupSize.VENTI;
                break;
            default:
                cupSize = CupSize.SHORT;
        }

        return cupSize.getPRICE();
    }

    /**
     * Retrieves the selected quantity of coffee.
     *
     * @return The selected quantity of coffee.
     */
    private int getQuantity() {
        int position = coffeeQuantitySpinner.getSelectedItemPosition();

        switch (position) {
            case 0:
                return 1;
            case 1:
                return 2;
            case 2:
                return 3;
            case 3:
                return 4;
            case 4:
                return 5;
            default:
                return 1;
        }
    }

    /**
     * Calculates the price of selected toppings.
     *
     * @return The total price of selected toppings.
     */
    private double getToppingsPrice() {
        double totalPrice = 0.0;

        if (sweetCreamCheckBox.isChecked()) {
            totalPrice += 0.30;
        }
        if (frenchVanillaCheckBox.isChecked()) {
            totalPrice += 0.30;
        }
        if (irishCreamCheckBox.isChecked()) {
            totalPrice += 0.30;
        }
        if (mochaCheckBox.isChecked()) {
            totalPrice += 0.30;
        }
        if (caramelCheckBox.isChecked()) {
            totalPrice += 0.30;
        }

        return totalPrice;
    }

    /**
     * Adds the selected coffee to the order and shows a confirmation dialog.
     */
    private void addToOrder() {
        String selectedSize = coffeeSizeSpinner.getSelectedItem().toString();
        CupSize cupSize;
        switch (selectedSize) {
            case "Short":
                cupSize = CupSize.SHORT;
                break;
            case "Tall":
                cupSize = CupSize.TALL;
                break;
            case "Grande":
                cupSize = CupSize.GRANDE;
                break;
            case "Venti":
                cupSize = CupSize.VENTI;
                break;
            default:
                cupSize = CupSize.SHORT;
        }

        int quantity = getQuantity();

        ArrayList<CoffeeAddIn> selectedAddIns = new ArrayList<>();
        if (sweetCreamCheckBox.isChecked()) {
            selectedAddIns.add(CoffeeAddIn.SWEET_CREAM);
        }
        if (frenchVanillaCheckBox.isChecked()) {
            selectedAddIns.add(CoffeeAddIn.FRENCH_VANILLA);
        }
        if (irishCreamCheckBox.isChecked()) {
            selectedAddIns.add(CoffeeAddIn.IRISH_CREAM);
        }
        if (mochaCheckBox.isChecked()) {
            selectedAddIns.add(CoffeeAddIn.MOCHA);
        }
        if (caramelCheckBox.isChecked()) {
            selectedAddIns.add(CoffeeAddIn.CARAMEL);
        }

        Coffee coffee = new Coffee(cupSize, quantity);
        for (CoffeeAddIn addIn : selectedAddIns) {
            coffee.add(addIn);
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(CoffeeActivity.this);
        builder.setTitle("Confirm Add to Order");
        builder.setMessage("Add this coffee to your order?");
        builder.setPositiveButton("Add", (dialogInterface, i) -> {
            DataManager.getDataManager().getCurrentOrder().add(coffee);

            Toast.makeText(CoffeeActivity.this, "Coffee added to order", Toast.LENGTH_SHORT).show();
        });
        builder.setNegativeButton("Cancel", null);

        builder.show();
    }
}