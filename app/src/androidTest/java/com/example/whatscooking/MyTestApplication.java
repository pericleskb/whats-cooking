package com.example.whatscooking;

import com.example.whatscooking.data.AppDatabase;
import com.example.whatscooking.di.DaggerTestAppComponent;

public class MyTestApplication extends MyApplication {

    @Override
    void initializeComponent() {
        AppDatabase appDatabase = AppDatabase.createInstance(this);
        appComponent = DaggerTestAppComponent.factory().create(getApplicationContext(), this,
                appDatabase);
    }
}
