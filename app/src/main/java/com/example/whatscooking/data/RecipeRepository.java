package com.example.whatscooking.data;

import androidx.lifecycle.LiveData;

import com.example.whatscooking.data.entities.Recipe;
import com.example.whatscooking.data.entities.RecipeDetails;

import java.util.List;

public interface RecipeRepository {

    LiveData<List<RecipeDetails>> getRecipesDetails();
    LiveData<Recipe> getRecipe(String recipeTitle);
    LiveData<RecipeDetails> getRecipeDetails(String recipeTitle);
}
