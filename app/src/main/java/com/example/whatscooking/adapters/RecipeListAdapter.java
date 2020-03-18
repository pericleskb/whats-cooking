package com.example.whatscooking.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.whatscooking.R;
import com.example.whatscooking.data.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeCardViewHolder> {

    List<Recipe> recipeList;

    public static class RecipeCardViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public CardView cardView;
        public RecipeCardViewHolder(CardView v) {
            super(v);
            cardView = v.findViewById(R.id.recipe_card);
            textView = v.findViewById(R.id.recipe_title);
        }
    }

    public RecipeListAdapter() {
        super();
        this.recipeList = new ArrayList<>();
    }

    public void setRecipeList(List<Recipe> recipeList) {
        this.recipeList = recipeList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RecipeCardViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView v = (CardView) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_card_view, parent, false);

        RecipeCardViewHolder recipeCardVH = new RecipeCardViewHolder(v);
        return recipeCardVH;
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeCardViewHolder holder, int position) {
        if (recipeList != null) {
            holder.textView.setText(recipeList.get(position).title);
        }
    }

    @Override
    public int getItemCount() {
        if (recipeList != null)
            return recipeList.size();
        else return 0;
    }
}
