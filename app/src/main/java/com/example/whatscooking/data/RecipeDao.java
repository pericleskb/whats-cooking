package com.example.whatscooking.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RecipeDao {

    @Query("SELECT * FROM recipe_table ORDER BY title ASC")
    LiveData<List<Recipe>> getAll();

//    @Query("SELECT * FROM recipe_table WHERE difficulty IS :difficulty")
//    List<Recipe> loadByDifficulty( Recipe.Difficulty difficulty);

    @Insert
    void insertAll(Recipe... recipes);

    @Delete
    void delete(Recipe recipe);

    @Query("DELETE FROM recipe_table")
    void deleteAll();
}
