package com.mobilesysteme.fatnessapp.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mobilesysteme.fatnessapp.DatabaseHelper;
import com.mobilesysteme.fatnessapp.R;
import com.mobilesysteme.fatnessapp.fragments.AddRecipeFragment;
import com.mobilesysteme.fatnessapp.sqlObjects.Food;
import com.mobilesysteme.fatnessapp.sqlObjects.Recipe;

import java.util.Date;
import java.util.List;

public class RecipeDetailsActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    Recipe recipe;
    private List<Food> ingredients;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipedetails);
        databaseHelper = new DatabaseHelper(getApplicationContext());
        recipe = databaseHelper.getRecipeById(getIntent().getIntExtra(AddRecipeFragment.RECIPE_ID_KEY, 1));
        ingredients = databaseHelper.getIngredientsByRecipeId(recipe.getId());

        setupToolbar();
        makeIngredientList();
        makeDescription();
        int totalCalories = makeTotalCalories();
        setupFloatingActionButton(totalCalories);
    }

    /**
     * sets up the FAB to add the given recipe to the "Eaten" list
     * @param totalCalories total calories of recipe
     */
    private void setupFloatingActionButton(int totalCalories) {
        FloatingActionButton confirmButton = findViewById(R.id.fab_confirmRecipe);
        confirmButton.setOnClickListener(view -> {
            databaseHelper.addEatenRecipe(recipe.getId(), totalCalories, new Date());
            finish();
        });
    }

    /**
     * Takes all ingredients from the recipe and sums them. Also sets corresponding textview.
     * @return sum of all calories of all ingredients
     */
    private int makeTotalCalories() {
        TextView totalCaloriesView = findViewById(R.id.tv_totalCalories);
        int totalCalories = 0;
        for(Food ingredient: ingredients) {
            // TODO da die Datenbank nur Food und nicht RecipeIngredient zurückgibt, ist eine Verrechnung der rezeptbezogenen Mengen nicht möglich
            int calories = ingredient.getCaloriesPer100g() * ingredient.getDefaultQuantity();
            totalCalories += calories;
        }
        totalCaloriesView.setText(totalCalories + " kcal");
        return totalCalories;
    }

    /**
     * populates the ingredient list view
     */
    private void makeIngredientList() {
        // TODO da die Datenbank nur Food und nicht RecipeIngredient zurückgibt, ist eine Verrechnung der rezeptbezogenen Mengen nicht möglich
        ListView ingredientView = findViewById(R.id.lv_ingredients);
        ingredientView.setAdapter(new ArrayAdapter<>(this, R.layout.list_item, R.id.tv_ingredientName, ingredients));
    }

    /**
     * Fetches description from recipe and assigns textview to it
     */
    private void makeDescription() {
        TextView description = findViewById(R.id.tv_recipeDescription);
        description.setText(recipe.getDescription());
    }

    /**
     * sets up the toolbar and title
     */
    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        if(toolbar != null) {
            setSupportActionBar(toolbar);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(recipe.getName());
    }
}
