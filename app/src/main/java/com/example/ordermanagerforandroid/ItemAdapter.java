package com.example.ordermanagerforandroid;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemHolder> {

    private Context context;
    private ArrayList<MenuItem> items;

    public ItemAdapter(Context context, ArrayList<MenuItem> items) {
        this.context = context;
        this.items = items;
    }

    public interface OnItemRemovedListener {
        void onItemRemoved();
    }

    private OnItemRemovedListener mListener;

    public void setOnItemRemovedListener(OnItemRemovedListener listener) {
        mListener = listener;
    }

    @NonNull
    @NotNull
    @Override
    public ItemAdapter.ItemHolder onCreateViewHolder(@NonNull @NotNull ViewGroup viewGroup, int i) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.order_row, viewGroup, false);
        return new ItemAdapter.ItemHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull ItemAdapter.ItemHolder itemHolder, int i) {

        itemHolder.description.setText(items.get(i).toString());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ItemHolder extends RecyclerView.ViewHolder {

        private TextView description;
        private Button removeButton;

        public ItemHolder(@NonNull @NotNull View itemView) {

            super(itemView);
            description = itemView.findViewById(R.id.itemDescription);
            removeButton = itemView.findViewById(R.id.removeButton);

            // Set OnClickListener for the removeButton
            removeButton.setOnClickListener(v -> {
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
            builder.setMessage("Are you sure you want to remove " + items.get(position).toString() + "?");
            builder.setPositiveButton("REMOVE", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    DataManager globalData = DataManager.getDataManager();
                    globalData.getCurrentOrder().remove(items.get(position));

                    // Notify the activity that an item is removed
                    if (mListener != null) {
                        mListener.onItemRemoved();
                    }

                    // Display a toast indicating that the item was removed
                    String toastMessage = "Item removed.";
                    Toast.makeText(itemView.getContext(), toastMessage, Toast.LENGTH_SHORT).show();

                }
            });

            builder.setNegativeButton("Cancel", null);
            builder.show();

        }

    }


}
