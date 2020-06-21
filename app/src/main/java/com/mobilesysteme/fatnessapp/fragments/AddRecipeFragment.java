package com.mobilesysteme.fatnessapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobilesysteme.fatnessapp.DatabaseHelper;
import com.mobilesysteme.fatnessapp.OnRecipeClickListener;
import com.mobilesysteme.fatnessapp.R;
import com.mobilesysteme.fatnessapp.RecipeAdapter;
import com.mobilesysteme.fatnessapp.activities.RecipeDetailsActivity;
import com.mobilesysteme.fatnessapp.sqlObjects.Recipe;

import java.util.ArrayList;
import java.util.List;

public class AddRecipeFragment extends Fragment implements OnRecipeClickListener {

    public static final String RECIPE_ID_KEY = "RECIPE_ID";
    private DatabaseHelper databaseHelper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_addrecipe, container, false);
        databaseHelper = new DatabaseHelper(this.getContext());

        RecyclerView recipeRecyclerView = view.findViewById(R.id.rv_recipes);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        recipeRecyclerView.setLayoutManager(linearLayoutManager);
        RecipeAdapter recipeAdapter = new RecipeAdapter(generateList(), this);
        recipeRecyclerView.setAdapter(recipeAdapter);

        return view;
    }

    @Override
    public void onRecipeClick(Recipe recipe) {
        Intent intent = new Intent(getActivity().getBaseContext(), RecipeDetailsActivity.class);
        intent.putExtra(RECIPE_ID_KEY, recipe.getId());
        startActivity(intent);
    }

    public List<Recipe> generateList() {
        List<Recipe> resultSet = new ArrayList<>();
        int i = 1;
        Recipe result = databaseHelper.getRecipeById(i);
        while(result != null) {
            resultSet.add(result);
            i++;
            result = databaseHelper.getRecipeById(i);
        }
        return resultSet;
    }
}
