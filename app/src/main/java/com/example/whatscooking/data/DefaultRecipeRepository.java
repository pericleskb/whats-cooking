package com.example.whatscooking.data;

import androidx.lifecycle.LiveData;

import java.util.List;

public class DefaultRecipeRepository implements RecipeRepository {

    protected static DefaultRecipeRepository instance;
    private RecipeDao recipeDao;
    LiveData<List<Recipe>> allRecipes;

    protected DefaultRecipeRepository(RecipeDao recipeDao) {
        this.recipeDao = recipeDao;
        allRecipes = recipeDao.getAll();
    }

    public static DefaultRecipeRepository getInstance(RecipeDao recipeDao) {
        if (instance == null) {
            synchronized (DefaultRecipeRepository.class) {
                if (instance == null) {
                    instance = new DefaultRecipeRepository(recipeDao);
                }
            }
        }
        return instance;
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
