package com.example.whatscooking;

import com.example.whatscooking.di.DaggerTestAppComponent;

public class MyTestApplication extends MyApplication {

    @Override
    void initializeComponent() {
        appComponent = DaggerTestAppComponent.factory().create(getApplicationContext(), this,
                appDatabase, retrofit, executorService);
    }
}
