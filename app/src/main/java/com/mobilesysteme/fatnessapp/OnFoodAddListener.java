package com.mobilesysteme.fatnessapp;

import com.mobilesysteme.fatnessapp.sqlObjects.Food;

public interface OnFoodAddListener {
    void onFoodAdd(Food food, int amount);
    void onFoodUnchecked(Food food);
}
