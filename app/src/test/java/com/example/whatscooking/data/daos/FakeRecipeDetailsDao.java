package com.example.whatscooking.data.daos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.whatscooking.data.entities.RecipeDetails;

import java.util.ArrayList;
import java.util.List;

public class FakeRecipeDetailsDao implements RecipeDetailsDao {

    private MutableLiveData<List<RecipeDetails>> recipesLiveData = new MutableLiveData<>();
    public ArrayList<RecipeDetails> recipesList = new ArrayList<>();

    @Override
    public LiveData<List<RecipeDetails>> getAll() {
        recipesLiveData.postValue(recipesList);
        return recipesLiveData;
    }

    @Override
    public LiveData<RecipeDetails> getRecipeDetails(String title) {
        for (RecipeDetails recipeDetails: recipesLiveData.getValue()) {
            if (recipeDetails.title.equals(title))
                return new MutableLiveData<>(recipeDetails);
        }
        return null;
    }

    @Override
    public void insert(RecipeDetails recipeDetails) {
        recipesList.add(recipeDetails);
        recipesLiveData.postValue(recipesList);
    }
}
