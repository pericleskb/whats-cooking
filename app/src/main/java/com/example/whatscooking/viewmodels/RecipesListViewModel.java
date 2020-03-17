package com.example.whatscooking.viewmodels;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.whatscooking.data.AppDatabase;
import com.example.whatscooking.data.Recipe;
import com.example.whatscooking.data.RecipeRepository;

import java.util.List;

public class RecipesListViewModel extends AndroidViewModel {

    private RecipeRepository recipeRepository;
    private LiveData<List<Recipe>> allRecipes;

    public RecipesListViewModel(Application application) {
        super(application);
        //TODO abstract repository creation into a pattern?
        recipeRepository = RecipeRepository.getInstance(
                AppDatabase.getInstance(application).recipeDao()
        );
        allRecipes = recipeRepository.getAllRecipes();
    }

    public LiveData<List<Recipe>> getAllRecipes() {
        return allRecipes;
    }

    public void insert(Recipe... recipes) {
        recipeRepository.insert(recipes);
    }
}
