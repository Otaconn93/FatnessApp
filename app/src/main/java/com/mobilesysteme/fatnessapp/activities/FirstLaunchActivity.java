package com.mobilesysteme.fatnessapp.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.mobilesysteme.fatnessapp.OnFirstLaunchStepFinished;
import com.mobilesysteme.fatnessapp.R;
import com.mobilesysteme.fatnessapp.preferences.SharedPreferenceUtils;

/**
 * @author Maximilian Grabau
 */
public class FirstLaunchActivity extends FragmentActivity implements OnFirstLaunchStepFinished {

    private NavController navController;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstlaunch);
        navController = Navigation.findNavController(this, R.id.firstlaunch_nav_host_fragment);
    }

    /**
     * Switch to new dashboard activity and close current activity
     */
    @Override
    public void openDashboardActivity() {
        SharedPreferenceUtils.saveFirstLaunch(this, false);
        startActivity(new Intent(this, DashboardActivity.class));
        finish();
    }

    /**
     * {@inheritDoc}
     * Navigates to the next fragment with the given action id
     */
    @Override
    public void onStepFinished(int actionId) {
        navController.navigate(actionId);
    }
}
