package com.mobilesysteme.fatnessapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.mobilesysteme.fatnessapp.sqlObjects.FoodGroup;

import java.util.List;


public class RootFoodGroupAdapter extends RecyclerView.Adapter<RootFoodGroupAdapter.RootFoodGroupViewHolder> {
    private List<FoodGroup> dataset;

    public static class RootFoodGroupViewHolder extends RecyclerView.ViewHolder {
        public CardView cv;
        public RootFoodGroupViewHolder(CardView v) {
            super(v);
            cv = v;
        }
    }

    public RootFoodGroupAdapter(List<FoodGroup> myDataset) {
        dataset = myDataset;
    }

    @NonNull
    @Override
    public RootFoodGroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardView view = (CardView) LayoutInflater.from(parent.getContext()).inflate(R.layout.foodgroup_item, parent, false);
        RootFoodGroupViewHolder vh = new RootFoodGroupViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RootFoodGroupViewHolder holder, int position) {
        TextView name = holder.cv.findViewById(R.id.tv_name);
        name.setText(dataset.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }


}
