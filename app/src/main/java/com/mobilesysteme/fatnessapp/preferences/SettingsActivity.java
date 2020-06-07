package com.mobilesysteme.fatnessapp.preferences;

import android.content.Context;
import android.os.Bundle;
import android.text.InputType;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.EditTextPreference;
import androidx.preference.PreferenceDataStore;
import androidx.preference.PreferenceFragmentCompat;

import com.mobilesysteme.fatnessapp.DateUtils;
import com.mobilesysteme.fatnessapp.R;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings, new SettingsFragment())
                .commit();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

            setPreferencesFromResource(R.xml.root_preferences, rootKey);

            DataStore dataStore = new DataStore();
            bindNumberPreference(dataStore, SharedPreferenceUtils.USER_HEIGHT_KEY, String.valueOf(SharedPreferenceUtils.getUserHeight(getContext())));
            bindNumberPreference(dataStore, SharedPreferenceUtils.USER_WEIGHT_KEY, String.valueOf(SharedPreferenceUtils.getUserWeight(getContext())));
            bindNumberPreference(dataStore, SharedPreferenceUtils.USER_AGE_KEY, String.valueOf(SharedPreferenceUtils.getUserAge(getContext())));
            bindNumberPreference(dataStore, SharedPreferenceUtils.USER_TARGETWEIGHT_KEY, String.valueOf(SharedPreferenceUtils.getUserTargetWeight(getContext())));

            bindDatePreference(dataStore, SharedPreferenceUtils.USER_DEADLINE_KEY, DateUtils.getDateAsString(SharedPreferenceUtils.getUserDeadline(getContext())));
        }

        private void bindNumberPreference(DataStore dataStore, String preferenceKey, String value) {

            EditTextPreference weightPreference = findPreference(preferenceKey);
            if (weightPreference != null) {
                weightPreference.setText(value);
                weightPreference.setOnBindEditTextListener(editText -> editText.setInputType(InputType.TYPE_CLASS_NUMBER));
                weightPreference.setPreferenceDataStore(dataStore);
            }
        }

        private void bindDatePreference(DataStore dataStore, String preferenceKey, String value) {

            EditTextPreference weightPreference = findPreference(preferenceKey);
            if (weightPreference != null) {
                weightPreference.setText(value);
                weightPreference.setOnBindEditTextListener(editText -> editText.setInputType(InputType.TYPE_CLASS_DATETIME));
                weightPreference.setPreferenceDataStore(dataStore);
            }
        }

        public class DataStore extends PreferenceDataStore {

            @Nullable
            @Override
            public String getString(String key, @Nullable String defValue) {

                Context context = getContext();
                if (context == null) {
                    return null;
                }

                Object value = -1;
                switch (key) {
                    case SharedPreferenceUtils.USER_HEIGHT_KEY:
                        value = SharedPreferenceUtils.getUserHeight(context);
                        break;
                    case SharedPreferenceUtils.USER_WEIGHT_KEY:
                        value = SharedPreferenceUtils.getUserWeight(context);
                        break;
                    case SharedPreferenceUtils.USER_AGE_KEY:
                        value = SharedPreferenceUtils.getUserAge(context);
                        break;
                    case SharedPreferenceUtils.USER_TARGETWEIGHT_KEY:
                        value = SharedPreferenceUtils.getUserTargetWeight(context);
                        break;
                    case SharedPreferenceUtils.USER_DEADLINE_KEY:
                        value = DateUtils.getDateAsString(SharedPreferenceUtils.getUserDeadline(context));
                        break;
                }

                return String.valueOf(value);
            }

            @Override
            public void putString(String key, @Nullable String value) {

                Context context = getContext();
                if (context == null) {
                    return;
                }

                switch (key) {
                    case SharedPreferenceUtils.USER_HEIGHT_KEY:
                        SharedPreferenceUtils.saveUserHeight(context, Integer.valueOf(value));
                        break;
                    case SharedPreferenceUtils.USER_WEIGHT_KEY:
                        SharedPreferenceUtils.saveUserWeight(context, Integer.valueOf(value));
                        break;
                    case SharedPreferenceUtils.USER_AGE_KEY:
                        SharedPreferenceUtils.saveUserAge(context, Integer.valueOf(value));
                        break;
                    case SharedPreferenceUtils.USER_TARGETWEIGHT_KEY:
                        SharedPreferenceUtils.saveUserTargetWeight(context, Integer.valueOf(value));
                        break;
                    case SharedPreferenceUtils.USER_DEADLINE_KEY:
                        SharedPreferenceUtils.saveUserDeadline(context, DateUtils.getDateFromString(value));
                        break;
                }
            }
        }
    }
}