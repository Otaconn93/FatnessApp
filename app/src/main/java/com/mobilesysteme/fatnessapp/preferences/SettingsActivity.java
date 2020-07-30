package com.mobilesysteme.fatnessapp.preferences;

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

/**
 * The Activity in which the Settings like the SheredPreferences can be entered by the user
 * @author Hoffmann
 */
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

    /**
     * Used to actually handle the settings
     */
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

        private void bindNumberPreference(DataStore dataStore, String preferenceKey, int value) {

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

        /**
         * Needed to indicate the SettingsFragment where the data is coming from an where to save it to
         */
        public class DataStore extends PreferenceDataStore {

            private UserAttributeHandler userAttributeHandler;

            public DataStore() {
                this.userAttributeHandler = new UserAttributeHandler(getContext());
            }

            @Nullable
            @Override
            public String getString(String key, @Nullable String defValue) {

                Object value = -1;
                switch (key) {
                    case SharedPreferenceUtils.USER_HEIGHT_KEY:
                        value = SharedPreferenceUtils.getUserHeight(getContext());
                        break;
                    case SharedPreferenceUtils.USER_WEIGHT_KEY:
                        value = SharedPreferenceUtils.getUserWeight(getContext());
                        break;
                    case SharedPreferenceUtils.USER_AGE_KEY:
                        value = SharedPreferenceUtils.getUserAge(getContext());
                        break;
                    case SharedPreferenceUtils.USER_GENDER_KEY:
                        value = SharedPreferenceUtils.getUserGender(getContext());
                        break;
                    case SharedPreferenceUtils.USER_TARGETWEIGHT_KEY:
                        value = SharedPreferenceUtils.getUserTargetWeight(getContext());
                        break;
                    case SharedPreferenceUtils.USER_DEADLINE_KEY:
                        value = DateUtils.getDateAsString(SharedPreferenceUtils.getUserDeadline(getContext()));
                        break;
                }

                return String.valueOf(value);
            }

            @Override
            public void putString(String key, @Nullable String value) {

                switch (key) {
                    case SharedPreferenceUtils.USER_HEIGHT_KEY:

                        if (!userAttributeHandler.handleSaveHeight(value)) {

                            String height = String.valueOf(SharedPreferenceUtils.getUserHeight(getContext()));
                            ((EditTextPreference)findPreference(SharedPreferenceUtils.USER_HEIGHT_KEY)).setText(height);
                        }

                        break;
                    case SharedPreferenceUtils.USER_WEIGHT_KEY:

                        if (!userAttributeHandler.handleSaveWeightNow(value)) {

                            String weight = String.valueOf(SharedPreferenceUtils.getUserWeight(getContext()));
                            ((EditTextPreference)findPreference(SharedPreferenceUtils.USER_WEIGHT_KEY)).setText(weight);
                        }

                        break;
                    case SharedPreferenceUtils.USER_AGE_KEY:

                        if (!userAttributeHandler.handleSaveAge(value)) {

                            String age = String.valueOf(SharedPreferenceUtils.getUserAge(getContext()));
                            ((EditTextPreference)findPreference(SharedPreferenceUtils.USER_AGE_KEY)).setText(age);
                        }

                        break;
                    case SharedPreferenceUtils.USER_GENDER_KEY:

                        if (!userAttributeHandler.handleSaveGender(Gender.findGenderById(Integer.parseInt(value)))) {

                            String genderId = String.valueOf(SharedPreferenceUtils.getUserGender(getContext()).getId());
                            ((ListPreference)findPreference(SharedPreferenceUtils.USER_GENDER_KEY)).setValue(genderId);
                        }

                        break;
                    case SharedPreferenceUtils.USER_TARGETWEIGHT_KEY:

                        if (!userAttributeHandler.handleSaveTargetWeight(value)) {

                            String targetWeight = String.valueOf(SharedPreferenceUtils.getUserTargetWeight(getContext()));
                            ((EditTextPreference)findPreference(SharedPreferenceUtils.USER_TARGETWEIGHT_KEY)).setText(targetWeight);
                        }

                        break;
                    case SharedPreferenceUtils.USER_DEADLINE_KEY:

                        userAttributeHandler.handleSaveDeadline(DateUtils.getDateFromString(value));
                        String deadline = DateUtils.getDateAsString(SharedPreferenceUtils.getUserDeadline(getContext()));
                        ((EditTextPreference)findPreference(SharedPreferenceUtils.USER_DEADLINE_KEY)).setText(deadline);

                        break;
                }
            }
        }
    }
}