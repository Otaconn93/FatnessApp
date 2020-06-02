package com.mobilesysteme.fatnessapp;

import android.content.Context;

import java.util.ArrayList;
import java.util.Date;

public class CalorieCalculator {

    private float height = 180; // in cm
    private float weight = 78; // in kg
    private float age = 25;
    private String gender = "m"; // w oder m
    private float weightGoal = 88; // in kg
    private Date goalDeadline = new Date();
    private SharedPreferenceUtils spu = new SharedPreferenceUtils();
    private ArrayList<Integer> eatenFoodID = new ArrayList<Integer>();
    private DatabaseHelper dh;

    public CalorieCalculator(Context context) {
        setHeight(spu.getUserHeight(context));
        setAge(spu.getUserAge(context));
        setWeight(spu.getUserWeight(context));
        setWeightGoal(spu.getUserTargetWeight(context));
        setGoalDeadline(spu.getUserDeadline(context));

        dh = new DatabaseHelper(context);

        eatenFoodID.add(1);
        eatenFoodID.add(10);
        eatenFoodID.add(20);
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
        if(getGender().equals("m")){
            return (float) ((float) 655 + (9.6 * getWeight()) + (1.8 * getHeight()) - (4.7 * getAge()));
        }else if(getGender().equals("w")){
            return (float) ((float) 66 + (13.7 * getWeight()) + (5 * getHeight()) - (6.8 * getAge()));
        }
        return 0;
    }

    /**
     * Actual Method to show on dashboard screen, how many calories the user has to consume.
     *
     * @return calories left for today
     */
    public int getDailyCaloriesLeft(){

        float dailyCaories = calculateDailyCalories();
        int lastCalories = 0;
        for(int i=0; i<eatenFoodID.size()-1; i++){
            lastCalories =+ dh.getFoodById(eatenFoodID.get(i)).getCalories();
        }

        return (int) (dailyCaories-lastCalories);
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

    public String getGender() { return gender; }

    public void setGender(String gender) { this.gender = gender; }

    public float getWeightGoal() { return weightGoal; }

    public void setWeightGoal(float weightGoal) { this.weightGoal = weightGoal; }

    public Date getGoalDeadline() { return goalDeadline; }

    public void setGoalDeadline(Date goalDeadline) { this.goalDeadline = goalDeadline; }
}
