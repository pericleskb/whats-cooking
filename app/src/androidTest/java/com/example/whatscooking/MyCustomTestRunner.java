package com.example.whatscooking;

import android.app.Application;
import android.content.Context;

import androidx.test.runner.AndroidJUnitRunner;

public class MyCustomTestRunner extends AndroidJUnitRunner {

    @Override
    public Application newApplication(ClassLoader cl, String className, Context context)
            throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        return super.newApplication(cl, MyTestApplication.class.getName(), context);
    }
}
