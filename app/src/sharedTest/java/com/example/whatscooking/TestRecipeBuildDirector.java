package com.example.whatscooking;

import com.example.whatscooking.data.entities.Recipe;
import com.example.whatscooking.data.entities.RecipeBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class TestRecipeBuildDirector {

    private Stack<String> recipeTitlesStack;

    //TODO handle case when stack runs out
    public TestRecipeBuildDirector() {
        recipeTitlesStack = TestUtils.GenerateRecipeTitlesStack();
    }

    public Recipe buildRecipe() {
        List<String> ingredients = Arrays.asList("ingredient00", "ingredient01", "ingredient02",
                "ingredient03", "ingredient04", "ingredient05", "ingredient06", "ingredient07",
                "ingredient08", "ingredient09");
        List<String> recipeSteps = Arrays.asList("Step00", "Step01", "Step02", "Step03", "Step04",
                "Step05");
        return new RecipeBuilder(recipeTitlesStack.pop()).setIngredients(new ArrayList<>(ingredients))
                .setInstructions(new ArrayList<>(recipeSteps)).build();
    }

}
