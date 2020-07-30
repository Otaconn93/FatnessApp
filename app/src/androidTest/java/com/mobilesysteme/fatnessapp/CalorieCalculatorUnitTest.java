package com.mobilesysteme.fatnessapp;

import android.content.Context;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;
import com.mobilesysteme.fatnessapp.preferences.SharedPreferenceUtils;
import java.util.Date;
import static org.junit.Assert.*;


/**
 * dashboard unit test, which tests correct displayed calories.
 *
 * @author Kevin Bücher
 *
 */
@RunWith(AndroidJUnit4.class)
public class CalorieCalculatorUnitTest {

    private Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
    private CalorieCalculator calorieCalculator;
    private DatabaseHelper databaseHelper;

    @Before
    public void init() {
        calorieCalculator = new CalorieCalculator(context);
        databaseHelper = new DatabaseHelper(context);
        databaseHelper.refillDatabase();
    }

    /**
     * Test with normal values and male gender to calculate needed calories
     */
    @Test
    public void checkCaloriesPerDay_Male() {

        setSharedPrefencesValues(50, 25, Gender.MALE, new Date(new Date().getTime() + DateUtils.WEEK_IN_MILLI_SECS), 175, 75);
        assertEquals(28890, calorieCalculator.getDailyCalories());
    }

    /**
     * Test with normal values and male gender to calculate calories left
     */
    @Test
    public void checkDailyCaloriesLeft_Male(){

        setSharedPrefencesValues(50, 25, Gender.MALE, new Date(new Date().getTime() + DateUtils.WEEK_IN_MILLI_SECS), 175, 75);
        databaseHelper.addEatenFood(1, 1000, new Date());
        assertEquals(27890, calorieCalculator.getDailyCaloriesLeft());
    }

    /**
     * Test with normal values and female gender to calculate needed calories
     */
    @Test
    public void checkCaloriesPerDay_Female() {

        setSharedPrefencesValues(50, 25, Gender.FEMALE, new Date(new Date().getTime() + DateUtils.WEEK_IN_MILLI_SECS), 175, 75);
        assertEquals(29013, calorieCalculator.getDailyCalories());
    }

    /**
     * Test with normal values and female gender to calculate calories left
     */
    @Test
    public void checkDailyCaloriesLeft_Female(){

        setSharedPrefencesValues(50, 25, Gender.FEMALE, new Date(new Date().getTime() + DateUtils.WEEK_IN_MILLI_SECS), 175, 75);
        databaseHelper.addEatenFood(1, 1000, new Date());
        assertEquals(28013, calorieCalculator.getDailyCaloriesLeft());
    }

    /**
     * Test with minimum values and male gender to calculate needed calories
     */
    @Test
    public void checkDailyCaloriesWithMinValues_Male() {

        setSharedPrefencesValues(2, 0, Gender.MALE, new Date(new Date().getTime() + DateUtils.DAY_IN_MILLI_SECS), 50, 5);
        assertEquals(23912, calorieCalculator.getDailyCalories());
    }

    /**
     * Test with minimum values and male gender to calculate calories left
     */
    @Test
    public void checkDailyCaloriesLeftWithMinValues_Male(){

        setSharedPrefencesValues(2, 0, Gender.MALE, new Date(new Date().getTime() + DateUtils.DAY_IN_MILLI_SECS), 50, 5);
        databaseHelper.addEatenFood(1, 1000, new Date());
        assertEquals(22912, calorieCalculator.getDailyCaloriesLeft());
    }

    /**
     * Test with minimum values and male gender to calculate needed calories
     */
    @Test
    public void checkDailyCaloriesWithMinValues_Female(){

        setSharedPrefencesValues(2, 0, Gender.FEMALE, new Date(new Date().getTime() + DateUtils.DAY_IN_MILLI_SECS), 50, 5);
        assertEquals(23491, calorieCalculator.getDailyCalories());
    }

    /**
     * Test with minimum values and female gender to calculate calories left
     */
    @Test
    public void checkDailyCaloriesLeftWithMinValues_Female(){

        setSharedPrefencesValues(2, 0, Gender.FEMALE, new Date(new Date().getTime() + DateUtils.DAY_IN_MILLI_SECS), 50, 5);
        databaseHelper.addEatenFood(1, 1000, new Date());
        assertEquals(22491, calorieCalculator.getDailyCaloriesLeft());
    }

    /**
     * Test with maximum values and male gender to calculate needed calories
     */
    @Test
    public void checkDailyCaloriesWithMaxValues_Male(){

        setSharedPrefencesValues(499, 150, Gender.MALE, new Date(new Date().getTime() + DateUtils.YEAR_IN_MILLI_SECS * 9L), 300, 500);
        assertEquals(5282, calorieCalculator.getDailyCalories());
    }

    /**
     * Test with maximum values and male gender to calculate calories left
     */
    @Test
    public void checkDailyCaloriesLeftWithMaxValues_Male(){

        setSharedPrefencesValues(499, 150, Gender.MALE, new Date(new Date().getTime() + DateUtils.YEAR_IN_MILLI_SECS * 9L), 300, 500);
        databaseHelper.addEatenFood(1, 1000, new Date());
        assertEquals(4282, calorieCalculator.getDailyCaloriesLeft());
    }

    /**
     * Test with minimum values and female gender to calculate needed calories
     */
    @Test
    public void checkDailyCaloriesWithMaxValues_Female(){

        setSharedPrefencesValues(499, 150, Gender.FEMALE, new Date(new Date().getTime() + DateUtils.YEAR_IN_MILLI_SECS * 9L), 300, 500);
        assertEquals(7384, calorieCalculator.getDailyCalories());
    }

    /**
     * Test with maximum values and female gender to calculate calories left
     */
    @Test
    public void checkDailyCaloriesLeftWithMaxValues_Female(){

        setSharedPrefencesValues(499, 150, Gender.FEMALE, new Date(new Date().getTime() + DateUtils.YEAR_IN_MILLI_SECS * 9L), 300, 500);
        databaseHelper.addEatenFood(1, 1000, new Date());
        assertEquals(6384, calorieCalculator.getDailyCaloriesLeft());
    }

    /**
     * Method to put test data into SharedPreferences
     *
     * @param weight
     * @param age
     * @param gender
     * @param deadline
     * @param height
     * @param targetWeight
     */
    private void setSharedPrefencesValues(int weight, int age, Gender gender, Date deadline, int height, int targetWeight){

        SharedPreferenceUtils.saveUserWeightNow(context, weight);
        SharedPreferenceUtils.saveUserAge(context, age);
        SharedPreferenceUtils.saveUserGender(context, gender);
        SharedPreferenceUtils.saveUserDeadline(context,deadline);
        SharedPreferenceUtils.saveUserHeight(context, height);
        SharedPreferenceUtils.saveUserTargetWeight(context, targetWeight);
    }

}