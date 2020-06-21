package com.mobilesysteme.fatnessapp.sqlObjects;

import androidx.annotation.Nullable;

import java.util.Date;

public interface Eaten {

    boolean equals(@Nullable Object obj);

    int getId();

    int getEatenId();

    int getCalories();

    Date getDate();
}
