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

//    @Query("SELECT * FROM recipe_table WHERE difficulty IS :difficulty")
//    List<Recipe> loadByDifficulty( Recipe.Difficulty difficulty);

    //TODO write tests
    @Insert
    void insert(RecipeDetails recipeDetails);

    @Delete
    void delete(RecipeDetails recipeDetails);

    @Update
    void update(RecipeDetails recipeDetails);

    @Query("DELETE FROM recipe_details WHERE title IN (:titles)")
    void deleteSelected(String... titles);

    @Query("DELETE FROM recipe_details")
    void deleteAll();
}
