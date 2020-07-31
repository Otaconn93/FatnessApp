package com.mobilesysteme.fatnessapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.mobilesysteme.fatnessapp.fragments.AddFoodFragment;
import com.mobilesysteme.fatnessapp.fragments.AddRecipeFragment;

/**
 * @author Grabau
 */
public class TabStatusAdapter extends FragmentStateAdapter {

    private final AddFoodFragment addFoodFragment;
    private final AddRecipeFragment addRecipeFragment;

    public TabStatusAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
        addFoodFragment = new AddFoodFragment();
        addRecipeFragment = new AddRecipeFragment();
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
            default:
                return addFoodFragment;
            case 1:
                return addRecipeFragment;
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
