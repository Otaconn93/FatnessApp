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
public class SubFoodGroupAdapter extends RecyclerView.Adapter<SubFoodGroupAdapter.SubFoodGroupViewHolder> {

    private List<FoodGroup> dataset;
    private OnFoodGroupClickListener listener;

    public static class SubFoodGroupViewHolder extends RecyclerView.ViewHolder {

        private CardView cardView;

        public SubFoodGroupViewHolder(CardView cardView) {
            super(cardView);
            this.cardView = cardView;
        }

        public CardView getCardView() {
            return cardView;
        }
    }

    public SubFoodGroupAdapter(List<FoodGroup> myDataset,  OnFoodGroupClickListener myListener) {
        dataset = myDataset;
        listener = myListener;
    }

    @NonNull
    @Override
    public SubFoodGroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        CardView view = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.foodgroup_item, parent, false);
        return new SubFoodGroupAdapter.SubFoodGroupViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull SubFoodGroupViewHolder holder, final int position) {

        TextView name = holder.getCardView().findViewById(R.id.tv_name);
        name.setText(dataset.get(position).getName());

        holder.getCardView().setOnClickListener(v -> listener.onItemClick(dataset.get(position), position, false));
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
