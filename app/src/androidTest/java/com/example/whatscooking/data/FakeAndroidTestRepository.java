package com.example.whatscooking.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.whatscooking.TestUtils;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class FakeAndroidTestRepository implements RecipeRepository {

    private MutableLiveData<List<Recipe>> recipesLiveData = new MutableLiveData<>();
    private MutableLiveData<List<Recipe>> testRecipesLiveData = new MutableLiveData<>();
    ArrayList<Recipe> recipesList = new ArrayList<>();

    @Inject
    protected FakeAndroidTestRepository() {
        testRecipesLiveData.postValue(TestUtils.getRecipesList());
    }

    @Override
    public LiveData<List<Recipe>> getAllRecipes() {
        return testRecipesLiveData;
    }

    @Override
    public void insert(Recipe... recipes) {
        for (Recipe recipe : recipes) {
            recipesList.add(recipe);
        }
        recipesLiveData.postValue(recipesList);
    }
}
