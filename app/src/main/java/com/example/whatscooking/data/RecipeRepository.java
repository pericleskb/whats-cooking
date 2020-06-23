package com.example.whatscooking.data;

import androidx.lifecycle.LiveData;

import com.example.whatscooking.data.entities.Recipe;
import com.example.whatscooking.data.entities.RecipeInfo;

import java.util.ArrayList;
import java.util.List;

public interface RecipeRepository {
    //TODO get part of the recipe infos ordered in case of large amounts of data
    LiveData<List<RecipeInfo>> getAllRecipesInfo();

    //TODO When I add the server maybe make this LiveData in case a shared recipe gets updated?
    LiveData<List<Recipe>> getAllRecipes();

    LiveData<Recipe> getRecipe(String recipeTitle);

    LiveData<RecipeInfo> getRecipeInfo(String recipeTitle);

    void insertRecipe(RecipeInfo recipesInfo, Recipe recipe);

    void updateRecipe(RecipeInfo recipeInfo, Recipe recipe);

    void deleteSelectedRecipes(String... recipeTitles);

    void deleteRecipe(RecipeInfo recipeInfo);
}
