package com.example.whatscooking.data.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.whatscooking.data.entities.RecipeInfo;

import java.util.List;

@Dao
public interface RecipeInfoDao {

    @Query("SELECT * FROM recipes_info ORDER BY title ASC")
    LiveData<List<RecipeInfo>> getAll();

//    @Query("SELECT * FROM recipe_table WHERE difficulty IS :difficulty")
//    List<Recipe> loadByDifficulty( Recipe.Difficulty difficulty);

    //TODO write tests
    @Insert
    void insertAll(RecipeInfo... recipesInfo);

    @Delete
    void delete(RecipeInfo recipeInfo);

    @Query("DELETE FROM recipes_info WHERE title IN (:titles)")
    void deleteSelected(String... titles);

    @Query("DELETE FROM recipes_info")
    void deleteAll();
}
