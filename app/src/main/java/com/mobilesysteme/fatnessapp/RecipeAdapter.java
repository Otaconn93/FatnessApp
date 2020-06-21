package com.mobilesysteme.fatnessapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.mobilesysteme.fatnessapp.sqlObjects.Recipe;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder> {

    private List<Recipe> dataset;
    private OnRecipeClickListener listener;

    public static class RecipeViewHolder extends RecyclerView.ViewHolder {
        public CardView cv;
        public RecipeViewHolder(CardView v) {
            super(v);
            cv = v;
        }
    }

    public RecipeAdapter(List<Recipe> myDataset, OnRecipeClickListener myListener) {
        dataset = myDataset;
        listener = myListener;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView view = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.foodgroup_item, parent, false);
        RecipeViewHolder vh = new RecipeViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeViewHolder holder, int position) {
        TextView name = holder.cv.findViewById(R.id.tv_name);
        name.setText(dataset.get(position).getName());
        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onRecipeClick(dataset.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }


}
