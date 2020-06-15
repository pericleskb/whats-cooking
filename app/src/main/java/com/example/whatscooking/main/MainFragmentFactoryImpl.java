package com.example.whatscooking.main;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentFactory;
import com.example.whatscooking.main.recipelist.RecipesListFragment;

public class MainFragmentFactoryImpl extends FragmentFactory {
    private final MainComponent mainComponent;

    public MainFragmentFactoryImpl(MainComponent mainComponent) {
        this.mainComponent = mainComponent;
    }

    @NonNull
    @Override
    public Fragment instantiate(@NonNull ClassLoader classLoader, @NonNull String className) {
        Class clazz = loadFragmentClass(classLoader, className);
        Fragment fragment = null;
        if (clazz == RecipesListFragment.class) {
            fragment = new RecipesListFragment(this.mainComponent);
        }
        return fragment;
    }
}
