package com.example.whatscooking.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.whatscooking.data.entities.Recipe;
import com.example.whatscooking.data.entities.RecipeInfo;

import java.util.ArrayList;
import java.util.List;

public class FakeRepository implements RecipeRepository {

    private MutableLiveData<List<RecipeInfo>> recipesLiveData = new MutableLiveData<>();
    ArrayList<RecipeInfo> recipesList = new ArrayList<>();

    @Override
    public LiveData<List<RecipeInfo>> getAllRecipesInfo() {
        return recipesLiveData;
    }

    @Override
    public void getRecipe(String recipeTitle) {

    }

    @Override
    public void insertRecipe(RecipeInfo recipeInfo, Recipe recipe) {
        recipesList.add(recipeInfo);
        recipesLiveData.postValue(recipesList);
    }

    @Override
    public void updateRecipeInfo(RecipeInfo recipeInfo) {

    }

    @Override
    public void updateRecipe(Recipe recipe) {

    }

    @Override
    public void deleteSelected(String... recipeTitles) {

    }

    @Override
    public void deleteRecipe(RecipeInfo recipeInfo) {

    }
}
