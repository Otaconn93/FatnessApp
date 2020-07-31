package com.mobilesysteme.fatnessapp;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.mobilesysteme.fatnessapp.sqlObjects.FoodGroup;

import java.util.List;

/**
 * @author Grabau
 */
public class RootFoodGroupAdapter extends RecyclerView.Adapter<RootFoodGroupAdapter.RootFoodGroupViewHolder> {

    private List<FoodGroup> foodGroups;
    private OnFoodGroupClickListener listener;

    public static class RootFoodGroupViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;

        public RootFoodGroupViewHolder(CardView cardView) {
            super(cardView);
            this.cardView = cardView;
        }

        public CardView getCardView() {
            return cardView;
        }
    }

    public RootFoodGroupAdapter(List<FoodGroup> myDataset, OnFoodGroupClickListener myListener) {
        foodGroups = myDataset;
        listener = myListener;
    }

    @NonNull
    @Override
    public RootFoodGroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        CardView view = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.foodgroup_item, parent, false);
        return new RootFoodGroupViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RootFoodGroupViewHolder holder, final int position) {

        TextView name = holder.getCardView().findViewById(R.id.tv_name);
        name.setText(foodGroups.get(position).getName());

        holder.getCardView().setOnClickListener(v -> listener.onItemClick(foodGroups.get(position), position, true));
    }

    @Override
    public int getItemCount() {
        return foodGroups.size();
    }
}
