package com.mobilesysteme.fatnessapp;

import androidx.fragment.app.Fragment;

import java.lang.reflect.InvocationTargetException;

public interface OnFirstLaunchStepFinished {
    /**
     * Used by FirstLaunchActivity to retrieve a status from it's fragments.
     * Indicates that a step of the first launch flow is completed.
     */
    void onStepFinished();
}
