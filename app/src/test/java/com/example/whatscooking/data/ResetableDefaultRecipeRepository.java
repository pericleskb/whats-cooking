package com.example.whatscooking.data;

import com.example.whatscooking.data.daos.RecipeDao;
import com.example.whatscooking.data.daos.RecipeInfoDao;

/*
 Used to reset the repository before each repository test
 */
public class ResetableDefaultRecipeRepository extends DefaultRecipeRepository {

    public ResetableDefaultRecipeRepository(RecipeInfoDao recipeInfoDao, RecipeDao recipeDao) {
        super(recipeInfoDao, recipeDao);
    }

    //TODO we can change this from being a Singleton to being generated anew from Dagger
    public static DefaultRecipeRepository getInstance(RecipeInfoDao recipeInfoDao,
                                                      RecipeDao recipeDao) {
        if (instance == null) {
            synchronized (ResetableDefaultRecipeRepository.class) {
                if (instance == null) {
                    instance = new ResetableDefaultRecipeRepository(recipeInfoDao, recipeDao);
                }
            }
        }
        return instance;
    }

    public static void resetInstance() {
        instance = null;
    }
}
