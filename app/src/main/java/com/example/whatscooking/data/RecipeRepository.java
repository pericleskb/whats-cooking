package com.example.whatscooking.data;

import androidx.lifecycle.LiveData;

import com.example.whatscooking.data.entities.RecipeInfo;

import java.util.List;

public interface RecipeRepository {
    LiveData<List<RecipeInfo>> getAllRecipes();

    void insert(RecipeInfo... recipesInfo);
}
