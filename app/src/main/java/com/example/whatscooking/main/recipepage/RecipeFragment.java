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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.transition.Slide;
import androidx.transition.Transition;
import androidx.transition.TransitionInflater;

import com.example.whatscooking.R;
import com.example.whatscooking.data.RecipeRepository;
import com.example.whatscooking.databinding.RecipeFragmentBindingImpl;
import com.example.whatscooking.main.MainActivity;
import com.example.whatscooking.main.MainComponent;
import com.example.whatscooking.utilities.Constants;

import javax.inject.Inject;

public class RecipeFragment extends Fragment implements View.OnClickListener {

    RecipeViewModel recipeViewModel;
    @Inject
    MainComponent mainComponent;
    @Inject
    RecipeRepository repository;

    FragmentManager fragmentManager;

    private RecipeFragmentBindingImpl binding;
    IngredientsChildFragment ingredientsFragment;
    RecipeInstructionsChildFragment recipeFragment;

    public RecipeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String recipeTitle = this.getArguments().getString(Constants.RECIPE_ARG);
        //TODO https://proandroiddev.com/5-common-mistakes-when-using-architecture-components-403e9899f4cb mistake #5
        this.recipeViewModel = new ViewModelProvider(this,
                new RecipeViewModelFactory(getActivity().getApplication(), repository, recipeTitle))
                .get(RecipeViewModel.class);
        setUpTransitions();
        ingredientsFragment = new IngredientsChildFragment();
        recipeFragment = new RecipeInstructionsChildFragment();
        fragmentManager = getActivity().getSupportFragmentManager();
    }

    private void setUpTransitions() {
        Transition sharedElementTransition = TransitionInflater.from(getContext()).inflateTransition(android.R.transition.move);
        sharedElementTransition.setDuration(500);
        setSharedElementEnterTransition(sharedElementTransition);
        Slide slide = new Slide();
        slide.setDuration(500);
        this.setEnterTransition(slide);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        postponeEnterTransition();

        if (savedInstanceState == null) {
            fragmentManager.beginTransaction().add(R.id.child_frame_layout, ingredientsFragment).commit();
        }
        View view = bind(inflater, container);
        subscribeUi();
        view.findViewById(R.id.change_view_button).setOnClickListener(this);
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
        binding.changeViewButton.setOnClickListener(l -> {});
        return binding.getRoot();
    }

    private void subscribeUi() {
        recipeViewModel.getRecipeInfo().removeObservers(getViewLifecycleOwner());
        recipeViewModel.getRecipe().removeObservers(getViewLifecycleOwner());

        recipeViewModel.getRecipe().observe(getViewLifecycleOwner(), recipe ->
                binding.setRecipe(recipeViewModel));
        recipeViewModel.getRecipeInfo().observe(getViewLifecycleOwner(),
                recipe -> {
                    binding.setRecipe(recipeViewModel);
                    startPostponedEnterTransition();
                });
    }

    @Override
    public void onClick(View v) {
        FragmentTransaction transaction = fragmentManager.beginTransaction().setCustomAnimations(
                R.animator.card_flip_right_in,
                R.animator.card_flip_right_out,
                R.animator.card_flip_left_in,
                R.animator.card_flip_left_out);
        if (ingredientsFragment != null && ingredientsFragment.isAdded() && ingredientsFragment.isVisible()) {
            transaction.replace(R.id.child_frame_layout, recipeFragment).commit();
        } else {
            transaction.replace(R.id.child_frame_layout, ingredientsFragment).commit();
        }
    }
}
