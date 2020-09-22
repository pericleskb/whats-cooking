package com.example.whatscooking;

import android.app.Application;

import com.example.whatscooking.data.AppDatabase;
import com.example.whatscooking.di.AppComponent;
import com.example.whatscooking.di.DaggerAppComponent;
import com.example.whatscooking.utilities.Constants;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyApplication extends Application {

    private static final int NUMBER_OF_THREADS = 4;
    public AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeComponent();
    }

    void initializeComponent() {
        ExecutorService executorService = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
        AppDatabase appDatabase = AppDatabase.createInstance(this, executorService);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        appComponent = DaggerAppComponent.factory().create(getApplicationContext(), this,
                appDatabase, retrofit, executorService);
    }
}
