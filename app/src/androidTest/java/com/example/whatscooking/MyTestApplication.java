package com.example.whatscooking;

import com.example.whatscooking.data.AppDatabase;
import com.example.whatscooking.di.DaggerTestAppComponent;
import com.example.whatscooking.di.TestAppComponent;

public class MyTestApplication extends MyApplication {

    public TestAppComponent testAppComponent;

    @Override
    void initializeComponent() {
        AppDatabase appDatabase = AppDatabase.createInstance(this);
        appComponent = DaggerTestAppComponent.factory().create(getApplicationContext(), this,
                appDatabase);
    }
}
