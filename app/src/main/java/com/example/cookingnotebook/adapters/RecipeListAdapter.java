package com.example.cookingnotebook.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cookingnotebook.R;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.RecipeCardViewHolder> {

    String[] recipes = {"Falafel wrap", "Humus", "Ntolmadakia", "Pantzarosalata", "Keftedakia",
    "Papaya salad", "Makaronakia", "Octopus", "Vlita", "Skoumpri"};

    public static class RecipeCardViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public CardView cardView;
        public RecipeCardViewHolder(CardView v) {
            super(v);
            cardView = v.findViewById(R.id.recipe_card);
            textView = v.findViewById(R.id.list_recipe_title);
        }
    }

    public RecipeListAdapter() {
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
        //get recipe from single source of truth
        holder.textView.setText(recipes[position]);
    }

    @Override
    public int getItemCount() {
        return recipes.length;
    }
}
