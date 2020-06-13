package com.example.whatscooking.di;

import android.app.Application;
import android.content.Context;

import com.example.whatscooking.MainComponent;
import com.example.whatscooking.MyApplication;
import com.example.whatscooking.data.AppDatabase;
import com.example.whatscooking.data.RecipeDao;
import com.example.whatscooking.ui.main.MainFragment;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = {RecipeRepositoryModule.class, AppSubcomponents.class}, dependencies = {AppDatabase.class})
public interface AppComponent {

    AppDatabase appDatabase();

    // Factory to create instances of the AppComponent
    @Component.Factory
    interface Factory {
        // With @BindsInstance, the Context passed in will be available in the graph
        AppComponent create(@BindsInstance Context context, @BindsInstance Application application,
                            AppDatabase appDatabase);
    }

    MainComponent.Factory mainComponent();
}
