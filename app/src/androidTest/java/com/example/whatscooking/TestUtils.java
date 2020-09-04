package com.example.whatscooking;

import androidx.test.core.app.ApplicationProvider;

import com.example.whatscooking.data.entities.RecipeDetails;
import com.example.whatscooking.data.entities.RecipeDetailsBuilder;
import com.example.whatscooking.utilities.MediaOperations;

import java.util.ArrayList;
import java.util.Stack;
import java.util.Vector;

//TODO move to sharing folder for test and androidTest
public class TestUtils {

    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }

    public static Stack<RecipeDetails> getRecipesStack() {
        Stack<RecipeDetails> recipesDetails = new Stack<>();
        recipesDetails.addAll(getRecipeVector());
        return recipesDetails;
    }

    public static ArrayList<RecipeDetails> getRecipesInfoList() {
        ArrayList<RecipeDetails> recipesDetails = new ArrayList<>();
        recipesDetails.addAll(getRecipeVector());
        return recipesDetails;
    }

    private static Vector<RecipeDetails> getRecipeVector() {
        Vector<RecipeDetails> recipeDetailsVector = new Vector<>();
        String uri = MediaOperations.createPlaceholderImage(ApplicationProvider.getApplicationContext());
        recipeDetailsVector.add(new RecipeDetailsBuilder("Dahl").setTimeMinutes(60).setDifficulty(RecipeDetails.Difficulty.medium)
                .setDescription("description")
                .setServings(5)
                .setImageUri(uri).build());
        recipeDetailsVector.add(new RecipeDetailsBuilder("Falafel wrap").setDifficulty(RecipeDetails.Difficulty.easy)
                .setDescription("description")
                .setImageUri(null)
                .setServings(4)
                .setImageUri(uri).build());
        recipeDetailsVector.add(new RecipeDetailsBuilder("Mousakas").setTimeMinutes(90).setDifficulty(RecipeDetails.Difficulty.hard)
                .setDescription("description")
                .setServings(8)
                .setImageUri(uri).build());
        recipeDetailsVector.add(new RecipeDetailsBuilder("Fried Squid").setTimeMinutes(45).setDifficulty(RecipeDetails.Difficulty.medium)
                .setDescription("description")
                .setServings(3)
                .setImageUri(uri).build());
        recipeDetailsVector.add(new RecipeDetailsBuilder("Venison steak").setTimeMinutes(30)
                .setDescription("description")
                .setServings(2)
                .setImageUri(uri).build());
        return recipeDetailsVector;
    }
}


