package com.mobilesysteme.fatnessapp.sqlObjects;

import androidx.annotation.Nullable;

import java.util.Date;

/**
 * The interface defining the necessary methods for something eaten
 * @author HHoffmann
 */
public interface Eaten {

    boolean equals(@Nullable Object obj);

    int getId();

    int getEatenId();

    int getCalories();

    Date getDate();
}
