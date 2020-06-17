package com.example.whatscooking.main.recipepage;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.whatscooking.data.RecipeRepository;
import com.example.whatscooking.data.entities.Recipe;
import com.example.whatscooking.main.FullRecipeViewModel;

import java.util.List;

import javax.inject.Inject;

public class RecipeViewModel extends FullRecipeViewModel {


    private RecipeRepository recipeRepository;
    private LiveData<List<Recipe>> recipeLiveData;

    @Inject
    public RecipeViewModel(@NonNull Application application, RecipeRepository repository) {
        super(application);
        this.recipeRepository = repository;
        this.recipeLiveData = recipeRepository.getAllRecipes();
    }

    public LiveData<List<Recipe>> getAllRecipes() {
        return recipeLiveData;
    }
}
