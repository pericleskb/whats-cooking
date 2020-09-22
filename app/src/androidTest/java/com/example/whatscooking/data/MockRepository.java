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

    private MutableLiveData<List<RecipeDetails>> mutableRecipeDetails = new MutableLiveData<>();

    private MutableLiveData<RecipeDetails> recipeDetailsMutableLiveData = new MutableLiveData<>();
    private MutableLiveData<Recipe> recipeMutableLiveData = new MutableLiveData<>();

    @Inject
    protected MockRepository() {
        //TODO add incomplete recipe details for tests
        mutableRecipeDetails.postValue(TestUtils.getRecipesInfoList());
        recipeDetailsMutableLiveData.postValue(TestUtils.getRecipeDetails());
        recipeMutableLiveData.postValue(TestUtils.getRecipe());
    }

    @Override
    public LiveData<List<RecipeDetails>> getRecipesDetails() {
        return mutableRecipeDetails;
    }

    @Override
    public LiveData<List<Recipe>> getAllRecipes() {
        return null;
    }

    @Override
    public LiveData<Recipe> getRecipe(String recipeTitle) {
        LiveData<Recipe> liveData = recipeMutableLiveData;
        return liveData;
    }

    @Override
    public LiveData<RecipeDetails> getRecipeDetails(String recipeTitle) {
        LiveData<RecipeDetails> liveData = recipeDetailsMutableLiveData;
        return liveData;
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
