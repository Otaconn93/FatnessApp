package com.mobilesysteme.fatnessapp;

import android.content.Context;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import com.mobilesysteme.fatnessapp.preferences.SharedPreferenceUtils;

import java.util.Date;

import static org.junit.Assert.*;


/**
 * dashboard unit test, which tests correct displayed calories.
 */
@RunWith(AndroidJUnit4.class)
public class CalorieCalculatorUnitTest {

    private Context context = ApplicationProvider.getApplicationContext();
    private CalorieCalculator calorieCalculator;
    private DatabaseHelper databaseHelper;
    private static final int WEEK_IN_MS = 604800000;

    @Before
    public void init() {
        calorieCalculator = new CalorieCalculator(context);
        databaseHelper = new DatabaseHelper(context);
        databaseHelper.refillDatabase();

        SharedPreferenceUtils.saveUserWeightNow(context, 50);
        SharedPreferenceUtils.saveUserAge(context, 25);
        SharedPreferenceUtils.saveUserGender(context, Gender.MALE);
        SharedPreferenceUtils.saveUserDeadline(context, new Date(new Date().getTime() + WEEK_IN_MS));
        SharedPreferenceUtils.saveUserHeight(context, 175);
        SharedPreferenceUtils.saveUserTargetWeight(context, 75);

    }

    @Test
    public void checkCaloriesPerDay() {
        assertEquals(28890, calorieCalculator.getDailyCalories());
    }

    @Test
    public void checkDailyCaloriesLeft(){
        databaseHelper.addEatenFood(1, 1000, new Date());
        assertEquals(27890, calorieCalculator.getDailyCaloriesLeft());

    }

    @After
    public void after(){
        databaseHelper.refillDatabase();
    }
}