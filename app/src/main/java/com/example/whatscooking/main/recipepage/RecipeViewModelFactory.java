package com.example.whatscooking.main.recipepage;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.whatscooking.data.RecipeRepository;

import javax.inject.Inject;

public class RecipeViewModelFactory extends ViewModelProvider.AndroidViewModelFactory {

    Application application;
    @Inject
    RecipeRepository repository;
    String recipeTitle;

    public RecipeViewModelFactory(@NonNull Application application, String recipeTitle) {
        super(application);
        this.application = application;
        this.recipeTitle = recipeTitle;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new RecipeViewModel(application, repository, recipeTitle);
    }
}
