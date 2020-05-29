package com.mobilesysteme.fatnessapp;

public class CalorieCalculator {

    private float height = 180; // in cm
    private float weight = 78; // in kg
    private float age = 25;
    private String gender = "m"; // w oder m
    private float dailyCaloriesLeft = 2000; // in kcal
    private float weightGoal = 88; // in kg


    public CalorieCalculator(float height) {
        this.height = height;
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
        if(gender.equals("m")){
            return (float) ((float) 655 + (9.6 * weight) + (1.8 * height) - (4.7 * age));
        }else if(gender.equals("w")){
            return (float) ((float) 66 + (13.7 * weight) + (5 * height) - (6.8 * age));
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

    private void setDailyCaloriesLeft(float dailyCaloriesLeft) {
        this.dailyCaloriesLeft = dailyCaloriesLeft;
    }

}
