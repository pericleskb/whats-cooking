package com.example.whatscooking.data;

import com.example.whatscooking.data.daos.RecipeDao;
import com.example.whatscooking.data.daos.RecipeDetailsDao;

import java.util.concurrent.ExecutorService;

import retrofit2.Retrofit;

/*
 Used to reset the repository before each repository test
 */
public class ResetableDefaultRecipeRepository extends DefaultRecipeRepository {

    private ResetableDefaultRecipeRepository(RecipeDetailsDao recipeDetailsDao, RecipeDao recipeDao,
                                             Retrofit retrofit, ExecutorService executorService) {
        super(recipeDetailsDao, recipeDao, retrofit, executorService);
    }

    //TODO we can change this from being a Singleton to being generated anew from Dagger
    public static DefaultRecipeRepository getInstance(RecipeDetailsDao recipeDetailsDao,
                                                      RecipeDao recipeDao, Retrofit retrofit,
                                                      ExecutorService executorService) {
        if (instance == null) {
            synchronized (ResetableDefaultRecipeRepository.class) {
                if (instance == null) {
                    instance = new ResetableDefaultRecipeRepository(recipeDetailsDao, recipeDao,
                            retrofit, executorService);
                }
            }
        }
        return instance;
    }

    public static void resetInstance() {
        instance = null;
    }
}
