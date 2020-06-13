package com.example.whatscooking;

import com.example.whatscooking.data.Recipe;
import com.example.whatscooking.data.RecipeBuilder;

import java.util.ArrayList;
import java.util.Stack;
import java.util.Vector;

public class TestUtils {
    
    public static Stack<Recipe> getRecipesStack() {
        Stack<Recipe> recipes = new Stack<>();
        recipes.addAll(getRecipeVector());
        return recipes;
    }

    public static ArrayList<Recipe> getRecipesList() {
        ArrayList<Recipe> recipes = new ArrayList<>();
        recipes.addAll(getRecipeVector());
        return recipes;
    }

    private static Vector<Recipe> getRecipeVector() {
        Vector<Recipe> recipeVector = new Vector<>();
        recipeVector.add(new RecipeBuilder("Dahl").setTimeMinutes(60).setDifficulty(Recipe.Difficulty.medium)
                .setDescription("description")
                .setServings(5).build());
        recipeVector.add(new RecipeBuilder("Falafel wrap").setDifficulty(Recipe.Difficulty.easy)
                .setDescription("description")
                .setImageUri(null)
                .setServings(4).build());
        recipeVector.add(new RecipeBuilder("Mousakas").setTimeMinutes(90).setDifficulty(Recipe.Difficulty.hard)
                .setDescription("description")
                .setServings(8).build());
        recipeVector.add(new RecipeBuilder("Fried Squid").setTimeMinutes(45).setDifficulty(Recipe.Difficulty.medium)
                .setDescription("description")
                .setServings(3).build());
        recipeVector.add(new RecipeBuilder("Venison steak").setTimeMinutes(30)
                .setDescription("description")
                .setServings(2).build());
        return recipeVector;
    }
}
