package com.mobilesysteme.fatnessapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.mobilesysteme.fatnessapp.DatabaseHelper;
import com.mobilesysteme.fatnessapp.R;

public class RecipeActivity extends AppCompatActivity {

    private static DatabaseHelper databaseHelper;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        // Setup toolbar and activity title
        Toolbar toolbar = findViewById(R.id.recipe_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Rezept auswählen");
    }
}