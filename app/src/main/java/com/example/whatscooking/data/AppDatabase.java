package com.example.whatscooking.data;

import android.content.Context;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.whatscooking.R;
import com.example.whatscooking.utilities.Constants;
import com.example.whatscooking.utilities.MediaOperations;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//https://medium.com/androiddevelopers/7-pro-tips-for-room-fbadea4bfbd1#4785
//TODO maybe set export schema to true
@Database(entities = Recipe.class, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase instance;
    public abstract RecipeDao recipeDao();
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    static Context context;

    public static AppDatabase getInstance(Context cont) {
        context = cont;
        if (instance == null) {
            synchronized (AppDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(cont, AppDatabase.class, Constants.DATABASE_NAME)
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
                try {
                    instance.recipeDao().deleteAll();
                    instance.recipeDao().insertAll(
                        new Recipe("Dahl", 60, Recipe.Difficulty.medium,
                                "description",
                                MediaOperations.storeImage(context, R.drawable.dahl).toString(), 5),
                        new Recipe("Falafel wrap", 40, Recipe.Difficulty.easy,
                                "description",
                                MediaOperations.storeImage(context, R.drawable.falafel).toString(), 4),
                        new Recipe("Mousakas", 90, Recipe.Difficulty.hard,
                                "description",
                                MediaOperations.storeImage(context, R.drawable.mousakas).toString(), 10),
                        new Recipe("Fried squid", 45, Recipe.Difficulty.medium,
                                "description",
                                MediaOperations.storeImage(context, R.drawable.squid).toString(), 3),
                        new Recipe("Venison steak", 30, Recipe.Difficulty.not_set,
                                "description",
                                MediaOperations.storeImage(context, R.drawable.venison).toString(), 2)
                    );
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    };
}