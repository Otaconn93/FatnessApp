package com.mobilesysteme.fatnessapp;

import com.mobilesysteme.fatnessapp.sqlObjects.Food;

public interface OnFoodCheckListener {
    void onFoodChecked(Food food);
    void onFoodUnchecked(Food food);
}
