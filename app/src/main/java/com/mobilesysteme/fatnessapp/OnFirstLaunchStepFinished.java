package com.mobilesysteme.fatnessapp;

public interface OnFirstLaunchStepFinished {
    /**
     * Used by FirstLaunchActivity to retrieve a status from it's fragments.
     * Indicates that a step of the first launch flow is completed.
     */
    void onStepFinished();
}
