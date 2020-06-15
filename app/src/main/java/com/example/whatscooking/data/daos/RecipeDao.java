package com.example.whatscooking.data.daos;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.whatscooking.data.entities.Recipe;

import java.util.ArrayList;

@Dao
public interface RecipeDao {

    //TODO write tests
    @Query("SELECT * FROM recipes WHERE title IS :recipeTitle")
    ArrayList<String> loadRecipe(String recipeTitle);

    @Query("SELECT ingredients FROM recipes WHERE title IS :recipeTitle")
    ArrayList<String> loadIngredients(String recipeTitle);

    @Insert
    void insert(Recipe recipe);

    @Delete
    void delete(Recipe recipe);
}
