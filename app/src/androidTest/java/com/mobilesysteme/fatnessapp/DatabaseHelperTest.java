package com.mobilesysteme.fatnessapp;

import android.content.Context;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.mobilesysteme.fatnessapp.sqlObjects.EatenFood;
import com.mobilesysteme.fatnessapp.sqlObjects.EatenRecipe;
import com.mobilesysteme.fatnessapp.sqlObjects.Food;
import com.mobilesysteme.fatnessapp.sqlObjects.FoodGroup;
import com.mobilesysteme.fatnessapp.sqlObjects.Recipe;
import com.mobilesysteme.fatnessapp.sqlObjects.Unit;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Test
    public void rootAndChildFoodGroups() {

        List<FoodGroup> rootFoodGroups = databaseHelper.getRootFoodGroups();
        assertEquals(8, rootFoodGroups.size());

        for(FoodGroup rootFoodGroup : rootFoodGroups) {

            List<FoodGroup> childFoodGroups = databaseHelper.getChildFoodGroups(rootFoodGroup.getId());
            if (childFoodGroups.isEmpty()) {

                List<Food> foods = databaseHelper.getFoodByFoodGroupId(rootFoodGroup.getId());
                assertFalse(foods.isEmpty());
            } else {

                for (FoodGroup childFoodGroup : childFoodGroups) {

                    List<Food> foods = databaseHelper.getFoodByFoodGroupId(childFoodGroup.getId());
                    assertFalse(foods.isEmpty());
                }
            }
        }
    }

    @Test
    public void foodGroupTable() {

        {
            int id = 1;
            FoodGroup foodGroup = databaseHelper.getFoodGroupById(id);
            assertNotNull(foodGroup);

            while (foodGroup != null) {

                assertEquals(id, foodGroup.getId());
                assertNotNull(foodGroup.getName());
                assertTrue(foodGroup.getParentId() == -1 || databaseHelper.getFoodGroupById(foodGroup.getParentId()) != null);

                id++;
                foodGroup = databaseHelper.getFoodGroupById(id);
            }

            assertNull(databaseHelper.getFoodGroupById(id));
        }

        {
            int parentId = 1;
            List<FoodGroup> parentChildFoodGroups = databaseHelper.getChildFoodGroups(parentId);
            String testName = "foodGroupName";
            int testId = databaseHelper.addFoodGroup(parentId, testName);
            FoodGroup newFoodGroup = databaseHelper.getFoodGroupById(testId);

            assertNotNull(newFoodGroup);
            assertEquals(parentId, newFoodGroup.getParentId());
            assertEquals(testName, newFoodGroup.getName());
            assertEquals(parentChildFoodGroups.size() + 1, databaseHelper.getChildFoodGroups(parentId).size());

            int updatedParentId = parentId + 1;
            String updatedTestName = "updatedFoodGroupName";
            databaseHelper.updateFoodGroup(testId, updatedParentId, updatedTestName);

            FoodGroup updatedFoodGroup = databaseHelper.getFoodGroupById(testId);

            assertNotNull(updatedFoodGroup);
            assertEquals(updatedParentId, updatedFoodGroup.getParentId());
            assertEquals(updatedTestName, updatedFoodGroup.getName());
            assertEquals(parentChildFoodGroups.size(), databaseHelper.getChildFoodGroups(parentId).size());

            databaseHelper.deleteFoodGroupById(testId);
            assertNull(databaseHelper.getFoodGroupById(testId));
        }
    }

    @Test
    public void foodTable() {

        {
            int id = 1;

            Food food = databaseHelper.getFoodById(id);
            assertNotNull(food);

            while (food != null) {

                System.out.println("id:  " + id);

                assertEquals(id, food.getId());
                assertNotNull(food.getName());
                assertNotNull(databaseHelper.getFoodGroupById(food.getGroupId()));
                assertNotNull(databaseHelper.getUnitById(food.getUnitId()));
                assertTrue(food.getCaloriesPer100g() >= 0);
                assertTrue(food.getDefaultQuantity() > 0);

                id++;
                food = databaseHelper.getFoodById(id);
            }
        }


        int groupId = 1;
        List<Food> foodGroupFoods = databaseHelper.getFoodByFoodGroupId(groupId);
        String name = "foodName";
        int unitId = 1;
        int calories = 2;
        int defaultQuantity = 3;

        int id = databaseHelper.addFood(groupId, name, unitId, calories, defaultQuantity);
        Food newFood = databaseHelper.getFoodById(id);

        assertEquals(id, newFood.getId());
        assertEquals(name, newFood.getName());
        assertEquals(unitId, newFood.getUnitId());
        assertEquals(calories, newFood.getCaloriesPer100g());
        assertEquals(defaultQuantity, newFood.getDefaultQuantity());
        assertEquals(foodGroupFoods.size() + 1, databaseHelper.getFoodByFoodGroupId(groupId).size());

        int updatedGroupId = groupId + 1;
        String updatedName = "updatedFoodName";
        int updatedUnitId = 2;
        int updatedCalories = 3;
        int updatedDefaultQuantity = 4;
        databaseHelper.updateFood(id, updatedGroupId, updatedName, updatedUnitId, updatedCalories, updatedDefaultQuantity);

        Food updatedFood = databaseHelper.getFoodById(id);

        assertEquals(id, updatedFood.getId());
        assertEquals(updatedName, updatedFood.getName());
        assertEquals(updatedUnitId, updatedFood.getUnitId());
        assertEquals(updatedCalories, updatedFood.getCaloriesPer100g());
        assertEquals(updatedDefaultQuantity, updatedFood.getDefaultQuantity());
        assertEquals(foodGroupFoods, databaseHelper.getFoodByFoodGroupId(groupId));

        databaseHelper.deleteFoodById(id);
        assertNull(databaseHelper.getFoodById(id));
    }

    @Test
    public void recipeTable() {

        {
            int id = 1;

            Recipe recipe = databaseHelper.getRecipeById(id);
            assertNotNull(recipe);

            while (recipe != null) {

                assertEquals(id, recipe.getId());
                assertNotNull(recipe.getName());
                assertNotNull(recipe.getDescription());

                id++;
                recipe = databaseHelper.getRecipeById(id);
            }
        }

        String name = "recipeName";
        String description = "recipeDescription";
        int id = databaseHelper.addRecipe(name, description);

        Recipe newRecipe = databaseHelper.getRecipeById(id);
        assertNotNull(newRecipe);
        assertEquals(newRecipe.getName(), name);
        assertEquals(newRecipe.getDescription(), description);

        String updatedName = "updatedRecipeName";
        String updatedDescription = "updatedRecipeDescription";
        databaseHelper.updateRecipe(id, updatedName, updatedDescription);

        Recipe updatedRecipe = databaseHelper.getRecipeById(id);
        assertNotNull(updatedRecipe);
        assertEquals(updatedRecipe.getName(), updatedName);
        assertEquals(updatedRecipe.getDescription(), updatedDescription);

        databaseHelper.deleteRecipeById(id);
        assertNull(databaseHelper.getRecipeById(id));
    }

    @Test
    public void eatenFoodTable() {

        {
            assertTrue(databaseHelper.getEatenFoods().isEmpty());
            assertTrue(databaseHelper.getTodayEatenFoods().isEmpty());

            databaseHelper.addEatenFood(1, 2, new Date());
            databaseHelper.addEatenFood(3, 4, new Date());
            databaseHelper.addEatenFood(5, 6, new Date(new Date().getTime() - 86400000));
            databaseHelper.addEatenFood(7, 8, new Date(new Date().getTime() - 86400000));

            List<EatenFood> eatenFoods = databaseHelper.getEatenFoods();
            assertEquals(4, eatenFoods.size());

            List<EatenFood> todayEatenFoods = databaseHelper.getTodayEatenFoods();
            assertEquals(2, todayEatenFoods.size());
        }

        {
            int foodId = 1;
            int calories = 2;
            Date date = new Date();
            int id = databaseHelper.addEatenFood(foodId, calories, date);

            EatenFood eatenFood = databaseHelper.getEatenFoodById(id);
            assertNotNull(eatenFood);
            assertEquals(foodId, eatenFood.getEatenId());
            assertEquals(calories, eatenFood.getCalories());
            assertEquals(date.getTime() - (date.getTime() % 1000), eatenFood.getDate().getTime());

            int updatedFoodId = 2;
            int updatedCalories = 2;
            Date updatedDate = new Date();
            databaseHelper.updateEatenFood(id, updatedFoodId, updatedCalories, updatedDate);

            EatenFood updatedEatenFood = databaseHelper.getEatenFoodById(id);
            assertNotNull(updatedEatenFood);
            assertEquals(updatedFoodId, updatedEatenFood.getEatenId());
            assertEquals(updatedCalories, updatedEatenFood.getCalories());
            assertEquals(updatedDate.getTime() - (updatedDate.getTime() % 1000), updatedEatenFood.getDate().getTime());

            databaseHelper.deleteEatenRecipeById(id);
            assertNull(databaseHelper.getEatenRecipeById(id));
        }
    }

    @Test
    public void eatenRecipeTable() {

        {
            assertTrue(databaseHelper.getEatenRecipes().isEmpty());
            assertTrue(databaseHelper.getTodayEatenRecipes().isEmpty());

            databaseHelper.addEatenRecipe(1, 2, new Date());
            databaseHelper.addEatenRecipe(3, 4, new Date());
            databaseHelper.addEatenRecipe(5, 6, new Date(new Date().getTime() - 86400000));
            databaseHelper.addEatenRecipe(7, 8, new Date(new Date().getTime() - 86400000));

            List<EatenRecipe> eatenRecipes = databaseHelper.getEatenRecipes();
            assertEquals(4, eatenRecipes.size());

            List<EatenRecipe> todayEatenRecipes = databaseHelper.getTodayEatenRecipes();
            assertEquals(2, todayEatenRecipes.size());
        }

        {
            int recipeId = 1;
            int calories = 2;
            Date date = new Date();
            int id = databaseHelper.addEatenRecipe(recipeId, calories, date);

            EatenRecipe eatenRecipe = databaseHelper.getEatenRecipeById(id);
            assertNotNull(eatenRecipe);
            assertEquals(recipeId, eatenRecipe.getEatenId());
            assertEquals(calories, eatenRecipe.getCalories());
            assertEquals(date.getTime() - (date.getTime() % 1000), eatenRecipe.getDate().getTime());

            int updatedRecipeId = 2;
            int updatedCalories = 3;
            Date updatedDate = new Date();
            databaseHelper.updateEatenRecipe(id, updatedRecipeId, updatedCalories, updatedDate);

            EatenRecipe updatedEatenRecipe = databaseHelper.getEatenRecipeById(id);
            assertNotNull(updatedEatenRecipe);
            assertEquals(updatedRecipeId, updatedEatenRecipe.getEatenId());
            assertEquals(updatedCalories, updatedEatenRecipe.getCalories());
            assertEquals(updatedDate.getTime() - (updatedDate.getTime() % 1000), updatedEatenRecipe.getDate().getTime());

            databaseHelper.deleteEatenFoodById(id);
            assertNull(databaseHelper.getEatenFoodById(id));
        }
    }

    @Test
    public void recipeIngredients () {

        int id = 1;

        Map<Food, Integer> ingredients = databaseHelper.getIngredientMapByRecipeId(id);
        assertNotNull(ingredients);
        assertFalse(ingredients.isEmpty());

        for (Map.Entry<Food, Integer> ingredientEntry : ingredients.entrySet()) {

            assertNotNull(ingredientEntry.getKey());
            assertNotNull(ingredientEntry.getValue());
            assertTrue(ingredientEntry.getValue() > 0);
        }

        databaseHelper.deleteRecipeIngredientsByRecipeId(id);
        assertTrue(databaseHelper.getIngredientMapByRecipeId(id).isEmpty());

        databaseHelper.addRecipeIngredient(id, 1, 2);
        assertEquals(1, databaseHelper.getIngredientMapByRecipeId(id).size());

        Map<Integer, Integer> ingredientQuantityMap = new HashMap<>();
        ingredientQuantityMap.put(3, 4);
        ingredientQuantityMap.put(5, 6);
        ingredientQuantityMap.put(7, 8);
        databaseHelper.addRecipeIngredients(id, ingredientQuantityMap);
        ingredientQuantityMap.put(1, 2);

        Map<Food, Integer> updatedIngredients = databaseHelper.getIngredientMapByRecipeId(id);
        assertEquals(4, updatedIngredients.size());

        for (Map.Entry<Food, Integer> updatedIngredientEntry : updatedIngredients.entrySet()) {

            int foodId = updatedIngredientEntry.getKey().getId();
            assertTrue(ingredientQuantityMap.containsKey(foodId));
            assertEquals(ingredientQuantityMap.get(foodId), updatedIngredientEntry.getValue());
        }
    }

    @Test
    public void todayEatenCalories() {

    }
}