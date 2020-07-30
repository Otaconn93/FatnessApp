package com.mobilesysteme.fatnessapp.preferences;

import android.content.Context;
import android.widget.Toast;

import com.mobilesysteme.fatnessapp.DateUtils;
import com.mobilesysteme.fatnessapp.Gender;
import com.mobilesysteme.fatnessapp.R;

import java.util.Date;

/**
 * The Activity in which the Settings like the SheredPreferences can be entered by the user
 * @author Hoffmann
 */
public class UserAttributeHandler {

    private Context context;

    public UserAttributeHandler(Context context) {
        this.context = context;
    }

    /**
     * Uses the SharedPreferenceUtils to store the height of the user
     * @param heightAsString the String to extract the height from
     * @return false if the given value was not qualified to store
     */
    public boolean handleSaveHeight(String heightAsString) {

        int height = Integer.parseInt(heightAsString);
        if (height < 50) {

            Toast.makeText(context, R.string.error_height_to_low, Toast.LENGTH_LONG).show();
            return false;
        } else if(height > 300) {

            Toast.makeText(context, R.string.error_height_to_high, Toast.LENGTH_LONG).show();
            return false;
        } else {

            SharedPreferenceUtils.saveUserHeight(context, height);
            return true;
        }
    }

    /**
     * Uses the SharedPreferenceUtils to store the weight of the user
     * @param date indicates the date when the user had the given weight
     * @param weightAsString the String to extract the weight from
     * @return false if the given value was not qualified to store
     */
    public boolean handleSaveWeight(Date date, String weightAsString) {

        int weight = Integer.parseInt(weightAsString);
        int targetWeight = SharedPreferenceUtils.getUserTargetWeight(context);
        if (targetWeight != 0 && weight > targetWeight) {

            Toast.makeText(context,  R.string.error_weight_beyond_target_weight, Toast.LENGTH_LONG).show();
            return false;
        } else if (weight < 2) {

            Toast.makeText(context, R.string.error_weight_to_low, Toast.LENGTH_LONG).show();
            return false;
        } else if(weight > 500) {

            Toast.makeText(context, R.string.error_weight_to_high, Toast.LENGTH_LONG).show();
            return false;
        } else {

            SharedPreferenceUtils.saveUserWeight(context, date, weight);
            return true;
        }
    }

    /**
     * Uses the SharedPreferenceUtils to store the weight of the user
     * @param weightAsString the String to extract the weight from
     * @return false if the given value was not qualified to store
     */
    public boolean handleSaveWeightNow(String weightAsString) {
        return handleSaveWeight(new Date(), weightAsString);
    }

    /**
     * Uses the SharedPreferenceUtils to store the age of the user
     * @param ageAsString the String to extract the age from
     * @return false if the given value was not qualified to store
     */
    public boolean handleSaveAge(String ageAsString) {

        int age = Integer.parseInt(ageAsString);
        if (age > 150) {

            Toast.makeText(context, R.string.error_age_to_high, Toast.LENGTH_LONG).show();
            return false;
        } else {

            SharedPreferenceUtils.saveUserAge(context, age);
            return true;
        }
    }

    /**
     * Uses the SharedPreferenceUtils to store the gender of the user
     * @param gender the gender to store
     * @return false if the given value was not qualified to store
     */
    public boolean handleSaveGender(Gender gender) {

        if (gender == null) {

            Toast.makeText(context, R.string.error_no_gender_found, Toast.LENGTH_LONG).show();
            return false;
        }

        SharedPreferenceUtils.saveUserGender(context, gender);
        return true;
    }

    /**
     * Uses the SharedPreferenceUtils to store the target weight of the user
     * @param targetWeightAsString the String to extract the target weight from
     * @return false if the given value was not qualified to store
     */
    public boolean handleSaveTargetWeight(String targetWeightAsString) {

        int targetWeight = Integer.parseInt(targetWeightAsString);
        if (targetWeight < SharedPreferenceUtils.getUserWeight(context)) {

            Toast.makeText(context, R.string.error_target_weight_below_weight, Toast.LENGTH_LONG).show();
            return false;
        } else if (targetWeight < 5) {

            Toast.makeText(context, R.string.error_targetweight_to_low, Toast.LENGTH_LONG).show();
            return false;
        } else if(targetWeight > 500) {

            Toast.makeText(context, R.string.error_targetweight_to_high, Toast.LENGTH_LONG).show();
            return false;
        } else {

            SharedPreferenceUtils.saveUserTargetWeight(context, targetWeight);
            return true;
        }
    }

    /**
     * Uses the SharedPreferenceUtils to store the deadline of the user
     * @param date the date to store
     * @return false if the given value was not qualified to store
     */
    public boolean handleSaveDeadline(Date date) {

        if (date == null) {

            Toast.makeText(context, R.string.error_deadline_wrong_format, Toast.LENGTH_LONG).show();
            return false;
        } else if (date.getTime() < Long.sum(DateUtils.getTodayMorning().getTime(), DateUtils.DAY_IN_MILLI_SECS)) {

            Toast.makeText(context, R.string.error_deadline_before_tomorrow, Toast.LENGTH_LONG).show();
            return false;
        } else if (date.getTime() > (Long.sum(DateUtils.getTodayMorning().getTime(),DateUtils.YEAR_IN_MILLI_SECS * 10L))) {

            Toast.makeText(context, R.string.error_deadline_to_high, Toast.LENGTH_LONG).show();
            return false;
        } else {

            SharedPreferenceUtils.saveUserDeadline(context, date);
            return true;
        }
    }
}
