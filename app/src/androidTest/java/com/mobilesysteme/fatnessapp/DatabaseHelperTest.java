package com.mobilesysteme.fatnessapp;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.mobilesysteme.fatnessapp.sqlObjects.Unit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class DatabaseHelperTest {

    private DatabaseHelper databaseHelper;

    @Before
    public void init() {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        databaseHelper = new DatabaseHelper(appContext);
        databaseHelper.refillDatabase();
    }

    @Test
    public void unitTable() {

        assertNull(databaseHelper.getUnitById(0));

        {
            Unit unit = databaseHelper.getUnitById(1);
            assertEquals(unit.getId(), 1);
            assertEquals(unit.getName(), "Milligram");
            assertEquals(unit.getToken(), "mg");
        }

        {
            Unit unit = databaseHelper.getUnitById(2);
            assertEquals(unit.getId(), 2);
            assertEquals(unit.getName(), "Milliliter");
            assertEquals(unit.getToken(), "ml");
        }

        {
            int unitId = 3;
            String testName = "unitName";
            String testToken = "unitToken";
            String updatedTestName = "updatedUnitName";
            String updatedTestToken = "updatedUnitToken";

            assertNull(databaseHelper.getUnitById(unitId));

            databaseHelper.addUnit(testName, testToken);

            Unit unit = databaseHelper.getUnitById(unitId);
            assertEquals(unit.getId(), unitId);
            assertEquals(unit.getName(), testName);
            assertEquals(unit.getToken(), testToken);

            databaseHelper.updateUnit(unitId, updatedTestName, updatedTestToken);

            unit = databaseHelper.getUnitById(unitId);
            assertEquals(unit.getId(), unitId);
            assertEquals(unit.getName(), updatedTestName);
            assertEquals(unit.getToken(), updatedTestToken);

            databaseHelper.deleteUnitById(unitId);
            assertNull(databaseHelper.getUnitById(unitId));
        }
    }
}