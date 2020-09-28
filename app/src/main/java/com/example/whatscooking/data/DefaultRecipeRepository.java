package com.example.whatscooking.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.whatscooking.data.daos.RecipeDao;
import com.example.whatscooking.data.daos.RecipeDetailsDao;
import com.example.whatscooking.data.entities.Recipe;
import com.example.whatscooking.data.entities.RecipeDetails;
import com.example.whatscooking.utilities.Constants;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DefaultRecipeRepository implements RecipeRepository {

    protected static DefaultRecipeRepository instance;
    private RecipeDetailsDao recipeDetailsDao;
    private RecipeDao recipeDao;

    private LiveData<List<RecipeDetails>> allRecipesDetails;
    //TODO change this to only not loaded recipes?
    private LiveData<List<Recipe>> allRecipes;
    private ExecutorService executor;
    private WebService webService;
    private SharedPreferences prefs;

    @Inject
    protected DefaultRecipeRepository(RecipeDetailsDao recipeDetailsDao, RecipeDao recipeDao,
                                      WebService webService, ExecutorService executor, Context context) {
        this.recipeDetailsDao = recipeDetailsDao;
        this.recipeDao = recipeDao;
        this.webService = webService;
        this.executor = executor;
        this.prefs = context.getSharedPreferences(Constants.API_PREFERENCES, Context.MODE_PRIVATE);
        allRecipesDetails = recipeDetailsDao.getAll();
        allRecipes = recipeDao.getAllRecipes();
    }

    @Override
    public LiveData<List<RecipeDetails>> getRecipesDetails() {
        executor.execute(() -> {
            int dataVersion = prefs.getInt(Constants.DATA_VERSION, 0);
            webService.getDetailsForRecipes(dataVersion).enqueue(new Callback<List<RecipeDetails>>() {
                @Override
                public void onResponse(Call<List<RecipeDetails>> call, Response<List<RecipeDetails>> response) {
                    recipeDetailsDao.insertAll(response.body());
                    int maxDataVersion = Collections.max(response.body(),
                            new RecipeDetails.RecipeVersionComparator()).dataVersion;
                    prefs.edit().putInt(Constants.DATA_VERSION, maxDataVersion).commit();
                }

                @Override
                public void onFailure(Call<List<RecipeDetails>> call, Throwable t) {
                    Log.i(Constants.INFO_TAG, "Unable to connect to server - " + t.getMessage());
                }
            });
        });
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
