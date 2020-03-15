package com.example.whatscooking.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.whatscooking.utilities.Constants;

//https://medium.com/androiddevelopers/7-pro-tips-for-room-fbadea4bfbd1#4785

@Database(entities = Recipe.class, version = 1)
abstract class AppDatabase extends RoomDatabase {
    public abstract RecipeDao recipeDao();

    public static AppDatabase instance;

    public AppDatabase getInstance(Context context) {
        return instance != null ? instance : buildDatabase(context);
    }

    private AppDatabase buildDatabase(Context context) {

        class roomDbCallback extends RoomDatabase.Callback {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
                getInstance(context).recipeDao().insertAll(
                        new Recipe("Dahl", 60, Recipe.Difficulty.medium),
                        new Recipe("Falafel wrap", 40, Recipe.Difficulty.easy),
                        new Recipe("Mousakas", 90, Recipe.Difficulty.hard),
                        new Recipe("Fried squid", 45, Recipe.Difficulty.medium),
                        new Recipe("Venison steak", 45, Recipe.Difficulty.easy)
                );
            }

        }

        return Room.databaseBuilder(context, AppDatabase.class, Constants.DATABASE_NAME)
                .addCallback(new roomDbCallback()).build();
    }
}