package com.example.whatscooking.data.daos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.whatscooking.data.entities.RecipeInfo;

import java.util.ArrayList;
import java.util.List;

public class FakeRecipeInfoDao implements RecipeInfoDao {

    private MutableLiveData<List<RecipeInfo>> recipesLiveData = new MutableLiveData<>();

    public ArrayList<RecipeInfo> recipesList = new ArrayList<>();

    @Override
    public LiveData<List<RecipeInfo>> getAll() {
        recipesLiveData.postValue(recipesList);
        return recipesLiveData;
    }

    @Override
    public void insert(RecipeInfo recipeInfo) {
        recipesList.add(recipeInfo);
        recipesLiveData.postValue(recipesList);
    }

    @Override
    public void delete(RecipeInfo recipeInfo) {

    }

    @Override
    public void deleteSelected(String... titles) {

    }

    @Override
    public void deleteAll() {

    }
}
