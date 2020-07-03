package com.example.whatscooking.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.whatscooking.data.entities.Recipe;
import com.example.whatscooking.data.entities.RecipeDetails;

import java.util.ArrayList;
import java.util.List;

public class FakeRepository implements RecipeRepository {

    private MutableLiveData<List<RecipeDetails>> recipesLiveData = new MutableLiveData<>();
    ArrayList<RecipeDetails> recipesList = new ArrayList<>();

    @Override
    public LiveData<List<RecipeDetails>> getAllRecipesDetails() {
        return recipesLiveData;
    }

    @Override
    public LiveData<List<Recipe>> getAllRecipes() {
        return null;
    }

    @Override
    public LiveData<Recipe> getRecipe(String recipeTitle) {
        return null;
    }

    @Override
    public LiveData<RecipeDetails> getRecipeDetails(String recipeTitle) {
        return null;
    }


    @Override
    public void insertRecipe(RecipeDetails recipeDetails, Recipe recipe) {
        recipesList.add(recipeDetails);
        recipesLiveData.postValue(recipesList);
    }

    @Override
    public void updateRecipe(RecipeDetails recipeDetails, Recipe recipe) {

    }

    @Override
    public void deleteSelectedRecipes(String... recipeTitles) {

    }

    @Override
    public void deleteRecipe(RecipeDetails recipeDetails) {

    }
}
