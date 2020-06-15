package com.example.whatscooking.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatscooking.R;
import com.example.whatscooking.data.entities.RecipeInfo;
import com.example.whatscooking.databinding.RecipeCardViewBinding;
import com.example.whatscooking.utilities.MediaOperations;

import java.util.List;
import javax.inject.Inject;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeCardViewHolder> {

    private List<RecipeInfo> recipeInfoList;
    private LayoutInflater layoutInflater;
    private Context context;

    @Inject
    public RecipeListAdapter() {
        super();
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
            MediaOperations.setImageToView(context, recipeInfo.imageUri, holder.binding.recipeImage);
        }
    }

    public static class RecipeCardViewHolder extends RecyclerView.ViewHolder {
        RecipeCardViewBinding binding;

        public RecipeCardViewHolder(@NonNull RecipeCardViewBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(RecipeInfo recipeInfo) {
            binding.setRecipeInfo(recipeInfo);
            binding.executePendingBindings();
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

    public void setContext(Context context) {
        this.context = context;
    }
}
