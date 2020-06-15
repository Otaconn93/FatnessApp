package com.mobilesysteme.fatnessapp.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mobilesysteme.fatnessapp.DatabaseHelper;
import com.mobilesysteme.fatnessapp.FoodListAdapter;
import com.mobilesysteme.fatnessapp.R;
import com.mobilesysteme.fatnessapp.sqlObjects.Food;
import com.mobilesysteme.fatnessapp.sqlObjects.FoodGroup;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FoodListActivity extends AppCompatActivity {

    private static DatabaseHelper databaseHelper;
    private Map<Food, Integer> allItems;
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
        selectedItems = new HashMap<>();

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
        foodListAdapter = new FoodListAdapter(getAllItems());
        foodListRecyclerView.setAdapter(foodListAdapter);

        // Setup Floating Action Button
        floatingActionButton = findViewById(R.id.fab_confirmFood);
        floatingActionButton.setOnClickListener(view -> confirmFood());

    }

    public Map<Food, Integer> getRecursiveFoodList(int foodgroup_id) {
        List<Food> resultSet = databaseHelper.getFoodByFoodGroupId(foodgroup_id);
        List<FoodGroup> childGroups = databaseHelper.getChildFoodGroups(foodgroup_id);
        if (childGroups.size() > 0) {
            for (FoodGroup foodGroup : childGroups) {
                List<Food> subResult = databaseHelper.getFoodByFoodGroupId(foodGroup.getId());
                resultSet.addAll(subResult);
            }
        }
        Map<Food, Integer> mapSet = new HashMap<>();
        for (Food food : resultSet) {
            mapSet.put(food, 0);
        }
        return mapSet;
    }

    /**
     * Saves all eaten food into EatenFood table and finish this activity
     */
    public void confirmFood() {
        boolean onDefaultChange = false;
        for (int i = 0; i < foodListRecyclerView.getChildCount(); i++) {
            ViewGroup childGroup = (ViewGroup) foodListRecyclerView.getChildAt(i);
            TextView currentCalories = childGroup.findViewById(R.id.tv_details);
            TextView amount = childGroup.findViewById(R.id.amount);
            EditText defaultValue = childGroup.findViewById(R.id.defaultValue);
            Food currentFood = new ArrayList<>(getAllItems().keySet()).get(i);

            if (Integer.parseInt(defaultValue.getText().toString()) != currentFood.getDefaultQuantity()) {
                onDefaultChange = true;
            }

            if (!amount.getText().toString().equals("0")) {
                int calories = Integer.parseInt((String) currentCalories.getText().toString().replace(" g", ""));
                selectedItems.put(currentFood, calories);
            }
        }

        for (Food food : selectedItems.keySet()) {
            System.out.println(food.getName() + " " + food.getDefaultQuantity() + " " + selectedItems.get(food));
            databaseHelper.addEatenFood(food.getId(), selectedItems.get(food), new Date());
        }
        if (onDefaultChange) {
            getChangeDefaultDialogAnswer();
        } else {
            finish();
        }
    }


    public Map<Food, Integer> getAllItems() {
        return allItems;
    }

    public void setAllItems(Map<Food, Integer> allItems) {
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
                    for (int i = 0; i < foodListRecyclerView.getChildCount(); i++) {
                        ViewGroup childGroup = (ViewGroup) foodListRecyclerView.getChildAt(i);
                        EditText defaultValue = childGroup.findViewById(R.id.defaultValue);
                        Food currentFood = new ArrayList<>(getAllItems().keySet()).get(i);

                        if (Integer.parseInt(defaultValue.getText().toString()) != currentFood.getDefaultQuantity()) {
                            databaseHelper.updateFood(currentFood.getId(), currentFood.getGroupId(),
                                    currentFood.getName(), currentFood.getUnitId(), currentFood.getCalories(),
                                    Integer.parseInt(String.valueOf(defaultValue.getText())));
                        }
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

}