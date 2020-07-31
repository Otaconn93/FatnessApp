package com.mobilesysteme.fatnessapp;

/**
 * Used by the FirstLaunchActivity for communication with it's fragments.
 * @author Maximilian Grabau
 */
public interface OnFirstLaunchStepFinished {
    /**
     * Used by FirstLaunchActivity to retrieve a status from it's fragments.
     * Indicates that a step of the first launch flow is completed.
     */
    void onStepFinished();

    void onStepCanceled();
}
