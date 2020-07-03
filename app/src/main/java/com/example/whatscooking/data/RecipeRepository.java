package com.example.whatscooking.data;

import androidx.lifecycle.LiveData;

import com.example.whatscooking.data.entities.Recipe;
import com.example.whatscooking.data.entities.RecipeDetails;

import java.util.List;

public interface RecipeRepository {
    //TODO get part of the recipe infos ordered in case of large amounts of data
    LiveData<List<RecipeDetails>> getAllRecipesDetails();

    //TODO When I add the server maybe make this LiveData in case a shared recipe gets updated?
    LiveData<List<Recipe>> getAllRecipes();

    LiveData<Recipe> getRecipe(String recipeTitle);

    LiveData<RecipeDetails> getRecipeDetails(String recipeTitle);

    void insertRecipe(RecipeDetails recipeDetails, Recipe recipe);

    void updateRecipe(RecipeDetails recipeDetails, Recipe recipe);

    void deleteSelectedRecipes(String... recipeTitles);

    void deleteRecipe(RecipeDetails recipeDetails);
}
