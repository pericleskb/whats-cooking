package com.example.whatscooking.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.ArrayList;
import java.util.List;

public class FakeTestRepository implements RecipeRepository {

    private MutableLiveData<List<Recipe>> recipesLiveData = new MutableLiveData<>();
    ArrayList<Recipe> recipesList = new ArrayList<>();

    @Override
    public LiveData<List<Recipe>> getAllRecipes() {
        return recipesLiveData;
    }

    @Override
    public void insert(Recipe... recipes) {
        for (Recipe recipe : recipes) {
            recipesList.add(recipe);
        }
        recipesLiveData.postValue(recipesList);
    }
}
