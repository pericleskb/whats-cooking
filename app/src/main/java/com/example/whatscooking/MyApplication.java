package com.example.whatscooking;

import android.app.Application;

import com.example.whatscooking.data.AppDatabase;
import com.example.whatscooking.di.AppComponent;
import com.example.whatscooking.di.DaggerAppComponent;
import com.example.whatscooking.utilities.Constants;
import com.google.gson.GsonBuilder;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyApplication extends Application {

    private static final int NUMBER_OF_THREADS = 4;
    public AppComponent appComponent;
    // Protected visibility for tests
    ExecutorService executorService;
    AppDatabase appDatabase;

    @Override
    public void onCreate() {
        super.onCreate();
        executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
        appDatabase = AppDatabase.createInstance(this, executorService);
        initializeComponent();
    }

    void initializeComponent() {
        appComponent = DaggerAppComponent.factory().create(getApplicationContext(), this,
                appDatabase, executorService);
    }
}
