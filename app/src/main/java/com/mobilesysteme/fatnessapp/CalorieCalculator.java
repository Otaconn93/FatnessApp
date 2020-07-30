package com.mobilesysteme.fatnessapp;

import android.content.Context;

import com.mobilesysteme.fatnessapp.preferences.SharedPreferenceUtils;
import com.mobilesysteme.fatnessapp.sqlObjects.EatenFood;
import com.mobilesysteme.fatnessapp.sqlObjects.EatenRecipe;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Calculator for all calorie calculations like how many calories the user have to consume
 * and how many are left.
 *
 * @author Kevin Bücher
 */
public class CalorieCalculator {

    private final Context context;
    private final DatabaseHelper databaseHelper;
    private static final float CALORIE_PER_KILO = 7716.1791764707f;

    public CalorieCalculator(Context context) {

        this.context = context;
        databaseHelper = new DatabaseHelper(context);
    }

    /**
     * Calculates BMR (Basal Metabolic Rate), a formula to get average calories:
     *
     * Women: BMR = 655 + (9.6 x weight in kg) + (1.8 x height in cm) - (4.7 x age in years)
     * Men: BMR = 66 + (13.7 x weight in kg) + (5 x height in cm) - (6.8 x age in years)
     *
     * @return average calories per Day calculated from personal details
     */
    private float calculateDailyCalories() {

        int height = SharedPreferenceUtils.getUserHeight(context);
        Gender gender = SharedPreferenceUtils.getUserGender(context);
        int weight = SharedPreferenceUtils.getUserWeight(context);
        int age = SharedPreferenceUtils.getUserAge(context);

        if(gender == Gender.MALE){
            return (float) (655 + (9.6 * weight) + (1.8 * height) - (4.7 * age));
        }else if(gender == Gender.FEMALE){
            return (float) (66 + (13.7 * weight) + (5 * height) - (6.8 * age));
        }
        return 0;
    }

    /**
     *  Calculates extra Calories for weight goal per day.
     *  1 kg = 7716.1791764707 Calories -> divided by number of days to deadline
     *
     * @return additionally Calories to reach weight Goal
     */
    private float calculateExtraCaloriesForWeightGoal(){

        int weight = SharedPreferenceUtils.getUserWeight(context);
        int targetWeight = SharedPreferenceUtils.getUserTargetWeight(context);
        Date deadline = SharedPreferenceUtils.getUserDeadline(context);

        float extraCalories = (targetWeight - weight) * CALORIE_PER_KILO;
        long diff = deadline.getTime() - DateUtils.getTodayMorning().getTime() ;
        long daysLeft = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        return extraCalories / daysLeft;
    }

    /**
     * Actual Method to show on dashboard screen, how many calories the user has to consume.
     *
     * @return calories left for today
     */
    public int getDailyCaloriesLeft(){

        float dailyCalories = calculateDailyCalories() + calculateExtraCaloriesForWeightGoal();

        int lastCalories = 0;
        for(EatenFood eaten : databaseHelper.getTodayEatenFoods()){
            lastCalories += eaten.getCalories();
        }

        for(EatenRecipe eatenRecipe : databaseHelper.getTodayEatenRecipes()){
            lastCalories += eatenRecipe.getCalories();
        }

        return (int) (dailyCalories-lastCalories);
    }

    /**
     * Calculates needed calories per day
     *
     * @return daily needed calories to reach goal
     */
    public int getDailyCalories(){
        return (int) (calculateDailyCalories() + calculateExtraCaloriesForWeightGoal());
    }
}
