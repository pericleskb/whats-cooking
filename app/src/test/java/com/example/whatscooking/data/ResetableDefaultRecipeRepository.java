package com.example.whatscooking.data;

import android.content.Context;

import com.example.whatscooking.data.daos.RecipeDao;
import com.example.whatscooking.data.daos.RecipeDetailsDao;

import java.util.concurrent.ExecutorService;

/*
 Used to reset the repository before each repository test
 */
public class ResetableDefaultRecipeRepository extends DefaultRecipeRepository {

    private ResetableDefaultRecipeRepository(RecipeDetailsDao recipeDetailsDao, RecipeDao recipeDao,
                                             WebService webService, ExecutorService executorService,
                                             Context context) {
        super(recipeDetailsDao, recipeDao, webService, executorService, context);
    }

    //TODO we can change this from being a Singleton to being generated anew from Dagger
    public static DefaultRecipeRepository getInstance(RecipeDetailsDao recipeDetailsDao,
                                                      RecipeDao recipeDao, WebService webService,
                                                      ExecutorService executorService, Context context) {
        if (instance == null) {
            synchronized (ResetableDefaultRecipeRepository.class) {
                if (instance == null) {
                    instance = new ResetableDefaultRecipeRepository(recipeDetailsDao, recipeDao,
                            webService, executorService, context);
                }
            }
        }
        return instance;
    }

    public static void resetInstance() {
        instance = null;
    }
}
