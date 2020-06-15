package com.mobilesysteme.fatnessapp.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

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

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FoodListActivity extends AppCompatActivity implements OnFoodAddListener {

    private static DatabaseHelper databaseHelper;
    private List<Food> allItems;
    private Map<Food, Integer> selectedItems;
    private int position;
    private RecyclerView foodListRecyclerView;
    private RecyclerView.LayoutManager linearLayoutManager;
    private FoodListAdapter foodListAdapter;
    private FloatingActionButton floatingActionButton;
    private Map<Food, Integer> foodWithChangedDefaultValues;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodlist);
        databaseHelper = new DatabaseHelper(getApplicationContext());
        selectedItems = new HashMap<>();
        foodWithChangedDefaultValues = new HashMap<>();

        position = getIntent().getIntExtra("POSITION", 0);

        // Setup toolbar and activity title
        Toolbar toolbar = findViewById(R.id.foodlist_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Nahrungsmittel hinzufügen");

        // Create RecyclerView with List Elements for Sub Food Groups
        foodListRecyclerView = findViewById(R.id.rv_FoodList);

        setAllItems(getRecursiveFoodList(position));

        //Setup layout manager
        linearLayoutManager = new LinearLayoutManager(this);
        foodListRecyclerView.setLayoutManager(linearLayoutManager);
        // add adapter
        foodListAdapter = new FoodListAdapter(getAllItems(), this);
        foodListRecyclerView.setAdapter(foodListAdapter);

        // Setup Floating Action Button
        floatingActionButton = findViewById(R.id.fab_confirmFood);
        floatingActionButton.setOnClickListener(view -> confirmFood());

    }

    public List<Food> getRecursiveFoodList(int foodgroup_id) {
        List<Food> resultSet = databaseHelper.getFoodByFoodGroupId(foodgroup_id);
        List<FoodGroup> childGroups = databaseHelper.getChildFoodGroups(foodgroup_id);
        if (childGroups.size() > 0) {
            for (FoodGroup foodGroup : childGroups) {
                List<Food> subResult = databaseHelper.getFoodByFoodGroupId(foodGroup.getId());
                resultSet.addAll(subResult);
            }
        }
        return resultSet;
    }

    /**
     * Saves all eaten food into EatenFood table and finish this activity
     */
    public void confirmFood() {
        for(Food food : selectedItems.keySet()){
            databaseHelper.addEatenFood(food.getId(), selectedItems.get(food), new Date());
        }
        if(foodWithChangedDefaultValues.size()>0){
            getChangeDefaultDialogAnswer();
        }else{
            finish();
        }

    }

    public List<Food> getAllItems() {
        return allItems;
    }

    public void setAllItems(List<Food> allItems) {
        this.allItems = allItems;
    }

    /**
     * Open an alert dialog, which asks the user to store default value changes into the database
     */
    private void getChangeDefaultDialogAnswer() {
        DialogInterface.OnClickListener dialogClickListener = (dialog, which) -> {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    //Yes button clicked
                    for(Food food : foodWithChangedDefaultValues.keySet()){
                        databaseHelper.updateFood(food.getId(), food.getGroupId(),
                                food.getName(), food.getUnitId(), food.getCalories(),
                                foodWithChangedDefaultValues.get(food));
                    }
                    finish();
                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    finish();
                    break;
            }
        };
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Sollen die veränderten Standard Werte der Nahrungsmittel gespeichert werden?").setPositiveButton("Ja", dialogClickListener)
                .setNegativeButton("Nein", dialogClickListener);
        builder.create();
        builder.show();
    }

    @Override
    public void addFood(Food food, int caloriesSum) {
        if(!selectedItems.containsKey(food)) {
            selectedItems.put(food, caloriesSum);
        }else{
            selectedItems.replace(food,caloriesSum);
        }
    }

    @Override
    public void rmFood(Food food) {
        selectedItems.remove(food);
    }

    @Override
    public void isDefaultChanged(Food food, int changedValue) {
        if(!foodWithChangedDefaultValues.containsKey(food)) {
            foodWithChangedDefaultValues.put(food, changedValue);
        }else{
            foodWithChangedDefaultValues.replace(food,changedValue);
        }
    }
}