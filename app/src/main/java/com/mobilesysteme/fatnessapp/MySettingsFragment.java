package com.mobilesysteme.fatnessapp;

import android.os.Bundle;

import androidx.preference.PreferenceFragmentCompat;

class MySettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.settings, rootKey);
    }
}

