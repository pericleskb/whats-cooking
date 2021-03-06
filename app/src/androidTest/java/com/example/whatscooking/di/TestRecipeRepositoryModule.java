package com.example.whatscooking.di;

import com.example.whatscooking.data.MockRepository;
import com.example.whatscooking.data.RecipeRepository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class TestRecipeRepositoryModule {

    //This will provide a FakeAndroidTestRepository when a DefaultRecipeRepository is requested
    @Binds
    abstract RecipeRepository provideRecipeRepository(MockRepository repository);

}
