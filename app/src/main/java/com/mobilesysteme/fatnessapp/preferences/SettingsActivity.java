package com.mobilesysteme.fatnessapp.preferences;

import android.content.Context;
import android.os.Bundle;
import android.text.InputType;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.preference.EditTextPreference;
import androidx.preference.ListPreference;
import androidx.preference.PreferenceDataStore;
import androidx.preference.PreferenceFragmentCompat;

import com.mobilesysteme.fatnessapp.DateUtils;
import com.mobilesysteme.fatnessapp.Gender;
import com.mobilesysteme.fatnessapp.R;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Einstellungen");
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings, new SettingsFragment())
                .commit();
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

            setPreferencesFromResource(R.xml.root_preferences, rootKey);

            DataStore dataStore = new DataStore();
            bindNumberPreference(dataStore, SharedPreferenceUtils.USER_HEIGHT_KEY, SharedPreferenceUtils.getUserHeight(getContext()));
            bindNumberPreference(dataStore, SharedPreferenceUtils.USER_WEIGHT_KEY, SharedPreferenceUtils.getUserWeight(getContext()));
            bindNumberPreference(dataStore, SharedPreferenceUtils.USER_AGE_KEY, SharedPreferenceUtils.getUserAge(getContext()));
            bindListPreferences(dataStore, SharedPreferenceUtils.USER_GENDER_KEY, SharedPreferenceUtils.getUserGender(getContext()));
            bindNumberPreference(dataStore, SharedPreferenceUtils.USER_TARGETWEIGHT_KEY,SharedPreferenceUtils.getUserTargetWeight(getContext()));
            bindDatePreference(dataStore, SharedPreferenceUtils.USER_DEADLINE_KEY, DateUtils.getDateAsString(SharedPreferenceUtils.getUserDeadline(getContext())));
        }

        private void bindListPreferences(DataStore dataStore, String preferenceKey, Gender gender) {

            ListPreference preference = findPreference(preferenceKey);
            if (preference != null) {

                preference.setValue(String.valueOf(gender.getId()));
                preference.setEntries(Gender.getTexts());
                preference.setEntryValues(Gender.getIds());
                preference.setPreferenceDataStore(dataStore);
            }
        }

        private void bindNumberPreference(DataStore dataStore, String preferenceKey, Integer value) {

            EditTextPreference preference = findPreference(preferenceKey);
            if (preference != null) {
                preference.setText(String.valueOf(value));
                preference.setOnBindEditTextListener(editText -> editText.setInputType(InputType.TYPE_CLASS_NUMBER));
                preference.setPreferenceDataStore(dataStore);
            }
        }

        private void bindDatePreference(DataStore dataStore, String preferenceKey, String value) {

            EditTextPreference preference = findPreference(preferenceKey);
            if (preference != null) {
                preference.setText(value);
                preference.setOnBindEditTextListener(editText -> editText.setInputType(InputType.TYPE_CLASS_DATETIME));
                preference.setPreferenceDataStore(dataStore);
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
                    case SharedPreferenceUtils.USER_GENDER_KEY:
                        value = SharedPreferenceUtils.getUserGender(context);
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
                        SharedPreferenceUtils.saveUserWeightNow(context, Integer.valueOf(value));
                        break;
                    case SharedPreferenceUtils.USER_AGE_KEY:
                        SharedPreferenceUtils.saveUserAge(context, Integer.valueOf(value));
                        break;
                    case SharedPreferenceUtils.USER_GENDER_KEY:
                        SharedPreferenceUtils.saveUserGender(context, Gender.findGenderById(Integer.valueOf(value)));
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