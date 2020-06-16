package com.example.whatscooking.data;

import androidx.lifecycle.LiveData;

import com.example.whatscooking.data.daos.RecipeDao;
import com.example.whatscooking.data.daos.RecipeInfoDao;
import com.example.whatscooking.data.entities.Recipe;
import com.example.whatscooking.data.entities.RecipeInfo;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class DefaultRecipeRepository implements RecipeRepository {

    protected static DefaultRecipeRepository instance;
    private RecipeInfoDao recipeInfoDao;
    private RecipeDao recipeDao;

    LiveData<List<RecipeInfo>> allRecipesInfo;
    //TODO change this to only not loaded recipes?
    LiveData<List<Recipe>> allRecipes;

    @Inject
    protected DefaultRecipeRepository(RecipeInfoDao recipeInfoDao, RecipeDao recipeDao) {
        this.recipeInfoDao = recipeInfoDao;
        this.recipeDao = recipeDao;
        allRecipesInfo = recipeInfoDao.getAll();
        allRecipes = recipeDao.getAllRecipes();
    }

    @Override
    public LiveData<List<RecipeInfo>> getAllRecipesInfo() {
        return allRecipesInfo;
    }

    @Override
    public LiveData<List<Recipe>> getAllRecipes() {
        return allRecipes;
    }

    @Override
    public Recipe getRecipe(String recipeTitle) {
        return recipeDao.getRecipe(recipeTitle);
    }

    @Override
    public void insertRecipe(RecipeInfo recipesInfo, Recipe recipe) {
        AppDatabase.databaseWriteExecutor.execute(() -> recipeInfoDao.insert(recipesInfo));
        AppDatabase.databaseWriteExecutor.execute(() -> recipeDao.insert(recipe));
    }

    @Override
    public void updateRecipeInfo(RecipeInfo recipeInfo) {
        recipeInfoDao.update(recipeInfo);
    }

    @Override
    public void updateRecipe(Recipe recipe) {
        recipeDao.updateRecipe(recipe);
    }

    @Override
    public void deleteSelectedRecipes(String... recipeTitles) {
        recipeInfoDao.deleteSelected(recipeTitles);
    }

    @Override
    public void deleteRecipe(RecipeInfo recipeInfo) {
        recipeInfoDao.delete(recipeInfo);
    }

}
