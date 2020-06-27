package com.example.whatscooking.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;

import android.os.Bundle;

import com.example.whatscooking.MyApplication;
import com.example.whatscooking.R;

public class MainActivity extends AppCompatActivity {

    public MainComponent mainComponent;
    private NavController mNavController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mainComponent = ((MyApplication) getApplication()).appComponent
                .mainComponent().create();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        Toolbar toolbar = findViewById(R.id.catalog_toolbar);
        setSupportActionBar(toolbar);
    }
}
