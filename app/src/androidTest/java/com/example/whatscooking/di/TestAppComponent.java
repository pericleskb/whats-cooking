package com.example.whatscooking.di;

import android.app.Application;
import android.content.Context;

import com.example.whatscooking.data.AppDatabase;

import java.util.concurrent.ExecutorService;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import retrofit2.Retrofit;

@Singleton
@Component(modules = {TestRecipeRepositoryModule.class}, dependencies = {AppDatabase.class})
public interface TestAppComponent extends AppComponent {

    @Component.Factory
    interface Factory {
        AppComponent create(@BindsInstance Context context, @BindsInstance Application application,
                            AppDatabase appDatabase, @BindsInstance Retrofit retrofit,
                            @BindsInstance ExecutorService executorService);
    }
}
