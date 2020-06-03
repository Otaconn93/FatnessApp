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
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class FirstLaunchActivity extends AppCompatActivity implements OnFirstLaunchStepFinished {

    Queue<Class> fragmentQueue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstlaunch);
        try {
            init();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void init() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        fragmentQueue = new LinkedList<>();

        // Order of First Launch Flow
        fragmentQueue.add(GenderFragment.class);
        fragmentQueue.add(AgeFragment.class);
        fragmentQueue.add(HeightFragment.class);
        fragmentQueue.add(CurrentWeightFragment.class);
        fragmentQueue.add(TargetWeightFragment.class);
        fragmentQueue.add(TimeGoalFragment.class);

        replaceFragment(new WelcomeFragment(this), false);
    }

    public void replaceFragment(Fragment fragment, boolean addToBackStack) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.firstlaunchFragmentContainer, fragment);
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(fragment.toString());
        }
        fragmentTransaction.commit();
    }

    public void openDashboardActivity() {
        SharedPreferenceUtils.saveFirstLaunch(this, false);
        Intent intent = new Intent(this, DashboardActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onStepFinished() throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        if(fragmentQueue.size() >= 1) {
            Class nextFragment = fragmentQueue.poll();
            Constructor constructor = nextFragment.getConstructor(OnFirstLaunchStepFinished.class);
            replaceFragment((Fragment) constructor.newInstance(this), true);
        } else {
            openDashboardActivity();
        }
    }
}
