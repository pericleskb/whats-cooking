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
    public static String BASE_URL = "http://192.168.1.11:8080/";
    public static String INFO_TAG = "WC-INFO";
    public static String LATEST_DATA_VERSION_HEADER = "latest_data_version";

    //Preferences
    public static String PREF_DB_INITIALIZED = "db_init";
    public static int NUM_OF_RECIPES_TO_FETCH = 5;
    public static String API_PREFERENCES = "com.example.whatscooking.API_PREFERENCES";
    public static String DATA_VERSION = "data_version";
}