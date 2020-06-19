package com.mobilesysteme.fatnessapp.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobilesysteme.fatnessapp.DatabaseHelper;
import com.mobilesysteme.fatnessapp.OnFoodGroupClickListener;
import com.mobilesysteme.fatnessapp.R;
import com.mobilesysteme.fatnessapp.RootFoodGroupAdapter;
import com.mobilesysteme.fatnessapp.SubFoodGroupAdapter;
import com.mobilesysteme.fatnessapp.sqlObjects.FoodGroup;

import java.util.ArrayList;
import java.util.List;

public class AddActivity extends AppCompatActivity implements OnFoodGroupClickListener {

    private DatabaseHelper databaseHelper;
    private RecyclerView subFoodGroupRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        databaseHelper = new DatabaseHelper(getApplicationContext());
        subFoodGroupRecyclerView = findViewById(R.id.rv_subFoodGroups);

        initToolbar();

        initRootFoodGroupRecyclerView();

        initSubFoodGroupRecyclerView();
    }

    /**
     * Creates the RecyclerView with cards for root FoodGroups
     */
    private void initRootFoodGroupRecyclerView() {

        RecyclerView rootFoodGroupRecyclerView = findViewById(R.id.rv_rootFoodGroups);
        rootFoodGroupRecyclerView.setHasFixedSize(true);
        // Setup layout manager
        RecyclerView.LayoutManager gridLayoutManager = new GridLayoutManager(this, 2) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        rootFoodGroupRecyclerView.setLayoutManager(gridLayoutManager);
        // Specify adapter
        RootFoodGroupAdapter rootFoodGroupAdapter = new RootFoodGroupAdapter(databaseHelper.getRootFoodGroups(), this);
        rootFoodGroupRecyclerView.setAdapter(rootFoodGroupAdapter);
    }

    /**
     * Creates the RecyclerView with List Elements for the sub FoodGroups
     */
    private void initSubFoodGroupRecyclerView() {

        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        subFoodGroupRecyclerView.setLayoutManager(linearLayoutManager);
    }

    private void initToolbar() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Nahrungsmittel hinzufügen");
    }

    @Override
    public void onItemClick(FoodGroup foodGroup, int position, boolean isRoot) {

        if(isRoot) { // check if food group has children

            List<FoodGroup> resultSet = new ArrayList<>();
            resultSet.add(foodGroup);
            resultSet.addAll(databaseHelper.getChildFoodGroups(foodGroup.getId()));
            SubFoodGroupAdapter subFoodGroupAdapter = new SubFoodGroupAdapter(resultSet, this);
            subFoodGroupRecyclerView.setAdapter(subFoodGroupAdapter);
        } else {

            Intent intent = new Intent(getBaseContext(), FoodListActivity.class);
            intent.putExtra("POSITION", foodGroup.getId());
            startActivity(intent);
            finish();
        }
    }
}
