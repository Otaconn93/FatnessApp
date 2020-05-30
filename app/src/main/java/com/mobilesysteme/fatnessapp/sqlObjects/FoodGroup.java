package com.mobilesysteme.fatnessapp.sqlObjects;

import androidx.annotation.Nullable;

public class FoodGroup {

    private int _id;
    private int parent_id;
    private String name;

    public FoodGroup(int _id, int parent_id, String name) {
        this._id = _id;
        this.parent_id = parent_id;
        this.name = name;
    }

    @Override
    public boolean equals(@Nullable Object obj) {

        if (obj == null || obj.getClass() != FoodGroup.class) {
            return false;
        }

        return _id == ((FoodGroup) obj).getId();
    }

    public int getId() {
        return _id;
    }

    public int getParentId() {
        return parent_id;
    }

    public String getName() {
        return name;
    }
}
