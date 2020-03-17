package com.example.whatscooking.ui.main;

import androidx.lifecycle.ViewModelProviders;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.whatscooking.R;
import com.example.whatscooking.adapters.RecipeListAdapter;
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
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);

        recipeRecycleView = view.findViewById(R.id.recipe_recycle_view);
        //recipeRecycleView.setHasFixedSize(true);

        recipeListAdapter = new RecipeListAdapter();
        recipeRecycleView.setAdapter(recipeListAdapter);
        recipeRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recipesListViewModel = ViewModelProviders.of(this).get(RecipesListViewModel.class);
        recipesListViewModel.getAllRecipes().observe(this, recipes -> {
            recipeListAdapter.setRecipeList(recipes);
        });
    }

}
