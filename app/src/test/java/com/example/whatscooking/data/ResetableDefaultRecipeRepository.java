package com.example.whatscooking.data;

import com.example.whatscooking.data.daos.RecipeDao;
import com.example.whatscooking.data.daos.RecipeDetailsDao;

/*
 Used to reset the repository before each repository test
 */
public class ResetableDefaultRecipeRepository extends DefaultRecipeRepository {

    public ResetableDefaultRecipeRepository(RecipeDetailsDao recipeDetailsDao, RecipeDao recipeDao) {
        super(recipeDetailsDao, recipeDao);
    }

    //TODO we can change this from being a Singleton to being generated anew from Dagger
    public static DefaultRecipeRepository getInstance(RecipeDetailsDao recipeDetailsDao,
                                                      RecipeDao recipeDao) {
        if (instance == null) {
            synchronized (ResetableDefaultRecipeRepository.class) {
                if (instance == null) {
                    instance = new ResetableDefaultRecipeRepository(recipeDetailsDao, recipeDao);
                }
            }
        }
        return instance;
    }

    public static void resetInstance() {
        instance = null;
    }
}
