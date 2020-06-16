package com.example.whatscooking.main.recipeslist;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.whatscooking.data.entities.RecipeInfo;
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
        recipeRepository = repository;
    }

    public LiveData<List<RecipeInfo>> getAllRecipes() {
        return recipeRepository.getAllRecipesInfo();
    }

    public void insert(RecipeInfo... recipesInfo) {
        recipeRepository.insertRecipe(recipesInfo);
    }
}
