package com.example.whatscooking.utilities;

import android.content.Context;

import com.example.whatscooking.R;
import com.example.whatscooking.data.entities.Recipe;
import com.example.whatscooking.data.entities.RecipeBuilder;
import com.example.whatscooking.data.entities.RecipeInfo;
import com.example.whatscooking.data.entities.RecipeInfoBuilder;

import java.io.IOException;
import java.util.ArrayList;

public class Utils {
    public static RecipeInfo buildTestRecipeInfo(String title, Context context) throws IOException {
        switch (title) {
            case "Dahl":
                return new RecipeInfoBuilder("Dahl").setTimeMinutes(60).setDifficulty(RecipeInfo.Difficulty.medium)
                        .setDescription("description")
                        .setImageUri(MediaOperations.storeImage(context, R.drawable.dahl).toString())
                        .setServings(5).build();
            case "Falafel wrap":
                return new RecipeInfoBuilder("Falafel wrap").setDifficulty(RecipeInfo.Difficulty.easy)
                        .setDescription("description")
                        .setImageUri(MediaOperations.storeImage(context, R.drawable.falafel).toString())
                        .setServings(4).build();
            case "Mousakas":
                return new RecipeInfoBuilder("Mousakas").setTimeMinutes(90).setDifficulty(RecipeInfo.Difficulty.hard)
                        .setDescription("description")
                        .setImageUri(MediaOperations.storeImage(context, R.drawable.mousakas).toString())
                        .setServings(8).build();
            case "Fried Squid":
                return new RecipeInfoBuilder("Fried Squid").setTimeMinutes(45).setDifficulty(RecipeInfo.Difficulty.medium)
                        .setDescription("description")
                        .setImageUri(MediaOperations.storeImage(context, R.drawable.squid).toString())
                        .setServings(3).build();
            case "Venison steak":
            default:
                return new RecipeInfoBuilder("Venison steak").setTimeMinutes(30)
                    .setDescription("description")
                    .setImageUri(MediaOperations.storeImage(context, R.drawable.venison).toString())
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

        return new RecipeBuilder(title).setIngredients(ingredients).setInstructions(instructions).build();
    }
}
