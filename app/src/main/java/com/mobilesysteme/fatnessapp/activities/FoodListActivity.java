package com.mobilesysteme.fatnessapp.activities;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mobilesysteme.fatnessapp.DatabaseHelper;
import com.mobilesysteme.fatnessapp.FoodListAdapter;
import com.mobilesysteme.fatnessapp.OnFoodAddListener;
import com.mobilesysteme.fatnessapp.R;
import com.mobilesysteme.fatnessapp.sqlObjects.Food;
import com.mobilesysteme.fatnessapp.sqlObjects.FoodGroup;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FoodListActivity extends AppCompatActivity implements OnFoodAddListener {

    private static DatabaseHelper databaseHelper;
    private Map<Food, Integer> selectedItems;
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
        selectedItems = new HashMap<Food, Integer>();

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

    public Map<Food,Integer> getRecursiveFoodList(int foodgroup_id) {
        List<Food> resultSet = databaseHelper.getFoodByFoodGroupId(foodgroup_id);
        List<FoodGroup> childGroups = databaseHelper.getChildFoodGroups(foodgroup_id);
        if(childGroups.size() > 0) {
            for(FoodGroup foodGroup : childGroups) {
                List<Food> subResult = databaseHelper.getFoodByFoodGroupId(foodGroup.getId());
                resultSet.addAll(subResult);
            }
        }
        Map<Food, Integer> mapSet = new HashMap<>();
        for (Food food : resultSet) {
            mapSet.put(food,0);
        }
        return mapSet;
    }

    @Override
    public void onFoodAdd(Food food, int amount) {
        selectedItems.put(food, amount);
    }

    @Override
    public void onFoodUnchecked(Food food) {
        selectedItems.remove(food);
    }

    public void confirmFood() {
        System.out.println(selectedItems);
        for(Food food : selectedItems.keySet()){
            databaseHelper.addEatenFood(food.getId(), selectedItems.get(food) * food.getDefaultQuantity(), new Date());
        }
        finish();
    }
}