package com.example.whatscooking.data;

import androidx.lifecycle.LiveData;

import java.util.List;

public class RecipeRepository {

    private static RecipeRepository instance;
    private RecipeDao recipeDao;
    LiveData<List<Recipe>> allRecipes;

    private RecipeRepository(RecipeDao recipeDao) {
        this.recipeDao = recipeDao;
        allRecipes = recipeDao.getAll();
    }

    public static RecipeRepository getInstance(RecipeDao recipeDao) {
        if (instance == null) {
            synchronized (RecipeRepository.class) {
                if (instance == null) {
                    instance = new RecipeRepository(recipeDao);
                }
            }
        }
        return instance;
    }

    public LiveData<List<Recipe>> getAllRecipes() {
        return allRecipes;
    }

    //TODO get recipes based on difficulty

    public void insert(Recipe... recipes) {
        AppDatabase.databaseWriteExecutor.execute(() -> recipeDao.insertAll(recipes));
    }
}
