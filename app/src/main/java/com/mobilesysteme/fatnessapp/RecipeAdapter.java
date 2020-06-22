package com.mobilesysteme.fatnessapp;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.mobilesysteme.fatnessapp.sqlObjects.Recipe;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private List<Recipe> recipes;
    private OnRecipeClickListener listener;

    public static class RecipeViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;

        public RecipeViewHolder(CardView cardView) {
            super(cardView);
            this.cardView = cardView;
        }

        public CardView getCardView() {
            return cardView;
        }
    }

    public RecipeAdapter(List<Recipe> myDataset, OnRecipeClickListener myListener) {
        recipes = myDataset;
        listener = myListener;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        CardView view = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.foodgroup_item, parent, false);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {

        TextView name = holder.getCardView().findViewById(R.id.tv_name);
        name.setText(recipes.get(position).getName());
        holder.getCardView().setOnClickListener(view -> listener.onRecipeClick(recipes.get(position)));
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }
}
