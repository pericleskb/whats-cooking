package com.example.whatscooking;

import com.example.whatscooking.data.entities.RecipeInfo;
import com.example.whatscooking.data.RecipeInfoBuilder;

import java.util.ArrayList;
import java.util.Stack;
import java.util.Vector;

public class TestUtils {
    
    public static Stack<RecipeInfo> getRecipesStack() {
        Stack<RecipeInfo> recipesInfo = new Stack<>();
        recipesInfo.addAll(getRecipeVector());
        return recipesInfo;
    }

    public static ArrayList<RecipeInfo> getRecipesList() {
        ArrayList<RecipeInfo> recipesInfo = new ArrayList<>();
        recipesInfo.addAll(getRecipeVector());
        return recipesInfo;
    }

    private static Vector<RecipeInfo> getRecipeVector() {
        Vector<RecipeInfo> recipeInfoVector = new Vector<>();
        recipeInfoVector.add(new RecipeInfoBuilder("Dahl").setTimeMinutes(60).setDifficulty(RecipeInfo.Difficulty.medium)
                .setDescription("description")
                .setServings(5).build());
        recipeInfoVector.add(new RecipeInfoBuilder("Falafel wrap").setDifficulty(RecipeInfo.Difficulty.easy)
                .setDescription("description")
                .setImageUri(null)
                .setServings(4).build());
        recipeInfoVector.add(new RecipeInfoBuilder("Mousakas").setTimeMinutes(90).setDifficulty(RecipeInfo.Difficulty.hard)
                .setDescription("description")
                .setServings(8).build());
        recipeInfoVector.add(new RecipeInfoBuilder("Fried Squid").setTimeMinutes(45).setDifficulty(RecipeInfo.Difficulty.medium)
                .setDescription("description")
                .setServings(3).build());
        recipeInfoVector.add(new RecipeInfoBuilder("Venison steak").setTimeMinutes(30)
                .setDescription("description")
                .setServings(2).build());
        return recipeInfoVector;
    }
}
