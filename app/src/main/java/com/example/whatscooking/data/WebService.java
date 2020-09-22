package com.example.whatscooking.data;

import com.example.whatscooking.data.entities.Recipe;
import com.example.whatscooking.data.entities.RecipeDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface WebService {
    @GET("recipeDetails/from/{from}/to/{to}/")
    Call<List<RecipeDetails>> getDetailsForRecipes(@Path("from") int from,
                                                   @Path("to") int to);

    @GET("recipeInstructions/{recipeName}/")
    Call<Recipe> getRecipe(@Path("recipeName") String name);
}
