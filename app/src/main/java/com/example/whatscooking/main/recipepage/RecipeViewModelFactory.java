package com.example.whatscooking.main.recipepage;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import com.example.whatscooking.data.RecipeRepository;

public class RecipeViewModelFactory extends ViewModelProvider.AndroidViewModelFactory {

    Application application;
    RecipeRepository repository;
    String recipeTitle;

    public RecipeViewModelFactory(@NonNull Application application, RecipeRepository repository,
                                  String recipeTitle) {
        super(application);
        this.application = application;
        this.repository = repository;
        this.recipeTitle = recipeTitle;
    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new RecipeViewModel(application, repository, recipeTitle);
    }
}
