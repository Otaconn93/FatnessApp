package com.mobilesysteme.fatnessapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobilesysteme.fatnessapp.sqlObjects.FoodGroup;

import java.io.Serializable;
import java.util.ArrayList;

public class AddActivity extends AppCompatActivity implements OnFoodGroupClickListener {

    private static DatabaseHelper databaseHelper;

    private RecyclerView rootFoodGroupRecyclerView;
    private RecyclerView.LayoutManager gridLayoutManager;
    private RootFoodGroupAdapter rootFoodGroupAdapter;

    private RecyclerView subFoodGroupRecyclerView;
    private RecyclerView.LayoutManager linearLayoutManager;
    private SubFoodGroupAdapter subFoodGroupAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        databaseHelper = new DatabaseHelper(getApplicationContext());

        // Setup toolbar and activity title
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Nahrungsmittel hinzufügen");

        // Create RecyclerView with Cards for Root Food Groups
        rootFoodGroupRecyclerView = findViewById(R.id.rv_rootFoodGroups);
        rootFoodGroupRecyclerView.setHasFixedSize(true);
        // Setup layout manager
        gridLayoutManager = new GridLayoutManager(this, 2) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        rootFoodGroupRecyclerView.setLayoutManager(gridLayoutManager);
        // Specify adapter
        rootFoodGroupAdapter = new RootFoodGroupAdapter(databaseHelper.getRootFoodGroups(), this);
        rootFoodGroupRecyclerView.setAdapter(rootFoodGroupAdapter);

        // Create RecyclerView with List Elements for Sub Food Groups
        subFoodGroupRecyclerView = findViewById(R.id.rv_subFoodGroups);
        //Setup layout manager
        linearLayoutManager = new LinearLayoutManager(this);
        subFoodGroupRecyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onItemClick(FoodGroup foodGroup, int position, boolean isRoot) {
        int datasetSize = databaseHelper.getChildFoodGroups(foodGroup.getId()).size();

        if(isRoot && datasetSize > 0) { // check if food group has children
            subFoodGroupAdapter = new SubFoodGroupAdapter(databaseHelper.getChildFoodGroups(foodGroup.getId()), this);
            subFoodGroupRecyclerView.setAdapter(subFoodGroupAdapter);
        } else {
            Intent intent = new Intent(getBaseContext(), FoodListActivity.class);
            intent.putExtra("POSITION", foodGroup.getId());
            startActivity(intent);
        }
    }
}
