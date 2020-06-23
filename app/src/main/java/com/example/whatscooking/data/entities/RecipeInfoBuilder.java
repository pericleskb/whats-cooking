package com.example.whatscooking.data.entities;

public class RecipeInfoBuilder {
    private RecipeInfo recipeInfo;

    public RecipeInfoBuilder(String title) {
        this.recipeInfo = new RecipeInfo(title);
    }

    public RecipeInfoBuilder setDescription(String description) {
        recipeInfo.description = description;
        return this;
    }

    public RecipeInfoBuilder setDifficulty(RecipeInfo.Difficulty difficulty) {
        recipeInfo.difficulty = difficulty;
        return this;
    }

    public RecipeInfoBuilder setServings(int servings) {
        recipeInfo.servings = servings;
        return this;
    }

    public RecipeInfoBuilder setImageUri(String uri) {
        recipeInfo.imageUri = uri;
        return this;
    }

    public RecipeInfoBuilder setTimeMinutes(int time) {
        recipeInfo.timeMinutes = time;
        return this;
    }

    public RecipeInfo build() {
        return this.recipeInfo;
    }
}
