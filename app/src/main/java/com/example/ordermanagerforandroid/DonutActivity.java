package com.example.ordermanagerforandroid;

import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static com.example.ordermanagerforandroid.DonutVariety.*;

/**
 * Window that allows the user to add donuts to the order.
 * @author Benjamin Leiby
 */
public class DonutActivity extends AppCompatActivity {


    /**
     * Configure the RCView.
     * @param savedInstanceState savedInstanceState.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donut);

        RecyclerView rcView = findViewById(R.id.donutRcView);
        ArrayList<ViewDonut> donuts = getAvailableDonuts();
        DonutAdapter adapter = new DonutAdapter(this, donuts);
        rcView.setAdapter(adapter);
        rcView.setLayoutManager(new LinearLayoutManager(this));

    }

    /**
     * Navigate back to the main activity. End the current activity.
     * @param view Button view of back button.
     */
    public void back(View view) {
        finish();
    }

    /**
     * Get all possible combinations of donut varieties and flavors along with images.
     * @return ArrayList<ViewDonut> that includes images.
     */
    private ArrayList<ViewDonut> getAvailableDonuts() {

        int [] images = getItemImages();

        ArrayList<ViewDonut> output = new ArrayList<>();
        for (DonutFlavor flavor : CAKE.getFLAVORS()) {
            ViewDonut d = new ViewDonut(CAKE, 1, 0);
            d.setFlavor(flavor);
            output.add(d);
        }
        for (DonutFlavor flavor : HOLE.getFLAVORS()) {
            ViewDonut d = new ViewDonut(HOLE, 1, 0);
            d.setFlavor(flavor);
            output.add(d);
        }
        for (DonutFlavor flavor : YEAST.getFLAVORS()) {
            ViewDonut d = new ViewDonut(YEAST, 1, 0);
            d.setFlavor(flavor);
            output.add(d);
        }

        for (int i = 0; i < output.size(); i++) {
            output.get(i).setImage(images[i]);
        }

        return output;

    }

    /**
     * Get item image resources.
     * @return int [] containing image resource ids.
     */
    private int [] getItemImages() {

        return new int[] {
           R.drawable.cakebacon, R.drawable.cakeblueberry, R.drawable.cakeboston,
           R.drawable.holepumpkin, R.drawable.holepowdered, R.drawable.holechoc,
           R.drawable.yeastcinammon, R.drawable.yeastchocolate, R.drawable.yeaststrawberry,
           R.drawable.yeastpowdered, R.drawable.yeastplain, R.drawable.yeastvanilla
        };

    }

}