package com.example.whatscooking.main;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.whatscooking.data.RecipeRepository;
import com.example.whatscooking.data.entities.Recipe;
import com.example.whatscooking.data.entities.RecipeDetails;

public abstract class ParentRecipeViewModel extends AndroidViewModel {

    protected RecipeRepository recipeRepository;

    public ParentRecipeViewModel(@NonNull Application application) {
        super(application);
    }
}
