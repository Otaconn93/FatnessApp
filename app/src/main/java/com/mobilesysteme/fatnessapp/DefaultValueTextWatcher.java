package com.mobilesysteme.fatnessapp;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.Toast;

import com.mobilesysteme.fatnessapp.sqlObjects.Food;

class DefaultValueTextWatcher implements TextWatcher {
    private final Food currentFood;
    private final OnFoodAddListener listener;
    private final Context context;
    private EditText editText;
    private final String oldValue;

    public DefaultValueTextWatcher(Food currentFood, OnFoodAddListener listener, Context context, EditText editText) {
        this.currentFood = currentFood;
        this.listener = listener;
        this.context = context;
        this.editText = editText;
        oldValue = editText.getText().toString();
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
        int number = 0;
        try {
            number = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            return false;
        }
        if(number>10000 || number < 0){
            return false;
        }else{
            return true;
        }
    }
}
