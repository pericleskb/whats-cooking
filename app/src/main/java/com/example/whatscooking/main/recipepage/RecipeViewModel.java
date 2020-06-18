package com.example.whatscooking.main.recipepage;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

import com.example.whatscooking.data.RecipeRepository;
import com.example.whatscooking.data.entities.Recipe;
import com.example.whatscooking.data.entities.RecipeInfo;
import com.example.whatscooking.main.FullRecipeViewModel;

import javax.inject.Inject;

public class RecipeViewModel extends FullRecipeViewModel {


    private RecipeRepository recipeRepository;
    private LiveData<Recipe> recipeLiveData;
    private LiveData<RecipeInfo> recipeInfoLiveData;

    @Inject
    public RecipeViewModel(@NonNull Application application, RecipeRepository repository,
                           String title) {
        super(application);
        this.recipeRepository = repository;
        this.recipeLiveData = recipeRepository.getRecipe(title);
        this.recipeInfoLiveData = recipeRepository.getRecipeInfo(title);
    }

    public LiveData<Recipe> getRecipe() {
        return recipeLiveData;
    }

    public LiveData<RecipeInfo> getRecipeInfo() {
        return recipeInfoLiveData;
    }
}
