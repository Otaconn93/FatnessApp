package com.mobilesysteme.fatnessapp;

import com.mobilesysteme.fatnessapp.sqlObjects.FoodGroup;

public interface OnFoodGroupClickListener {
    void onItemClick(FoodGroup foodGroup, int position, boolean isRoot);
}
