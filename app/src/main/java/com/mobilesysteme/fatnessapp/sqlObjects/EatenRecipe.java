package com.mobilesysteme.fatnessapp.sqlObjects;

import androidx.annotation.Nullable;

import java.util.Date;

public class EatenRecipe implements Eaten {

    private int _id;
    private int recipe_id;
    private int calories;
    private Date date;

    public EatenRecipe(int _id, int recipe_id, int calories, Date date) {
        this._id = _id;
        this.recipe_id = recipe_id;
        this.calories = calories;
        this.date = date;
    }

    @Override
    public boolean equals(@Nullable Object obj) {

        if (obj == null || obj.getClass() != EatenRecipe.class) {
            return false;
        }

        return _id == ((EatenRecipe) obj).getId();
    }

    @Override
    public int getId() {
        return _id;
    }

    @Override
    public int getEatenId() {
        return recipe_id;
    }

    @Override
    public int getCaloriesPer100g() {
        return calories;
    }

    @Override
    public double getCaloriesForAmount(int amount) {
        return Integer.valueOf(calories).doubleValue() / 100 * amount;
    }

    @Override
    public Date getDate() {
        return date;
    }
}
