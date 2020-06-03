package com.mobilesysteme.fatnessapp;

import androidx.fragment.app.Fragment;

import java.lang.reflect.InvocationTargetException;

public interface OnFirstLaunchStepFinished {
    void onStepFinished() throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException;
}
