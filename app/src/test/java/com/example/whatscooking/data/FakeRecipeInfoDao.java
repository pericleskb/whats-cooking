package com.example.whatscooking.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.whatscooking.data.daos.RecipeInfoDao;
import com.example.whatscooking.data.entities.RecipeInfo;

import java.util.ArrayList;
import java.util.List;

public class FakeRecipeInfoDao implements RecipeInfoDao {

    private MutableLiveData<List<RecipeInfo>> recipesLiveData = new MutableLiveData<>();

    ArrayList<RecipeInfo> recipesList = new ArrayList<>();

    @Override
    public LiveData<List<RecipeInfo>> getAll() {
        recipesLiveData.postValue(recipesList);
        return recipesLiveData;
    }

    @Override
    public void insertAll(RecipeInfo... recipesInfo) {
        for (RecipeInfo recipeInfo : recipesInfo) {
            recipesList.add(recipeInfo);
        }
        recipesLiveData.postValue(recipesList);
    }

    @Override
    public void delete(RecipeInfo recipeInfo) {

    }

    @Override
    public void deleteAll() {

    }
}
