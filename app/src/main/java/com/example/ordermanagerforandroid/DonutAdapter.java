package com.example.ordermanagerforandroid;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class DonutAdapter extends RecyclerView.Adapter<DonutAdapter.DonutHolder> {

    private Context context;
    private ArrayList<ViewDonut> items;
    private ArrayAdapter<String> quantityAdapter;

    public DonutAdapter(Context context, ArrayList<ViewDonut> items) {

        this.context = context;
        this.items = items;
        String[] quantityItems = {"1", "2", "3", "4", "5"};
        quantityAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, quantityItems);

    }

    @NonNull
    @NotNull
    @Override
    public DonutAdapter.DonutHolder onCreateViewHolder(@NonNull @NotNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.donut_row, viewGroup, false);
        return new DonutHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull DonutAdapter.DonutHolder donutHolder, int position) {

        String name = items.get(position).getFlavor() + " " + items.get(position).getVariety();
        donutHolder.itemName.setText(name);
        donutHolder.itemPrice.setText(String.valueOf("$ " + items.get(position).price()));
        donutHolder.itemImage.setImageResource(items.get(position).getImage());
        donutHolder.quantitySpinner.setAdapter(quantityAdapter);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class DonutHolder extends RecyclerView.ViewHolder {

        private TextView itemName, itemPrice;
        private ImageView itemImage;
        private Button addButton;
        private Spinner quantitySpinner;
        private ConstraintLayout parentLayout;

        public DonutHolder(@NonNull @NotNull View itemView) {

            super(itemView);
            itemName = itemView.findViewById(R.id.donutName);
            itemImage = itemView.findViewById(R.id.donutImage);
            itemPrice = itemView.findViewById(R.id.donutPrice);
            quantitySpinner = itemView.findViewById(R.id.quantitySpinner);
            addButton = itemView.findViewById(R.id.addButton);
            parentLayout = itemView.findViewById(R.id.rowLayout);

            // Set OnClickListener for the addButton
            addButton.setOnClickListener(v -> {
                // Handle button click event
                int position = getAdapterPosition(); // Get the adapter position of the clicked item
                if (position != RecyclerView.NO_POSITION) {
                    // Execute the desired action, such as showing a dialog or updating data
                    showDialog(position);
                }
            });

        }

        private void showDialog(int position) {

            AlertDialog.Builder builder = new AlertDialog.Builder(itemView.getContext());
            String quantitySelected = (String) quantitySpinner.getSelectedItem();
            builder.setMessage("Add " + quantitySelected + " " +
                items.get(position).getFlavor() + " " + items.get(position).getVariety() + " donut to cart?");

            builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Handle adding item to cart
                    // You can access the item at the clicked position using items.get(position)
                    // Perform the necessary action here

                    Donut donut = new Donut(items.get(position).getVariety(), Integer.parseInt(quantitySelected));
                    donut.setFlavor(items.get(position).getFlavor());
                    DataManager globalData = DataManager.getDataManager();
                    globalData.getCurrentOrder().add(donut);

                    // Display a toast indicating that the item was added to the cart
                    String toastMessage = "Added. New subtotal: " + globalData.getCurrentOrder().subTotal();
                    Toast.makeText(itemView.getContext(), toastMessage, Toast.LENGTH_SHORT).show();

                }
            });

            builder.setNegativeButton("Cancel", null);
            builder.show();

        }

    }

}
