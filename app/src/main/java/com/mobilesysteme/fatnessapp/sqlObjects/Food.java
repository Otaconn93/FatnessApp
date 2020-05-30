package com.mobilesysteme.fatnessapp.sqlObjects;

import androidx.annotation.Nullable;

public class Food {

    private int _id;
    private int group_id;
    private String name;
    private int unit_id;
    private int calories;
    private int default_quantity;

    public Food(int _id, int group_id, String name, int calories, int unit_id, int default_quantity) {
        this._id = _id;
        this.group_id = group_id;
        this.name = name;
        this.unit_id = unit_id;
        this.calories = calories;
        this.default_quantity = default_quantity;
    }

    @Override
    public boolean equals(@Nullable Object obj) {

        if (obj == null || obj.getClass() != Food.class) {
            return false;
        }

        return _id == ((Food) obj).getId();
    }

    public int getId() {
        return _id;
    }

    public int getGroupId() {
        return group_id;
    }

    public String getName() {
        return name;
    }

    public int getUnitId() {
        return unit_id;
    }

    public int getCalories() {
        return calories;
    }

    public int getDefaultQuantity() {
        return default_quantity;
    }
}
