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
    public LiveData<List<RecipeDetails>> getRecipesDetails() {
        return allRecipesDetails;
    }

    @Override
    public LiveData<Recipe> getRecipe(String recipeTitle) {
        return recipeDao.getRecipe(recipeTitle);
    }

    @Override
    public LiveData<RecipeDetails> getRecipeDetails(String recipeTitle) {
        return recipeDetailsDao.getRecipeDetails(recipeTitle);
    }
}
