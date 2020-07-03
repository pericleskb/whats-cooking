package com.example.whatscooking;

import com.example.whatscooking.data.entities.RecipeDetails;
import com.example.whatscooking.data.entities.RecipeDetailsBuilder;

import java.util.ArrayList;
import java.util.Stack;
import java.util.Vector;

public class TestUtils {
    
    public static Stack<RecipeDetails> getRecipesStack() {
        Stack<RecipeDetails> recipeDetails = new Stack<>();
        recipeDetails.addAll(getRecipeVector());
        return recipeDetails;
    }

    public static ArrayList<RecipeDetails> getRecipesList() {
        ArrayList<RecipeDetails> recipesDetails = new ArrayList<>();
        recipesDetails.addAll(getRecipeVector());
        return recipesDetails;
    }

    private static Vector<RecipeDetails> getRecipeVector() {
        Vector<RecipeDetails> recipeDetailsVector = new Vector<>();
        recipeDetailsVector.add(new RecipeDetailsBuilder("Dahl").setTimeMinutes(60).setDifficulty(RecipeDetails.Difficulty.medium)
                .setDescription("description")
                .setServings(5).build());
        recipeDetailsVector.add(new RecipeDetailsBuilder("Falafel wrap").setDifficulty(RecipeDetails.Difficulty.easy)
                .setDescription("description")
                .setImageUri(null)
                .setServings(4).build());
        recipeDetailsVector.add(new RecipeDetailsBuilder("Mousakas").setTimeMinutes(90).setDifficulty(RecipeDetails.Difficulty.hard)
                .setDescription("description")
                .setServings(8).build());
        recipeDetailsVector.add(new RecipeDetailsBuilder("Fried Squid").setTimeMinutes(45).setDifficulty(RecipeDetails.Difficulty.medium)
                .setDescription("description")
                .setServings(3).build());
        recipeDetailsVector.add(new RecipeDetailsBuilder("Venison steak").setTimeMinutes(30)
                .setDescription("description")
                .setServings(2).build());
        return recipeDetailsVector;
    }
}
