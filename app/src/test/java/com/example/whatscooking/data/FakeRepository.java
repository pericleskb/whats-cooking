package com.example.whatscooking.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.whatscooking.data.entities.RecipeInfo;

import java.util.ArrayList;
import java.util.List;

public class FakeRepository implements RecipeRepository {

    private MutableLiveData<List<RecipeInfo>> recipesLiveData = new MutableLiveData<>();
    ArrayList<RecipeInfo> recipesList = new ArrayList<>();

    @Override
    public LiveData<List<RecipeInfo>> getAllRecipes() {
        return recipesLiveData;
    }

    @Override
    public void insert(RecipeInfo... recipesInfo) {
        for (RecipeInfo recipeInfo : recipesInfo) {
            recipesList.add(recipeInfo);
        }
        recipesLiveData.postValue(recipesList);
    }
}
