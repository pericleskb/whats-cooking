package com.example.whatscooking.data.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.whatscooking.data.entities.RecipeDetails;

import java.util.List;

@Dao
public interface RecipeDetailsDao {

    @Query("SELECT * FROM recipe_details ORDER BY title ASC")
    LiveData<List<RecipeDetails>> getAll();

    @Query("SELECT * FROM recipe_details WHERE title IS :title")
    LiveData<RecipeDetails> getRecipeDetails(String title);

    @Insert
    void insert(RecipeDetails recipeDetails);
}
