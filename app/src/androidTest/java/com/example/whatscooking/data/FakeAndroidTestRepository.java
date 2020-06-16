package com.example.whatscooking.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.whatscooking.TestUtils;
import com.example.whatscooking.data.entities.RecipeInfo;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class FakeAndroidTestRepository implements RecipeRepository {

    private MutableLiveData<List<RecipeInfo>> recipesLiveData = new MutableLiveData<>();
    private MutableLiveData<List<RecipeInfo>> testRecipesLiveData = new MutableLiveData<>();
    ArrayList<RecipeInfo> recipesList = new ArrayList<>();

    @Inject
    protected FakeAndroidTestRepository() {
        testRecipesLiveData.postValue(TestUtils.getRecipesList());
    }

    @Override
    public LiveData<List<RecipeInfo>> getAllRecipesInfo() {
        return testRecipesLiveData;
    }

    @Override
    public void insertRecipe(RecipeInfo... recipesInfo) {
        for (RecipeInfo recipeInfo : recipesInfo) {
            recipesList.add(recipeInfo);
        }
        recipesLiveData.postValue(recipesList);
    }
}
