package com.mobilesysteme.fatnessapp.sqlObjects;

import androidx.annotation.Nullable;

import java.util.Date;

/**
 * The equivalent for the object within the data base
 * Gives informations aboud a specific recipe that got eaten in an unknown quantity
 * @author Hoffmann
 */
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
    public int getCalories() {
        return calories;
    }

    @Override
    public Date getDate() {
        return date;
    }
}
