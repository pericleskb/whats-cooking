package com.example.whatscooking;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.whatscooking.data.RecipeRepository;
import com.example.whatscooking.data.entities.Recipe;
import com.example.whatscooking.data.entities.RecipeDetails;

import java.util.ArrayList;
import java.util.List;

public class FakeRepository implements RecipeRepository {

    private MutableLiveData<List<RecipeDetails>> recipesLiveData = new MutableLiveData<>();
    ArrayList<RecipeDetails> recipesList = new ArrayList<>();
    MutableLiveData<Recipe> recipeMutableLiveData = new MutableLiveData<>();
    MutableLiveData<RecipeDetails> recipeDetailsMutableLiveData = new MutableLiveData<>();

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
        return recipeMutableLiveData;
    }

    @Override
    public LiveData<RecipeDetails> getRecipeDetails(String recipeTitle) {
        return recipeDetailsMutableLiveData;
    }

    @Override
    public void insertRecipe(RecipeDetails recipeDetails, Recipe recipe) {
        recipesList.add(recipeDetails);
        recipesLiveData.postValue(recipesList);
        recipeMutableLiveData.postValue(recipe);
        recipeDetailsMutableLiveData.postValue(recipeDetails);
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

    public int getNumberOfRecipes() {
        return recipesList.size();
    }

    public void addIngredient(String newIngredient) {
        Recipe recipe = recipeMutableLiveData.getValue();
        recipe.ingredients.add(newIngredient);
        recipeMutableLiveData.postValue(recipe);
    }

    public void changeRecipeDetailsServings(int newServings) {
        RecipeDetails recipeDetails = recipeDetailsMutableLiveData.getValue();
        recipeDetails.servings = newServings;
        recipeDetailsMutableLiveData.postValue(recipeDetails);
    }
}
