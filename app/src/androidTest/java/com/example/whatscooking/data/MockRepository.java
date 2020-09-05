package com.example.whatscooking.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.whatscooking.TestUtils;
import com.example.whatscooking.data.entities.Recipe;
import com.example.whatscooking.data.entities.RecipeDetails;

import java.util.List;

import javax.inject.Inject;

/* This repository is injected by Dagger in the ViewModel classes so we can control what data
   is available during integration tests.
 */
public class MockRepository implements RecipeRepository {

    private MutableLiveData<List<RecipeDetails>> testRecipeDetailsLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Recipe>> testRecipesLiveData = new MutableLiveData<>();

    @Inject
    protected MockRepository() {
        testRecipeDetailsLiveData.postValue(TestUtils.getRecipesInfoList());
    }

    @Override
    public LiveData<List<RecipeDetails>> getAllRecipesDetails() {
        return testRecipeDetailsLiveData;
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
