package com.example.whatscooking.main.recipeslist;

import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.whatscooking.R;
import com.example.whatscooking.adapters.RecipeListAdapter;
import com.example.whatscooking.databinding.MainFragmentBindingImpl;
import com.example.whatscooking.main.MainActivity;
import com.example.whatscooking.main.MainComponent;

import javax.inject.Inject;

public class RecipesListFragment extends Fragment implements RecipeListAdapter.OnRecipeClickedListener {

    @Inject
    RecipesListViewModel recipesListViewModel;
    RecipeListAdapter recipeListAdapter;
    @Inject
    MainComponent mainComponent;
    private MainFragmentBindingImpl binding;

    public RecipesListFragment() {
        this.recipeListAdapter = new RecipeListAdapter(this);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    public void onAttach(Context context) {
        super.onAttach(context);
        ((MainActivity)getActivity()).mainComponent.inject(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void subscribeUi() {
        //remove leftover observers
        recipesListViewModel.getAllRecipesInfo().removeObservers(getViewLifecycleOwner());
        //could happen from new recipe activity or a new recipe pushed into a joined account
        recipesListViewModel.getAllRecipesInfo().observe(getViewLifecycleOwner(), recipes -> {
            //TODO create binding adapter to set recycler view to GONE when empty and show a text view message
            binding.setHasRecipes(recipes != null && recipes.isEmpty());
            recipeListAdapter.setRecipeInfoList(recipes);
        });
    }

    private View bind(LayoutInflater inflater, ViewGroup container) {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment,
                container, false);
        binding.setLifecycleOwner(this);
        recipeListAdapter.setRecipeInfoList(recipesListViewModel.getAllRecipesInfo().getValue());
        recipeListAdapter.setContext(getContext());
        binding.recipeRecycleView.setAdapter(recipeListAdapter);
        return binding.getRoot();
    }

    @Override
    public void recipeClicked(int position) {
        String title = recipeListAdapter.getTitleAtPosition(position);
        NavDirections action =
                RecipesListFragmentDirections.actionRecipesListFragmentToRecipeFragment(title);
        NavHostFragment.findNavController(this).navigate(action);
    }
}
