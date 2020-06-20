package com.mobilesysteme.fatnessapp.preferences;

import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings, new SettingsFragment())
                .commit();
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {

            setPreferencesFromResource(R.xml.root_preferences, rootKey);

            DataStore dataStore = new DataStore(getContext());
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

            private Context context;

            public DataStore(Context context) {
                this.context = context;
            }

            @Nullable
            @Override
            public String getString(String key, @Nullable String defValue) {

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

                switch (key) {
                    case SharedPreferenceUtils.USER_HEIGHT_KEY:
                        handleHeight(value);
                        break;
                    case SharedPreferenceUtils.USER_WEIGHT_KEY:
                        handleWeight(value);
                        break;
                    case SharedPreferenceUtils.USER_AGE_KEY:
                        handleAge(value);
                        break;
                    case SharedPreferenceUtils.USER_GENDER_KEY:
                        SharedPreferenceUtils.saveUserGender(context, Gender.findGenderById(Integer.valueOf(value)));
                        break;
                    case SharedPreferenceUtils.USER_TARGETWEIGHT_KEY:
                        handleTargetWeight(value);
                        break;
                    case SharedPreferenceUtils.USER_DEADLINE_KEY:
                        SharedPreferenceUtils.saveUserDeadline(context, DateUtils.getDateFromString(value));
                        break;
                }
            }

            private void handleHeight(String value) {

                int height = Integer.valueOf(value).intValue();
                if (height < 50) {

                    Toast.makeText(getContext(), R.string.error_height_to_low, Toast.LENGTH_SHORT).show();
                } else if(height > 300) {

                    Toast.makeText(getContext(), R.string.error_height_to_high, Toast.LENGTH_SHORT).show();
                } else {

                    SharedPreferenceUtils.saveUserHeight(context, height);
                }
            }

            private void handleWeight(String value) {

                int weight = Integer.valueOf(value).intValue();
                if (weight > SharedPreferenceUtils.getUserTargetWeight(context)) {

                    // TODO
                } else if (weight < 2) {

                    Toast.makeText(getContext(), R.string.error_weight_to_low, Toast.LENGTH_SHORT).show();
                } else if(weight > 500) {

                    Toast.makeText(getContext(), R.string.error_weight_to_high, Toast.LENGTH_SHORT).show();
                } else {

                    SharedPreferenceUtils.saveUserHeight(context, weight);
                }
            }

            private void handleTargetWeight(String value) {

                int targetWeight = Integer.valueOf(value).intValue();
                if (targetWeight <= SharedPreferenceUtils.getUserWeight(context).intValue()) {

                    Toast.makeText(getContext(), R.string.error_weight_target_weight, Toast.LENGTH_SHORT).show();
                } else if (targetWeight < 5) {

                    Toast.makeText(getContext(), R.string.error_targetweight_to_low, Toast.LENGTH_SHORT).show();
                } else if(targetWeight > 500) {

                    Toast.makeText(getContext(), R.string.error_targetweight_to_high, Toast.LENGTH_SHORT).show();
                } else {

                    SharedPreferenceUtils.saveUserHeight(context, targetWeight);
                }
            }

            private void handleAge(String value) {

                int age = Integer.valueOf(value).intValue();
                if (age > 150) {

                    Toast.makeText(getContext(), R.string.error_age_to_high, Toast.LENGTH_SHORT).show();
                } else {

                    SharedPreferenceUtils.saveUserHeight(context, age);
                }
            }
        }
    }
}