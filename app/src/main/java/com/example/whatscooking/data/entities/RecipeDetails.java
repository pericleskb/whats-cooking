package com.example.whatscooking.data.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.whatscooking.data.Converters;

import java.util.Comparator;

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

    @Ignore
    public Integer dataVersion;

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof RecipeDetails) {
            return this.title.equals(((RecipeDetails) obj).title);
        }
        return false;
    }

    public static class RecipeVersionComparator implements Comparator<RecipeDetails> {
        public int compare(RecipeDetails details1, RecipeDetails details2) {
            return details1.dataVersion.compareTo(details2.dataVersion);
        }
    }
}

