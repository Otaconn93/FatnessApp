package com.mobilesysteme.fatnessapp.sqlObjects;

import androidx.annotation.Nullable;

public class Recipe {

    private int _id;
    private String name;
    private int default_calories;
    private String description;

    public Recipe(int _id, String name, int default_calories, String description) {
        this._id = _id;
        this.name = name;
        this.default_calories = default_calories;
        this.description = description;
    }

    @Override
    public boolean equals(@Nullable Object obj) {

        if (obj == null || obj.getClass() != Recipe.class) {
            return false;
        }

        return _id == ((Recipe) obj).getId();
    }

    public int getId() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public int getDefaultCalories() {
        return default_calories;
    }

    public String getDescription() {
        return description;
    }
}
