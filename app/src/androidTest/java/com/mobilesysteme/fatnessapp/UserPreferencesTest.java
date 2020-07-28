package com.mobilesysteme.fatnessapp;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;

import com.mobilesysteme.fatnessapp.preferences.SharedPreferenceUtils;
import com.mobilesysteme.fatnessapp.preferences.UserAttributeHandler;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

public class UserPreferencesTest {

    private static final String TAG = "UserPreferencesTest";
    private Context context;
    private UserAttributeHandler userAttributeHandler;
    private DatabaseHelper databaseHelper;

    @Before
    public void init() {
        context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        userAttributeHandler = new UserAttributeHandler(context);
        databaseHelper = new DatabaseHelper(context);

        databaseHelper.refillDatabase();
        SharedPreferenceUtils.resetAll(context);
    }

    @Test
    public void testHeightValidation() {
        // Test minimum height
        assertTrue(userAttributeHandler.validateHeight(50));
        assertFalse(userAttributeHandler.validateHeight(49));

        // Test random normal height value
        assertTrue(userAttributeHandler.validateHeight(180));

        // Test maximum height
        assertTrue(userAttributeHandler.validateHeight(300));
        assertFalse(userAttributeHandler.validateHeight(301));
    }

    @Test
    public void testUserHeightSaveAndGet() {
        String userHeightInput = "180";
        int userHeight = Integer.parseInt(userHeightInput);
        userAttributeHandler.handleSaveHeight(userHeightInput);
        int result = SharedPreferenceUtils.getUserHeight(context);
        assertEquals(userHeight, result);
    }

    @Test
    public void testWeightValidation() {
        // Test minimum weight
        assertTrue(userAttributeHandler.validateWeight(20));
        assertFalse(userAttributeHandler.validateWeight(19));

        // Test random normal weight value
        assertTrue(userAttributeHandler.validateWeight(100));

        // Test maximum weight
        assertTrue(userAttributeHandler.validateWeight(500));
        assertFalse(userAttributeHandler.validateWeight(501));
    }

    @Test
    public void testUserWeightSaveAndGet() {
        String userWeightInput = "100";
        int userWeight = Integer.parseInt(userWeightInput);

        // Weight history should be empty because it's reset in init()
        assertTrue(SharedPreferenceUtils.getUserWeightHistory(context).isEmpty());

        userAttributeHandler.handleSaveWeight(userWeightInput);
        // Weight history should exist because it's initialized in handleSaveWeight()
        assertFalse(SharedPreferenceUtils.getUserWeightHistory(context).isEmpty());

        int result = SharedPreferenceUtils.getUserWeight(context);
        assertEquals(userWeight, result);
    }

    @Test
    public void testAgeValidation() {
        // Test minimum height
        assertTrue(userAttributeHandler.validateAge(18));
        assertFalse(userAttributeHandler.validateAge(17));

        // Test random normal height value
        assertTrue(userAttributeHandler.validateAge(20));

        // Test maximum height
        assertTrue(userAttributeHandler.validateAge(150));
        assertFalse(userAttributeHandler.validateAge(151));
    }

    @Test
    public void testUserAgeSaveAndGet() {
        String userAgeInput = "20";
        int userAge = Integer.parseInt(userAgeInput);
        userAttributeHandler.handleSaveAge(userAgeInput);
        int result = SharedPreferenceUtils.getUserAge(context);
        assertEquals(userAge, result);
    }

    @Test
    public void testGenderValidation() {
        assertTrue(userAttributeHandler.validateGender(Gender.findGenderById(0)));
        assertTrue(userAttributeHandler.validateGender(Gender.findGenderById(1)));
        assertFalse(userAttributeHandler.validateGender(Gender.findGenderById(2)));
    }

    @Test
    public void testUserGenderSaveAndGet() {
        int userGenderId = 0;
        Gender userGender = Gender.findGenderById(userGenderId);
        userAttributeHandler.handleSaveGender(userGenderId);
        Gender result = SharedPreferenceUtils.getUserGender(context);
        assertEquals(userGender, result);
    }

    @Test
    public void testTargetWeightValidation() {
        // Test minimum weight
        assertTrue(userAttributeHandler.validateWeight(20));
        assertFalse(userAttributeHandler.validateWeight(19));

        // Test random normal weight value
        assertTrue(userAttributeHandler.validateWeight(200));

        // Test maximum weight
        assertTrue(userAttributeHandler.validateWeight(500));
        assertFalse(userAttributeHandler.validateWeight(501));
    }

    @Test
    public void testUserTargetWeightSaveAndGet() {
        SharedPreferenceUtils.saveUserWeightNow(context, 100);

        String userTargetWeightInput = "200";
        int userTargetWeight = Integer.parseInt(userTargetWeightInput);
        userAttributeHandler.handleSaveTargetWeight(userTargetWeightInput);
        int result = SharedPreferenceUtils.getUserTargetWeight(context);
        assertEquals(userTargetWeight, result);
    }

    @Test
    public void testDeadlineValidation() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());

        // Current date should not be accepted
        assertFalse(userAttributeHandler.validateDeadline(cal.getTime()));

        // Date a day in the future should be accepted
        cal.add(Calendar.DATE, 1);
        assertTrue(userAttributeHandler.validateDeadline(cal.getTime()));
    }

}
