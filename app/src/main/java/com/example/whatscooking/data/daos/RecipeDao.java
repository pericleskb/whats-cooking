package com.example.whatscooking.data.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.whatscooking.data.entities.Recipe;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface RecipeDao {

    @Query("SELECT * FROM recipes ORDER BY title ASC")
    LiveData<List<Recipe>> getAllRecipes();

    //TODO write tests
    @Query("SELECT * FROM recipes WHERE title IS :recipeTitle")
    Recipe getRecipe(String recipeTitle);

    @Query("SELECT ingredients FROM recipes WHERE title IS :recipeTitle")
    List<String> getIngredients(String recipeTitle);

    @Insert
    void insert(Recipe recipe);

    @Delete
    void delete(Recipe recipe);

    @Update
    void updateRecipe(Recipe recipe);
}
