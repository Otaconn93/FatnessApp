package com.mobilesysteme.fatnessapp;

/**
 * Used by the FirstLaunchActivity for communication with it's fragments.
 * @author Maximilian Grabau
 */
public interface OnFirstLaunchStepFinished {
    /**
     * Indicates that a step of the first launch flow is completed.
     */
    void onStepFinished(int actionId);

    /**
     * Indicates that the FirstLaunchFlow is finished and the dashboard activity should be started.
     */
    void openDashboardActivity();
}
