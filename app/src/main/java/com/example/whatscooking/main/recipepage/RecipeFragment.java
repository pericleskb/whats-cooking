package com.example.whatscooking.main.recipepage;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.whatscooking.R;
import com.example.whatscooking.databinding.RecipeFragmentBindingImpl;
import com.example.whatscooking.main.MainComponent;

public class RecipeFragment extends Fragment {

    RecipeViewModel recipeViewModel;
    MainComponent mainComponent;
    String recipeTitle;
    private RecipeFragmentBindingImpl binding;

    public RecipeFragment(MainComponent mainComponent, String title) {
        this.mainComponent = mainComponent;
        this.recipeTitle = title;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.recipeViewModel = new ViewModelProvider(this,
                new RecipeViewModelFactory(getActivity().getApplication(), recipeTitle))
                .get(RecipeViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = bind(inflater, container);
        //TODO add code to observe LiveData
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mainComponent.inject(this);
    }

    private View bind(LayoutInflater inflater, ViewGroup container) {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment,
                container, false);
        binding.setLifecycleOwner(this);
        return binding.getRoot();
    }
}
