package com.mobilesysteme.fatnessapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.mobilesysteme.fatnessapp.sqlObjects.Eaten;
import com.mobilesysteme.fatnessapp.sqlObjects.EatenFood;
import com.mobilesysteme.fatnessapp.sqlObjects.EatenRecipe;
import com.mobilesysteme.fatnessapp.sqlObjects.Food;
import com.mobilesysteme.fatnessapp.sqlObjects.FoodGroup;
import com.mobilesysteme.fatnessapp.sqlObjects.Recipe;
import com.mobilesysteme.fatnessapp.sqlObjects.RecipeIngredient;
import com.mobilesysteme.fatnessapp.sqlObjects.Unit;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "fatness.db";
    private static final int DATABASE_VERSION = 2;


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
    private static final String RECIPE_TABLE_NAME = "recipe";

    private static final String RECIPE_ID = "_id";
    private static final String RECIPE_ID_TYPE = SqlFieldType.INTEGER.toString();

    private static final String RECIPE_NAME = "name";
    private static final String RECIPE_NAME_TYPE = SqlFieldType.TEXT.toString();

    private static final String RECIPE_DESCRIPTION = "description";
    private static final String RECIPE_DESCRIPTION_TYPE = SqlFieldType.TEXT.toString();

    private static final String RECIPE_CREATE_TABLE = "CREATE TABLE " + RECIPE_TABLE_NAME + "("
            + RECIPE_ID + " " + RECIPE_ID_TYPE + " PRIMARY KEY AUTOINCREMENT, "
            + RECIPE_NAME + " " + RECIPE_NAME_TYPE + ", "
            + RECIPE_DESCRIPTION + " " + RECIPE_DESCRIPTION_TYPE + ")";


    // RECIPEINGREDIENT_TABLE
    private static final String RECIPEINGREDIENT_TABLE_NAME = "recipe_ingredient";

    private static final String RECIPEINGREDIENT_RECIPE_ID = "recipe_id";
    private static final String RECIPEINGREDIENT_RECIPE_ID_TYPE = SqlFieldType.INTEGER.toString();

    private static final String RECIPEINGREDIENT_INGREDIENT_ID = "ingredient_id";
    private static final String RECIPEINGREDIENT_INGREDIENT_ID_TYPE = SqlFieldType.INTEGER.toString();

    private static final String RECIPEINGREDIENT_INGREDIENT_QUANTITY = "ingredient_quantity";
    private static final String RECIPEINGREDIENT_INGREDIENT_QUANTITY_TYPE = SqlFieldType.INTEGER.toString();

    private static final String RECIPEINGREDIENT_CREATE_TABLE = "CREATE TABLE " + RECIPEINGREDIENT_TABLE_NAME + "("
            + RECIPEINGREDIENT_RECIPE_ID + " " + RECIPEINGREDIENT_RECIPE_ID_TYPE + ", "
            + RECIPEINGREDIENT_INGREDIENT_ID + " " + RECIPEINGREDIENT_INGREDIENT_ID_TYPE + ", "
            + RECIPEINGREDIENT_INGREDIENT_QUANTITY + " " + RECIPEINGREDIENT_INGREDIENT_QUANTITY_TYPE + ", "
            + "FOREIGN KEY(" + RECIPEINGREDIENT_RECIPE_ID + ") REFERENCES " + RECIPE_TABLE_NAME + "(" + RECIPE_ID + ") ON DELETE CASCADE, "
            + "FOREIGN KEY(" + RECIPEINGREDIENT_INGREDIENT_ID + ") REFERENCES " + FOOD_TABLE_NAME + "(" + FOOD_ID + ") ON DELETE CASCADE)";

    // EATENFOOD_TABLE & EATENRECIPE_TABLE
    private static final String EATENFOOD_TABLE_NAME = "eaten_food";
    private static final String EATENRECIPE_TABLE_NAME = "eaten_recipe";

    private static final String EATEN_ID = "_id";
    private static final String EATEN_ID_TYPE = SqlFieldType.INTEGER.toString();

    private static final String EATEN_EATEN_ID = "eaten_id";
    private static final String EATEN_EATEN_ID_TYPE = SqlFieldType.INTEGER.toString();

    private static final String EATEN_CALORIES = "calories";
    private static final String EATEN_CALORIES_TYPE = SqlFieldType.INTEGER.toString();

    private static final String EATEN_DATE = "date";
    private static final String EATEN_DATE_TYPE = SqlFieldType.DATETIME.toString();

    private static final String EATENFOOD_CREATE_TABLE_TYPE = "CREATE TABLE " + EATENFOOD_TABLE_NAME + "("
            + EATEN_ID + " " + EATEN_ID_TYPE + " PRIMARY KEY AUTOINCREMENT, "
            + EATEN_EATEN_ID + " " + EATEN_EATEN_ID_TYPE + ", "
            + EATEN_CALORIES + " " + EATEN_CALORIES_TYPE + ", "
            + EATEN_DATE + " " + EATEN_DATE_TYPE + " DEFAULT CURRENT_TIMESTAMP, "
            + "FOREIGN KEY(" + EATEN_EATEN_ID + ") REFERENCES " + FOOD_TABLE_NAME + "(" + FOOD_ID + "))";

    private static final String EATENRECIPE_CREATE_TABLE_TYPE = "CREATE TABLE " + EATENRECIPE_TABLE_NAME + "("
            + EATEN_ID + " " + EATEN_ID_TYPE + " PRIMARY KEY AUTOINCREMENT, "
            + EATEN_EATEN_ID + " " + EATEN_EATEN_ID_TYPE + ", "
            + EATEN_CALORIES + " " + EATEN_CALORIES_TYPE + ", "
            + EATEN_DATE + " " + EATEN_DATE_TYPE + " DEFAULT CURRENT_TIMESTAMP, "
            + "FOREIGN KEY(" + EATEN_EATEN_ID + ") REFERENCES " + RECIPE_TABLE_NAME + "(" + RECIPE_ID + "))";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * drops and afterwards restarts and refills the Database
     *
     * recommended to use when u want to reset the database with its content
     */
    public void refillDatabase() {

        try(SQLiteDatabase db = this.getWritableDatabase()) {

            dropTables(db);

            createTables(db);

            DatabaseContentHelperUtils.fillDatabase(this);
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            createTables(db);
        } catch (SQLException ex) {
            Log.e("DatabaseHelper", "Error creating tables", ex);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        dropTables(db);

        onCreate(db);
    }

    /**
     * created all tables
     * @param db the database to execute the command on
     */
    private void createTables(SQLiteDatabase db) {

        db.execSQL(UNIT_CREATE_TABLE);
        db.execSQL(FOODGROUP_CREATE_TABLE);
        db.execSQL(FOOD_CREATE_TABLE);
        db.execSQL(RECIPE_CREATE_TABLE);
        db.execSQL(RECIPEINGREDIENT_CREATE_TABLE);
        db.execSQL(EATENFOOD_CREATE_TABLE_TYPE);
        db.execSQL(EATENRECIPE_CREATE_TABLE_TYPE);
    }

    /**
     * takes a database and drops all defined tables
     * @param db the database to execute the command on
     */
    private void dropTables(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + UNIT_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + FOODGROUP_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + FOOD_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + RECIPE_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + RECIPEINGREDIENT_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + EATENFOOD_TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + EATENRECIPE_TABLE_NAME);
    }

    /**
     * returns a Unit by its id
     * @param _id id of the searched Unit
     * @return the Unit with the given id
     */
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

    /**
     * returns a FoodGroup by its id
     * @param _id id of the searched FoodGroup
     * @return the FoodGroup with the given id
     */
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

    /**
     * returns a Food by its id
     * @param _id id of the searched Food
     * @return the Food with the given id
     */
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

    /**
     * returns a Recipe by its id
     * @param _id id of the searched Recipe
     * @return the Recipe with the given id
     */
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

    /**
     * returns a List of all ingredients used for a Recipe
     * @param recipe_id id of the Recipe that u want to get the ingredients from
     * @return a List of all the used ingredients as Food
     */
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

    /**
     * returns the entry for an EatenFood by the id
     * @param _id id of the searched EatenFood
     * @return the EatenFood with the given id
     */
    public EatenFood getEatenFoodById(int _id) {

        String select = "SELECT * FROM " + EATENFOOD_TABLE_NAME
                + " WHERE " + EATEN_ID + " = '" + _id + "'";

        try(SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(select, null)) {

            EatenFood eatenFood = null;
            if (cursor.moveToFirst()) {
                eatenFood = buildEatenFood(cursor);
            }

            return eatenFood;
        }
    }

    /**
     * returns the entry for an EatenRecipe by the id
     * @param _id id of the searched EatenRecipe
     * @return the EatenRecipe with the given id
     */
    public EatenRecipe getEatenRecipeById(int _id) {

        String select = "SELECT * FROM " + EATENRECIPE_TABLE_NAME
                + " WHERE " + EATEN_ID + " = '" + _id + "'";

        try(SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(select, null)) {

            EatenRecipe eatenRecipe = null;
            if (cursor.moveToFirst()) {
                eatenRecipe = buildEatenRecipe(cursor);
            }

            return eatenRecipe;
        }
    }

    /**
     * @param foodgroup_id the id of the FoodGroup
     * @return a list of Food that are part of a specific FoodGroup
     */
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

    /**
     * @return all FoodGroups that have no other parent FoodGroup
     */
    public List<FoodGroup> getRootFoodGroups() {
        return getChildFoodGroups(-1);
    }

    /**
     * @param parent_id the id of the parent FoodGroup
     * @return all FoodGroups have a specific FoodGroup as Parent
     */
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

    /**
     * @return a List of all EatenFood
     */
    public List<EatenFood> getEatenFoods() {

        String select = "SELECT * FROM " + EATENFOOD_TABLE_NAME;

        try(SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(select, null)) {

            List<EatenFood> eatenFoods = new ArrayList<>();
            if (cursor.moveToFirst()) {

                do {
                    eatenFoods.add(buildEatenFood(cursor));
                } while (cursor.moveToNext());
            }

            return eatenFoods;
        }
    }

    /**
     * @return a List of all EatenRecipes
     */
    public List<EatenRecipe> getEatenRecipes() {

        String select = "SELECT * FROM " + EATENRECIPE_TABLE_NAME;

        try(SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(select, null)) {

            List<EatenRecipe> eatenRecipes = new ArrayList<>();
            if (cursor.moveToFirst()) {

                do {
                    eatenRecipes.add(buildEatenRecipe(cursor));
                } while (cursor.moveToNext());
            }

            return eatenRecipes;
        }
    }

    /**
     * @return a List of all EatenFood that where consumed today
     */
    public List<EatenFood> getTodayEatenFoods() {

        String select = "SELECT * FROM " + EATENFOOD_TABLE_NAME
                + " WHERE DATE(" + EATEN_DATE + ") = DATE('now')";

        try(SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(select, null)) {

            List<EatenFood> eatenFoods = new ArrayList<>();
            if (cursor.moveToFirst()) {

                do {
                    eatenFoods.add(buildEatenFood(cursor));
                } while (cursor.moveToNext());
            }

            return eatenFoods;
        }
    }

    /**
     * @return a List of all EatenFood that where consumed today
     */
    public List<EatenRecipe> getTodayEatenRecipes() {

        String select = "SELECT * FROM " + EATENRECIPE_TABLE_NAME
                + " WHERE DATE(" + EATEN_DATE + ") = DATE('now')";

        try(SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(select, null)) {

            List<EatenRecipe> eatenRecipes = new ArrayList<>();
            if (cursor.moveToFirst()) {

                do {
                    eatenRecipes.add(buildEatenRecipe(cursor));
                } while (cursor.moveToNext());
            }

            return eatenRecipes;
        }
    }

    /**
     * @return a List of all EatenRecipes that where consumed today
     */
    public int getTodayConsumedCalories() {

        String select = "SELECT Sum(" + EATEN_CALORIES + ") " +
                "FROM " + EATENRECIPE_TABLE_NAME +  ", " + EATENRECIPE_TABLE_NAME
                + " WHERE DATE(" + EATEN_DATE + ") = DATE('now')";

        try(SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery(select, null)) {

            if (cursor.moveToFirst()) {
                return cursor.getInt(cursor.getColumnIndex(EATEN_CALORIES));
            }

            return -1;
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
        String description = cursor.getString(cursor.getColumnIndex(RECIPE_DESCRIPTION));

        return new Recipe(_id, name, description);
    }

    private RecipeIngredient buildRecipeIngredient(Cursor cursor) {

        int recipe_id = cursor.getInt(cursor.getColumnIndex(RECIPEINGREDIENT_RECIPE_ID));
        int ingredient_id = cursor.getInt(cursor.getColumnIndex(RECIPEINGREDIENT_INGREDIENT_ID));
        int ingredient_quantity = cursor.getInt(cursor.getColumnIndex(RECIPEINGREDIENT_INGREDIENT_QUANTITY));

        return new RecipeIngredient(recipe_id, ingredient_id, ingredient_quantity);
    }

    private EatenFood buildEatenFood(Cursor cursor) {

        int _id = cursor.getInt(cursor.getColumnIndex(EATEN_ID));
        int food_id = cursor.getInt(cursor.getColumnIndex(EATEN_EATEN_ID));
        int calories = cursor.getInt(cursor.getColumnIndex(EATEN_CALORIES));
        String dateSting = cursor.getString(cursor.getColumnIndex(EATEN_DATE));

        return new EatenFood(_id, food_id, calories, DateUtils.getSqlDateFromString(dateSting));
    }

    private EatenRecipe buildEatenRecipe(Cursor cursor) {

        int _id = cursor.getInt(cursor.getColumnIndex(EATEN_ID));
        int recipe_id = cursor.getInt(cursor.getColumnIndex(EATEN_EATEN_ID));
        int calories = cursor.getInt(cursor.getColumnIndex(EATEN_CALORIES));
        String dateSting = cursor.getString(cursor.getColumnIndex(EATEN_DATE));

        return new EatenRecipe(_id, recipe_id, calories, DateUtils.getSqlDateFromString(dateSting));
    }

    /**
     * adds a Unit to the Database
     * @param name full name of the Unit
     * @param token the token (also called acronym) of the Unit
     * @return the id of the newly inserted Unit or -1 if it was not successful
     */
    public int addUnit(String name, String token) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(UNIT_NAME, name);
        contentValues.put(UNIT_TOKEN, token);

        return add(UNIT_TABLE_NAME, contentValues);
    }

    /**
     * adds a FoodGroup to the Database
     * @param parent_id the id of the parent FoodGroup or -1 if it has no parent FoodGroup
     * @param name the name of the FoodGroup
     * @return the id of the newly inserted FoodGroup or -1 if it was not successful
     */
    public int addFoodGroup(int parent_id, String name) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(FOODGROUP_PARENT_ID, parent_id);
        contentValues.put(FOODGROUP_NAME, name);

        return add(FOODGROUP_TABLE_NAME, contentValues);
    }

    /**
     * adds a Food to the Database
     * @param group_id the id of the FoodGroup that it is part of
     * @param name the name of the Food
     * @param unit_id the id of the Unit in which it is measured in
     * @param calories the kcal for 100g of the food
     * @param default_quantity the default quantity for this food - most of the time the weight of one entity
     * @return the id of the newly inserted Food or -1 if it was not successful
     */
    public int addFood(int group_id, String name, int unit_id, int calories, int default_quantity) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(FOOD_GROUP_ID, group_id);
        contentValues.put(FOOD_NAME, name);
        contentValues.put(FOOD_UNIT_ID, unit_id);
        contentValues.put(FOOD_CALORIES, calories);
        contentValues.put(FOOD_DEFAULT_QUANTITY, default_quantity);

        return add(FOOD_TABLE_NAME, contentValues);
    }

    /**
     * adds a Recipe to the Database
     * @param name the name of the Recipe
     * @param description the description on how to make it
     * @return the id of the newly inserted Recipe or -1 if it was not successful
     */
    public int addRecipe(String name, String description) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(RECIPE_NAME, name);
        contentValues.put(RECIPE_DESCRIPTION, description);

        return add(RECIPE_TABLE_NAME, contentValues);
    }

    /**
     * adds a RecipeIngredient to the Database
     * @param recipe_id the id of the Recipe u want to add it to
     * @param ingredient_id the id of the Food u want to add as ingredient
     * @param ingredient_quantity defines how much gram is needed of this ingredient
     * @return the id of the newly inserted RecipeIngredient or -1 if it was not successful
     */
    public int addRecipeIngredient(int recipe_id, int ingredient_id, int ingredient_quantity) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(RECIPEINGREDIENT_RECIPE_ID, recipe_id);
        contentValues.put(RECIPEINGREDIENT_INGREDIENT_ID, ingredient_id);
        contentValues.put(RECIPEINGREDIENT_INGREDIENT_QUANTITY, ingredient_quantity);

        return add(RECIPEINGREDIENT_TABLE_NAME, contentValues);
    }

    /**
     * adds multiple RecipeIngredients to the Database
     * @param recipe_id the id of the Recipe u want to add it to
     * @param ingredientWithQuantity a map of the addable ingredient ids as key with its quantities as values
     */
    public void addRecipeIngredients(int recipe_id, Map<Integer, Integer> ingredientWithQuantity) {

        for (Map.Entry<Integer, Integer> entry : ingredientWithQuantity.entrySet()) {
            addRecipeIngredient(recipe_id, entry.getKey(), entry.getValue());
        }
    }

    /**
     * adds a EatenFood to the Database
     * @param food_id the id of the Food u want to add as EatenFood
     * @param calories the calories that where consumed with this Food and its quantity
     * @param date the Date on which the Food was eaten
     * @return the id of the newly inserted EatenFood or -1 if it was not successful
     */
    public int addEatenFood(int food_id, int calories, Date date) {         // sag bescheid wenn du es anders haben willst

        ContentValues contentValues = new ContentValues();
        contentValues.put(EATEN_EATEN_ID, food_id);
        contentValues.put(EATEN_CALORIES, calories);
        contentValues.put(EATEN_DATE, DateUtils.getSqlDateAsString(date));

        return add(EATENFOOD_TABLE_NAME, contentValues);
    }

    /**
     * adds a EatenRecipe to the Database
     * @param recipe_id the id of the Recipe u want to add as EatenRecipe
     * @param calories the calories that where consumed with this Recipe and its quantity
     * @param date the Date on which the Recipe was eaten
     * @return the id of the newly inserted EatenRecipe or -1 if it was not successful
     */
    public int addEatenRecipe(int recipe_id, int calories, Date date) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(EATEN_EATEN_ID, recipe_id);
        contentValues.put(EATEN_CALORIES, calories);
        contentValues.put(EATEN_DATE, DateUtils.getSqlDateAsString(date));

        return add(EATENRECIPE_TABLE_NAME, contentValues);
    }

    public int addEatenToday(Eaten eaten, int quantity) {

        Class classOfEaten = eaten.getClass();
        if(EatenFood.class.equals(classOfEaten)) {

            return addEatenFood(eaten.getId(), getConsumedCalories(eaten, quantity), new Date());
        } else if(EatenRecipe.class.equals(classOfEaten)) {

            return addEatenRecipe(eaten.getId(), getConsumedCalories(eaten, quantity), new Date());
        } else {
            
            return -1;
        }
    }

    private int getConsumedCalories(Eaten eaten, int quantity) {
        return eaten.getCalories() / 100 * quantity;
    }

    private int add(String tableName, ContentValues contentValues) {

        try(SQLiteDatabase db = this.getWritableDatabase()) {
            return Long.valueOf(db.insert(tableName, null, contentValues)).intValue();
        }
    }

    /**
     * updates one the Unit with the given id
     */
    public void updateUnit(int _id, String name, String token) {

        String query = "UPDATE " + UNIT_TABLE_NAME + " SET " + UNIT_NAME + " = '" + name + "', " + UNIT_TOKEN + " = '" + token + "' WHERE " + UNIT_ID + " = '" + _id + "'";
        update(query);
    }

    /**
     * updates one the FoodGroup with the given id
     */
    public void updateFoodGroup(int _id, int parent_id, String name) {

        String query = "UPDATE " + FOODGROUP_TABLE_NAME + " SET " + FOODGROUP_PARENT_ID + " = '" + parent_id + "', " + FOODGROUP_NAME + " = '" + name + "' WHERE " + FOOD_ID + " = '" + _id + "'";
        update(query);
    }

    /**
     * updates one the Food with the given id
     */
    public void updateFood(int _id, int group_id, String name, int unit_id, int calories, int default_quantity) {

        String query = "UPDATE " + FOOD_TABLE_NAME + " SET " + FOOD_GROUP_ID + " = '" + group_id + "', " + FOOD_NAME + " = '" + name + "', " + FOOD_UNIT_ID + " = '" + unit_id + "', " + FOOD_CALORIES + " = '" + calories + "', " + FOOD_DEFAULT_QUANTITY + " = '" + default_quantity + "' WHERE " + FOOD_ID + " = '" + _id + "'";
        update(query);
    }

    /**
     * updates one the Recipe with the given id
     */
    public void updateRecipe(int _id, String name, String description) {

        String query = "UPDATE " + RECIPE_TABLE_NAME + " SET " + RECIPE_NAME + " = '" + name + "', " + RECIPE_DESCRIPTION + " = '" + description + "' WHERE " + RECIPE_ID + " = '" + _id + "'";
        update(query);
    }

    /**
     * updates one the EatenFood with the given id
     */
    public void updateEatenFood(int _id, int food_id, int calories, Date date) {

        String query = "UPDATE " + EATENFOOD_TABLE_NAME + " SET " + EATEN_EATEN_ID + " = '" + food_id + "', " + EATEN_CALORIES + " = '" + calories + "', " + EATEN_DATE + " = '" + date + "' WHERE " + EATEN_ID + " = '" + _id + "'";
        update(query);
    }

    /**
     * updates one the EatenRecipe with the given id
     */
    public void updateEatenRecipe(int _id, int recipe_id, int calories, Date date) {

        String query = "UPDATE " + EATENFOOD_TABLE_NAME + " SET " + EATEN_EATEN_ID + " = '" + recipe_id + "', " + EATEN_CALORIES + " = '" + calories + "', " + EATEN_DATE + " = '" + date + "' WHERE " + EATEN_ID + " = '" + _id + "'";
        update(query);
    }

    private void update(String query) {

        try(SQLiteDatabase db = this.getWritableDatabase()) {
            db.execSQL(query);
        }
    }

    /**
     * delete one the Unit with the given id
     */
    public boolean deleteUnitById(int _id) {
        return delete("DELETE FROM " + UNIT_TABLE_NAME + " WHERE " + UNIT_ID + " = '" + _id + "'");
    }

    /**
     * delete one the FoodGroup with the given id
     */
    public boolean deleteFoodGroupById(int _id) {
        return delete("DELETE FROM " + FOODGROUP_TABLE_NAME + " WHERE " + FOODGROUP_ID + " = '" + _id + "'");
    }

    /**
     * delete one the Food with the given id
     */
    public boolean deleteFoodById(int _id) {
        return delete("DELETE FROM " + FOOD_TABLE_NAME + " WHERE " + FOOD_ID + " = '" + _id + "'");
    }

    /**
     * delete one the Recipe with the given id
     */
    public boolean deleteRecipeById(int _id) {
        return delete("DELETE FROM " + RECIPE_TABLE_NAME + " WHERE " + RECIPE_ID + " = '" + _id + "'");
    }

    /**
     * delete one the RecipeIngredient with the given id
     */
    public boolean deleteRecipeIngredientsByRecipeId(int recipe_id) {
        return delete("DELETE FROM " + RECIPEINGREDIENT_TABLE_NAME + " WHERE " + RECIPEINGREDIENT_RECIPE_ID + " = '" + recipe_id + "'");
    }

    /**
     * delete one the EatenFood with the given id
     */
    public boolean deleteEatenFoodById(int _id) {
        return delete("DELETE FROM " + EATENFOOD_TABLE_NAME + " WHERE " + EATEN_ID + " = '" + _id + "'");
    }

    /**
     * delete one the EatenRecipe with the given id
     */
    public boolean deleteEatenRecipeById(int _id) {
        return delete("DELETE FROM " + EATENRECIPE_TABLE_NAME + " WHERE " + EATEN_ID + " = '" + _id + "'");
    }

    private boolean delete(String deleteStatement) {

        try(SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(deleteStatement, null)) {

            return cursor.moveToFirst();
        }
    }
}
