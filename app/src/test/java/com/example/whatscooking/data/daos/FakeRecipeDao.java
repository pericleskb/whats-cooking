package com.example.whatscooking.data.daos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.whatscooking.data.entities.Recipe;

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
        for (Recipe recipe: recipesLiveData.getValue()) {
            if (recipe.title.equals(recipeTitle))
                return new MutableLiveData<>(recipe);
        }
        return null;
    }

    @Override
    public void insert(Recipe recipe) {
        recipesList.add(recipe);
        recipesLiveData.postValue(recipesList);
    }
}
