package com.example.whatscooking;

import com.example.whatscooking.data.entities.RecipeDetails;

import java.util.ArrayList;
import java.util.Stack;

public class TestUtils {

    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }

    public static ArrayList<RecipeDetails> getRecipesInfoList() {
        TestRecipeDetailsBuildDirector director = new TestRecipeDetailsBuildDirector();
        ArrayList<RecipeDetails> recipesDetails = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            recipesDetails.add(director.buildFullRecipeDetails());
        }
        return recipesDetails;
    }

    public static Stack<String> GenerateRecipeTitlesStack() {
        Stack<String> recipeTitlesStack = new Stack<>();
        recipeTitlesStack.push("Venison steak");
        recipeTitlesStack.push("Fried squid");
        recipeTitlesStack.push("Mousakas 04/09/20");
        recipeTitlesStack.push("Falafel wrap 2");
        recipeTitlesStack.push("Dahl");
        return recipeTitlesStack;
    }

//    private static Vector<Recipe> getRecipeVector() {
//        Vector<Recipe> recipeVector = new Vector<>();
//        List<String> ingredients4 = Arrays.asList("ingredient40", "ingredient41", "ingredient42", "ingredient43", "ingredient44");
//        List<String> recipeSteps4 = Arrays.asList("Step40", "Step41", "Step42", "Step43", "Step44", "Step45");
//        List<String> ingredients3 = Arrays.asList("ingredient30", "ingredient31", "ingredient32", "ingredient33", "ingredient34");
//        List<String> recipeSteps3 = Arrays.asList("Step30", "Step31", "Step32", "Step33", "Step34", "Step35");
//        List<String> ingredients2 = Arrays.asList("ingredient20", "ingredient21", "ingredient22", "ingredient23", "ingredient24");
//        List<String> recipeSteps2 = Arrays.asList("Step20", "Step21", "Step22", "Step23", "Step24", "Step25");
//        List<String> ingredients1 = Arrays.asList("ingredient10", "ingredient11", "ingredient12", "ingredient13", "ingredient14");
//        List<String> recipeSteps1 = Arrays.asList("Step10", "Step11", "Step12", "Step13", "Step14", "Step15");
//        List<String> ingredients0 = Arrays.asList("ingredient00", "ingredient01", "ingredient02", "ingredient03", "ingredient04");
//        List<String> recipeSteps0 = Arrays.asList("Step00", "Step01", "Step02", "Step03", "Step04", "Step05");
//
//        recipeVector.add(new RecipeBuilder("recipe4").setIngredients(new ArrayList<>(ingredients4))
//                .setInstructions(new ArrayList<>(recipeSteps4)).build());
//        recipeVector.add(new RecipeBuilder("recipe3").setIngredients(new ArrayList<>(ingredients3))
//                .setInstructions(new ArrayList<>(recipeSteps3)).build());
//        recipeVector.add(new RecipeBuilder("recipe2").setIngredients(new ArrayList<>(ingredients2))
//                .setInstructions(new ArrayList<>(recipeSteps2)).build());
//        recipeVector.add(new RecipeBuilder("recipe1").setIngredients(new ArrayList<>(ingredients1))
//                .setInstructions(new ArrayList<>(recipeSteps1)).build());
//        recipeVector.add(new RecipeBuilder("recipe0").setIngredients(new ArrayList<>(ingredients0))
//                .setInstructions(new ArrayList<>(recipeSteps0)).build());
//        return recipeVector;
//    }

}


