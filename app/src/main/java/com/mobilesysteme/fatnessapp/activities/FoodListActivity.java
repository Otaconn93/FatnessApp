package com.mobilesysteme.fatnessapp.activities;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.inputmethod.InputMethodManager;

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

/**
 *
 * Activity with all foods in a category. The user can select specific consumed food.
 *
 * @author Kevin Bücher
 *
 */
public class FoodListActivity extends AppCompatActivity implements OnFoodAddListener {

    private static DatabaseHelper databaseHelper;
    private List<Food> allItems;
    private Map<Food, Integer> selectedItems;
    private Map<Food, Integer> foodWithChangedDefaultValues;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodlist);

        initToolbar();

        databaseHelper = new DatabaseHelper(getApplicationContext());
        selectedItems = new HashMap<>();
        foodWithChangedDefaultValues = new HashMap<>();

        setAllItems(getRecursiveFoodList(getIntent().getIntExtra("POSITION", 0)));

        // Create RecyclerView with List Elements for Sub Food Groups
        RecyclerView foodListRecyclerView = findViewById(R.id.rv_FoodList);
        // Hide Keyboard when touched outside
        foodListRecyclerView.setOnTouchListener((v, event) -> {
            InputMethodManager input = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            input.hideSoftInputFromWindow(v.getWindowToken(), 0);
            return false;
        });

        initLayoutManager(foodListRecyclerView);
        initFoodListAdapter(foodListRecyclerView);
        initFloatingActionButton();
    }

    private void initLayoutManager(RecyclerView foodListRecyclerView) {
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        foodListRecyclerView.setLayoutManager(linearLayoutManager);
    }

    private void initFoodListAdapter(RecyclerView foodListRecyclerView) {
        FoodListAdapter foodListAdapter = new FoodListAdapter(getAllItems(), this, getApplicationContext());
        foodListRecyclerView.setAdapter(foodListAdapter);
    }

    private void initFloatingActionButton() {
        FloatingActionButton floatingActionButton = findViewById(R.id.fab_confirmFood);
        floatingActionButton.setOnClickListener(view -> confirmFood());
    }

    /**
     * Setup toolbar and activity title
     */
    private void initToolbar() {

        Toolbar toolbar = findViewById(R.id.foodlist_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Nahrungsmittel hinzufügen");
    }

    /**
     * Creates a set of food from with the same category
     *
     * @param foodgroup_id
     * @return a set of all food in one category
     */
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
            databaseHelper.addEatenFood(food.getId(), calculateCaloriesPer100GToSumCalories(selectedItems.get(food),food), new Date());
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
                                food.getName(), food.getUnitId(), food.getCaloriesPer100g(),
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

    /**
     * Adds food, if the user clicked at least one time on the increase button
     *
     * @param food
     * @param calorieSum
     */
    @Override
    public void addFood(Food food, int calorieSum) {

        if(!selectedItems.containsKey(food)) {
            selectedItems.put(food, calorieSum);
        }else{
            selectedItems.replace(food,calorieSum);
        }
    }

    /**
     * Removes food, if the user clicks on the discounter button till 0 is reached.
     *
     * @param food
     */
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

    /**
     * Calculates calorie sum, because calories were saved per 100g in Food class
     *
     * @param gramsSum grams sum of selected food item
     * @param food selected food
     * @return calories sum of selected food item
     */
    private int calculateCaloriesPer100GToSumCalories(int gramsSum, Food food){
        return (int) ((float)(gramsSum/100) * food.getCaloriesPer100g());
    }
}