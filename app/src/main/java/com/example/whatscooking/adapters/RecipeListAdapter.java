package com.example.whatscooking.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatscooking.R;
import com.example.whatscooking.data.entities.RecipeDetails;
import com.example.whatscooking.databinding.RecipeCardViewBinding;
import java.util.List;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeCardViewHolder> {

    private List<RecipeDetails> recipeDetailsList;
    private LayoutInflater layoutInflater;
    private static OnRecipeClickedListener itemClickListener;

    public RecipeListAdapter(OnRecipeClickedListener listener) {
        super();
        itemClickListener = listener;
    }

    @NonNull
    @Override
    public RecipeCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        RecipeCardViewBinding binding = DataBindingUtil.inflate(layoutInflater,
                R.layout.recipe_card_view, parent,false);
        return new RecipeCardViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeCardViewHolder holder, int position) {
        if (recipeDetailsList != null) {
            RecipeDetails recipeDetails = recipeDetailsList.get(position);
            holder.bind(recipeDetails);
        }
    }

    @Override
    public int getItemCount() {
        if (recipeDetailsList != null)
            return recipeDetailsList.size();
        else return 0;
    }

    public void setRecipeInfoList(List<RecipeDetails> recipeDetailsList) {
        this.recipeDetailsList = recipeDetailsList;
        notifyDataSetChanged();
    }

    public static class RecipeCardViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {
        RecipeCardViewBinding binding;

        public RecipeCardViewHolder(@NonNull RecipeCardViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(this);
        }

        void bind(RecipeDetails recipeDetails) {
            binding.setRecipeDetails(recipeDetails);
            binding.recipeImage.setTransitionName("image_" + getLayoutPosition());
            binding.recipeTitle.setTransitionName(binding.getRecipeDetails().title + getLayoutPosition());
            binding.executePendingBindings();
        }

        @Override
        public void onClick(View v) {
            itemClickListener.recipeClicked(this.binding.getRecipeDetails(), this.binding.recipeImage,
                    this.binding.recipeTitle);
        }
    }

    public interface OnRecipeClickedListener {
        void recipeClicked(RecipeDetails recipeDetails, View imageView, View textView);
    }
}
