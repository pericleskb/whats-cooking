package com.example.whatscooking.main.recipepage;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.whatscooking.main.MainComponent;

import javax.inject.Inject;

public class NewRecipeFragment extends Fragment {

    @Inject
    RecipeViewModel recipeViewModel;
    MainComponent mainComponent;

    public NewRecipeFragment(MainComponent mainComponent) {
        this.mainComponent = mainComponent;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainComponent.inject(this);
    }
}
