package com.example.whatscooking.data;

import com.example.whatscooking.data.entities.Recipe;
import com.example.whatscooking.data.entities.RecipeDetails;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface WebService {
    @GET("recipeDetails/")
    Call<List<RecipeDetails>> getDetailsForRecipes(@Query("dataVersion") int dataVersion);

    @GET("recipeInstructions/{recipeName}/")
    Call<Recipe> getRecipe(@Path("recipeName") String name);
}
