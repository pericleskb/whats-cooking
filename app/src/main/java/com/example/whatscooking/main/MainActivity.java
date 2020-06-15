package com.example.whatscooking.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.example.whatscooking.MyApplication;
import com.example.whatscooking.R;
import com.example.whatscooking.main.recipelist.RecipesListFragment;

public class MainActivity extends AppCompatActivity {

    public MainComponent mainComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mainComponent = ((MyApplication) getApplication()).appComponent
                .mainComponent().create();
        getSupportFragmentManager().setFragmentFactory(new MainFragmentFactoryImpl(mainComponent));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.catalog_toolbar);
        setSupportActionBar(toolbar);
        RecipesListFragment fragment = (RecipesListFragment) getSupportFragmentManager()
                .getFragmentFactory().
                instantiate(getClassLoader(), RecipesListFragment.class.getName());
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().
                    replace(R.id.container, fragment)
                    .commitNow();
        }
    }
}
