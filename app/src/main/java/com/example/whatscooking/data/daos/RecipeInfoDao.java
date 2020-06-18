package com.example.whatscooking.data.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.whatscooking.data.entities.RecipeInfo;

import java.util.List;

@Dao
public interface RecipeInfoDao {

    @Query("SELECT * FROM recipes_info ORDER BY title ASC")
    LiveData<List<RecipeInfo>> getAll();

    @Query("SELECT * FROM recipes_info WHERE title IS :title")
    LiveData<RecipeInfo> getRecipeInfo(String title);

//    @Query("SELECT * FROM recipe_table WHERE difficulty IS :difficulty")
//    List<Recipe> loadByDifficulty( Recipe.Difficulty difficulty);

    //TODO write tests
    @Insert
    void insert(RecipeInfo recipeInfo);

    @Delete
    void delete(RecipeInfo recipeInfo);

    @Update
    void update(RecipeInfo recipeInfo);

    @Query("DELETE FROM recipes_info WHERE title IN (:titles)")
    void deleteSelected(String... titles);

    @Query("DELETE FROM recipes_info")
    void deleteAll();
}
