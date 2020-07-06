package com.example.whatscooking.data.daos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.whatscooking.data.entities.Recipe;
import com.example.whatscooking.data.entities.RecipeDetails;

import java.util.ArrayList;
import java.util.List;

public class FakeRecipeDao implements RecipeDao {

    private MutableLiveData<List<Recipe>> recipesLiveData = new MutableLiveData<>();
    public ArrayList<Recipe> recipesList = new ArrayList<>();

    @Override
    public LiveData<List<Recipe>> getAllRecipes() {
        recipesLiveData.postValue(recipesList);
        return recipesLiveData;
    }

    @Override
    public LiveData<Recipe> getRecipe(String recipeTitle) {
        return null;
    }

    @Override
    public List<String> getIngredients(String recipeTitle) {
        return null;
    }

    @Override
    public void insert(Recipe recipe) {
        recipesList.add(recipe);
        recipesLiveData.postValue(recipesList);
    }

    @Override
    public void delete(Recipe recipe) {

    }

    @Override
    public void updateRecipe(Recipe recipe) {

    }
}
