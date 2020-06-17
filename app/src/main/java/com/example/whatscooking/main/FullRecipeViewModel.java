package com.example.whatscooking.main;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.whatscooking.data.RecipeRepository;
import com.example.whatscooking.data.entities.Recipe;
import com.example.whatscooking.data.entities.RecipeInfo;

public abstract class FullRecipeViewModel extends AndroidViewModel {

    protected RecipeRepository recipeRepository;

    public FullRecipeViewModel(@NonNull Application application) {
        super(application);
    }

    public void insert(RecipeInfo recipesInfo, Recipe recipe) {
        recipeRepository.insertRecipe(recipesInfo, recipe);
    }
}
