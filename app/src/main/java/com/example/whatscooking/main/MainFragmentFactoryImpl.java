package com.example.whatscooking.main;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentFactory;

import com.example.whatscooking.main.recipepage.NewRecipeFragment;
import com.example.whatscooking.main.recipeslist.RecipesListFragment;

public class MainFragmentFactoryImpl extends FragmentFactory {
    private final MainComponent mainComponent;

    public MainFragmentFactoryImpl(MainComponent mainComponent) {
        this.mainComponent = mainComponent;
    }

    @NonNull
    @Override
    public Fragment instantiate(@NonNull ClassLoader classLoader, @NonNull String className) {
        Class clazz = loadFragmentClass(classLoader, className);
        if (clazz == RecipesListFragment.class) {
            return new RecipesListFragment(this.mainComponent);
        } else if (clazz == NewRecipeFragment.class) {
            return new NewRecipeFragment(this.mainComponent);
        } else {
            return null;
        }
    }
}
