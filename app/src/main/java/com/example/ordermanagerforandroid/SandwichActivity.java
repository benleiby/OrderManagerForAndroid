package com.example.ordermanagerforandroid;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * This activity allows users to customize and order sandwiches.
 * @author Matteus Coste
 */
public class SandwichActivity extends AppCompatActivity {

    private CheckBox lettuceCheckBox;
    private CheckBox tomatoCheckBox;
    private CheckBox cheeseCheckBox;
    private CheckBox onionCheckBox;
    private Spinner breadSpinner;
    private Spinner proteinSpinner;
    private Button addToOrderButton;
    private Spinner sandwichQuantitySpinner;
    private TextView subTotalTextView;

    private double subTotal;
    private DecimalFormat form;

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
        setContentView(R.layout.activity_sandwich);

        // Initialize UI components
        initializeUI();

        // Set up spinner adapters
        setUpSpinnerAdapters();

        // Set up listeners for UI components
        setupListeners();

        // Initialize subtotal and format
        subTotal = 0.00;
        form = new DecimalFormat("0.00");
        subTotalTextView.setText(form.format(subTotal));
    }

    /**
     * Initializes UI components by finding views by their IDs.
     */
    private void initializeUI() {
        lettuceCheckBox = findViewById(R.id.lettuceCheckBox);
        tomatoCheckBox = findViewById(R.id.tomatoCheckBox);
        cheeseCheckBox = findViewById(R.id.cheeseCheckBox);
        onionCheckBox = findViewById(R.id.onionCheckBox);
        breadSpinner = findViewById(R.id.breadSpinner);
        proteinSpinner = findViewById(R.id.proteinSpinner);
        addToOrderButton = findViewById(R.id.addToOrderButton);
        sandwichQuantitySpinner = findViewById(R.id.sandwichQuantitySpinner);
        subTotalTextView = findViewById(R.id.subTotalTextView);
    }

    /**
     * Sets up spinner adapters for bread, protein, and sandwich quantity spinners.
     */
    private void setUpSpinnerAdapters() {
        ArrayAdapter<CharSequence> breadAdapter = ArrayAdapter.createFromResource(this,
                R.array.bread_array, android.R.layout.simple_spinner_item);
        breadAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        breadSpinner.setAdapter(breadAdapter);

        ArrayAdapter<CharSequence> proteinAdapter = ArrayAdapter.createFromResource(this,
                R.array.protein_array, android.R.layout.simple_spinner_item);
        proteinAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        proteinSpinner.setAdapter(proteinAdapter);

        ArrayAdapter<CharSequence> quantityAdapter = ArrayAdapter.createFromResource(this,
                R.array.quantity_array, android.R.layout.simple_spinner_item);
        quantityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sandwichQuantitySpinner.setAdapter(quantityAdapter);
    }

    /**
     * Provides the back button functionality
     * @param view
     */
    public void back(View view) {
        finish();
    }

    /**
     * Sets up listeners for UI components.
     */
    private void setupListeners() {
        breadSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                updateSubTotal();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Do nothing
            }
        });

        proteinSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                updateSubTotal();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Do nothing
            }
        });

        lettuceCheckBox.setOnCheckedChangeListener((compoundButton, isChecked) -> updateSubTotal());
        tomatoCheckBox.setOnCheckedChangeListener((compoundButton, isChecked) -> updateSubTotal());
        cheeseCheckBox.setOnCheckedChangeListener((compoundButton, isChecked) -> updateSubTotal());
        onionCheckBox.setOnCheckedChangeListener((compoundButton, isChecked) -> updateSubTotal());

        sandwichQuantitySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                updateSubTotal();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // Do nothing
            }
        });

        addToOrderButton.setOnClickListener(view -> addToOrder());
    }

    /**
     * Updates the subtotal based on selected options.
     */
    private void updateSubTotal() {
        double proteinPrice = calculateProteinPrice();
        double addOnPrice = calculateAddOnPrice();
        double cheesePrice = cheeseCheckBox.isChecked() ? 1.00 : 0.00;

        int quantity = 1;
        if (sandwichQuantitySpinner.getSelectedItem() != null) {
            quantity = Integer.parseInt(sandwichQuantitySpinner.getSelectedItem().toString());
        }

        subTotal = (proteinPrice + addOnPrice + cheesePrice) * quantity;
        subTotalTextView.setText(form.format(subTotal));
    }

    /**
     * Calculates the price of the selected protein.
     *
     * @return The price of the selected protein.
     */
    private double calculateProteinPrice() {
        double proteinPrice = 0.00;
        String selectedProtein = proteinSpinner.getSelectedItem().toString();
        if (selectedProtein.equals("Beef")) {
            proteinPrice = Meat.BEEF.getPRICE();
        } else if (selectedProtein.equals("Chicken")) {
            proteinPrice = Meat.CHX.getPRICE();
        } else if (selectedProtein.equals("Fish")) {
            proteinPrice = Meat.FISH.getPRICE();
        }
        return proteinPrice;
    }

    /**
     * Calculates the price of selected add-ons.
     *
     * @return The total price of selected add-ons.
     */
    private double calculateAddOnPrice() {
        double addOnPrice = 0.00;
        if (lettuceCheckBox.isChecked()) {
            addOnPrice += 0.30;
        }
        if (tomatoCheckBox.isChecked()) {
            addOnPrice += 0.30;
        }
        if (onionCheckBox.isChecked()) {
            addOnPrice += 0.30;
        }
        return addOnPrice;
    }

    /**
     * Adds the customized sandwich to the order.
     */
    private void addToOrder() {
        Bread selectedBread = getSelectedBread();
        Meat selectedMeat = getSelectedMeat();
        boolean cheeseIncluded = cheeseCheckBox.isChecked();
        int quantity = Integer.parseInt(sandwichQuantitySpinner.getSelectedItem().toString());
        Sandwich sandwich = new Sandwich(selectedBread, selectedMeat, cheeseIncluded, quantity);

        if (lettuceCheckBox.isChecked()) {
            sandwich.add(SandwichAddOn.LETTUCE);
        }
        if (tomatoCheckBox.isChecked()) {
            sandwich.add(SandwichAddOn.TOMATO);
        }
        if (onionCheckBox.isChecked()) {
            sandwich.add(SandwichAddOn.ONION);
        }

        DataManager.getDataManager().getCurrentOrder().add(sandwich);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Confirm Order");
        builder.setMessage("Add this sandwich to your order?");
        builder.setPositiveButton("Add", (dialogInterface, i) -> {
        });
        builder.setNegativeButton("Cancel", null);
        builder.show();
    }

    /**
     * Gets the selected bread from the spinner.
     *
     * @return The selected bread.
     */
    private Bread getSelectedBread() {
        String selectedBread = breadSpinner.getSelectedItem().toString();
        if (selectedBread.equals("Bagel")) {
            return Bread.BAGEL;
        } else if (selectedBread.equals("Wheat")) {
            return Bread.WHEAT;
        } else if (selectedBread.equals("Sourdough")) {
            return Bread.SOUR;
        }
        return null;
    }

    /**
     * Gets the selected meat from the spinner.
     *
     * @return The selected meat.
     */
    private Meat getSelectedMeat() {
        String selectedProtein = proteinSpinner.getSelectedItem().toString();
        if (selectedProtein.equals("Beef")) {
            return Meat.BEEF;
        } else if (selectedProtein.equals("Chicken")) {
            return Meat.CHX;
        } else if (selectedProtein.equals("Fish")) {
            return Meat.FISH;
        }
        return null;
    }
}
