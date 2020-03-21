package com.example.whatscooking.ui.main;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.whatscooking.R;
import com.example.whatscooking.adapters.RecipeListAdapter;
import com.example.whatscooking.databinding.MainFragmentBindingImpl;
import com.example.whatscooking.viewmodels.RecipesListViewModel;

public class MainFragment extends Fragment {

    private RecipesListViewModel recipesListViewModel;
    private RecyclerView recipeRecycleView;
    private RecipeListAdapter recipeListAdapter;

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initViewModel();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = bind(inflater, container);
        initRecyclerView(view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void initViewModel() {
        recipesListViewModel = ViewModelProviders.of(this).get(RecipesListViewModel.class);
        //could happen from new recipe activity or a new recipe pushed into a joined account
        recipesListViewModel.getAllRecipes().observe(this, recipes -> {
            recipeListAdapter.setRecipeList(recipes);
        });
    }

    private void initRecyclerView(View view) {
        recipeRecycleView = view.findViewById(R.id.recipe_recycle_view);
        recipeListAdapter = new RecipeListAdapter(recipesListViewModel.getAllRecipes().getValue());
        recipeRecycleView.setAdapter(recipeListAdapter);
    }

    private View bind(LayoutInflater inflater, ViewGroup container) {
        MainFragmentBindingImpl binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment,
                container, false);
        binding.setLifecycleOwner(this);
        binding.setViewModel(recipesListViewModel);
        return binding.getRoot();
    }
}
