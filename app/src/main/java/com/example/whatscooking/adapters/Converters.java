package com.example.whatscooking.adapters;

import androidx.room.TypeConverter;

import com.example.whatscooking.data.Recipe;

public class Converters {
    @TypeConverter
    public Recipe.Difficulty fromInteger(Integer value) {

        if (value == null) return null;
        switch (value) {
            case 1: return Recipe.Difficulty.easy;
            case 2: return Recipe.Difficulty.medium;
            case 3: return Recipe.Difficulty.hard;
            default: return Recipe.Difficulty.not_set;
        }
    }

    @TypeConverter
    public Integer difficultyToInt(Recipe.Difficulty difficulty) {

        if (difficulty == null) return null;
        switch (difficulty) {
            case easy: return 1;
            case medium: return 2;
            case hard: return 3;
            default: return 0;
        }
    }
}
