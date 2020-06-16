package com.example.whatscooking.data;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.whatscooking.R;
import com.example.whatscooking.data.daos.RecipeInfoDao;
import com.example.whatscooking.data.entities.RecipeInfo;
import com.example.whatscooking.utilities.Constants;
import com.example.whatscooking.utilities.MediaOperations;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//https://medium.com/androiddevelopers/7-pro-tips-for-room-fbadea4bfbd1#4785
//TODO maybe set export schema to true
@Database(entities = RecipeInfo.class, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase instance;
    public abstract RecipeInfoDao recipeDao();
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);
    static Context context;

    public static AppDatabase createInstance(Context cont) {
        context = cont;
        RoomDatabase.Builder<AppDatabase> instanceBuilder = Room.databaseBuilder(cont, AppDatabase.class, Constants.DATABASE_NAME)
                .fallbackToDestructiveMigration();
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.APP_PACKAGE_NAME, Context.MODE_PRIVATE);
        if (!sharedPreferences.getBoolean(Constants.PREF_DB_INITIALIZED, false)) {
            instanceBuilder.addCallback(roomDatabaseCallback);
            sharedPreferences.edit().putBoolean(Constants.PREF_DB_INITIALIZED, true).commit();
        }
        instance = instanceBuilder.build();
        return instance;
    }

    private static RoomDatabase.Callback roomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            databaseWriteExecutor.execute(() -> {
                try {
                    instance.recipeDao().insert(
                            new RecipeBuilder("Dahl").setTimeMinutes(60).setDifficulty(RecipeInfo.Difficulty.medium)
                                    .setDescription("description")
                                    .setImageUri(MediaOperations.storeImage(context, R.drawable.dahl).toString())
                                    .setServings(5).build(),
                            new RecipeBuilder("Falafel wrap").setDifficulty(RecipeInfo.Difficulty.easy)
                                .setDescription("description")
                                .setImageUri(MediaOperations.storeImage(context, R.drawable.falafel).toString())
                                .setServings(4).build(),
                            new RecipeBuilder("Mousakas").setTimeMinutes(90).setDifficulty(RecipeInfo.Difficulty.hard)
                                    .setDescription("description")
                                    .setImageUri(MediaOperations.storeImage(context, R.drawable.mousakas).toString())
                                    .setServings(8).build(),
                            new RecipeBuilder("Fried Squid").setTimeMinutes(45).setDifficulty(RecipeInfo.Difficulty.medium)
                                    .setDescription("description")
                                    .setImageUri(MediaOperations.storeImage(context, R.drawable.squid).toString())
                                    .setServings(3).build(),
                            new RecipeBuilder("Venison steak").setTimeMinutes(30)
                                    .setDescription("description")
                                    .setImageUri(MediaOperations.storeImage(context, R.drawable.venison).toString())
                                    .setServings(2).build()
                    );
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    };
}