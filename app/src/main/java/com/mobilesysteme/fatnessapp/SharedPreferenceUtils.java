package com.mobilesysteme.fatnessapp;

import android.content.Context;

public class SharedPreferenceUtils {

    private static final String FILE_NAME = "FatnessPreferences";
    private static final String USER_HEIGHT_KEY = "userHeight";
    private static final String USER_WEIGHT_KEY = "userWeight";
    private static final String USER_AGE_KEY = "userAge";

    /**
     * saves the users height to the SharedPreferences
     * @param context the ApplicationContext needed to access the SharedPreferences
     * @param heightInCentimeter the height of the user in centimeter
     */
    public static void saveUserHeight(Context context, int heightInCentimeter) {

        context.getSharedPreferences(FILE_NAME, 0)
                .edit()
                .putInt(USER_HEIGHT_KEY, heightInCentimeter)
                .apply();
    }

    /**
     * saves the users weight to the SharedPreferences
     * @param context the ApplicationContext needed to access the SharedPreferences
     * @param weightInKilogram the weight of the user in kilogram
     */
    public static void saveUserWeight(Context context, int weightInKilogram) {

        context.getSharedPreferences(FILE_NAME, 0)
                .edit()
                .putInt(USER_WEIGHT_KEY, weightInKilogram)
                .apply();
    }

    /**
     * saves the users age to the SharedPreferences
     * @param context the ApplicationContext needed to access the SharedPreferences
     * @param ageInYears the age of the user in years
     */
    public static void saveUserAge(Context context, int ageInYears) {

        context.getSharedPreferences(FILE_NAME, 0)
                .edit()
                .putInt(USER_AGE_KEY, ageInYears)
                .apply();
    }

    /**
     * extracts the users height from the SharedPreferences
     * @param context the ApplicationContext needed to access the SharedPreferences
     * @return the height of the user in centimeter
     */
    public static int getUserHeight(Context context) {

        return context.getSharedPreferences(FILE_NAME, 0)
                .getInt(USER_HEIGHT_KEY, 0);
    }

    /**
     * extracts the users weight from the SharedPreferences
     * @param context the ApplicationContext needed to access the SharedPreferences
     * @return the weight of the user in kilogram
     */
    public static int getUserWeight(Context context) {

        return context.getSharedPreferences(FILE_NAME, 0)
                .getInt(USER_WEIGHT_KEY, 0);
    }

    /**
     * extracts the users age from the SharedPreferences
     * @param context the ApplicationContext needed to access the SharedPreferences
     * @return the age of the user in years
     */
    public static int getUserAge(Context context) {

        return context.getSharedPreferences(FILE_NAME, 0)
                .getInt(USER_AGE_KEY, 0);
    }

    /**
     * removes the users height from the SharedPreferences
     * @param context the ApplicationContext needed to access the SharedPreferences
     */
    public static void removeUserHeight(Context context) {

        context.getSharedPreferences(FILE_NAME, 0)
                .edit()
                .remove(USER_HEIGHT_KEY)
                .apply();
    }

    /**
     * removes the users weight from the SharedPreferences
     * @param context the ApplicationContext needed to access the SharedPreferences
     */
    public static void removeUserWeight(Context context) {

        context.getSharedPreferences(FILE_NAME, 0)
                .edit()
                .remove(USER_WEIGHT_KEY)
                .apply();
    }

    /**
     * removes the users age from the SharedPreferences
     * @param context the ApplicationContext needed to access the SharedPreferences
     */
    public static void removeUserAge(Context context) {

        context.getSharedPreferences(FILE_NAME, 0)
                .edit()
                .remove(USER_AGE_KEY)
                .apply();
    }
}
