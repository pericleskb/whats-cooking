package com.example.whatscooking.di;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = TestRecipeRepositoryModule.class)
public interface TestAppComponent extends AppComponent {

}
