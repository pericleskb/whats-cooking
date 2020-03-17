package com.example.whatscooking.data;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "recipe_table")
public class Recipe {
//    enum Difficulty {
//        easy,
//        medium,
//        hard
//    }

    @PrimaryKey
    @NonNull
    public String title;

    @ColumnInfo(name = "time")
    public int timeInMinutes;

//    public Difficulty difficulty;
    public int difficulty;

    public Recipe(String title, int timeInMinutes, int difficulty) {
        this.title = title;
        this.timeInMinutes = timeInMinutes;
        this.difficulty = difficulty;
    }
}

