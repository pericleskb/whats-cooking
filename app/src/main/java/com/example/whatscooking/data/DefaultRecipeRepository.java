package com.example.whatscooking.data;

import androidx.lifecycle.LiveData;

import com.example.whatscooking.data.daos.RecipeDao;
import com.example.whatscooking.data.daos.RecipeDetailsDao;
import com.example.whatscooking.data.entities.Recipe;
import com.example.whatscooking.data.entities.RecipeDetails;

import java.util.List;

import javax.inject.Inject;

public class DefaultRecipeRepository implements RecipeRepository {

    protected static DefaultRecipeRepository instance;
    private RecipeDetailsDao recipeDetailsDao;
    private RecipeDao recipeDao;

    private LiveData<List<RecipeDetails>> allRecipesDetails;
    //TODO change this to only not loaded recipes?
    private LiveData<List<Recipe>> allRecipes;

    @Inject
    protected DefaultRecipeRepository(RecipeDetailsDao recipeDetailsDao, RecipeDao recipeDao) {
        this.recipeDetailsDao = recipeDetailsDao;
        this.recipeDao = recipeDao;
        allRecipesDetails = recipeDetailsDao.getAll();
        allRecipes = recipeDao.getAllRecipes();
    }

    @Override
    public LiveData<List<RecipeDetails>> getAllRecipesDetails() {
        return allRecipesDetails;
    }

    @Override
    public LiveData<List<Recipe>> getAllRecipes() {
        return allRecipes;
    }

    @Override
    public LiveData<Recipe> getRecipe(String recipeTitle) {
        return recipeDao.getRecipe(recipeTitle);
    }

    @Override
    public LiveData<RecipeDetails> getRecipeDetails(String recipeTitle) {
        return recipeDetailsDao.getRecipeDetails(recipeTitle);
    }

    @Override
    public void insertRecipe(RecipeDetails recipeDetails, Recipe recipe) {
        AppDatabase.databaseWriteExecutor.execute(() -> recipeDetailsDao.insert(recipeDetails));
        AppDatabase.databaseWriteExecutor.execute(() -> recipeDao.insert(recipe));
    }

    @Override
    public void updateRecipe(RecipeDetails recipeDetails, Recipe recipe) {
        AppDatabase.databaseWriteExecutor.execute(() -> recipeDetailsDao.update(recipeDetails));
        AppDatabase.databaseWriteExecutor.execute(() -> recipeDao.updateRecipe(recipe));
    }

    @Override
    public void deleteSelectedRecipes(String... recipeTitles) {
        recipeDetailsDao.deleteSelected(recipeTitles);
    }

    @Override
    public void deleteRecipe(RecipeDetails recipeDetails) {
        recipeDetailsDao.delete(recipeDetails);
    }

}
