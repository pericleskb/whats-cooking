package com.example.whatscooking.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatscooking.R;
import com.example.whatscooking.data.Recipe;
import com.example.whatscooking.databinding.RecipeCardViewBinding;
import com.example.whatscooking.utilities.MediaOperations;

import java.util.List;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeCardViewHolder> {

    public List<Recipe> recipeList;
    private LayoutInflater layoutInflater;

    //TODO remove this dependency
    private Context context;

    public RecipeListAdapter(Context context, List<Recipe> recipeList) {
        super();
        this.recipeList = recipeList;
        this.context = context;
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
        if (recipeList != null) {
            Recipe recipe = recipeList.get(position);
            holder.bind(recipe);
            MediaOperations.setImageToView(context, recipe.imageUri, holder.binding.recipeImage);
        }
    }

    public void setRecipeList(List<Recipe> recipeList) {
        this.recipeList = recipeList;
        notifyDataSetChanged();
    }

    public static class RecipeCardViewHolder extends RecyclerView.ViewHolder {
        RecipeCardViewBinding binding;

        public RecipeCardViewHolder(@NonNull RecipeCardViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Recipe recipe) {
            binding.setRecipe(recipe);
            binding.executePendingBindings();
        }
    }

    @Override
    public int getItemCount() {
        if (recipeList != null)
            return recipeList.size();
        else return 0;
    }
}