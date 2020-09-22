package com.example.whatscooking.main.recipeslist;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.whatscooking.data.entities.Recipe;
import com.example.whatscooking.data.entities.RecipeDetails;
import com.example.whatscooking.data.RecipeRepository;
import com.example.whatscooking.di.ActivityScope;

import java.util.List;

import javax.inject.Inject;

@ActivityScope
public class RecipesListViewModel extends AndroidViewModel {

    private LiveData<List<RecipeDetails>> recipeDetailsLiveData;
    RecipeRepository recipeRepository;

    @Inject
    public RecipesListViewModel(Application application, RecipeRepository repository) {
        super(application);
        recipeRepository = repository;
        recipeDetailsLiveData = recipeRepository.loadRecipesDetails();
    }

    public LiveData<List<RecipeDetails>> getAllRecipesDetails() {
        return recipeDetailsLiveData;
    }

    public void insert(RecipeDetails recipeDetails, Recipe recipe) {
        recipeRepository.insertRecipe(recipeDetails, recipe);
    }
}
