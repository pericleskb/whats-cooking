package com.example.whatscooking.ui.main;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.whatscooking.R;
import com.example.whatscooking.adapters.RecipeListAdapter;
import com.example.whatscooking.databinding.MainFragmentBindingImpl;
import com.example.whatscooking.viewmodels.RecipesListViewModel;

public class MainFragment extends Fragment {

    private RecipesListViewModel recipesListViewModel;
    private RecipeListAdapter recipeListAdapter;
    private MainFragmentBindingImpl binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recipesListViewModel = ViewModelProviders.of(this).get(RecipesListViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = bind(inflater, container);
        subscribeUi();
        binding.fab.setOnClickListener(v -> Toast.makeText(getContext(),
                "Go to new recipe activity", Toast.LENGTH_SHORT).show());
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void subscribeUi() {
        //could happen from new recipe activity or a new recipe pushed into a joined account
        recipesListViewModel.getAllRecipes().observe(this, recipes -> {
            //TODO create binding adapter to set recycler view to GONE when empty and show a text view message
            binding.setHasRecipes(recipes != null && recipes.isEmpty());
            recipeListAdapter.setRecipeList(recipes);
        });
    }

    private View bind(LayoutInflater inflater, ViewGroup container) {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment,
                container, false);
        binding.setLifecycleOwner(this);
        recipeListAdapter = new RecipeListAdapter(recipesListViewModel.getAllRecipes().getValue());
        binding.recipeRecycleView.setAdapter(recipeListAdapter);
        return binding.getRoot();
    }
}
