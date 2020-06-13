package com.example.whatscooking.viewmodels;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.whatscooking.data.Recipe;
import com.example.whatscooking.data.RecipeRepository;
import com.example.whatscooking.di.ActivityScope;

import java.util.List;

import javax.inject.Inject;

@ActivityScope
public class RecipesListViewModel extends AndroidViewModel {

    private RecipeRepository recipeRepository;

    @Inject
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
