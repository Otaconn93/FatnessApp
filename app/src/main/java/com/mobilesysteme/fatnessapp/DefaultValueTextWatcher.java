package com.mobilesysteme.fatnessapp;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import com.mobilesysteme.fatnessapp.sqlObjects.Food;

public class DefaultValueTextWatcher implements TextWatcher {

    private final Food currentFood;
    private final OnFoodAddListener listener;
    private final Context context;
    private final EditText editText;
    private final String oldValue;

    public DefaultValueTextWatcher(Food currentFood, OnFoodAddListener listener, Context context, EditText editText) {
        this.currentFood = currentFood;
        this.listener = listener;
        this.context = context;
        this.editText = editText;
        this.oldValue = editText.getText().toString();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) { }

    @Override
    public void afterTextChanged(Editable s) {
        if(!checkValidInputs(String.valueOf(s))){
            Toast.makeText(context, "Der eingegebene Wert ist ungültig! (Gramm darf nicht negativ sein! " +
                    "Gramm darf nicht höher sein als 10kg! Gramm darf nur aus natürlichen Zahlen bestehen!)",
                    Toast.LENGTH_LONG).show();
            editText.setText(oldValue);
        }else{
            String defaultUnit = String.valueOf(currentFood.getDefaultQuantity());
            if (!defaultUnit.equals(String.valueOf(s))) {
                listener.isDefaultChanged(currentFood, Integer.parseInt(String.valueOf(s)));
            }
        }
    }

    private boolean checkValidInputs(String input){

        try {
            int number = Integer.parseInt(input);
            return (number >= 0 && number <= 10000);
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
