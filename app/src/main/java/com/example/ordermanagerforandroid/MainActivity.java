package com.example.ordermanagerforandroid;

import android.content.Intent;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    DataManager globalData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        globalData = DataManager.getDataManager();
    }

    public void onDonutButtonClick(View view) {
        Intent intent = new Intent(MainActivity.this, DonutActivity.class);
        startActivity(intent);
    }

    public void onCoffeeButtonClick(View view) {
        Intent intent = new Intent(MainActivity.this, CoffeeActivity.class);
        startActivity(intent);
    }

    public void onSandwichButtonClick(View view) {
        Intent intent = new Intent(MainActivity.this, SandwichActivity.class);
        startActivity(intent);
    }

    public void onCurrentOrderButtonClick(View view) {
        Intent intent = new Intent(MainActivity.this, CurrentOrderActivity.class);
        startActivity(intent);
    }

    public void onOrderListButtonClick(View view) {
        Intent intent = new Intent(MainActivity.this, OrderListActivity.class);
        startActivity(intent);
    }

}