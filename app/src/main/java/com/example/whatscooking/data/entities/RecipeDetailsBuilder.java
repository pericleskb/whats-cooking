package com.example.whatscooking.data.entities;

public class RecipeDetailsBuilder {
    private RecipeDetails recipeDetails;

    public RecipeDetailsBuilder(String title) {
        this.recipeDetails = new RecipeDetails(title);
    }

    public RecipeDetailsBuilder setDescription(String description) {
        recipeDetails.description = description;
        return this;
    }

    public RecipeDetailsBuilder setDifficulty(RecipeDetails.Difficulty difficulty) {
        recipeDetails.difficulty = difficulty;
        return this;
    }

    public RecipeDetailsBuilder setServings(int servings) {
        recipeDetails.servings = servings;
        return this;
    }

    public RecipeDetailsBuilder setImageUri(String uri) {
        recipeDetails.imageUri = uri;
        return this;
    }

    public RecipeDetailsBuilder setTimeMinutes(int time) {
        recipeDetails.timeMinutes = time;
        return this;
    }

    public RecipeDetailsBuilder setDataVersion(int version) {
        recipeDetails.dataVersion = version;
        return this;
    }

    public RecipeDetails build() {
        return this.recipeDetails;
    }
}
