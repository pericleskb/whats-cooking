package com.example.whatscooking.data;

import com.example.whatscooking.data.entities.RecipeInfo;

public class RecipeBuilder {
    private RecipeInfo recipeInfo;

    public RecipeBuilder(String title) {
        this.recipeInfo = new RecipeInfo(title);
    }

    public RecipeBuilder setDescription(String description) {
        recipeInfo.description = description;
        return this;
    }

    public RecipeBuilder setDifficulty(RecipeInfo.Difficulty difficulty) {
        recipeInfo.difficulty = difficulty;
        return this;
    }

    public RecipeBuilder setServings(int servings) {
        recipeInfo.servings = servings;
        return this;
    }

    public RecipeBuilder setImageUri(String uri) {
        recipeInfo.imageUri = uri;
        return this;
    }

    public RecipeBuilder setTimeMinutes(int time) {
        recipeInfo.timeMinutes = time;
        return this;
    }

    public RecipeInfo build() {
        return this.recipeInfo;
    }
}
