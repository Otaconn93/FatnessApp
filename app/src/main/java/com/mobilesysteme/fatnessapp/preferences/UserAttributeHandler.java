package com.mobilesysteme.fatnessapp.preferences;

import android.content.Context;
import android.widget.Toast;

import com.mobilesysteme.fatnessapp.DateUtils;
import com.mobilesysteme.fatnessapp.Gender;
import com.mobilesysteme.fatnessapp.R;

import java.util.Date;

public class UserAttributeHandler {

    private Context context;

    public UserAttributeHandler(Context context) {
        this.context = context;
    }

    public boolean handleSaveHeight(String value) {

        int height = Integer.parseInt(value);
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

    public boolean handleSaveWeight(String value) {

        int weight = Integer.parseInt(value);
        if (weight >= SharedPreferenceUtils.getUserTargetWeight(context)) {

            Toast.makeText(context,  R.string.error_weight_beyond_target_weight, Toast.LENGTH_LONG).show();
            return false;
        } else if (weight < 2) {

            Toast.makeText(context, R.string.error_weight_to_low, Toast.LENGTH_LONG).show();
            return false;
        } else if(weight > 500) {

            Toast.makeText(context, R.string.error_weight_to_high, Toast.LENGTH_LONG).show();
            return false;
        } else {

            SharedPreferenceUtils.saveUserWeightNow(context, weight);
            return true;
        }
    }

    public boolean handleSaveAge(String value) {

        int age = Integer.parseInt(value);
        if (age > 150) {

            Toast.makeText(context, R.string.error_age_to_high, Toast.LENGTH_LONG).show();
            return false;
        } else {

            SharedPreferenceUtils.saveUserAge(context, age);
            return true;
        }
    }

    public boolean handleSaveGender(int value) {

        Gender gender = Gender.findGenderById(value);
        if (gender == null) {

            Toast.makeText(context, R.string.error_no_gender_found, Toast.LENGTH_LONG).show();
            return false;
        }

        SharedPreferenceUtils.saveUserGender(context, gender);
        return true;
    }

    public boolean handleSaveTargetWeight(String value) {

        int targetWeight = Integer.parseInt(value);
        if (targetWeight <= SharedPreferenceUtils.getUserWeight(context).intValue()) {

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
