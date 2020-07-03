package com.example.whatscooking.main.recipepage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.whatscooking.adapters.IngredientsListAdapter;
import com.example.whatscooking.databinding.IngredientsFragmentBinding;

public class IngredientsChildFragment extends Fragment {

    IngredientsListAdapter ingredientsListAdapter;
    IngredientsFragmentBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.ingredientsListAdapter = new IngredientsListAdapter();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = IngredientsFragmentBinding.inflate(inflater, container, false);
        binding.setLifecycleOwner(this);
        RecipeViewModel recipeViewModel = new ViewModelProvider(getParentFragment()).get(RecipeViewModel.class);
        binding.ingredientsRecyclerView.setAdapter(ingredientsListAdapter);
        recipeViewModel.getRecipeLiveData().observe(getViewLifecycleOwner(), recipe ->
                ingredientsListAdapter.setIngredientsList(recipe.ingredients));
        return binding.getRoot();
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
