package com.example.whatscooking.data;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Recipe {
    enum Difficulty {
        easy,
        medium,
        hard
    }

    @PrimaryKey
    public String title;

    @ColumnInfo(name = "time")
    public int timeInMinutes;

    public Difficulty difficulty;

    public Recipe(String title, int timeInMinutes, Difficulty difficulty) {
        this.title = title;
        this.timeInMinutes = timeInMinutes;
        this.difficulty = difficulty;
    }
}

