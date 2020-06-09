package com.mobilesysteme.fatnessapp.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.mobilesysteme.fatnessapp.OnFirstLaunchStepFinished;
import com.mobilesysteme.fatnessapp.SharedPreferenceUtils;
import com.mobilesysteme.fatnessapp.fragments.AgeFragment;
import com.mobilesysteme.fatnessapp.fragments.CurrentWeightFragment;
import com.mobilesysteme.fatnessapp.R;
import com.mobilesysteme.fatnessapp.fragments.GenderFragment;
import com.mobilesysteme.fatnessapp.fragments.HeightFragment;
import com.mobilesysteme.fatnessapp.fragments.TargetWeightFragment;
import com.mobilesysteme.fatnessapp.fragments.TimeGoalFragment;
import com.mobilesysteme.fatnessapp.fragments.WelcomeFragment;

import java.lang.reflect.Constructor;
import java.util.LinkedList;
import java.util.Queue;

public class FirstLaunchActivity extends AppCompatActivity implements OnFirstLaunchStepFinished {

    private Queue<Class> fragmentQueue; // queue which handles the order of fragments to load

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstlaunch);
        init();
    }

    /**
     * Initializes the new FirstLaunchActivity and sets up the fragment queue.
     */
    public void init() {
        fragmentQueue = new LinkedList<>();

        // Order of First Launch Flow fragments
        fragmentQueue.add(GenderFragment.class);
        fragmentQueue.add(AgeFragment.class);
        fragmentQueue.add(HeightFragment.class);
        fragmentQueue.add(CurrentWeightFragment.class);
        fragmentQueue.add(TargetWeightFragment.class);
        fragmentQueue.add(TimeGoalFragment.class);

        replaceFragment(new WelcomeFragment(this), false);
    }

    /**
     * Replaces the currently active fragment within the fragment container of FirstLaunchActivity
     * @param fragment Instance of the new fragment to be displayed
     * @param addToBackStack if the fragment should be added to the backstack
     */
    public void replaceFragment(Fragment fragment, boolean addToBackStack) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
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
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
        finish();
    }

    /**
     * {@inheritDoc}
     * Instantiates a new fragment from the class given by the fragmentQueue and loads it into the activity
     */
    @Override
    public void onStepFinished() {
        try {
            if(fragmentQueue.size() >= 1) {
                Class nextFragment = fragmentQueue.poll();
                Constructor constructor = nextFragment.getConstructor(OnFirstLaunchStepFinished.class);
                replaceFragment((Fragment) constructor.newInstance(this), true);
            } else {
                openDashboardActivity();
            }
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
        }
    }
}