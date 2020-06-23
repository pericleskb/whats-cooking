package com.example.whatscooking.main.recipeslist;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.whatscooking.data.entities.Recipe;
import com.example.whatscooking.data.entities.RecipeInfo;
import com.example.whatscooking.data.RecipeRepository;
import com.example.whatscooking.di.ActivityScope;
import com.example.whatscooking.main.FullRecipeViewModel;

import java.util.List;

import javax.inject.Inject;

@ActivityScope
public class RecipesListViewModel extends FullRecipeViewModel {

    private RecipeRepository recipeRepository;
    private LiveData<List<RecipeInfo>> recipeInfoLiveData;

    @Inject
    public RecipesListViewModel(Application application, RecipeRepository repository) {
        super(application);
        recipeRepository = repository;
        recipeInfoLiveData = recipeRepository.getAllRecipesInfo();
    }

    public LiveData<List<RecipeInfo>> getAllRecipesInfo() {
        return recipeInfoLiveData;
    }
}
