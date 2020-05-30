package com.example.whatscooking.di;

import com.example.whatscooking.data.DefaultRecipeRepository;
import com.example.whatscooking.data.RecipeRepository;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class RecipeRepositoryModule {

    @Binds
    abstract RecipeRepository provideRecipeRepository(DefaultRecipeRepository repository);

}
