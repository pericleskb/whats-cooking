package com.example.whatscooking.data;

public class ResetableDefaultRecipeRepository extends DefaultRecipeRepository {

    public ResetableDefaultRecipeRepository(RecipeDao recipeDao) {
        super(recipeDao);
    }

    public static DefaultRecipeRepository getInstance(RecipeDao recipeDao) {
        if (instance == null) {
            synchronized (ResetableDefaultRecipeRepository.class) {
                if (instance == null) {
                    instance = new ResetableDefaultRecipeRepository(recipeDao);
                }
            }
        }
        return instance;
    }

    public static void resetInstance() {
        instance = null;
    }
}
