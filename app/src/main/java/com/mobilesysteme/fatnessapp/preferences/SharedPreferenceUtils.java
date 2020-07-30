package com.mobilesysteme.fatnessapp.preferences;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mobilesysteme.fatnessapp.Gender;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.TreeMap;

/**
 * A utils handeling the usages of the SharedPreferences
 * @author Hoffmann
 */
public abstract class SharedPreferenceUtils {

    private static final String FILE_NAME = "FatnessPreferences";

    static final String USER_HEIGHT_KEY = "userHeight";
    static final String USER_WEIGHT_KEY = "userWeight";
    static final String USER_AGE_KEY = "userAge";
    static final String USER_GENDER_KEY = "userGender";
    static final String USER_TARGETWEIGHT_KEY = "userTargetWeight";
    static final String USER_DEADLINE_KEY = "userDeadline";

    private static final String FIRST_LAUNCH_KEY = "firstLaunch";

    private static final Gson GSON = new Gson();

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

        TreeMap<Long, Integer> userWeightHistory = getUserWeightHistory(context);
        if (userWeightHistory.isEmpty()) {
            throw new RuntimeException("Saved weight should never be empty");
        }

        return userWeightHistory.get(userWeightHistory.lastKey()).intValue();
    }

    /**
     * extracts the users weight history from the SharedPreferences
     * @param context the ApplicationContext needed to access the SharedPreferences
     * @return the TreeMap with the long value for date as key and the weight in kg
     */
    public static TreeMap<Long, Integer> getUserWeightHistory(Context context) {

        String currentWeightHistoryJson = context.getSharedPreferences(FILE_NAME, 0)
                .getString(USER_WEIGHT_KEY, null);
        if (currentWeightHistoryJson == null) {
            return new TreeMap<>();
        }

        Type type = new TypeToken<TreeMap<Long, Integer>>(){}.getType();
        return GSON.fromJson(currentWeightHistoryJson, type);
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
     * extracts the users gender from the SharedPreferences
     * @param context the ApplicationContext needed to access the SharedPreferences
     * @return the gender of the user in years
     */
    public static Gender getUserGender(Context context) {

        int genderId = context.getSharedPreferences(FILE_NAME, 0)
                .getInt(USER_GENDER_KEY, 0);
        for (Gender gender : Gender.values()) {

            if(genderId == gender.getId()) {
                return gender;
            }
        }

        return null;
    }

    /**
     * extracts the users targeted weight from the SharedPreferences
     * @param context the ApplicationContext needed to access the SharedPreferences
     * @return the targeted weight of the user in kg
     */
    public static int getUserTargetWeight(Context context) {

        return context.getSharedPreferences(FILE_NAME, 0)
                .getInt(USER_TARGETWEIGHT_KEY, 0);
    }

    /**
     * extracts the users deadline for achieving his targeted weight from the SharedPreferences
     * @param context the ApplicationContext needed to access the SharedPreferences
     * @return the deadline of the user
     */
    public static Date getUserDeadline(Context context) {

        return new Date(context.getSharedPreferences(FILE_NAME, 0)
                .getLong(USER_DEADLINE_KEY, 0));
    }

    /**
     * extracts from the SharedPreferences the information whether or not the app is started for the first time
     * @param context the ApplicationContext needed to access the SharedPreferences
     * @return false if the FirstLaunchFlow already got executed successfully at least once since installation
     */
    public static boolean getFirstLaunch(Context context) {
        return context.getSharedPreferences(FILE_NAME, 0)
                .getBoolean(FIRST_LAUNCH_KEY, true);
    }
  
    /**
     * saves the users height to the SharedPreferences
     * @param context the ApplicationContext needed to access the SharedPreferences
     * @param heightInCentimeter the height of the user in centimeter
     */
    static void saveUserHeight(Context context, int heightInCentimeter) {

        context.getSharedPreferences(FILE_NAME, 0)
                .edit()
                .putInt(USER_HEIGHT_KEY, heightInCentimeter)
                .apply();
    }

    /**
     * saves the users weight to the SharedPreferences
     * @param context the ApplicationContext needed to access the SharedPreferences
     * @param date the Date where the user had the given
     * @param weightInKilogram the weight of the user in kilogram
     */
    static void saveUserWeight(Context context, Date date, int weightInKilogram) {

        TreeMap<Long, Integer> userWeightHistory = getUserWeightHistory(context);
        if (userWeightHistory.isEmpty()) {
            userWeightHistory = new TreeMap<>();
        }

        userWeightHistory.put(Long.valueOf(date.getTime()), weightInKilogram);

        context.getSharedPreferences(FILE_NAME, 0)
                .edit()
                .putString(USER_WEIGHT_KEY, GSON.toJson(userWeightHistory))
                .apply();
    }

    /**
     * saves the users age to the SharedPreferences
     * @param context the ApplicationContext needed to access the SharedPreferences
     * @param ageInYears the age of the user in years
     */
    static void saveUserAge(Context context, int ageInYears) {

        context.getSharedPreferences(FILE_NAME, 0)
                .edit()
                .putInt(USER_AGE_KEY, ageInYears)
                .apply();
    }

    /**
     * saves the users gender to the SharedPreferences
     * @param context the ApplicationContext needed to access the SharedPreferences
     * @param gender the gender of the user
     */
    static void saveUserGender(Context context, Gender gender) {

        context.getSharedPreferences(FILE_NAME, 0)
                .edit()
                .putInt(USER_GENDER_KEY, gender.getId())
                .apply();
    }

    /**
     * extracts the users height from the SharedPreferences
     * @param context the ApplicationContext needed to access the SharedPreferences
     * @param targetWeight the targeted weight of the user in kg
     */
    static void saveUserTargetWeight(Context context, int targetWeight) {

        context.getSharedPreferences(FILE_NAME, 0)
                .edit()
                .putInt(USER_TARGETWEIGHT_KEY, targetWeight)
                .apply();
    }

    /**
     * saves the users deadline to the SharedPreferences
     * @param context the ApplicationContext needed to access the SharedPreferences
     * @param deadline the date of the users deadline
     */
    static void saveUserDeadline(Context context, Date deadline) {

        context.getSharedPreferences(FILE_NAME, 0)
                .edit()
                .putLong(USER_DEADLINE_KEY, deadline.getTime())
                .apply();
    }

    /**
     * saves within the SharedPreferences the information whether or not the app is started for the first time
     * @param context the ApplicationContext needed to access the SharedPreferences
     * @param isFirstLaunch updated first launch value
     */
    public static void saveFirstLaunch(Context context, boolean isFirstLaunch) {

        context.getSharedPreferences(FILE_NAME, 0)
                .edit()
                .putBoolean(FIRST_LAUNCH_KEY, isFirstLaunch)
                .apply();
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

    /**
     * removes the users age from the SharedPreferences
     * @param context the ApplicationContext needed to access the SharedPreferences
     */
    public static void removeUserGender(Context context) {

        context.getSharedPreferences(FILE_NAME, 0)
                .edit()
                .remove(USER_GENDER_KEY)
                .apply();
    }

    /**
     * removes the targeted weight from the SharedPreferences
     * @param context the ApplicationContext needed to access the SharedPreferences
     */
    public static void removeUserTargetWeight(Context context) {

        context.getSharedPreferences(FILE_NAME, 0)
                .edit()
                .remove(USER_TARGETWEIGHT_KEY)
                .apply();
    }

    /**
     * removes the users deadline to achieve its target weight from the SharedPreferences
     * @param context the ApplicationContext needed to access the SharedPreferences
     */
    public static void removeUserDeadline(Context context) {

        context.getSharedPreferences(FILE_NAME, 0)
                .edit()
                .remove(USER_DEADLINE_KEY)
                .apply();
    }

    /**
     * removes the users deadline to achieve its target weight from the SharedPreferences
     * @param context the ApplicationContext needed to access the SharedPreferences
     */
    public static void removeFirstLaunch(Context context) {

        context.getSharedPreferences(FILE_NAME, 0)
                .edit()
                .remove(FIRST_LAUNCH_KEY)
                .apply();
    }

    public static void resetAll(Context context) {

        removeUserWeight(context);
        removeUserAge(context);
        removeUserDeadline(context);
        removeUserGender(context);
        removeUserTargetWeight(context);
        removeUserHeight(context);
        removeFirstLaunch(context);
    }
}
