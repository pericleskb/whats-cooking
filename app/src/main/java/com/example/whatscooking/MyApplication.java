package com.example.whatscooking;

import android.app.Application;

import com.example.whatscooking.data.AppDatabase;
import com.example.whatscooking.di.AppComponent;
import com.example.whatscooking.di.DaggerAppComponent;

public class MyApplication extends Application {

    public AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        initializeComponent();
    }

    void initializeComponent() {
        AppDatabase appDatabase = AppDatabase.createInstance(this);
        appComponent = DaggerAppComponent.factory().create(getApplicationContext(), this,
                appDatabase);
    }
}
