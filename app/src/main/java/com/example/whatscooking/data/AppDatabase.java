package com.example.whatscooking.data;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.whatscooking.data.daos.RecipeDao;
import com.example.whatscooking.data.daos.RecipeInfoDao;
import com.example.whatscooking.data.entities.RecipeInfo;
import com.example.whatscooking.utilities.Constants;
import com.example.whatscooking.utilities.Utils;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//https://medium.com/androiddevelopers/7-pro-tips-for-room-fbadea4bfbd1#4785
//TODO maybe set export schema to true
@Database(entities = RecipeInfo.class, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase instance;
    public abstract RecipeInfoDao recipeInfoDao();
    public abstract RecipeDao recipeDao();
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
                    //Dahl
                    instance.recipeInfoDao().insert(Utils.buildTestRecipeInfo("Dahl", context));
                    instance.recipeDao().insert(Utils.buildTestRecipe("Dahl", context));
                    //Falafel wrap
                    instance.recipeInfoDao().insert(Utils.buildTestRecipeInfo("Falafel wrap", context));
                    instance.recipeDao().insert(Utils.buildTestRecipe("Falafel wrap", context));
                    //Mousakas
                    instance.recipeInfoDao().insert(Utils.buildTestRecipeInfo("Mousakas", context));
                    instance.recipeDao().insert(Utils.buildTestRecipe("Mousakas", context));
                    //Fried Squid
                    instance.recipeInfoDao().insert(Utils.buildTestRecipeInfo("Fried Squid", context));
                    instance.recipeDao().insert(Utils.buildTestRecipe("Fried Squid", context));
                    //Venison steak
                    instance.recipeInfoDao().insert(Utils.buildTestRecipeInfo("Venison steak", context));
                    instance.recipeDao().insert(Utils.buildTestRecipe("Venison steak", context));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    };
}