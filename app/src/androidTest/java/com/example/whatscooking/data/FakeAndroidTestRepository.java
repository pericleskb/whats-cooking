package com.example.whatscooking.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.whatscooking.TestUtils;
import com.example.whatscooking.data.entities.Recipe;
import com.example.whatscooking.data.entities.RecipeInfo;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class FakeAndroidTestRepository implements RecipeRepository {

    private MutableLiveData<List<RecipeInfo>> recipesInfoLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Recipe>> recipesLiveData = new MutableLiveData<>();

    private MutableLiveData<List<RecipeInfo>> testRecipesInfoLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Recipe>> testRecipesLiveData = new MutableLiveData<>();

    ArrayList<RecipeInfo> recipeInfosList = new ArrayList<>();
    ArrayList<Recipe> recipeList = new ArrayList<>();

    @Inject
    protected FakeAndroidTestRepository() {
        testRecipesInfoLiveData.postValue(TestUtils.getRecipesInfoList());
    }

    @Override
    public LiveData<List<RecipeInfo>> getAllRecipesInfo() {
        return testRecipesInfoLiveData;
    }

    @Override
    public LiveData<List<Recipe>> getAllRecipes() {
        return null;
    }

    @Override
    public Recipe getRecipe(String recipeTitle) {
        return null;
    }

    @Override
    public void insertRecipe(RecipeInfo recipeInfo, Recipe recipe) {
        recipeInfosList.add(recipeInfo);
        recipesInfoLiveData.postValue(recipeInfosList);
    }

    @Override
    public void updateRecipe(RecipeInfo recipeInfo, Recipe recipe) {

    }

    @Override
    public void deleteSelectedRecipes(String... recipeTitles) {

    }

    @Override
    public void deleteRecipe(RecipeInfo recipeInfo) {

    }
}
