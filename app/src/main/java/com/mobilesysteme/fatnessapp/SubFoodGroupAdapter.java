package com.mobilesysteme.fatnessapp;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mobilesysteme.fatnessapp.sqlObjects.FoodGroup;

import java.util.List;

public class SubFoodGroupAdapter extends RecyclerView.Adapter<SubFoodGroupAdapter.SubFoodGroupViewHolder> {
    private List<FoodGroup> dataset;

    public static class SubFoodGroupViewHolder extends RecyclerView.ViewHolder {
        public TextView tv;
        public SubFoodGroupViewHolder(@NonNull TextView itemView) {
            super(itemView);
            tv = itemView;
        }
    }

    public SubFoodGroupAdapter(List<FoodGroup> myDataset) {
        dataset = myDataset;
    }

    @NonNull
    @Override
    public SubFoodGroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TextView view = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.subfoodgroup_item, parent, false);
        SubFoodGroupViewHolder vh = new SubFoodGroupViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull SubFoodGroupViewHolder holder, int position) {
        TextView name = holder.tv;
        name.setText(dataset.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return dataset.size();
    }
}
