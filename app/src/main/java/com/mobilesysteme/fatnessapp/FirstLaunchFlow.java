package com.mobilesysteme.fatnessapp;

import com.mobilesysteme.fatnessapp.fragments.AgeFragment;
import com.mobilesysteme.fatnessapp.fragments.CurrentWeightFragment;
import com.mobilesysteme.fatnessapp.fragments.GenderFragment;
import com.mobilesysteme.fatnessapp.fragments.HeightFragment;
import com.mobilesysteme.fatnessapp.fragments.TargetWeightFragment;
import com.mobilesysteme.fatnessapp.fragments.TimeGoalFragment;
import com.mobilesysteme.fatnessapp.fragments.WelcomeFragment;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;

public class FirstLaunchFlow {

    private static FirstLaunchFlow firstLaunchFlow;

    private List<Class> fragments;
    private int currentIndex;

    private FirstLaunchFlow() {

        this.fragments = new ArrayList<>();
        this.currentIndex = 0;

        fillFragments();
    }

    public static FirstLaunchFlow getInstance() {

        if (FirstLaunchFlow.firstLaunchFlow == null) {

            FirstLaunchFlow.firstLaunchFlow = new FirstLaunchFlow();
            return firstLaunchFlow;
        } else {

            return FirstLaunchFlow.firstLaunchFlow;
        }
    }

    public void resetFirstLaunchFlow() {
        this.currentIndex = 0;
    }

    public Constructor getFragment() {

        if (fragments.size() > currentIndex) {

            try {
                return fragments.get(currentIndex).getConstructor(OnFirstLaunchStepFinished.class);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
                return null;
            }
        } else {
            return null;
        }
    }

    public void incrementCursor() {
        currentIndex++;
    }

    public void decrementCursor() {
        if (currentIndex > 0) {
            currentIndex--;
        }
    }

    public Constructor goBack() {

        if (currentIndex >= 0) {
            return null;
        } else {
            currentIndex--;
            try {
                return fragments.get(currentIndex).getConstructor(OnFirstLaunchStepFinished.class);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    private void fillFragments() {

        fragments.add(WelcomeFragment.class);
        fragments.add(GenderFragment.class);
        fragments.add(AgeFragment.class);
        fragments.add(HeightFragment.class);
        fragments.add(CurrentWeightFragment.class);
        fragments.add(TargetWeightFragment.class);
        fragments.add(TimeGoalFragment.class);
    }
}
