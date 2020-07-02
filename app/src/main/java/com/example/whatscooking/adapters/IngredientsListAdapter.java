package com.example.whatscooking.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatscooking.R;
import com.example.whatscooking.databinding.IngredientListingBinding;

import java.util.List;

public class IngredientsListAdapter extends RecyclerView.Adapter<IngredientsListAdapter.IngredientListingViewHolder> {

    private List<String> ingredients;
    private LayoutInflater layoutInflater;

    @NonNull
    @Override
    public IngredientListingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        IngredientListingBinding binding = DataBindingUtil.inflate(layoutInflater,
                R.layout.ingredient_listing, parent,false);
        return new IngredientListingViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientListingViewHolder holder, int position) {
        if (ingredients != null) {
            String ingredient = ingredients.get(position);
            holder.bind(ingredient);
        }
    }

    @Override
    public int getItemCount() {
        if (ingredients != null)
            return ingredients.size();
        else return 0;
    }

    public void setIngredientsList(List<String> ingredientsList) {
        this.ingredients = ingredientsList;
        notifyDataSetChanged();
    }

    public static class IngredientListingViewHolder extends RecyclerView.ViewHolder {
        IngredientListingBinding binding;

        public IngredientListingViewHolder(@NonNull IngredientListingBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(String ingredient) {
            binding.setIngredient(ingredient);
            binding.executePendingBindings();
        }
    }
}
