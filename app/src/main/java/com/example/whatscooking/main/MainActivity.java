package com.example.whatscooking.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.example.whatscooking.MyApplication;
import com.example.whatscooking.R;
import com.example.whatscooking.main.recipelist.MainFragment;

public class MainActivity extends AppCompatActivity {

    public MainComponent mainComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mainComponent = ((MyApplication) getApplication()).appComponent
                .mainComponent().create();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        Toolbar toolbar = (Toolbar) findViewById(R.id.catalog_toolbar);
        setSupportActionBar(toolbar);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new MainFragment())
                    .commitNow();
        }
    }
}
