package com.example.whatscooking.utilities;

import java.io.File;

public class Constants {
    public static String DATABASE_NAME = "recipes-db";
    public static String APP_PACKAGE_NAME = "com.example.whatscooking";
    public static String FILE_PROVIDER_AUTHORITY = "com.example.whatscooking.provider";
    public static String IMAGES_DIR = File.separator + "pictures";
    public static String RECIPE_ARG = "recipeTitle";
    public static String RECIPE_IMG_TRANSITION_ID = "recipe_image";
    public static String RECIPE_TITLE_TRANSITION_ID = "recipe_title";
    public static String BASE_URL = "http://localhost:8080/";

    //Preferences
    public static String PREF_DB_INITIALIZED = "db_init";
    public static int NUM_OF_RECIPES_TO_FETCH = 5;
}