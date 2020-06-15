package com.example.whatscooking.data;

import androidx.lifecycle.LiveData;

import com.example.whatscooking.data.daos.RecipeInfoDao;
import com.example.whatscooking.data.entities.RecipeInfo;

import java.util.List;

import javax.inject.Inject;

public class DefaultRecipeRepository implements RecipeRepository {

    protected static DefaultRecipeRepository instance;
    private RecipeInfoDao recipeInfoDao;
    LiveData<List<RecipeInfo>> allRecipes;

    @Inject
    protected DefaultRecipeRepository(RecipeInfoDao recipeInfoDao) {
        this.recipeInfoDao = recipeInfoDao;
        allRecipes = recipeInfoDao.getAll();
    }

    @Override
    public LiveData<List<RecipeInfo>> getAllRecipes() {
        return allRecipes;
    }

    //TODO get recipes based on difficulty

    @Override
    public void insert(RecipeInfo... recipesInfo) {
        AppDatabase.databaseWriteExecutor.execute(() -> recipeInfoDao.insertAll(recipesInfo));
    }
}
