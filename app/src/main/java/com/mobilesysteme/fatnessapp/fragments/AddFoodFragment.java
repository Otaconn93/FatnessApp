package com.mobilesysteme.fatnessapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mobilesysteme.fatnessapp.DatabaseHelper;
import com.mobilesysteme.fatnessapp.OnFoodGroupClickListener;
import com.mobilesysteme.fatnessapp.R;
import com.mobilesysteme.fatnessapp.RootFoodGroupAdapter;
import com.mobilesysteme.fatnessapp.SubFoodGroupAdapter;
import com.mobilesysteme.fatnessapp.activities.FoodListActivity;
import com.mobilesysteme.fatnessapp.sqlObjects.FoodGroup;

import java.util.ArrayList;
import java.util.List;

public class AddFoodFragment extends Fragment implements OnFoodGroupClickListener {

    private DatabaseHelper databaseHelper;
    private RecyclerView subFoodGroupRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_addfood, container, false);
        databaseHelper = new DatabaseHelper(this.getContext());
        subFoodGroupRecyclerView = view.findViewById(R.id.rv_subFoodGroups);
        initRootFoodGroupRecyclerView(view);
        initSubFoodGroupRecyclerView();
        return view;
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
            Intent intent = new Intent(getActivity().getBaseContext(), FoodListActivity.class);
            intent.putExtra("POSITION", foodGroup.getId());
            startActivity(intent);
        }
    }

    /**
     * Creates the RecyclerView with cards for root FoodGroups
     */
    private void initRootFoodGroupRecyclerView(View view) {

        RecyclerView rootFoodGroupRecyclerView = view.findViewById(R.id.rv_rootFoodGroups);
        rootFoodGroupRecyclerView.setHasFixedSize(true);
        // Setup layout manager
        RecyclerView.LayoutManager gridLayoutManager = new GridLayoutManager(this.getContext(), 2) {
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

        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        subFoodGroupRecyclerView.setLayoutManager(linearLayoutManager);
    }
}
