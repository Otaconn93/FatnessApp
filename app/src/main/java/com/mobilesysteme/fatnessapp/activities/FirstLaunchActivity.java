package com.mobilesysteme.fatnessapp.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.mobilesysteme.fatnessapp.FirstLaunchFlow;
import com.mobilesysteme.fatnessapp.OnFirstLaunchStepFinished;
import com.mobilesysteme.fatnessapp.R;
import com.mobilesysteme.fatnessapp.preferences.SharedPreferenceUtils;

import java.lang.reflect.Constructor;

/**
 * @author Grabau, Hoffmann
 */
public class FirstLaunchActivity extends AppCompatActivity implements OnFirstLaunchStepFinished {

    private FirstLaunchFlow firstLaunchFlow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstlaunch);
        init();
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();

        firstLaunchFlow.decrementCursor();
        replaceFragment(getFragmentFromConstructor(firstLaunchFlow.getFragment()), false);
    }

    /**
     * Initializes the new FirstLaunchActivity and sets up the fragment queue.
     */
    public void init() {

        firstLaunchFlow = FirstLaunchFlow.getInstance();
        replaceFragment(getFragmentFromConstructor(firstLaunchFlow.getFragment()), false);
    }

    private Fragment getFragmentFromConstructor(Constructor constructor) {

        if (constructor == null) {
            return null;
        }

        try {
            return (Fragment) constructor.newInstance(this);
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Replaces the currently active fragment within the fragment container of FirstLaunchActivity
     * @param fragment Instance of the new fragment to be displayed
     * @param addToBackStack if the fragment should be added to the backstack
     */
    public void replaceFragment(Fragment fragment, boolean addToBackStack) {

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.firstlaunchFragmentContainer, fragment);
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragment.toString());
        }
        fragmentTransaction.commit();
    }

    /**
     * Switch to new dashboard activity and close current activity
     */
    public void openDashboardActivity() {

        SharedPreferenceUtils.saveFirstLaunch(this, false);
        startActivity(new Intent(this, DashboardActivity.class));
        finish();
    }

    /**
     * {@inheritDoc}
     * Instantiates a new fragment from the class given by the fragmentQueue and loads it into the activity
     */
    @Override
    public void onStepFinished() {

        firstLaunchFlow.incrementCursor();
        Fragment fragment = getFragmentFromConstructor(firstLaunchFlow.getFragment());
        if(fragment != null) {
            replaceFragment(fragment, true);
        } else {
            openDashboardActivity();
        }
    }

    /**
     * {@inheritDoc}
     * Instantiates a new fragment from the class given by the fragmentQueue and loads it into the activity
     */
    @Override
    public void onStepCanceled() {

        Fragment fragment = getFragmentFromConstructor(firstLaunchFlow.goBack());
        if(fragment != null) {
            replaceFragment(fragment, true);
        }
    }
}
