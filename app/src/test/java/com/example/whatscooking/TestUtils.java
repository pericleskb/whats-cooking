package com.example.whatscooking;

import android.content.Context;

import com.example.whatscooking.data.Recipe;
import com.example.whatscooking.data.RecipeBuilder;
import com.example.whatscooking.utilities.MediaOperations;

import java.io.IOException;
import java.util.Stack;

public class TestUtils {

    Stack<Recipe> recipes = new Stack<>();

    public TestUtils(Context context) {
        try {
            recipes.push(new RecipeBuilder("Dahl").setTimeMinutes(60).setDifficulty(Recipe.Difficulty.medium)
                    .setDescription("description")
                    .setImageUri(MediaOperations.storeImage(context, R.drawable.dahl).toString())
                    .setServings(5).build());
            recipes.push(new RecipeBuilder("Falafel wrap").setDifficulty(Recipe.Difficulty.easy)
                    .setDescription("description")
                    .setImageUri(MediaOperations.storeImage(context, R.drawable.falafel).toString())
                    .setServings(4).build());
            recipes.push(new RecipeBuilder("Mousakas").setTimeMinutes(90).setDifficulty(Recipe.Difficulty.hard)
                    .setDescription("description")
                    .setImageUri(MediaOperations.storeImage(context, R.drawable.mousakas).toString())
                    .setServings(8).build());
            recipes.push(new RecipeBuilder("Fried Squid").setTimeMinutes(45).setDifficulty(Recipe.Difficulty.medium)
                    .setDescription("description")
                    .setImageUri(MediaOperations.storeImage(context, R.drawable.squid).toString())
                    .setServings(3).build());
            recipes.push(new RecipeBuilder("Venison steak").setTimeMinutes(30)
                    .setDescription("description")
                    .setImageUri(MediaOperations.storeImage(context, R.drawable.venison).toString())
                    .setServings(2).build());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Stack<Recipe> getRecipesStack() {
        return recipes;
    }
}
