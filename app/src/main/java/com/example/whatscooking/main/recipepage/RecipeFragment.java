package com.example.whatscooking.main.recipepage;

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
import com.example.whatscooking.data.RecipeRepository;
import com.example.whatscooking.databinding.RecipeFragmentBindingImpl;
import com.example.whatscooking.main.MainActivity;
import com.example.whatscooking.main.MainComponent;
import com.example.whatscooking.utilities.Constants;

import javax.inject.Inject;

public class RecipeFragment extends Fragment {

    RecipeViewModel recipeViewModel;
    @Inject
    MainComponent mainComponent;
    @Inject
    RecipeRepository repository;

    String recipeTitle;
    private RecipeFragmentBindingImpl binding;

    public RecipeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.recipeTitle = this.getArguments().getString(Constants.RECIPE_ARG);
        //TODO https://proandroiddev.com/5-common-mistakes-when-using-architecture-components-403e9899f4cb mistake #5
        this.recipeViewModel = new ViewModelProvider(this,
                new RecipeViewModelFactory(getActivity().getApplication(), repository, recipeTitle))
                .get(RecipeViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = bind(inflater, container);
        subscribeUi();
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        ((MainActivity)getActivity()).mainComponent.inject(this);
    }

    private View bind(LayoutInflater inflater, ViewGroup container) {
        binding = DataBindingUtil.inflate(inflater, R.layout.recipe_fragment,
                container, false);
        binding.setLifecycleOwner(this);
        binding.setRecipe(this.recipeViewModel);
        binding.changeViewButton.setOnClickListener(l -> {});

        return binding.getRoot();
    }

    private void subscribeUi() {
        recipeViewModel.getRecipeInfo().removeObservers(getViewLifecycleOwner());
        recipeViewModel.getRecipe().removeObservers(getViewLifecycleOwner());

        recipeViewModel.getRecipe().observe(getViewLifecycleOwner(), recipe ->
                binding.setRecipe(recipeViewModel));
        recipeViewModel.getRecipeInfo().observe(getViewLifecycleOwner(),
                recipe -> binding.setRecipe(recipeViewModel));
    }
}
