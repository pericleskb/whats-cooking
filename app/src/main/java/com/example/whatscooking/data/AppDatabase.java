package com.example.whatscooking.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.whatscooking.utilities.Constants;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//https://medium.com/androiddevelopers/7-pro-tips-for-room-fbadea4bfbd1#4785
//TODO maybe set export schema to true
@Database(entities = Recipe.class, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase instance;
    public abstract RecipeDao recipeDao();
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (AppDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context, AppDatabase.class, Constants.DATABASE_NAME)
                            .addCallback(roomDatabaseCallback)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return instance;
    }

    private static RoomDatabase.Callback roomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            databaseWriteExecutor.execute(() -> {
                instance.recipeDao().deleteAll();
                instance.recipeDao().insertAll(
                new Recipe("Dahl", 60, 2),
                new Recipe("Falafel wrap", 40, 1),
                new Recipe("Mousakas", 90, 3),
                new Recipe("Fried squid", 45, 2),
                new Recipe("Venison steak", 45, 2)
//                        new Recipe("Dahl", 60, Recipe.Difficulty.medium),
//                        new Recipe("Falafel wrap", 40, Recipe.Difficulty.easy),
//                        new Recipe("Mousakas", 90, Recipe.Difficulty.hard),
//                        new Recipe("Fried squid", 45, Recipe.Difficulty.medium),
//                        new Recipe("Venison steak", 45, Recipe.Difficulty.easy)
                );
            });
        }
    };
}