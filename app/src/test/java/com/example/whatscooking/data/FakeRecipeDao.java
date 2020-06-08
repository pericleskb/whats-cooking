package com.example.whatscooking.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

public class FakeRecipeDao implements RecipeDao {

    private MutableLiveData<List<Recipe>> recipesLiveData = new MutableLiveData<>();

    ArrayList<Recipe> recipesList = new ArrayList<>();

    @Override
    public LiveData<List<Recipe>> getAll() {
        recipesLiveData.postValue(recipesList);
        return recipesLiveData;
    }

    @Override
    public void insertAll(Recipe... recipes) {
        for (Recipe recipe : recipes) {
            recipesList.add(recipe);
        }
        recipesLiveData.postValue(recipesList);
    }

    @Override
    public void delete(Recipe recipe) {

    }

    @Override
    public void deleteAll() {

    }
}
