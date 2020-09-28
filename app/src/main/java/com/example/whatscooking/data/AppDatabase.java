package com.example.whatscooking.data;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.whatscooking.data.daos.RecipeDao;
import com.example.whatscooking.data.daos.RecipeDetailsDao;
import com.example.whatscooking.data.entities.Recipe;
import com.example.whatscooking.data.entities.RecipeDetails;
import com.example.whatscooking.utilities.Constants;
import com.example.whatscooking.utilities.Utils;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//https://medium.com/androiddevelopers/7-pro-tips-for-room-fbadea4bfbd1#4785
//TODO investigate correct usage of the Room Database
//TODO maybe set export schema to true
@Database(entities = {RecipeDetails.class, Recipe.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase instance;
    public abstract RecipeDetailsDao recipeDetailsDao();
    public abstract RecipeDao recipeDao();

    static Context context;

    static Executor myExecutor;

    public static AppDatabase createInstance(Context cont, Executor pExecutor) {
        context = cont;
        myExecutor = pExecutor;
        RoomDatabase.Builder<AppDatabase> instanceBuilder = Room.databaseBuilder(cont, AppDatabase.class, Constants.DATABASE_NAME)
                .fallbackToDestructiveMigration();
        SharedPreferences sharedPreferences = context.getSharedPreferences(Constants.APP_PACKAGE_NAME, Context.MODE_PRIVATE);
//        if (!sharedPreferences.getBoolean(Constants.PREF_DB_INITIALIZED, false)) {
//            instanceBuilder.addCallback(roomDatabaseCallback);
//            sharedPreferences.edit().putBoolean(Constants.PREF_DB_INITIALIZED, true).commit();
//        }
        instance = instanceBuilder.build();
        return instance;
    }

//    private static RoomDatabase.Callback roomDatabaseCallback = new RoomDatabase.Callback() {
//        @Override
//        public void onOpen(@NonNull SupportSQLiteDatabase db) {
//            super.onOpen(db);
//            myExecutor.execute(() -> {
//                try {
//                    //Dahl
//                    instance.recipeDetailsDao().insert(Utils.buildTestRecipeDetails("Dahl", context));
//                    instance.recipeDao().insert(Utils.buildTestRecipe("Dahl", context));
//                    //Falafel wrap
//                    instance.recipeDetailsDao().insert(Utils.buildTestRecipeDetails("Falafel wrap", context));
//                    instance.recipeDao().insert(Utils.buildTestRecipe("Falafel wrap", context));
//                    //Mousakas
//                    instance.recipeDetailsDao().insert(Utils.buildTestRecipeDetails("Mousakas", context));
//                    instance.recipeDao().insert(Utils.buildTestRecipe("Mousakas", context));
//                    //Fried Squid
//                    instance.recipeDetailsDao().insert(Utils.buildTestRecipeDetails("Fried Squid", context));
//                    instance.recipeDao().insert(Utils.buildTestRecipe("Fried Squid", context));
//                    //Venison steak
//                    instance.recipeDetailsDao().insert(Utils.buildTestRecipeDetails("Venison steak", context));
//                    instance.recipeDao().insert(Utils.buildTestRecipe("Venison steak", context));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            });
//        }
//    };
}