package com.example.whatscooking.data;

import androidx.lifecycle.LiveData;

import java.util.List;

public interface RecipeRepository {
    LiveData<List<Recipe>> getAllRecipes();

    void insert(Recipe... recipes);
}
