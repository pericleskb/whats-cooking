package com.example.whatscooking.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.whatscooking.MyApplication;
import com.example.whatscooking.R;
import com.example.whatscooking.main.recipepage.RecipeFragment;
import com.example.whatscooking.main.recipeslist.RecipesListFragment;
import com.example.whatscooking.utilities.Constants;

public class MainActivity extends AppCompatActivity
        implements RecipesListFragment.OnRecipeSelectedListener {

    public MainComponent mainComponent;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mainComponent = ((MyApplication) getApplication()).appComponent
                .mainComponent().create();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        Toolbar toolbar = findViewById(R.id.catalog_toolbar);
        setSupportActionBar(toolbar);
        fragmentManager = getSupportFragmentManager();
        RecipesListFragment fragment = new RecipesListFragment();
        if (savedInstanceState == null) {
        fragmentManager.beginTransaction().add(R.id.container, fragment).commit();
        }
    }

    @Override
    public void onRecipeSelected(String recipeTitle) {
        Bundle args = new Bundle();
        args.putString(Constants.RECIPE_ARG, recipeTitle);
        RecipeFragment fragment = new RecipeFragment();
        fragmentManager.beginTransaction().replace(R.id.container, fragment, null)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onBackPressed() {
        if (fragmentManager.getBackStackEntryCount() > 0) {
            fragmentManager.popBackStack();
        } else {
            super.onBackPressed();
        }
    }
}
