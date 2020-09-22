package com.example.whatscooking.data.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.whatscooking.data.entities.Recipe;

import java.util.List;

@Dao
public interface RecipeDao {

    @Query("SELECT * FROM recipes ORDER BY title ASC")
    LiveData<List<Recipe>> getAllRecipes();

    //TODO write tests
    @Query("SELECT * FROM recipes WHERE title IS :recipeTitle")
    LiveData<Recipe> getRecipe(String recipeTitle);

    @Insert
    void insert(Recipe recipe);
}
