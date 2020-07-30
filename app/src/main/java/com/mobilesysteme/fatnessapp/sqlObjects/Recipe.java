package com.mobilesysteme.fatnessapp.sqlObjects;

import androidx.annotation.Nullable;

/**
 * The equivalent for the object within the data base
 * Contains information for a specific Recipe
 * @author Hoffmann
 */
public class Recipe {

    private int _id;
    private String name;
    private String description;

    public Recipe(int _id, String name, String description) {
        this._id = _id;
        this.name = name;
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

    public String getDescription() {
        return description;
    }
}
