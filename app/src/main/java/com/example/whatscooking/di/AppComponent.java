package com.example.whatscooking.di;

import android.app.Application;
import android.content.Context;

import com.example.whatscooking.main.MainComponent;
import com.example.whatscooking.data.AppDatabase;

import java.util.concurrent.ExecutorService;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import retrofit2.Retrofit;

@Singleton
@Component(modules = {RecipeRepositoryModule.class, AppSubcomponents.class},
        dependencies = {AppDatabase.class})
public interface AppComponent {

    // Factory to create instances of the AppComponent
    @Component.Factory
    interface Factory {
        // With @BindsInstance, the Context passed in will be available in the graph
        // AppDataBase remains in memory for as long as the application lives
        AppComponent create(@BindsInstance Context context, @BindsInstance Application application,
                            AppDatabase appDatabase, @BindsInstance Retrofit retrofit,
                            @BindsInstance ExecutorService executor);
    }

    MainComponent.Factory mainComponent();
}
