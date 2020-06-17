package com.example.whatscooking.data.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.whatscooking.data.Converters;

import java.util.ArrayList;

import static androidx.room.ForeignKey.CASCADE;

/*
 Creating a separate table for Recipe is probably not a good design
 but I will use it in favour of learning
 */

@Entity(tableName = "recipes", foreignKeys = @ForeignKey(entity = RecipeInfo.class,
                                                         parentColumns = "title",
                                                         childColumns = "title",
                                                         onDelete = CASCADE))
public class Recipe {

    public Recipe(String title) {
        this.title = title;
    }

    @PrimaryKey @NonNull
    public String title;

    @TypeConverters(Converters.class)
    public ArrayList<String> instructions;

    @TypeConverters(Converters.class)
    public ArrayList<String> ingredients;
}
