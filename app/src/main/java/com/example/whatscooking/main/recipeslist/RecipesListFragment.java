package com.example.whatscooking.main.recipeslist;

import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.fragment.FragmentNavigator;
import androidx.navigation.fragment.NavHostFragment;
import androidx.transition.Transition;
import androidx.transition.TransitionInflater;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.whatscooking.R;
import com.example.whatscooking.adapters.RecipeListAdapter;
import com.example.whatscooking.data.entities.RecipeDetails;
import com.example.whatscooking.databinding.MainFragmentBindingImpl;
import com.example.whatscooking.main.MainActivity;
import com.example.whatscooking.utilities.Constants;

import javax.inject.Inject;

public class RecipesListFragment extends Fragment implements RecipeListAdapter.OnRecipeClickedListener {

    @Inject
    RecipesListViewModel recipesListViewModel;
    RecipeListAdapter recipeListAdapter;
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

    private View bind(LayoutInflater inflater, ViewGroup container) {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment,
                container, false);
        binding.setLifecycleOwner(this);
        binding.recipeRecyclerView.setAdapter(recipeListAdapter);
        postponeEnterTransition();
        binding.recipeRecyclerView.getViewTreeObserver().addOnPreDrawListener(() -> {
            startPostponedEnterTransition();
            return true;
        });
        return binding.getRoot();
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

    @Override
    public void recipeClicked(RecipeDetails recipeDetails, View imageView, View titleTextView) {
        NavDirections direction =
                RecipesListFragmentDirections.actionRecipesListFragmentToRecipeFragment(recipeDetails.title);
        FragmentNavigator.Extras extras = new FragmentNavigator.Extras.Builder()
                .addSharedElement(imageView, Constants.RECIPE_IMG_TRANSITION_ID)
                .addSharedElement(titleTextView, Constants.RECIPE_TITLE_TRANSITION_ID).build();
//        this.setExitTransition(createExitTransition(imageView));
        NavHostFragment.findNavController(this).navigate(direction, extras);
    }

    /*
    Split transition
    Keeping the code for reference but transition looks better without it
    https://medium.com/@jim.zack.hu/android-inbox-material-transitions-for-recyclerview-71fc7326bcb5
    https://medium.com/bynder-tech/how-to-use-material-transitions-in-fragment-transactions-5a62b9d0b26b
     */
    private Transition createExitTransition(View imageView) {
        Transition exitTransition = TransitionInflater.from(getContext()).inflateTransition(android.R.transition.explode);
        Rect epicCenterRect = new Rect();
        imageView.getGlobalVisibleRect(epicCenterRect);
        epicCenterRect.top = epicCenterRect.bottom;
        exitTransition.setEpicenterCallback(new Transition.EpicenterCallback() {
            @Override
            public Rect onGetEpicenter(@NonNull Transition transition) {
                return epicCenterRect;
            }
        });
        exitTransition.setPropagation(null);
        exitTransition.setDuration(500);
        return exitTransition;
    }
}
