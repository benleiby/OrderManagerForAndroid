package com.example.ordermanagerforandroid;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
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

}