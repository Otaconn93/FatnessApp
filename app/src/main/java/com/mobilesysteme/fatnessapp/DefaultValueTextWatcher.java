package com.mobilesysteme.fatnessapp;

import android.text.Editable;
import android.text.TextWatcher;

import com.mobilesysteme.fatnessapp.sqlObjects.Food;

class DefaultValueTextWatcher implements TextWatcher {
    private final Food currentFood;
    private final OnFoodAddListener listener;

    public DefaultValueTextWatcher(Food currentFood, OnFoodAddListener listener) {
        this.currentFood = currentFood;
        this.listener = listener;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        String defaultUnit = String.valueOf(currentFood.getDefaultQuantity());
        if(!defaultUnit.equals(String.valueOf(s))) {
            listener.isDefaultChanged(currentFood, Integer.parseInt(String.valueOf(s)));
        }
    }
}
