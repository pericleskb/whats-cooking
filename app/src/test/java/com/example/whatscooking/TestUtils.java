package com.example.whatscooking;

import com.example.whatscooking.data.entities.Recipe;
import com.example.whatscooking.data.entities.RecipeBuilder;
import com.example.whatscooking.data.entities.RecipeDetails;
import com.example.whatscooking.data.entities.RecipeDetailsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

public class TestUtils {
    
    public static Stack<RecipeDetails> getRecipesDetailsStack() {
        Stack<RecipeDetails> recipeDetails = new Stack<>();
        recipeDetails.addAll(getRecipeDetailsVector());
        return recipeDetails;
    }

    public static Stack<Recipe> getRecipesStack() {
        Stack<Recipe> recipeStack = new Stack<>();
        recipeStack.addAll(getRecipeVector());
        return recipeStack;
    }

    public static ArrayList<RecipeDetails> getRecipesDetailsList() {
        ArrayList<RecipeDetails> recipesDetails = new ArrayList<>();
        recipesDetails.addAll(getRecipeDetailsVector());
        return recipesDetails;
    }

    private static Vector<RecipeDetails> getRecipeDetailsVector() {
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

    private static Vector<Recipe> getRecipeVector() {
        Vector<Recipe> recipeVector = new Vector<>();
        List<String> ingredients3 = Arrays.asList("ingredient30", "ingredient31", "ingredient32", "ingredient33", "ingredient34");
        List<String> recipeSteps3 = Arrays.asList("Step30", "Step31", "Step32", "Step33", "Step34", "Step35");
        List<String> ingredients2 = Arrays.asList("ingredient20", "ingredient21", "ingredient22", "ingredient23", "ingredient24");
        List<String> recipeSteps2 = Arrays.asList("Step20", "Step21", "Step22", "Step23", "Step24", "Step25");
        List<String> ingredients1 = Arrays.asList("ingredient10", "ingredient11", "ingredient12", "ingredient13", "ingredient14");
        List<String> recipeSteps1 = Arrays.asList("Step10", "Step11", "Step12", "Step13", "Step14", "Step15");
        List<String> ingredients0 = Arrays.asList("ingredient00", "ingredient01", "ingredient02", "ingredient03", "ingredient04");
        List<String> recipeSteps0 = Arrays.asList("Step00", "Step01", "Step02", "Step03", "Step04", "Step05");

        recipeVector.add(new RecipeBuilder("recipe3").setIngredients(new ArrayList<>(ingredients3))
                .setInstructions(new ArrayList<>(recipeSteps3)).build());
        recipeVector.add(new RecipeBuilder("recipe2").setIngredients(new ArrayList<>(ingredients2))
                .setInstructions(new ArrayList<>(recipeSteps2)).build());
        recipeVector.add(new RecipeBuilder("recipe1").setIngredients(new ArrayList<>(ingredients1))
                .setInstructions(new ArrayList<>(recipeSteps1)).build());
        recipeVector.add(new RecipeBuilder("recipe0").setIngredients(new ArrayList<>(ingredients0))
                .setInstructions(new ArrayList<>(recipeSteps0)).build());
        return recipeVector;
    }
}
