package com.example.whatscooking.data;

import androidx.lifecycle.LiveData;

import com.example.whatscooking.data.daos.RecipeDao;
import com.example.whatscooking.data.daos.RecipeDetailsDao;
import com.example.whatscooking.data.entities.Recipe;
import com.example.whatscooking.data.entities.RecipeDetails;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

import javax.inject.Inject;

import retrofit2.Retrofit;

public class DefaultRecipeRepository implements RecipeRepository {

    protected static DefaultRecipeRepository instance;
    private RecipeDetailsDao recipeDetailsDao;
    private RecipeDao recipeDao;

    private LiveData<List<RecipeDetails>> allRecipesDetails;
    //TODO change this to only not loaded recipes?
    private LiveData<List<Recipe>> allRecipes;
    private Retrofit retrofit;
    private ExecutorService executor;

    @Inject
    protected DefaultRecipeRepository(RecipeDetailsDao recipeDetailsDao, RecipeDao recipeDao,
                                      Retrofit retrofit, ExecutorService executor) {
        this.recipeDetailsDao = recipeDetailsDao;
        this.recipeDao = recipeDao;
        this.retrofit = retrofit;
        this.executor = executor;
        allRecipesDetails = recipeDetailsDao.getAll();
        allRecipes = recipeDao.getAllRecipes();
    }

    @Override
    public LiveData<List<RecipeDetails>> loadRecipesDetails() {
        return allRecipesDetails;
    }

    @Override
    public LiveData<Recipe> getRecipe(String recipeTitle) {
        return recipeDao.getRecipe(recipeTitle);
    }

    // Not needed
    @Override
    public LiveData<List<Recipe>> getAllRecipes() {
        return allRecipes;
    }

    // Not needed
    @Override
    public LiveData<RecipeDetails> getRecipeDetails(String recipeTitle) {
        return recipeDetailsDao.getRecipeDetails(recipeTitle);
    }

    // We must call this on a non-UI thread or the app will throw an exception. Room ensures
    // that we're not doing any long running operations on the main thread, blocking the UI.
    @Override
    public void insertRecipe(RecipeDetails recipeDetails, Recipe recipe) {
        executor.execute(() -> recipeDetailsDao.insert(recipeDetails));
        executor.execute(() -> recipeDao.insert(recipe));
    }

    //TODO write tests from here on downwards
    @Override
    public void updateRecipe(RecipeDetails recipeDetails, Recipe recipe) {
        executor.execute(() -> recipeDetailsDao.update(recipeDetails));
        executor.execute(() -> recipeDao.updateRecipe(recipe));
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
