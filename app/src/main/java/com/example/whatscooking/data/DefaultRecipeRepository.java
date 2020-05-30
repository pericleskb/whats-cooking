package com.example.whatscooking.data;

import androidx.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

public class DefaultRecipeRepository implements RecipeRepository {

    protected static DefaultRecipeRepository instance;
    private RecipeDao recipeDao;
    LiveData<List<Recipe>> allRecipes;

    @Inject
    protected DefaultRecipeRepository(RecipeDao recipeDao) {
        this.recipeDao = recipeDao;
        allRecipes = recipeDao.getAll();
    }

    @Override
    public LiveData<List<Recipe>> getAllRecipes() {
        return allRecipes;
    }

    //TODO get recipes based on difficulty

    @Override
    public void insert(Recipe... recipes) {
        AppDatabase.databaseWriteExecutor.execute(() -> recipeDao.insertAll(recipes));
    }
}
