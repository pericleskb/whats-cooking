package com.example.whatscooking.data;

import com.example.whatscooking.data.daos.RecipeInfoDao;

/*
 Used to reset the repository before each repository test
 */
public class ResetableDefaultRecipeRepository extends DefaultRecipeRepository {

    public ResetableDefaultRecipeRepository(RecipeInfoDao recipeInfoDao) {
        super(recipeInfoDao);
    }

    public static DefaultRecipeRepository getInstance(RecipeInfoDao recipeInfoDao) {
        if (instance == null) {
            synchronized (ResetableDefaultRecipeRepository.class) {
                if (instance == null) {
                    instance = new ResetableDefaultRecipeRepository(recipeInfoDao);
                }
            }
        }
        return instance;
    }

    public static void resetInstance() {
        instance = null;
    }
}
