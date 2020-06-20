package com.mobilesysteme.fatnessapp.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mobilesysteme.fatnessapp.DatabaseHelper;
import com.mobilesysteme.fatnessapp.R;
import com.mobilesysteme.fatnessapp.fragments.AddRecipeFragment;
import com.mobilesysteme.fatnessapp.sqlObjects.Food;
import com.mobilesysteme.fatnessapp.sqlObjects.Recipe;
import com.mobilesysteme.fatnessapp.sqlObjects.RecipeIngredient;

import org.w3c.dom.Text;

import java.util.Date;
import java.util.List;

public class RecipeDetailsActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    Recipe recipe;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipedetails);
        databaseHelper = new DatabaseHelper(getApplicationContext());
        recipe = databaseHelper.getRecipeById(getIntent().getIntExtra(AddRecipeFragment.RECIPE_ID_KEY, 1));

        Toolbar toolbar = findViewById(R.id.toolbar);
        if(toolbar != null) {
            setSupportActionBar(toolbar);
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(recipe.getName());

        // TODO da die Datenbank nur Food und nicht RecipeIngredient zurückgibt, ist eine Verrechnung der rezeptbezogenen Mengen nicht möglich
        List<Food> ingredients = databaseHelper.getIngredientsByRecipeId(recipe.getId());
        ListView ingredientView = findViewById(R.id.lv_ingredients);
        ingredientView.setAdapter(new ArrayAdapter<Food>(this, R.layout.list_item, R.id.tv_ingredientName, ingredients));

        TextView description = findViewById(R.id.tv_recipeDescription);
        description.setText(recipe.getDescription());

        TextView totalCaloriesView = findViewById(R.id.tv_totalCalories);
        int totalCalories = 0;
        for(Food ingredient: ingredients) {
            // TODO da die Datenbank nur Food und nicht RecipeIngredient zurückgibt, ist eine Verrechnung der rezeptbezogenen Mengen nicht möglich
            int calories = ingredient.getCalories() * ingredient.getDefaultQuantity();
            totalCalories += calories;
        }
        totalCaloriesView.setText(totalCalories + " kcal");

        FloatingActionButton confirmButton = findViewById(R.id.fab_confirmRecipe);
        int finalTotalCalories = totalCalories;
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                databaseHelper.addEatenRecipe(recipe.getId(), finalTotalCalories, new Date());
                finish();
            }
        });
    }
}
