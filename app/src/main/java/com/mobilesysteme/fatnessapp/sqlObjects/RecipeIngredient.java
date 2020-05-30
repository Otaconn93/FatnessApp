package com.mobilesysteme.fatnessapp.sqlObjects;

import androidx.annotation.Nullable;

public class RecipeIngredient {

    private int recipe_id;
    private int ingredient_id;
    private int ingredient_quantity;

    public RecipeIngredient(int recipe_id, int ingredient_id, int ingredient_quantity) {
        this.recipe_id = recipe_id;
        this.ingredient_id = ingredient_id;
        this.ingredient_quantity = ingredient_quantity;
    }

    @Override
    public boolean equals(@Nullable Object obj) {

        if (obj == null || obj.getClass() != RecipeIngredient.class) {
            return false;
        }

        return (recipe_id == ((RecipeIngredient) obj).getRecipeId())
                && (ingredient_id == ((RecipeIngredient) obj).getIngredientId());
    }

    public int getRecipeId() {
        return recipe_id;
    }

    public int getIngredientId() {
        return ingredient_id;
    }

    public int getIngredientQuantity() {
        return ingredient_quantity;
    }
}
