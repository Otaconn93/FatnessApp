package com.mobilesysteme.fatnessapp.sqlObjects;

import androidx.annotation.Nullable;

/**
 * The equivalent for the object within the data base
 * Contains all needed information for a specific Unit in which e.g. the quantity of a Food can be measured
 * @author Hoffmann
 */
public class Unit {

    private int _id;
    private String name;
    private String token;

    public Unit(int _id, String name, String token) {
        this._id = _id;
        this.name = name;
        this.token = token;
    }

    @Override
    public boolean equals(@Nullable Object obj) {

        if (obj == null || obj.getClass() != Unit.class) {
            return false;
        }

        return _id == ((Unit) obj).getId();
    }

    public int getId() {
        return _id;
    }

    public String getName() {
        return name;
    }

    public String getToken() {
        return token;
    }
}
