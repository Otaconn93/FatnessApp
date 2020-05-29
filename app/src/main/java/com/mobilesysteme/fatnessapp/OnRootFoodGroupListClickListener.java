package com.mobilesysteme.fatnessapp;

import com.mobilesysteme.fatnessapp.sqlObjects.FoodGroup;

public interface OnRootFoodGroupListClickListener {
    void onItemClick(FoodGroup foodGroup, int position);
}
