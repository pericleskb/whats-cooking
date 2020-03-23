package com.example.whatscooking.data;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.whatscooking.adapters.Converters;

@Entity(tableName = "recipe_table")
public class Recipe {
    public enum Difficulty {
        not_set,
        easy,
        medium,
        hard
    }

    @PrimaryKey
    @NonNull
    public String title;

    @ColumnInfo(name = "time")
    public int timeInMinutes;

    @TypeConverters(Converters.class)
    public Difficulty difficulty;

    public Recipe(String title, int timeInMinutes, Difficulty difficulty) {
        this.title = title;
        this.timeInMinutes = timeInMinutes;
        this.difficulty = difficulty;
    }

}

