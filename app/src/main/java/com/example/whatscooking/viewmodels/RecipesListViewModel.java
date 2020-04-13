package com.example.whatscooking.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.whatscooking.data.Recipe;
import com.example.whatscooking.data.RecipeRepository;

import java.util.List;

public class RecipesListViewModel extends AndroidViewModel {

    private RecipeRepository recipeRepository;

    public RecipesListViewModel(Application application, RecipeRepository repository) {
        super(application);
        //TODO abstract repository creation into a pattern as in sunflower?
        recipeRepository = repository;
    }

    public LiveData<List<Recipe>> getAllRecipes() {
        return recipeRepository.getAllRecipes();
    }

    public void insert(Recipe... recipes) {
        recipeRepository.insert(recipes);
    }
}
