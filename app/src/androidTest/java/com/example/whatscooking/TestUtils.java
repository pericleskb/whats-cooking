package com.example.whatscooking;

import com.example.whatscooking.data.entities.RecipeDetails;

import java.util.ArrayList;

//TODO move to sharing folder for test and androidTest
public class TestUtils {

    public static RecyclerViewMatcher withRecyclerView(final int recyclerViewId) {
        return new RecyclerViewMatcher(recyclerViewId);
    }

    public static ArrayList<RecipeDetails> getRecipesInfoList() {
        TestRecipeDetailsBuildDirector director = new TestRecipeDetailsBuildDirector();
        ArrayList<RecipeDetails> recipesDetails = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            recipesDetails.add(director.buildFullRecipeDetails());
        }
        return recipesDetails;
    }

}


