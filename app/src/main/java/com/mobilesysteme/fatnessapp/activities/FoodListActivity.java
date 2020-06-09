package com.mobilesysteme.fatnessapp.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.mobilesysteme.fatnessapp.DatabaseHelper;
import com.mobilesysteme.fatnessapp.FoodListAdapter;
import com.mobilesysteme.fatnessapp.OnFoodCheckListener;
import com.mobilesysteme.fatnessapp.R;
import com.mobilesysteme.fatnessapp.sqlObjects.Food;
import com.mobilesysteme.fatnessapp.sqlObjects.FoodGroup;

import java.util.ArrayList;
import java.util.List;

public class FoodListActivity extends AppCompatActivity implements OnFoodCheckListener {

    private static DatabaseHelper databaseHelper;
    private List<Food> selectedItems;
    private int position;

    private RecyclerView foodListRecyclerView;
    private RecyclerView.LayoutManager linearLayoutManager;
    private FoodListAdapter foodListAdapter;

    private FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodlist);
        databaseHelper = new DatabaseHelper(getApplicationContext());
        selectedItems = new ArrayList<>();

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
        foodListAdapter = new FoodListAdapter(getRecursiveFoodList(position), this);
        foodListRecyclerView.setAdapter(foodListAdapter);

        // Setup Floating Action Button
        floatingActionButton = findViewById(R.id.fab_confirmFood);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmFood();
            }
        });

    }

    public List<Food> getRecursiveFoodList(int foodgroup_id) {
        List<Food> resultSet = databaseHelper.getFoodByFoodGroupId(foodgroup_id);
        List<FoodGroup> childGroups = databaseHelper.getChildFoodGroups(foodgroup_id);
        if(childGroups.size() > 0) {
            for(FoodGroup foodGroup : childGroups) {
                List<Food> subResult = databaseHelper.getFoodByFoodGroupId(foodGroup.getId());
                resultSet.addAll(subResult);
            }
        }
        return resultSet;
    }

    @Override
    public void onFoodChecked(Food food) {
        selectedItems.add(food);
    }

    @Override
    public void onFoodUnchecked(Food food) {
        selectedItems.remove(food);
    }

    public void confirmFood() {
        System.out.println(selectedItems);
        finish();
    }
}