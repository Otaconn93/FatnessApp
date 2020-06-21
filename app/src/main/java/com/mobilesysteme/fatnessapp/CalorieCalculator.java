package com.mobilesysteme.fatnessapp;

import android.content.Context;

import com.mobilesysteme.fatnessapp.preferences.SharedPreferenceUtils;
import com.mobilesysteme.fatnessapp.sqlObjects.EatenFood;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CalorieCalculator {

    private float height = 180; // in cm
    private float weight = 78; // in kg
    private float age = 25;
    private Gender gender;
    private float weightGoal = 88; // in kg
    private Date goalDeadline;
    private List<EatenFood> eatenFood;
    private DatabaseHelper dh;

    public CalorieCalculator(Context context) {
        goalDeadline = new Date();
        eatenFood = new ArrayList<>();
        setHeight(SharedPreferenceUtils.getUserHeight(context));
        setAge(SharedPreferenceUtils.getUserAge(context));
        setWeight(SharedPreferenceUtils.getUserWeight(context).intValue());
        setWeightGoal(SharedPreferenceUtils.getUserTargetWeight(context));
        setGoalDeadline(SharedPreferenceUtils.getUserDeadline(context));
        setGender(SharedPreferenceUtils.getUserGender(context));

        dh = new DatabaseHelper(context);
        setEatenFood(dh.getTodayEatenFoods());
    }

    /**
     * Calculates BMR (Basal Metabolic Rate), a formula to get average calories:
     *
     * Women: BMR = 655 + (9.6 x weight in kg) + (1.8 x height in cm) - (4.7 x age in years)
     * Men: BMR = 66 + (13.7 x weight in kg) + (5 x height in cm) - (6.8 x age in years)
     *
     * @return average calories per Day calculated from personal details
     */
    private float calculateDailyCalories(){
        if(getGender().id == 0){
            return (float) (655 + (9.6 * getWeight()) + (1.8 * getHeight()) - (4.7 * getAge()));
        }else if(getGender().id == 1){
            return (float) (66 + (13.7 * getWeight()) + (5 * getHeight()) - (6.8 * getAge()));
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
        float extraCalories = (getWeightGoal() - getWeight()) * 7716.1791764707f;
        Date today = new Date();
        long diff = getGoalDeadline().getTime() - today.getTime() ;
        long daysLeft =  TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
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

        for(int i=0; i<getEatenFood().size()-1; i++){
            lastCalories = lastCalories + getEatenFood().get(i).getCalories();
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

    public float getHeight() {
        return height;
    }

    public float getWeight() {
        return weight;
    }

    public float getAge() {
        return age;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public void setAge(float age) {
        this.age = age;
    }

    public Gender getGender() { return gender; }

    public void setGender(Gender gender) { this.gender = gender; }

    public float getWeightGoal() { return weightGoal; }

    public void setWeightGoal(float weightGoal) { this.weightGoal = weightGoal; }

    public Date getGoalDeadline() { return goalDeadline; }

    public void setGoalDeadline(Date goalDeadline) { this.goalDeadline = goalDeadline; }

    public List<EatenFood> getEatenFood() { return eatenFood; }

    public void setEatenFood(List<EatenFood> eatenFood) { this.eatenFood = eatenFood; }

}
