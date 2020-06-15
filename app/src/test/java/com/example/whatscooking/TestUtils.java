package com.example.whatscooking;

import com.example.whatscooking.data.entities.RecipeInfo;
import com.example.whatscooking.data.RecipeBuilder;

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
        recipeInfoVector.add(new RecipeBuilder("Dahl").setTimeMinutes(60).setDifficulty(RecipeInfo.Difficulty.medium)
                .setDescription("description")
                .setServings(5).build());
        recipeInfoVector.add(new RecipeBuilder("Falafel wrap").setDifficulty(RecipeInfo.Difficulty.easy)
                .setDescription("description")
                .setImageUri(null)
                .setServings(4).build());
        recipeInfoVector.add(new RecipeBuilder("Mousakas").setTimeMinutes(90).setDifficulty(RecipeInfo.Difficulty.hard)
                .setDescription("description")
                .setServings(8).build());
        recipeInfoVector.add(new RecipeBuilder("Fried Squid").setTimeMinutes(45).setDifficulty(RecipeInfo.Difficulty.medium)
                .setDescription("description")
                .setServings(3).build());
        recipeInfoVector.add(new RecipeBuilder("Venison steak").setTimeMinutes(30)
                .setDescription("description")
                .setServings(2).build());
        return recipeInfoVector;
    }
}
