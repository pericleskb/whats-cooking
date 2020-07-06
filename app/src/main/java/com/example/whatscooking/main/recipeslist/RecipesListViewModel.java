package com.example.whatscooking.main.recipeslist;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.whatscooking.data.entities.RecipeDetails;
import com.example.whatscooking.data.RecipeRepository;
import com.example.whatscooking.di.ActivityScope;
import com.example.whatscooking.main.ParentRecipeViewModel;

import java.util.List;

import javax.inject.Inject;

@ActivityScope
public class RecipesListViewModel extends ParentRecipeViewModel {

    private LiveData<List<RecipeDetails>> recipeDetailsLiveData;

    @Inject
    public RecipesListViewModel(Application application, RecipeRepository repository) {
        super(application);
        recipeRepository = repository;
        recipeDetailsLiveData = recipeRepository.getAllRecipesDetails();
    }

    public LiveData<List<RecipeDetails>> getAllRecipesDetails() {
        return recipeDetailsLiveData;
    }
}
