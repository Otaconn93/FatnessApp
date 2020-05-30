package com.mobilesysteme.fatnessapp;

import android.content.Context;

public class CalorieCalculator {

    private float height = 180; // in cm
    private float weight = 78; // in kg
    private float age = 25;
    private String gender = "m"; // w oder m
    private float dailyCaloriesLeft = 2000; // in kcal
    private float weightGoal = 88; // in kg
    private SharedPreferenceUtils spu = new SharedPreferenceUtils();

    public CalorieCalculator(Context context) {
        setHeight(spu.getUserHeight(context));
        setAge(spu.getUserAge(context));
        setWeight(spu.getUserWeight(context));
    }

    /**
     * Calculates BMR (Basal Metabolic Rate), a formula to get average calories:
     *
     * Women: BMR = 655 + (9.6 x weight in kg) + (1.8 x height in cm) - (4.7 x age in years)
     * Men: BMR = 66 + (13.7 x weight in kg) + (5 x height in cm) - (6.8 x age in years)
     *
     * @return average calories per Day calculated from personal details
     */
    public float calculateDailyCalories(){
        if(getGender().equals("m")){
            return (float) ((float) 655 + (9.6 * getWeight()) + (1.8 * getHeight()) - (4.7 * getAge()));
        }else if(getGender().equals("w")){
            return (float) ((float) 66 + (13.7 * getWeight()) + (5 * getHeight()) - (6.8 * getAge()));
        }
        return 0;
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

    public float getDailyCaloriesLeft() {
        return dailyCaloriesLeft;
    }

    private void setDailyCaloriesLeft(float dailyCaloriesLeft) { this.dailyCaloriesLeft = dailyCaloriesLeft; }

    public String getGender() { return gender; }

    public void setGender(String gender) { this.gender = gender; }


}
