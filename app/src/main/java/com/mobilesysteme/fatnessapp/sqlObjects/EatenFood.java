package com.mobilesysteme.fatnessapp.sqlObjects;

import androidx.annotation.Nullable;

import java.util.Date;

/**
 * The equivalent for the object within the data base
 * Gives informations aboud a specific food that got eaten in an unknown quantity
 * @author HHoffmann
 */
public class EatenFood implements Eaten {

    private int _id;
    private int food_id;
    private int calories;
    private Date date;

    public EatenFood(int _id, int food_id, int calories, Date date) {
        this._id = _id;
        this.food_id = food_id;
        this.calories = calories;
        this.date = date;
    }

    @Override
    public boolean equals(@Nullable Object obj) {

        if (obj == null || obj.getClass() != EatenFood.class) {
            return false;
        }

        return _id == ((EatenFood) obj).getId();
    }

    @Override
    public int getId() {
        return _id;
    }

    @Override
    public int getEatenId() {
        return food_id;
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
