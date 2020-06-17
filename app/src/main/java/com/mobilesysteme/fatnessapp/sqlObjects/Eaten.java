package com.mobilesysteme.fatnessapp.sqlObjects;

import androidx.annotation.Nullable;

import java.util.Date;

public interface Eaten {

    boolean equals(@Nullable Object obj);

    int getId();

    int getEatenId();

    /**
     * @return the calories for 100g of the Eaten
     */
    int getCaloriesPer100g();

    /**
     * @return the calories for a specific amount in g of the Eaten
     */
    double getCaloriesForAmount(int amount);

    Date getDate();
}
