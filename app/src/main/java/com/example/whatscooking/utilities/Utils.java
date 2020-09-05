package com.example.whatscooking.utilities;

import android.content.Context;

import com.example.whatscooking.R;
import com.example.whatscooking.data.entities.Recipe;
import com.example.whatscooking.data.entities.RecipeBuilder;
import com.example.whatscooking.data.entities.RecipeDetails;
import com.example.whatscooking.data.entities.RecipeDetailsBuilder;

import java.io.IOException;
import java.util.ArrayList;

public class Utils {
    public static RecipeDetails buildTestRecipeDetails(String title, Context context) throws IOException {
        switch (title) {
            case "Dahl":
                return new RecipeDetailsBuilder("Dahl").setTimeMinutes(60).setDifficulty(RecipeDetails.Difficulty.medium)
                        .setDescription("description")
                        .setImageUri(MediaOperations.storeImage(context, R.drawable.dahl,
                                MediaOperations.getUniqueImageName()).toString())
                        .setServings(5).build();
            case "Falafel wrap":
                return new RecipeDetailsBuilder("Falafel wrap").setDifficulty(RecipeDetails.Difficulty.easy)
                        .setDescription("description")
                        .setImageUri(MediaOperations.storeImage(context, R.drawable.falafel,
                                MediaOperations.getUniqueImageName()).toString())
                        .setServings(4).build();
            case "Mousakas":
                return new RecipeDetailsBuilder("Mousakas").setTimeMinutes(90).setDifficulty(RecipeDetails.Difficulty.hard)
                        .setDescription("description")
                        .setImageUri(MediaOperations.storeImage(context, R.drawable.mousakas,
                                MediaOperations.getUniqueImageName()).toString())
                        .setServings(8).build();
            case "Fried Squid":
                return new RecipeDetailsBuilder("Fried Squid").setTimeMinutes(45).setDifficulty(RecipeDetails.Difficulty.medium)
                        .setDescription("description")
                        .setImageUri(MediaOperations.storeImage(context, R.drawable.squid,
                                MediaOperations.getUniqueImageName()).toString())
                        .setServings(3).build();
            case "Venison steak":
            default:
                return new RecipeDetailsBuilder("Venison steak").setTimeMinutes(30)
                    .setDescription("description")
                    .setImageUri(MediaOperations.storeImage(context, R.drawable.venison,
                            MediaOperations.getUniqueImageName()).toString())
                    .setServings(2).build();
        }
    }

    public static Recipe buildTestRecipe(String title, Context context) {
        ArrayList<String> instructions = new ArrayList<>();
        ArrayList<String> ingredients = new ArrayList<>();

        instructions.add(context.getResources().getString(R.string.test_recipe_step_1));
        instructions.add(context.getResources().getString(R.string.test_recipe_step_2));
        instructions.add(context.getResources().getString(R.string.test_recipe_step_3));
        instructions.add(context.getResources().getString(R.string.test_recipe_step_4));
        instructions.add(context.getResources().getString(R.string.test_recipe_step_5));
        instructions.add(context.getResources().getString(R.string.test_recipe_step_6));

        ingredients.add(context.getResources().getString(R.string.test_ingredient_1));
        ingredients.add(context.getResources().getString(R.string.test_ingredient_2));
        ingredients.add(context.getResources().getString(R.string.test_ingredient_3));
        ingredients.add(context.getResources().getString(R.string.test_ingredient_4));
        ingredients.add(context.getResources().getString(R.string.test_ingredient_5));
        ingredients.add(context.getResources().getString(R.string.test_ingredient_6));
        ingredients.add(context.getResources().getString(R.string.test_ingredient_7));
        ingredients.add(context.getResources().getString(R.string.test_ingredient_8));
        ingredients.add(context.getResources().getString(R.string.test_ingredient_8));
        ingredients.add(context.getResources().getString(R.string.test_ingredient_8));
        ingredients.add(context.getResources().getString(R.string.test_ingredient_8));
        ingredients.add(context.getResources().getString(R.string.test_ingredient_8));

        return new RecipeBuilder(title).setIngredients(ingredients).setInstructions(instructions).build();
    }
}
