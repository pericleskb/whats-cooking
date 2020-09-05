package com.example.whatscooking;

import androidx.test.core.app.ApplicationProvider;

import com.example.whatscooking.data.entities.RecipeDetails;
import com.example.whatscooking.data.entities.RecipeDetailsBuilder;
import com.example.whatscooking.utilities.MediaOperations;

import java.util.Stack;

public class TestRecipeDetailsBuildDirector {

    private Stack<String> recipeTitlesStack = new Stack<>();
    String uri;

    //TODO handle case when stack runs out
    public TestRecipeDetailsBuildDirector() {
        recipeTitlesStack.push("Venison steak");
        recipeTitlesStack.push("Fried squid");
        recipeTitlesStack.push("Mousakas 04/09/20");
        recipeTitlesStack.push("Falafel wrap 2");
        recipeTitlesStack.push("Dahl");
        uri = MediaOperations.createPlaceholderImage(
                ApplicationProvider.getApplicationContext());
    }

    public RecipeDetails buildFullRecipeDetails() {
        return new RecipeDetailsBuilder(recipeTitlesStack.pop())
                .setTimeMinutes(60)
                .setDifficulty(RecipeDetails.Difficulty.medium)
                .setDescription("description")
                .setServings(5)
                .setImageUri(uri)
                .build();
    }

    public RecipeDetails buildRecipeMissingMinutes() {
        return new RecipeDetailsBuilder(recipeTitlesStack.pop())
                .setDifficulty(RecipeDetails.Difficulty.medium)
                .setDescription("description")
                .setServings(5)
                .setImageUri(uri)
                .build();
    }

    public RecipeDetails buildecipeDetailsMissingDifficulty() {
        return new RecipeDetailsBuilder(recipeTitlesStack.pop())
                .setTimeMinutes(60)
                .setDescription("description")
                .setServings(5)
                .setImageUri(uri)
                .build();
    }

    public RecipeDetails buildRecipeDetailsMissingDescription() {
        return new RecipeDetailsBuilder(recipeTitlesStack.pop())
                .setTimeMinutes(60)
                .setDifficulty(RecipeDetails.Difficulty.medium)
                .setServings(5)
                .setImageUri(uri)
                .build();
    }

    public RecipeDetails buildRecipeDetailsMissingServings() {
        return new RecipeDetailsBuilder(recipeTitlesStack.pop())
                .setTimeMinutes(60)
                .setDifficulty(RecipeDetails.Difficulty.medium)
                .setDescription("description")
                .setImageUri(uri)
                .build();
    }

    public RecipeDetails buildRecipeDetailsMissingImage() {
        return new RecipeDetailsBuilder(recipeTitlesStack.pop())
                .setTimeMinutes(60)
                .setDifficulty(RecipeDetails.Difficulty.medium)
                .setDescription("description")
                .setServings(5)
                .build();
    }
}
