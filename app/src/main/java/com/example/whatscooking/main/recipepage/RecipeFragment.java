package com.example.whatscooking.main.recipepage;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.transition.Slide;
import androidx.transition.Transition;
import androidx.transition.TransitionInflater;

import com.example.whatscooking.R;
import com.example.whatscooking.data.RecipeRepository;
import com.example.whatscooking.databinding.RecipeFragmentBindingImpl;
import com.example.whatscooking.main.MainActivity;
import com.example.whatscooking.utilities.Constants;

import javax.inject.Inject;

public class RecipeFragment extends Fragment implements
        View.OnClickListener,
        TouchInterceptChecker
{

    RecipeViewModel recipeViewModel;
    @Inject
    RecipeRepository repository;
    FragmentManager fragmentManager;
    private RecipeFragmentBindingImpl binding;
    IngredientsChildFragment ingredientsFragment;
    RecipeStepsChildFragment recipeFragment;
    RecipeFragment thisFragment;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String recipeTitle = this.getArguments().getString(Constants.RECIPE_ARG);
        //TODO https://proandroiddev.com/5-common-mistakes-when-using-architecture-components-403e9899f4cb mistake #5
        RecipeViewModelFactory factory = new RecipeViewModelFactory(getActivity().getApplication(),
                repository, recipeTitle);
        this.recipeViewModel = new ViewModelProvider(this, factory)
                .get(RecipeViewModel.class);
        setUpTransitions();
        ingredientsFragment = new IngredientsChildFragment();
        recipeFragment = new RecipeStepsChildFragment();
        fragmentManager = getChildFragmentManager();
        thisFragment = this;
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
        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.recipeScrollView.setInterceptChecker(this);
        //TODO could extend View.OnTouchListener and provide the getScrollY() value
        binding.recipeScrollView.setOnTouchListener(new View.OnTouchListener() {
            final float scrollThreshold = 100;
            float xStart = 0;
            float yStart = 0;
            float scrollViewStartPositionY = 0;
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN: {
                        xStart = event.getX();
                        yStart = event.getY();
                        scrollViewStartPositionY = binding.recipeScrollView.getScrollY();
                        break;
                    }
                    // could detect ACTION_MOVE and call popBackStack() before release
                    case MotionEvent.ACTION_UP: {
                        float xDelta = event.getX() - xStart;
                        float yDelta = event.getY() - yStart;
                        if (Math.abs(yDelta) >= Math.abs(xDelta) && Math.abs(yDelta) > scrollThreshold) {
                            if (scrollViewStartPositionY == 0 && yDelta > 0) {
                                NavHostFragment.findNavController(thisFragment).popBackStack();
                                return true;
                            }
                        }
                    }
                }
                return false;
            }
        });
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
        binding.changeViewButton.setOnClickListener(this);
        return binding.getRoot();
    }

    private void subscribeUi() {
        //TODO check if these are needed. I think not anymore
        recipeViewModel.getRecipeDetailsLiveData().removeObservers(getViewLifecycleOwner());
        recipeViewModel.getRecipeLiveData().removeObservers(getViewLifecycleOwner());

        recipeViewModel.getRecipeDetailsLiveData().observe(getViewLifecycleOwner(),
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
            this.binding.changeViewButton.setText(R.string.view_ingredients);
        } else {
            transaction.replace(R.id.child_frame_layout, ingredientsFragment).commit();
            this.binding.changeViewButton.setText(R.string.view_recipe);
        }
    }

    @Override
    public boolean shouldInterceptTouch(int x, int y) {
        Rect rect = new Rect();
        binding.recipeImage.getHitRect(rect);
        return rect.contains(x, y);
    }
}
