package com.mobilesysteme.fatnessapp;

public enum Gender {

    MALE(0),
    FEMALE(1);

    int id;

    Gender(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
