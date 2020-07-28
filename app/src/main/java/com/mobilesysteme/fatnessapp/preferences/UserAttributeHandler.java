package com.mobilesysteme.fatnessapp.preferences;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.mobilesysteme.fatnessapp.DateUtils;
import com.mobilesysteme.fatnessapp.Gender;
import com.mobilesysteme.fatnessapp.R;

import java.util.Date;

public class UserAttributeHandler {
    
    public static final String TAG = "UserAtributeHandler";
    private final Context context;

    private static final int MIN_HEIGHT = 50;
    private static final int MAX_HEIGHT = 300;

    private static final int MIN_WEIGHT = 20;
    private static final int MAX_WEIGHT = 500;

    private static final int MIN_AGE = 18;
    private static final int MAX_AGE = 150;

    public UserAttributeHandler(Context context) {
        this.context = context;
    }

    public void handleSaveHeight(String value) {
        int height = Integer.parseInt(value);
        if(validateHeight(height)) {
            SharedPreferenceUtils.saveUserHeight(context, height);
        } else {
            throw new RuntimeException("Invalid height input");
        }
    }

    public boolean validateHeight(int heightInput) {
        return heightInput >= MIN_HEIGHT
                && heightInput <= MAX_HEIGHT;
    }

    //Toast.makeText(context, R.string.error_height_to_low, Toast.LENGTH_LONG).show();
    //Toast.makeText(context, R.string.error_height_to_high, Toast.LENGTH_LONG).show();

    public void handleSaveWeight(String value) {
        int weight = Integer.parseInt(value);
        if(validateWeight(weight)) {
            SharedPreferenceUtils.saveUserWeightNow(context, weight);
        } else {
            throw new RuntimeException("Invalid weight input");
        }
    }

    public boolean validateWeight(int weightInput) {
        int targetWeight = SharedPreferenceUtils.getUserTargetWeight(context);
        if(targetWeight != 0) { // check if target weight has been set
            return weightInput >= MIN_WEIGHT
                    && weightInput <= MAX_WEIGHT
                    && weightInput < targetWeight;
        } else {
            return weightInput >= MIN_WEIGHT
                    && weightInput <= MAX_WEIGHT;
        }
    }

    // Toast.makeText(context,  R.string.error_weight_beyond_target_weight, Toast.LENGTH_LONG).show();
    // Toast.makeText(context, R.string.error_weight_to_low, Toast.LENGTH_LONG).show();
    // Toast.makeText(context, R.string.error_weight_to_high, Toast.LENGTH_LONG).show();

    public void handleSaveAge(String value) {
        int age = Integer.parseInt(value);
        if(validateAge(age)) {
            SharedPreferenceUtils.saveUserAge(context, age);
        } else {
            throw new RuntimeException("Invalid age input");
        }
    }

    public boolean validateAge(int ageInput) {
        return ageInput >= MIN_AGE
                && ageInput <= MAX_AGE;
    }


    public void handleSaveGender(int value) {
        Gender gender = Gender.findGenderById(value);
        if(validateGender(gender)) {
            SharedPreferenceUtils.saveUserGender(context, gender);
        } else {
            throw new RuntimeException("Invalid gender input");
        }
    }

    public boolean validateGender(Gender genderInput) {
        return genderInput != null;
    }

    // Toast.makeText(context, R.string.error_no_gender_found, Toast.LENGTH_LONG).show();

    public void handleSaveTargetWeight(String value) {
        int targetWeight = Integer.parseInt(value);
        if(validateTargetWeight(targetWeight)) {
            SharedPreferenceUtils.saveUserTargetWeight(context, targetWeight);
        } else {
            throw new RuntimeException("Invalid weight input");
        }
    }

    public boolean validateTargetWeight(int targetWeightInput) {
        int currentWeight = SharedPreferenceUtils.getUserWeight(context);
        if(currentWeight != 0) {
            return targetWeightInput >= MIN_WEIGHT
                    && targetWeightInput <= MAX_WEIGHT
                    && targetWeightInput > currentWeight;
        } else {
            return targetWeightInput >= MIN_WEIGHT
                    && targetWeightInput <= MAX_WEIGHT;
        }
    }

    // Toast.makeText(context, R.string.error_target_weight_below_weight, Toast.LENGTH_LONG).show();
    // Toast.makeText(context, R.string.error_targetweight_to_low, Toast.LENGTH_LONG).show();
    // Toast.makeText(context, R.string.error_targetweight_to_high, Toast.LENGTH_LONG).show();

    public void handleSaveDeadline(Date date) {
        if(validateDeadline(date)) {
            SharedPreferenceUtils.saveUserDeadline(context, date);
        } else {
            throw new RuntimeException("Invalid deadline input");
        }
    }

    public boolean validateDeadline(Date dateInput) {
        return dateInput != null
                && dateInput.getTime() >= Long.sum(DateUtils.getTodayMorning().getTime(), DateUtils.DAY_IN_MILLI_SECS)
                && dateInput.getTime() <= Long.sum(DateUtils.getTodayMorning().getTime(), DateUtils.YEAR_IN_MILLI_SECS * 10L);
    }

    // Toast.makeText(context, R.string.error_deadline_wrong_format, Toast.LENGTH_LONG).show();
    // Toast.makeText(context, R.string.error_deadline_before_tomorrow, Toast.LENGTH_LONG).show();
    // Toast.makeText(context, R.string.error_deadline_to_high, Toast.LENGTH_LONG).show();

}
