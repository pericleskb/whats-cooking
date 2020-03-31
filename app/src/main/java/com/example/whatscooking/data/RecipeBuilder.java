package com.example.whatscooking.data;

public class RecipeBuilder {
    private Recipe recipe;

    public RecipeBuilder(String title) {
        this.recipe = new Recipe(title);
    }

    public RecipeBuilder setDescription(String description) {
        recipe.description = description;
        return this;
    }

    public RecipeBuilder setDifficulty(Recipe.Difficulty difficulty) {
        recipe.difficulty = difficulty;
        return this;
    }

    public RecipeBuilder setServings(int servings) {
        recipe.servings = servings;
        return this;
    }

    public RecipeBuilder setImageUri(String uri) {
        recipe.imageUri = uri;
        return this;
    }

    public RecipeBuilder setTimeMinutes(int time) {
        recipe.timeMinutes = time;
        return this;
    }

    public Recipe build() {
        return this.recipe;
    }
}
