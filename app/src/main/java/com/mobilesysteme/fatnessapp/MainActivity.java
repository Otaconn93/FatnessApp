package com.mobilesysteme.fatnessapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

//SPYDER IS AIDS

    private static DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    private void init() {

        databaseHelper = new DatabaseHelper(getApplicationContext());
//        DatabaseContentHelperUtils.fillDatabase(databaseHelper);
    }


}
