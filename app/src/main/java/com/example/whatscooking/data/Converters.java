package com.example.whatscooking.data;

import androidx.room.TypeConverter;

import com.example.whatscooking.data.entities.RecipeDetails;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class Converters {
    @TypeConverter
    public RecipeDetails.Difficulty fromInteger(Integer value) {

        if (value == null) return null;
        switch (value) {
            case 1: return RecipeDetails.Difficulty.easy;
            case 2: return RecipeDetails.Difficulty.medium;
            case 3: return RecipeDetails.Difficulty.hard;
            default: return RecipeDetails.Difficulty.not_set;
        }
    }

    @TypeConverter
    public Integer difficultyToInt(RecipeDetails.Difficulty difficulty) {

        if (difficulty == null) return null;
        switch (difficulty) {
            case easy: return 1;
            case medium: return 2;
            case hard: return 3;
            default: return 0;
        }
    }

    @TypeConverter
    public ArrayList<String> fromJsonString(String jsonString) {
        Type listType = new TypeToken<ArrayList<String>>() {}.getType();
        return new Gson().fromJson(jsonString, listType);
    }

    @TypeConverter
    public String fromArrayList(ArrayList<String> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);
        return json;
    }
}
