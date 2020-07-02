package com.example.whatscooking.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatscooking.R;
import com.example.whatscooking.data.entities.RecipeInfo;
import com.example.whatscooking.databinding.RecipeCardViewBinding;
import java.util.List;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeCardViewHolder> {

    private List<RecipeInfo> recipeInfoList;
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
        if (recipeInfoList != null) {
            RecipeInfo recipeInfo = recipeInfoList.get(position);
            holder.bind(recipeInfo);
        }
    }

    @Override
    public int getItemCount() {
        if (recipeInfoList != null)
            return recipeInfoList.size();
        else return 0;
    }

    public void setRecipeInfoList(List<RecipeInfo> recipeInfoList) {
        this.recipeInfoList = recipeInfoList;
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

        void bind(RecipeInfo recipeInfo) {
            binding.setRecipeInfo(recipeInfo);
            binding.recipeImage.setTransitionName("image_" + getLayoutPosition());
            binding.recipeTitle.setTransitionName(binding.getRecipeInfo().title + getLayoutPosition());
            binding.executePendingBindings();
        }

        @Override
        public void onClick(View v) {
            itemClickListener.recipeClicked(this.binding.getRecipeInfo(), this.binding.recipeImage,
                    this.binding.recipeTitle);
        }
    }

    public interface OnRecipeClickedListener {
        void recipeClicked(RecipeInfo recipeInfo, View imageView, View textView);
    }
}
