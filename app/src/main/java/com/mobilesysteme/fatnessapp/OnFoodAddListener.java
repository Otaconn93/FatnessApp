package com.mobilesysteme.fatnessapp;

import com.mobilesysteme.fatnessapp.sqlObjects.Food;

public interface OnFoodAddListener {
    void addFood(Food food, int caloriesSum);
    void rmFood(Food food);
    void isDefaultChanged(Food food, int changedValue);
}