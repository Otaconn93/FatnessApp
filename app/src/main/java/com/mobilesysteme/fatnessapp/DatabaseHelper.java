package com.mobilesysteme.fatnessapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.mobilesysteme.fatnessapp.sqlObjects.Food;
import com.mobilesysteme.fatnessapp.sqlObjects.FoodGroup;
import com.mobilesysteme.fatnessapp.sqlObjects.Recipe;
import com.mobilesysteme.fatnessapp.sqlObjects.RecipeIngredient;
import com.mobilesysteme.fatnessapp.sqlObjects.Unit;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "fatness.db";
    private static final int DATABASE_VERSION = 1;


    // UNIT_TABLE
    private static final String UNIT_TABLE_NAME = "unit";

    private static final String UNIT_ID = "_id";
    private static final String UNIT_ID_TYPE = SqlFieldType.INTEGER.toString();

    private static final String UNIT_NAME = "name";
    private static final String UNIT_NAME_TYPE = SqlFieldType.TEXT.toString();

    private static final String UNIT_TOKEN = "token";
    private static final String UNIT_TOKEN_TYPE = SqlFieldType.TEXT.toString();

    private static final String UNIT_CREATE_TABLE = "CREATE TABLE " + UNIT_TABLE_NAME + "("
            + UNIT_ID + " " + UNIT_ID_TYPE + " PRIMARY KEY AUTOINCREMENT, "
            + UNIT_NAME + " " + UNIT_NAME_TYPE + ", "
            + UNIT_TOKEN + " " + UNIT_TOKEN_TYPE + ")";


    // FOODGROUP_TABLE
    private static final String FOODGROUP_TABLE_NAME = "food_group";

    private static final String FOODGROUP_ID = "_id";
    private static final String FOODGROUP_ID_TYPE = SqlFieldType.INTEGER.toString();

    private static final String FOODGROUP_PARENT_ID = "parent_id";
    private static final String FOODGROUP_PARENT_ID_TYPE = SqlFieldType.INTEGER.toString();

    private static final String FOODGROUP_NAME = "name";
    private static final String FOODGROUP_NAME_TYPE = SqlFieldType.TEXT.toString();

    private static final String FOODGROUP_CREATE_TABLE = "CREATE TABLE " + FOODGROUP_TABLE_NAME + "("
            + FOODGROUP_ID + " " + FOODGROUP_ID_TYPE + " PRIMARY KEY AUTOINCREMENT, "
            + FOODGROUP_PARENT_ID + " " + FOODGROUP_PARENT_ID_TYPE + ", "
            + FOODGROUP_NAME + " " + FOODGROUP_NAME_TYPE + ", "
            + "FOREIGN KEY(" + FOODGROUP_PARENT_ID + ") REFERENCES " + FOODGROUP_TABLE_NAME + "(" + FOODGROUP_ID + "))";


    // FOOD_TABLE
    private static final String FOOD_TABLE_NAME = "food";

    private static final String FOOD_ID = "_id";
    private static final String FOOD_ID_TYPE = SqlFieldType.INTEGER.toString();

    private static final String FOOD_GROUP_ID = "group_id";
    private static final String FOOD_GROUP_ID_TYPE = SqlFieldType.INTEGER.toString();

    private static final String FOOD_NAME = "name";
    private static final String FOOD_NAME_TYPE = SqlFieldType.TEXT.toString();

    private static final String FOOD_UNIT_ID = "unit_id";
    private static final String FOOD_UNIT_ID_TYPE = SqlFieldType.INTEGER.toString();

    private static final String FOOD_CALORIES = "calories";
    private static final String FOOD_CALORIES_TYPE = SqlFieldType.INTEGER.toString();

    private static final String FOOD_DEFAULT_QUANTITY = "default_quantity";
    private static final String FOOD_DEFAULT_QUANTITY_TYPE = SqlFieldType.INTEGER.toString();

    private static final String FOOD_CREATE_TABLE = "CREATE TABLE " + FOOD_TABLE_NAME + "("
            + FOOD_ID + " " + FOOD_ID_TYPE + " PRIMARY KEY AUTOINCREMENT, "
            + FOOD_GROUP_ID + " " + FOOD_GROUP_ID_TYPE + ", "
            + FOOD_NAME + " " + FOOD_NAME_TYPE + ", "
            + FOOD_UNIT_ID + " " + FOOD_UNIT_ID_TYPE + ", "
            + FOOD_CALORIES + " " + FOOD_CALORIES_TYPE + ", "
            + FOOD_DEFAULT_QUANTITY + " " + FOOD_DEFAULT_QUANTITY_TYPE + ", "
            + "FOREIGN KEY(" + FOOD_GROUP_ID + ") REFERENCES " + FOODGROUP_TABLE_NAME + "(" + FOODGROUP_ID + "), "
            + "FOREIGN KEY(" + FOOD_UNIT_ID + ") REFERENCES " + UNIT_TABLE_NAME + "(" + UNIT_ID + "))";


    // RECIPE_TABLE
    private static final String RECIPE_TABLE_NAME = "food_group";

    private static final String RECIPE_ID = "_id";
    private static final String RECIPE_ID_TYPE = SqlFieldType.INTEGER.toString();

    private static final String RECIPE_NAME = "name";
    private static final String RECIPE_NAME_TYPE = SqlFieldType.TEXT.toString();

    private static final String RECIPE_DEFAULT_CALORIES = "default_calories";
    private static final String RECIPE_DEFAULT_CALORIES_TYPE = SqlFieldType.INTEGER.toString();

    private static final String RECIPE_DESCRIPTION = "description";
    private static final String RECIPE_DESCRIPTION_TYPE = SqlFieldType.TEXT.toString();

    private static final String RECIPE_CREATE_TABLE = "CREATE TABLE " + RECIPE_TABLE_NAME + "("
            + RECIPE_ID + " " + RECIPE_ID_TYPE + " PRIMARY KEY AUTOINCREMENT, "
            + RECIPE_NAME + " " + RECIPE_NAME_TYPE + ", "
            + RECIPE_DEFAULT_CALORIES + " " + RECIPE_DEFAULT_CALORIES_TYPE + ", "
            + RECIPE_DESCRIPTION + " " + RECIPE_DESCRIPTION_TYPE + ")";


    // RECIPEINGREDIENT_TABLE
    private static final String RECIPEINGREDIENT_TABLE_NAME = "food_group";

    private static final String RECIPEINGREDIENT_RECIPE_ID = "recipe_id";
    private static final String RECIPEINGREDIENT_RECIPE_ID_TYPE = SqlFieldType.INTEGER.toString();

    private static final String RECIPEINGREDIENT_INGREDIENT_ID = "ingredient_id";
    private static final String RECIPEINGREDIENT_INGREDIENT_ID_TYPE = SqlFieldType.INTEGER.toString();

    private static final String RECIPEINGREDIENT_INGREDIENT_QUANTITY = "ingredient_quantity";
    private static final String RECIPEINGREDIENT_INGREDIENT_QUANTITY_TYPE = SqlFieldType.INTEGER.toString();

    private static final String RECIPEINGREDIENT_CREATE_TABLE = "CREATE TABLE " + RECIPE_TABLE_NAME + "("
            + RECIPEINGREDIENT_RECIPE_ID + " " + RECIPEINGREDIENT_RECIPE_ID_TYPE + ", "
            + RECIPEINGREDIENT_INGREDIENT_ID + " " + RECIPEINGREDIENT_INGREDIENT_ID_TYPE + ", "
            + RECIPEINGREDIENT_INGREDIENT_QUANTITY + " " + RECIPEINGREDIENT_INGREDIENT_QUANTITY_TYPE + ", "
            + "FOREIGN KEY(" + RECIPEINGREDIENT_RECIPE_ID + ") REFERENCES " + RECIPE_TABLE_NAME + "(" + RECIPE_ID + ") ON DELETE CASCADE, "
            + "FOREIGN KEY(" + RECIPEINGREDIENT_INGREDIENT_ID + ") REFERENCES " + FOOD_TABLE_NAME + "(" + FOOD_ID + ") ON DELETE CASCADE)";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            db.execSQL(UNIT_CREATE_TABLE);
            db.execSQL(FOODGROUP_CREATE_TABLE);
            db.execSQL(FOOD_CREATE_TABLE);
            db.execSQL(RECIPE_CREATE_TABLE);
            db.execSQL(RECIPEINGREDIENT_CREATE_TABLE);
        } catch (SQLException ex) {
            Log.e("DatabaseHelper", "Error creating tables", ex);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + UNIT_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + FOODGROUP_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + FOOD_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + RECIPE_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + RECIPEINGREDIENT_TABLE_NAME);

        onCreate(db);
    }

    public Unit getUnitById(int _id) {

        String select = "SELECT * FROM " + UNIT_TABLE_NAME
                + " WHERE " + UNIT_ID + " = '" + _id + "'";

        try(SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(select, null)) {

            Unit unit = null;
            if (cursor.moveToFirst()) {
                unit = buildUnit(cursor);
            }

            return unit;
        }
    }

    public FoodGroup getFoodGroupById(int _id) {

        String select = "SELECT * FROM " + FOODGROUP_TABLE_NAME
                + " WHERE " + FOODGROUP_ID + " = '" + _id + "'";

        try(SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(select, null)) {

            FoodGroup foodGroup = null;
            if (cursor.moveToFirst()) {
                foodGroup = buildFoodGroup(cursor);
            }

            return foodGroup;
        }
    }

    public Food getFoodById(int _id) {

        String select = "SELECT * FROM " + FOOD_TABLE_NAME
                + " WHERE " + FOOD_ID + " = '" + _id + "'";

        try(SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(select, null)) {

            Food food = null;
            if (cursor.moveToFirst()) {
                food = buildFood(cursor);
            }

            return food;
        }
    }

    public Recipe getRecipeById(int _id) {

        String select = "SELECT * FROM " + RECIPE_TABLE_NAME
                + " WHERE " + RECIPE_ID + " = '" + _id + "'";

        try(SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(select, null)) {

            Recipe recipe = null;
            if (cursor.moveToFirst()) {
                recipe = buildRecipe(cursor);
            }

            return recipe;
        }
    }

    public List<Food> getIngredientsByRecipeId(int recipe_id) {

        String select = "SELECT " + RECIPEINGREDIENT_INGREDIENT_ID
                + " FROM " + RECIPEINGREDIENT_TABLE_NAME
                + " WHERE " + RECIPEINGREDIENT_RECIPE_ID + " = '" + recipe_id + "'";

        try(SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(select, null)) {

            List<Food> ingredients = new ArrayList<>();
            if (cursor.moveToFirst()) {

                do {
                    int ingredient_id = cursor.getInt(0);
                    Food food = getFoodById(ingredient_id);                                             // TODO get by a single sql-statement
                    ingredients.add(food);
                } while (cursor.moveToNext());
            }

            return ingredients;
        }
    }

    public List<Food> getFoodByFoodGroupId(int foodgroup_id) {

        String select = "SELECT * FROM " + FOOD_TABLE_NAME
                + " WHERE " + FOOD_GROUP_ID + " = '" + foodgroup_id + "'";

        try(SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(select, null)) {

            List<Food> foods = new ArrayList<>();
            if (cursor.moveToFirst()) {

                do {
                    foods.add( buildFood(cursor));
                } while (cursor.moveToNext());
            }

            return foods;
        }
    }

    public List<FoodGroup> getRootFoodGroups() {

        return getChildFoodGroups(-1);
    }

    public List<FoodGroup> getChildFoodGroups(int parent_id) {

        String select = "SELECT * FROM " + FOODGROUP_TABLE_NAME
                + " WHERE " + FOODGROUP_PARENT_ID + " = '" + parent_id + "'";

        try(SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(select, null)) {

            List<FoodGroup> rootFoodGroups = new ArrayList<>();
            if (cursor.moveToFirst()) {

                do {
                    rootFoodGroups.add(buildFoodGroup(cursor));
                } while (cursor.moveToNext());
            }

            return rootFoodGroups;
        }
    }

    private Unit buildUnit(Cursor cursor) {

        int _id = cursor.getInt(cursor.getColumnIndex(UNIT_ID));
        String name = cursor.getString(cursor.getColumnIndex(UNIT_NAME));
        String token = cursor.getString(cursor.getColumnIndex(UNIT_TOKEN));

        return new Unit(_id, name, token);
    }

    private FoodGroup buildFoodGroup(Cursor cursor) {

        int _id = cursor.getInt(cursor.getColumnIndex(FOODGROUP_ID));
        int parent_id = cursor.getInt(cursor.getColumnIndex(FOODGROUP_PARENT_ID));
        String name = cursor.getString(cursor.getColumnIndex(FOODGROUP_NAME));

        return new FoodGroup(_id, parent_id, name);
    }

    private Food buildFood(Cursor cursor) {

        int _id = cursor.getInt(cursor.getColumnIndex(FOOD_ID));
        int group_id = cursor.getInt(cursor.getColumnIndex(FOOD_GROUP_ID));
        String name = cursor.getString(cursor.getColumnIndex(FOOD_NAME));
        int unit_id = cursor.getInt(cursor.getColumnIndex(FOOD_UNIT_ID));
        int calories = cursor.getInt(cursor.getColumnIndex(FOOD_CALORIES));
        int default_quantity = cursor.getInt(cursor.getColumnIndex(FOOD_DEFAULT_QUANTITY));

        return new Food(_id, group_id, name, unit_id, calories, default_quantity);
    }

    private Recipe buildRecipe(Cursor cursor) {

        int _id = cursor.getInt(cursor.getColumnIndex(RECIPE_ID));
        String name = cursor.getString(cursor.getColumnIndex(RECIPE_NAME));
        int default_calories = cursor.getInt(cursor.getColumnIndex(RECIPE_DEFAULT_CALORIES));
        String description = cursor.getString(cursor.getColumnIndex(RECIPE_DESCRIPTION));

        return new Recipe(_id, name, default_calories, description);
    }

    private RecipeIngredient buildRecipeIngredient(Cursor cursor) {

        int recipe_id = cursor.getInt(cursor.getColumnIndex(RECIPEINGREDIENT_RECIPE_ID));
        int ingredient_id = cursor.getInt(cursor.getColumnIndex(RECIPEINGREDIENT_INGREDIENT_ID));
        int ingredient_quantity = cursor.getInt(cursor.getColumnIndex(RECIPEINGREDIENT_INGREDIENT_QUANTITY));

        return new RecipeIngredient(recipe_id, ingredient_id, ingredient_quantity);
    }

    public int addUnit(String name, String token) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(UNIT_NAME, name);
        contentValues.put(UNIT_TOKEN, token);

        return add(UNIT_TABLE_NAME, contentValues);
    }

    public int addFoodGroup(int parent_id, String name) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(FOODGROUP_PARENT_ID, parent_id);
        contentValues.put(FOODGROUP_NAME, name);

        return add(FOODGROUP_TABLE_NAME, contentValues);
    }

    public int addFood(int group_id, String name, int unit_id, int calories, int default_quantity) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(FOOD_GROUP_ID, group_id);
        contentValues.put(FOOD_NAME, name);
        contentValues.put(FOOD_UNIT_ID, unit_id);
        contentValues.put(FOOD_CALORIES, calories);
        contentValues.put(FOOD_DEFAULT_QUANTITY, default_quantity);

        return add(FOOD_TABLE_NAME, contentValues);
    }

    public int addRecipe(String name, int default_calories, String description) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(RECIPE_NAME, name);
        contentValues.put(RECIPE_DEFAULT_CALORIES, default_calories);
        contentValues.put(RECIPE_DESCRIPTION, description);

        return add(FOOD_TABLE_NAME, contentValues);
    }

    public int addRecipeIngredient(int recipe_id, int ingredient_id, int ingredient_quantity) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(RECIPEINGREDIENT_RECIPE_ID, recipe_id);
        contentValues.put(RECIPEINGREDIENT_INGREDIENT_ID, ingredient_id);
        contentValues.put(RECIPEINGREDIENT_INGREDIENT_QUANTITY, ingredient_quantity);

        return add(FOOD_TABLE_NAME, contentValues);
    }

    public void addRecipeIngredients(int recipe_id, Map<Integer, Integer> ingredientWithQuantity) {

        for (Map.Entry<Integer, Integer> entry : ingredientWithQuantity.entrySet()) {
            addRecipeIngredient(recipe_id, entry.getKey(), entry.getValue());
        }
    }

    private int add(String tableName, ContentValues contentValues) {

        try(SQLiteDatabase db = this.getWritableDatabase()) {
            return Long.valueOf(db.insert(tableName, null, contentValues)).intValue();
        }
    }

    public void updateFoodGroup(int _id, int parent_id, String name) {

        String query = "UPDATE " + FOODGROUP_TABLE_NAME + " SET " + FOODGROUP_PARENT_ID + " = '" + parent_id + "', " + FOODGROUP_NAME + " = '" + name + "' WHERE " + FOOD_ID + " = '" + _id + "'";
        update(query);
    }

    public void updateFood(int _id, int group_id, String name, int unit_id, int calories, int default_quantity) {

        String query = "UPDATE " + FOOD_TABLE_NAME + " SET " + FOOD_GROUP_ID + " = '" + group_id + "', " + FOOD_NAME + " = '" + name + "', " + FOOD_UNIT_ID + " = '" + unit_id + "', " + FOOD_CALORIES + " = '" + calories + "', " + FOOD_DEFAULT_QUANTITY + " = '" + default_quantity + "' WHERE " + FOOD_ID + " = '" + _id + "'";
        update(query);
    }

    public void updateRecipe(int _id, String name, int default_calories, String description) {

        String query = "UPDATE " + RECIPE_TABLE_NAME + " SET " + RECIPE_NAME + " = '" + name + "', " + RECIPE_DEFAULT_CALORIES + " = '" + default_calories + "', " + RECIPE_DESCRIPTION + " = '" + description + "' WHERE " + RECIPE_ID + " = '" + _id + "'";
        update(query);
    }

    private void update(String query) {

        try(SQLiteDatabase db = this.getWritableDatabase()) {
            db.execSQL(query);
        }
    }

    public boolean deleteUnitById(int _id) {
        return delete("DELETE FROM " + UNIT_TABLE_NAME + " WHERE " + UNIT_ID + " = '" + _id + "'");
    }

    public boolean deleteFoodGroupById(int _id) {
        return delete("DELETE FROM " + FOODGROUP_TABLE_NAME + " WHERE " + FOODGROUP_ID + " = '" + _id + "'");
    }

    public boolean deleteFoodById(int _id) {
        return delete("DELETE FROM " + FOOD_TABLE_NAME + " WHERE " + FOOD_ID + " = '" + _id + "'");
    }

    public boolean deleteRecipeById(int _id) {
        return delete("DELETE FROM " + RECIPE_TABLE_NAME + " WHERE " + RECIPE_ID + " = '" + _id + "'");
    }

    public boolean deleteRecipeIngredientsByRecipeId(int recipe_id) {
        return delete("DELETE FROM " + RECIPEINGREDIENT_TABLE_NAME + " WHERE " + RECIPEINGREDIENT_RECIPE_ID + " = '" + recipe_id + "'");
    }

    private boolean delete(String deleteStatement) {

        try(SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(deleteStatement, null)) {

            return cursor.moveToFirst();
        }
    }
}
