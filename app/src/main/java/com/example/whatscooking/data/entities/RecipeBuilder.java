package com.example.whatscooking.data.entities;

import java.util.ArrayList;

public class RecipeBuilder {
    private Recipe recipe;

    public RecipeBuilder(String title) {
        this.recipe = new Recipe(title);
    }

    public RecipeBuilder setInstructions(ArrayList<String> instructions) {
        recipe.instructions = instructions;
        return this;
    }

    public RecipeBuilder setIngredients(ArrayList<String> ingredients) {
        recipe.instructions = ingredients;
        return this;
    }

    public Recipe build() {
        return this.recipe;
    }
}
