package com.example.whatscooking.data.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.whatscooking.data.Converters;

@Entity(tableName = "recipe_details")
public class RecipeDetails {

    public RecipeDetails(String title) {
        this.title = title;
        this.difficulty = null;
    }

    public enum Difficulty {
        not_set,
        easy,
        medium,
        hard
    }

    @PrimaryKey @NonNull
    public String title;

    @ColumnInfo(name = "time")
    public Integer timeMinutes;

    @TypeConverters(Converters.class)
    public Difficulty difficulty;

    public String description;

    public String imageUri;

    public Integer servings;

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof RecipeDetails) {
            RecipeDetails otherObject = (RecipeDetails) obj;
            return this.title == otherObject.title && this.imageUri == otherObject.imageUri
                    && this.description == otherObject.description && this.difficulty == otherObject.difficulty
                    && this.servings == otherObject.servings && this.timeMinutes == otherObject.timeMinutes;
        }
        return false;
    }

}

