package com.mobilesysteme.fatnessapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobilesysteme.fatnessapp.sqlObjects.Food;
import com.mobilesysteme.fatnessapp.sqlObjects.FoodGroup;

public class FoodListActivity extends AppCompatActivity {

    private static DatabaseHelper databaseHelper;
    private int position;

    private RecyclerView foodListRecyclerView;
    private RecyclerView.LayoutManager linearLayoutManager;
    private FoodListAdapter foodListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodlist);
        databaseHelper = new DatabaseHelper(getApplicationContext());

        position = getIntent().getIntExtra("POSITION", 0);

        // Setup toolbar and activity title
        Toolbar toolbar = (Toolbar) findViewById(R.id.foodlist_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Nahrungsmittel hinzufügen");

        // Create RecyclerView with List Elements for Sub Food Groups
        foodListRecyclerView = findViewById(R.id.rv_FoodList);
        //Setup layout manager
        linearLayoutManager = new LinearLayoutManager(this);
        foodListRecyclerView.setLayoutManager(linearLayoutManager);
        // add adapter
        foodListAdapter = new FoodListAdapter(databaseHelper.getFoodByFoodGroupId(position));
        foodListRecyclerView.setAdapter(foodListAdapter);

    }
}