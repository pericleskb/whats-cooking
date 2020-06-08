package com.example.whatscooking.di;

import android.app.Application;
import android.content.Context;

import com.example.whatscooking.data.AppDatabase;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = {TestRecipeRepositoryModule.class}, dependencies = {AppDatabase.class})
public interface TestAppComponent extends AppComponent {


    // Factory to create instances of the AppComponent
    @Component.Factory
    interface Factory {
        // With @BindsInstance, the Context passed in will be available in the graph
        AppComponent create(@BindsInstance Context context, @BindsInstance Application application,
                            AppDatabase appDatabase);
    }
}
