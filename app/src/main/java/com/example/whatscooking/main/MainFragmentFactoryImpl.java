package com.example.whatscooking.main;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentFactory;

import com.example.whatscooking.main.recipepage.RecipeFragment;
import com.example.whatscooking.main.recipeslist.RecipesListFragment;

public class MainFragmentFactoryImpl extends FragmentFactory {
    private final MainComponent mainComponent;
    private final String title;


    public MainFragmentFactoryImpl(MainComponent mainComponent, @Nullable String title) {
        this.mainComponent = mainComponent;
        this.title = title;
    }

    @NonNull
    @Override
    public Fragment instantiate(@NonNull ClassLoader classLoader, @NonNull String className) {
        Class clazz = loadFragmentClass(classLoader, className);
        if (clazz == RecipesListFragment.class) {
            return new RecipesListFragment(this.mainComponent);
        } else if (clazz == RecipeFragment.class) {
            return new RecipeFragment(this.mainComponent, this.title);
        } else {
            return null;
        }
    }
}
