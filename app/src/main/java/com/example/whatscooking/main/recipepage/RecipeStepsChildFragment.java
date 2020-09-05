package com.example.whatscooking.main.recipepage;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.PagerAdapter;

import com.example.whatscooking.databinding.RecipeStepBinding;
import com.example.whatscooking.databinding.RecipeStepsFragmentBinding;

import java.util.ArrayList;
import java.util.List;

public class RecipeStepsChildFragment extends Fragment {

    RecipeStepsFragmentBinding binding;
    TextViewPagerAdapter pagerAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = RecipeStepsFragmentBinding.inflate(inflater);
        RecipeViewModel recipeViewModel =
                new ViewModelProvider(getParentFragment()).get(RecipeViewModel.class);
        recipeViewModel.getRecipeLiveData().observe(getViewLifecycleOwner(), recipe -> {
                ((TextViewPagerAdapter) binding.viewPager.getAdapter()).setRecipeSteps(recipe.instructions);
                binding.viewPager.getAdapter().notifyDataSetChanged();
        });
        pagerAdapter = new TextViewPagerAdapter(getContext());
        binding.viewPager.setAdapter(pagerAdapter);
        return binding.getRoot();
    }

    private class TextViewPagerAdapter extends PagerAdapter {
        List<String> recipeSteps;
        Context context;

        public TextViewPagerAdapter(Context context) {
            this.context = context;
            this.recipeSteps = new ArrayList<>();
        }

        @Override
        public Object instantiateItem(ViewGroup collection, int position) {
            LayoutInflater inflater = LayoutInflater.from(context);
            RecipeStepBinding binding = RecipeStepBinding.inflate(inflater);
            binding.stepText.setText(recipeSteps.get(position));
            collection.addView(binding.getRoot());
            return binding.getRoot();
        }

        @Override
        public void destroyItem(ViewGroup collection, int position, Object view) {
            collection.removeView((View) view);
        }

        @Override
        public int getCount() {
            return recipeSteps.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        public void setRecipeSteps(List<String> recipeSteps) {
            this.recipeSteps = recipeSteps;
        }
    }
}
